package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbdd.ConexionBBDD;

public class DaoEvento {

	private static Connection conection;
	
	public static void crearEvento(String nombreOlimpidada,int idOlimpiada,int idDeporte) {
		conection=ConexionBBDD.getConnection();
		String insertar="INSERT INTO Evento (nombre,id_olimpiada,id_deporte) VALUES (?,?,?)";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(insertar);
			pstmt.setString(1,nombreOlimpidada);
			pstmt.setInt(2, idOlimpiada);
			pstmt.setInt(3, idDeporte);
			pstmt.executeUpdate();
			conection.commit();
			pstmt.close();
			conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
