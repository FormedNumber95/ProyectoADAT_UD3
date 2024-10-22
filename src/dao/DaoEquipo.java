package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String conseguirIdEquipo(String nombre, String iniciales) {
		conection=ConexionBBDD.getConnection();
		String select="SELECT id_equipo FROM Equipo WHERE nombre=? AND iniciales=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setString(1,nombre);
			pstmt.setString(2, iniciales);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String id=rs.getString("id_equipo");
				conection.commit();
				pstmt.close();
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
