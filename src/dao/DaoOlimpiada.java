package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbdd.ConexionBBDD;

public class DaoOlimpiada {
	
	private static Connection conection;
	
	public static void aniadirOlimpiada(String nombre, int anio,String temporada,String ciudad) {
		conection=ConexionBBDD.getConnection();
		String insertar="INSERT INTO Olimpiada (nombre,anio,temporada,ciudad) VALUES (?,?,?,?)";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(insertar);
			pstmt.setString(1,nombre);
			pstmt.setInt(2, anio);
			pstmt.setString(3,temporada);
			pstmt.setString(4,ciudad);
			pstmt.executeUpdate();
			conection.commit();
			pstmt.close();
			conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
