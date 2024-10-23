package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bbdd.ConexionBBDD;
import modelos.ModeloEvento;

/**
 * Clase DaoEvento.
 */
public class DaoEvento {

	/** The conection. */
	private static Connection conection;
	
	/**
	 * Aniadir evento.
	 *
	 * @param nombreEvento the nombre evento
	 * @param idOlimpiada the id olimpiada
	 * @param idDeporte the id deporte
	 */
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Conseguir id evento.
	 *
	 * @param nombreEvento the nombre evento
	 * @param idOlimpiada the id olimpiada
	 * @param idDeporte the id deporte
	 * @return the string
	 */
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
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Crear por id.
	 *
	 * @param id the id
	 * @return the modelo evento
	 */
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
				return new ModeloEvento(rs.getString("nombre"), DaoDeporte.crearModeloDeporte(rs.getInt("id_deporte")),DaoOlimpiada.crearModeloOlimpiada(rs.getInt("id_olimpiada")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Crear lista modelos por deporte y olimpiada.
	 *
	 * @param idDeporte the id deporte
	 * @param idOlimpiada the id olimpiada
	 * @return the array list
	 */
	public static ArrayList<ModeloEvento> crearListaModelosPorDeporteYOlimpiada(int idDeporte,int idOlimpiada) {
		conection=ConexionBBDD.getConnection();
		String select="SELECT nombre FROM Evento WHERE id_deporte=? AND id_olimpiada=?";
		ArrayList<ModeloEvento>lst=new ArrayList<ModeloEvento>();
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setInt(1, idDeporte);
			pstmt.setInt(2, idOlimpiada);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				conection.commit();
				ModeloEvento evento=new ModeloEvento(rs.getString("nombre"),DaoDeporte.crearModeloDeporte(idDeporte),DaoOlimpiada.crearModeloOlimpiada(idOlimpiada));
				if(!lst.contains(evento)) {
					lst.add(evento);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;	
	}
	
	/**
	 * Crear lista modelos por lista de ids.
	 *
	 * @param lstId the lst id
	 * @return the array list
	 */
	public static ArrayList<ModeloEvento> crearListaModelosPorLstId(ArrayList<String>lstId){
		conection=ConexionBBDD.getConnection();
		ArrayList<ModeloEvento>lst=new ArrayList<ModeloEvento>();
		String select="SELECT nombre,id_deporte,id_olimpiada FROM Evento where id_evento=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			for(String id:lstId) {
				pstmt.setInt(1,Integer.parseInt(id));
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					conection.commit();
					ModeloEvento evento=new ModeloEvento(rs.getString("nombre"),DaoDeporte.crearModeloDeporte(rs.getInt("id_deporte")),DaoOlimpiada.crearModeloOlimpiada(rs.getInt("id_olimpiada")));
					lst.add(evento);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
}
