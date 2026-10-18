package olimpogym;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Configuración centralizada (antes estaba repetida y fija en cada ventana).
 *
 * Cada valor se puede cambiar con una propiedad del sistema (-Dolimpo.xxx=...)
 * o con una variable de entorno (OLIMPO_XXX). Si no se indica nada se usan los
 * valores por defecto, pensados para la base de datos local de desarrollo.
 */
public final class Config {

    private Config() {
    }

    private static String get(String propiedad, String entorno, String porDefecto) {
        String valor = System.getProperty(propiedad);
        if (valor == null || valor.isEmpty()) {
            valor = System.getenv(entorno);
        }
        return (valor == null || valor.isEmpty()) ? porDefecto : valor;
    }

    public static String dbUrl() {
        return get("olimpo.db.url", "OLIMPO_DB_URL", "jdbc:mysql://localhost/pruebaparcial");
    }

    public static String dbUser() {
        return get("olimpo.db.user", "OLIMPO_DB_USER", "root");
    }

    public static String dbPassword() {
        return get("olimpo.db.password", "OLIMPO_DB_PASSWORD", "root");
    }

    /** Carpeta donde se guardan las fotos de los clientes (una por DNI). */
    public static File carpetaFotos() {
        File carpeta = new File(get("olimpo.fotos", "OLIMPO_FOTOS", "fotos"));
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        return carpeta;
    }

    /** Fichero de la foto de un cliente, según su DNI. */
    public static File fotoDe(String dni) {
        return new File(carpetaFotos(), dni + ".jpg");
    }

    /** Abre una conexión nueva con la base de datos; lanza SQLException si falla. */
    public static Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Falta el driver de MySQL (mysql-connector-j) en el classpath", ex);
        }
        return DriverManager.getConnection(dbUrl(), dbUser(), dbPassword());
    }
}
