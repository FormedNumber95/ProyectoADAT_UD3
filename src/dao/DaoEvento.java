package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbdd.ConexionBBDD;
import modelos.ModeloEvento;

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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String conseguirIdEvento(String nombreEvento,int idOlimpiada,int idDeporte) {
		conection=ConexionBBDD.getConnection();
		String select="SELECT id_evento FROM Evento WHERE nombre=? AND id_olimpiada=? AND id_deporte=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setString(1,nombreEvento);
			pstmt.setInt(2, idOlimpiada);
			pstmt.setInt(3, idDeporte);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String id=rs.getString("id_evento");
				conection.commit();
				pstmt.close();
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ModeloEvento crearPorId(int id) {
		conection=ConexionBBDD.getConnection();
		String select="SELECT nombre,id_deporte,id_olimpiada FROM Evento WHERE id_evento=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				conection.commit();
				pstmt.close();
				return new ModeloEvento(rs.getString("nombre"), DaoDeporte.crearModeloDeporte(rs.getInt("id_deporte")),DaoOlimpiada.crearModeloOlimpiada(rs.getInt("id_olimpiada")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
