package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbdd.ConexionBBDD;

public class DaoCreaBBDD {
	
	private static Connection conection;
	
	public static void crearBBDD(String ruta) {
		conection=ConexionBBDD.getConnection();
		File archivoCSV=new File(ruta);
		
		String crearSchema="DROP SCHEMA IF EXISTS `olimpiadas` ;\r\n"
				+ "CREATE SCHEMA IF NOT EXISTS `olimpiadas` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci;\r\n"
				+ "USE `olimpiadas`;\r\n"
				+ "SET SQL_MODE = \"NO_AUTO_VALUE_ON_ZERO\";\r\n"
				+ "SET AUTOCOMMIT = 0;\r\n"
				+ "START TRANSACTION;";
		String crearTablaDeporte="CREATE TABLE `Deporte` (\r\n"
				+ "	`id_deporte` int(11) NOT NULL AUTO_INCREMENT,\r\n"
				+ "	`nombre` varchar(100)  NOT NULL,\r\n"
				+ "	PRIMARY KEY (`id_deporte`)\r\n"
				+ ") ENGINE = InnoDB AUTO_INCREMENT = 10 DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;";
		String crearTablaDeportista="CREATE TABLE `Deportista` (\r\n"
				+ "	`id_deportista` int(11) NOT NULL AUTO_INCREMENT,\r\n"
				+ "	`nombre` varchar(150)  NOT NULL,\r\n"
				+ "	`sexo` enum('M', 'F')  NOT NULL,\r\n"
				+ "	`peso` int(11) DEFAULT NULL,\r\n"
				+ "	`altura` int(11) DEFAULT NULL,\r\n"
				+ "	PRIMARY KEY (`id_deportista`)\r\n"
				+ ") ENGINE = InnoDB AUTO_INCREMENT = 10 DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;";
		String crearTablaEquipo="CREATE TABLE `Equipo` (\r\n"
				+ "	`id_equipo` int(11) NOT NULL AUTO_INCREMENT,\r\n"
				+ "	`nombre` varchar(50)  NOT NULL,\r\n"
				+ "	`iniciales` varchar(3)  NOT NULL,\r\n"
				+ "	PRIMARY KEY (`id_equipo`)\r\n"
				+ ") ENGINE = InnoDB AUTO_INCREMENT = 10 DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;";
		String crearTablaOlimpiada="CREATE TABLE `Olimpiada` (\r\n"
				+ "	`id_olimpiada` int(11) NOT NULL AUTO_INCREMENT,\r\n"
				+ "	`nombre` varchar(11)  NOT NULL,\r\n"
				+ "	`anio` smallint(6) NOT NULL,\r\n"
				+ "	`temporada` enum('Summer', 'Winter')  NOT NULL,\r\n"
				+ "	`ciudad` varchar(50)  NOT NULL ,\r\n"
				+ "    PRIMARY KEY (`id_olimpiada`)\r\n"
				+ ") ENGINE = InnoDB AUTO_INCREMENT = 10 DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;";
		String crearTablaEvento="CREATE TABLE `Evento` (\r\n"
				+ "	`id_evento` int(11) NOT NULL AUTO_INCREMENT,\r\n"
				+ "	`nombre` varchar(150) NOT NULL,\r\n"
				+ "	`id_olimpiada` int(11) NOT NULL,\r\n"
				+ "	`id_deporte` int(11) NOT NULL,\r\n"
				+ "	PRIMARY KEY (`id_evento`),\r\n"
				+ "	CONSTRAINT `FK_Evento_Deporte` FOREIGN KEY (`id_deporte`) REFERENCES `Deporte` (`id_deporte`),\r\n"
				+ "	CONSTRAINT `FK_Evento_Olimpiada` FOREIGN KEY (`id_olimpiada`) REFERENCES `Olimpiada` (`id_olimpiada`)\r\n"
				+ ") ENGINE = InnoDB AUTO_INCREMENT = 10 DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;";
		String crearTablaParticipacion="CREATE TABLE `Participacion` (\r\n"
				+ "	`id_deportista` int(11) NOT NULL,\r\n"
				+ "	`id_evento` int(11) NOT NULL,\r\n"
				+ "	`id_equipo` int(11) NOT NULL,\r\n"
				+ "	`edad` tinyint(4) DEFAULT NULL,\r\n"
				+ "	`medalla` varchar(6) DEFAULT NULL,\r\n"
				+ "    PRIMARY KEY (`id_deportista`, `id_evento`),\r\n"
				+ "	CONSTRAINT `FK_Participacion_Deportista` FOREIGN KEY (`id_deportista`) REFERENCES `Deportista` (`id_deportista`),\r\n"
				+ "    CONSTRAINT `FK_Participacion_Equipo` FOREIGN KEY (`id_equipo`) REFERENCES `Equipo` (`id_equipo`),\r\n"
				+ "    CONSTRAINT `FK_Participacion_Evento` FOREIGN KEY (`id_evento`) REFERENCES `Evento` (`id_evento`)\r\n"
				+ ") ENGINE = InnoDB DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;";
		 try {
			
			ejecutarUpdate(crearSchema);
			ejecutarUpdate(crearTablaDeporte);
			ejecutarUpdate(crearTablaDeportista);
			ejecutarUpdate(crearTablaEquipo);
			ejecutarUpdate(crearTablaOlimpiada);
			ejecutarUpdate(crearTablaEvento);
			ejecutarUpdate(crearTablaParticipacion);
			if(archivoCSV.exists()&&archivoCSV.isFile()&&ruta.endsWith(".csv")) {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void ejecutarUpdate(String sentencia) throws SQLException {
		PreparedStatement pstmt = conection.prepareStatement(sentencia);
		pstmt.executeUpdate();
	}
	
}
