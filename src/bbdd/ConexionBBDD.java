package bbdd;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBBDD {

	private static Connection connection;
	
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
	
	public Connection CloseConexion() throws SQLException{
		connection.close();
        return connection;
    }
	
	public static Connection getConnection() {
		return connection;
	}
	
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
