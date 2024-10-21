package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbdd.ConexionBBDD;

public class DaoDeporte {
	
	private static Connection conection;
	
	public static void aniadirDeportista(String nombreDeporte) {
		conection=ConexionBBDD.getConnection();
		String insertar="INSERT INTO Deporte (nombre) VALUES (?)";
		try {
			PreparedStatement pstmt=conection.prepareStatement(insertar);
			pstmt.setString(1, nombreDeporte);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
