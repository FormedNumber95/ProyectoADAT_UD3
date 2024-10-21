package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbdd.ConexionBBDD;

public class DaoDeporte {
	
	private static Connection conection;
	
	public static void aniadirDeporte(String nombreDeporte) {
		conection=ConexionBBDD.getConnection();
		String insertar="INSERT INTO Deporte (nombre) VALUES (?)";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(insertar);
			pstmt.setString(1, nombreDeporte);
			pstmt.executeUpdate();
			conection.commit();
			pstmt.close();
			conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String conseguirIdDeporte(String nombre) {
		conection=ConexionBBDD.getConnection();
		String select="SELECT id_deporte FROM Deporte where nombre=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setString(1,nombre);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String id=rs.getString("id_deporte");
				pstmt.close();
				conection.close();
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
