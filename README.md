# 🏋️‍♂️ Olimpo NGR Gym — gestión de clientes

Aplicación de escritorio en Java (Swing) para gestionar los clientes de un gimnasio: alta, edición, baja, búsqueda,
filtro por fecha de fin de cuota, exportación a CSV/Excel y foto del cliente con la webcam.

## ✨ Funcionalidades

- **Clientes**: nombre, apellidos, estatus, matrícula, teléfono, DNI, dirección, fecha de inicio y fin.
- **Búsqueda** por cualquier campo y **filtro** por rango de fecha de fin.
- **📸 Webcam**: foto del cliente (opcional; se guarda como `<DNI>.jpg`).
- **Exportar a Excel**: CSV con nombre, apellidos, estatus, teléfono y fecha fin (UTF-8, se abre bien en Excel).
- **🗄️ MySQL** como base de datos.

## 🚀 Demo en local (Windows)

Guía para arrancar el proyecto en un ordenador cualquiera y enseñarlo, sin tocar nada de producción.
Usa una base de datos MySQL propia en Docker con **clientes ficticios**.

### Requisitos

- **JDK 8 o superior** (`javac -version` debe funcionar, o tener definido `JAVA_HOME`).
- **Docker Desktop** abierto y en marcha (para la base de datos).
- **Internet** la primera vez (descarga la imagen de MySQL y las librerías).
- Webcam: opcional.

### Pasos

1. Clona el repositorio y entra en la carpeta:

   ```powershell
   git clone https://github.com/Javierms15/OlimpoGym.git
   cd OlimpoGym
   ```

2. Arranca todo con un solo comando:

   ```powershell
   powershell -ExecutionPolicy Bypass -File local\run-local.ps1
   ```

   El script, en este orden:
   1. Levanta MySQL 8 en Docker (`local/docker-compose.yml`) con la BD `pruebaparcial`, el esquema
      (`local/init/01-schema.sql`) y 5 clientes ficticios (`local/init/02-datos-demo.sql`).
   2. Descarga las librerías a `lib/` si faltan.
   3. Compila a `build/` y abre la aplicación.

   La primera vez tarda unos minutos (imagen de ~600 MB); las siguientes, unos segundos.

3. Se abre la ventana **OLIMPO NGR GYM** con la lista de clientes de ejemplo.

### Guion sugerido para la demo

| Qué enseñar | Cómo |
|---|---|
| Buscar | Elige un campo en el desplegable (p. ej. *Apellidos*), escribe parte del texto y pulsa **Buscar**. |
| Filtrar por vencimiento | Elige *Fecha de* y *Fecha hasta* y pulsa **Filtrar** (filtra por fecha de fin de cuota). |
| Nuevo cliente | **Nuevo cliente** → rellena los datos → **Encender** cámara → **Foto** → **Guardar**. Sin cámara se puede guardar igualmente. |
| Editar | Selecciona una fila → **Editar cliente** → cambia datos o repite la foto → **Guardar**. |
| Eliminar | Selecciona una fila → **Eliminar cliente** → confirma. |
| Exportar | **Exportar a Excel** → elige carpeta y nombre → se genera un `.csv` que abre Excel. |

Las fotos que hagas se guardan en la carpeta `fotos/` del proyecto (no se sube a git).

### Parar y reiniciar

```bash
docker compose -f local/docker-compose.yml down        # parar la BD (los datos se conservan)
docker compose -f local/docker-compose.yml down -v     # parar y borrar los datos: vuelve a los 5 clientes de ejemplo
```

Para volver a abrir la app más adelante basta con repetir el paso 2. Si solo quieres la base de datos
(por ejemplo, para lanzar la app desde NetBeans o IntelliJ):

```powershell
powershell -ExecutionPolicy Bypass -File local\run-local.ps1 -SoloBD
```

## ⚙️ Configuración

Todo tiene valor por defecto para el entorno local; para otro entorno se cambia con propiedad `-D` o variable de entorno:

| Qué | Propiedad | Variable de entorno | Por defecto |
|---|---|---|---|
| URL de la BD | `olimpo.db.url` | `OLIMPO_DB_URL` | `jdbc:mysql://localhost/pruebaparcial` |
| Usuario | `olimpo.db.user` | `OLIMPO_DB_USER` | `root` |
| Contraseña | `olimpo.db.password` | `OLIMPO_DB_PASSWORD` | `root` |
| Carpeta de fotos | `olimpo.fotos` | `OLIMPO_FOTOS` | `fotos` (junto a donde se ejecuta) |

> ⚠️ Antes las fotos se guardaban en una carpeta fija del escritorio (`...\Desktop\FOTOSGIMNASIO`). Si se despliega esta
> versión en el equipo del gimnasio, hay que indicar esa carpeta con `OLIMPO_FOTOS` para seguir viendo las fotos antiguas.

## 🗃️ Esquema de la base de datos

```sql
CREATE TABLE cliente (
  idcliente       INT AUTO_INCREMENT PRIMARY KEY,
  Nombre          VARCHAR(255),
  Apellidos       VARCHAR(255),
  Estatus         VARCHAR(255),
  PrecioMatricula INT,
  Telefono        INT,
  DNI             VARCHAR(255),
  Direccion       VARCHAR(255),
  fechaInicio     DATE,
  fechaFin        DATE,
  foto            VARCHAR(255)
);
```

## 🧰 Desde NetBeans / IntelliJ

El proyecto es de NetBeans (Ant): `nbproject/project.properties` apunta a las librerías de `lib/` (las descarga
`run-local.ps1`). Clase principal: `olimpogym.OlimpoGym`.

## 🐛 Problemas habituales

- **"No se pudo conectar con la base de datos"**: comprueba que Docker está en marcha y que el contenedor
  `olimpo-mysql` está *healthy* (`docker ps`), o que el puerto 3306 no lo usa otro MySQL.
- **"No se detecta ninguna cámara"**: la app funciona igual, simplemente sin fotos.

## 📧 Autor

Javier Martín Sendra — javiervader15@gmail.com
