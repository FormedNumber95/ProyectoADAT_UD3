package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbdd.ConexionBBDD;

public class DaoEvento {

	private static Connection conection;
	
	public static void aniadirEvento(String nombreEvento,int idOlimpiada,int idDeporte) {
		conection=ConexionBBDD.getConnection();
		String insertar="INSERT INTO Evento (nombre,id_olimpiada,id_deporte) VALUES (?,?,?)";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(insertar);
			pstmt.setString(1,nombreEvento);
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
	
	public static String conseguirIdEvento(String nombreEvento,int idOlimpiada,int idDeporte) {
		conection=ConexionBBDD.getConnection();
		String select="SELECT id_evento FROM Evento where nombre=? AND id_olimpiada=? AND id_deporte=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setString(1,nombreEvento);
			pstmt.setInt(2, idOlimpiada);
			pstmt.setInt(3, idDeporte);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String id=rs.getString("id_evento");
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
