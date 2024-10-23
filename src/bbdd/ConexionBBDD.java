package bbdd;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase ConexionBBDD.
 */
public class ConexionBBDD {

	/** The connection. */
	private static Connection connection;
	
	/**
	 * Instantiates a new conexion BBDD.
	 *
	 * @throws SQLException the SQL exception
	 */
	public ConexionBBDD() throws SQLException {
		 Properties connConfig=loadProperties() ;
		 String url=connConfig.getProperty("dburl");
         connection = DriverManager.getConnection(url, connConfig);
         DatabaseMetaData databaseMetaData = connection.getMetaData();
         //debug
         /*
         System.out.println();
         System.out.println("--- Datos de conexión ------------------------------------------");
         System.out.printf("Base de datos: %s%n", databaseMetaData.getDatabaseProductName());
         System.out.printf("  Versión: %s%n", databaseMetaData.getDatabaseProductVersion());
         System.out.printf("Driver: %s%n", databaseMetaData.getDriverName());
         System.out.printf("  Versión: %s%n", databaseMetaData.getDriverVersion());
         System.out.println("----------------------------------------------------------------");
         System.out.println();
         */
         connection.setAutoCommit(false);
	}
	
	/**
	 * Close conexion.
	 *
	 * @return the connection
	 * @throws SQLException the SQL exception
	 */
	public Connection CloseConexion() throws SQLException{
		connection.close();
        return connection;
    }
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public static Connection getConnection() {
		return connection;
	}
	
	/**
	 * Load properties.
	 *
	 * @return the properties
	 */
	public static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
