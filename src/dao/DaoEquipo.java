package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbdd.ConexionBBDD;

public class DaoEquipo {
	
	private static Connection conection;
	
	public static void aniadirEquipo(String nombre, String iniciales) {
		conection=ConexionBBDD.getConnection();
		String insertar="INSERT INTO Equipo (nombre,iniciales) VALUES (?,?)";
		
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(insertar);
			pstmt.setString(1,nombre);
			pstmt.setString(2,iniciales);
			pstmt.executeUpdate();
			conection.commit();
			pstmt.close();
			conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
