<#
  Arranca OlimpoGym en local: BD MySQL en Docker + compilación + ejecución.
  Uso (desde la carpeta del proyecto):   powershell -ExecutionPolicy Bypass -File local\run-local.ps1
  Opciones:   -SoloBD   solo levanta la base de datos
#>
param([switch]$SoloBD)
$ErrorActionPreference = 'Continue'   # los avisos de javac/docker salen por stderr; los fallos se comprueban a mano
$raiz = Split-Path -Parent $PSScriptRoot
Set-Location $raiz

# 1) Base de datos
docker compose -f local/docker-compose.yml up -d
Write-Host 'Esperando a que MySQL esté listo...'
for ($i = 0; $i -lt 60; $i++) {
    $estado = docker inspect -f '{{.State.Health.Status}}' olimpo-mysql 2>$null
    if ($estado -eq 'healthy') { break }
    Start-Sleep -Seconds 2
}
if ($estado -ne 'healthy') { throw 'MySQL no ha arrancado a tiempo (mira: docker logs olimpo-mysql)' }
if ($SoloBD) { return }

# 2) JDK
function Buscar-Jdk {
    if ($env:JAVA_HOME -and (Test-Path "$env:JAVA_HOME\bin\javac.exe")) { return $env:JAVA_HOME }
    $cmd = Get-Command javac -ErrorAction SilentlyContinue
    if ($cmd) { return (Split-Path -Parent (Split-Path -Parent $cmd.Source)) }
    $c = Get-ChildItem "$env:USERPROFILE\.jdks","C:\Program Files\Java","C:\Program Files\Eclipse Adoptium" -Directory -ErrorAction SilentlyContinue |
         Where-Object { Test-Path "$($_.FullName)\bin\javac.exe" } | Sort-Object Name -Descending | Select-Object -First 1
    if ($c) { return $c.FullName }
    throw 'No encuentro un JDK. Instala uno (o define JAVA_HOME).'
}
$jdk = Buscar-Jdk
$javac = "$jdk\bin\javac.exe"; $java = "$jdk\bin\java.exe"
Write-Host "Usando JDK: $jdk"

# 3) Librerías (lib\ no se sube a git)
$libs = @(
  'com/mysql/mysql-connector-j/8.0.33/mysql-connector-j-8.0.33.jar',
  'com/toedter/jcalendar/1.4/jcalendar-1.4.jar',
  'com/github/sarxos/webcam-capture/0.3.10/webcam-capture-0.3.10.jar',
  'com/nativelibs4java/bridj/0.6.2/bridj-0.6.2.jar',
  'org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar')
New-Item -ItemType Directory -Force lib | Out-Null
foreach ($l in $libs) {
    $destino = Join-Path lib (Split-Path -Leaf $l)
    if (-not (Test-Path $destino)) {
        Write-Host "Descargando $(Split-Path -Leaf $l)..."
        Invoke-WebRequest "https://repo1.maven.org/maven2/$l" -OutFile $destino
    }
}
$cp = (Get-ChildItem lib\*.jar | ForEach-Object { $_.FullName }) -join ';'

# 4) Compilar y copiar recursos (imágenes)
if (Test-Path build) { Remove-Item -Recurse -Force build }
New-Item -ItemType Directory -Force build\classes | Out-Null
$fuentes = Get-ChildItem src -Recurse -Filter *.java | ForEach-Object { $_.FullName }
& $javac -encoding UTF-8 -nowarn -cp $cp -d build\classes $fuentes
if ($LASTEXITCODE -ne 0) { throw 'Error de compilación' }
Get-ChildItem src -Recurse -File | Where-Object { $_.Extension -notin '.java','.form' } | ForEach-Object {
    $rel = $_.FullName.Substring((Resolve-Path src).Path.Length + 1)
    $dst = Join-Path build\classes $rel
    New-Item -ItemType Directory -Force (Split-Path -Parent $dst) | Out-Null
    Copy-Item $_.FullName $dst
}

# 5) Ejecutar (las fotos se guardan en .\fotos)
& $java -cp "build\classes;$cp" olimpogym.OlimpoGym
