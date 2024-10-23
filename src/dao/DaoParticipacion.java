package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bbdd.ConexionBBDD;
import modelos.ModeloParticipacion;

/**
 * Clase DaoParticipacion.
 */
public class DaoParticipacion {
	
	/** The conection. */
	private static Connection conection;
	
	/**
	 * Aniadir participacion.
	 *
	 * @param idDeportista the id deportista
	 * @param idEvento the id evento
	 * @param idEquipo the id equipo
	 * @param edad the edad
	 * @param medalla the medalla
	 */
	public static void aniadirParticipacion(int idDeportista,int idEvento,int idEquipo,int edad,String medalla) {
		conection=ConexionBBDD.getConnection();
		String insertar="INSERT INTO Participacion (id_deportista,id_evento,id_equipo,edad,medalla) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(insertar);
			pstmt.setInt(1,idDeportista);
			pstmt.setInt(2, idEvento);
			pstmt.setInt(3,idEquipo);
			pstmt.setInt(4, edad);
			pstmt.setString(5, medalla);
			pstmt.executeUpdate();
			conection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Verifica si existe el id de la participacion.
	 *
	 * @param idDeportista the id deportista
	 * @param idEvento the id evento
	 * @return true, if successful
	 */
	public static boolean existeIdParticipacion(int idDeportista,int idEvento) {
		conection=ConexionBBDD.getConnection();
		String buscar="SELECT id_equipo FROM Participacion WHERE id_deportista=? AND id_evento=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(buscar);
			pstmt.setInt(1, idDeportista);
			pstmt.setInt(2, idEvento);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				conection.commit();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Dar id evento.
	 *
	 * @param idDeportista the id deportista
	 * @return the array list
	 */
	public static ArrayList<String> darIdEvento(int idDeportista) {
		conection=ConexionBBDD.getConnection();
		String select="SELECT id_evento FROM Participacion WHERE id_deportista=?";
		ArrayList<String> lst=new ArrayList<String>();
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setInt(1, idDeportista);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				conection.commit();
				lst.add(rs.getString("id_evento"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	/**
	 * Dar id deportista.
	 *
	 * @param idEvento the id evento
	 * @return the array list
	 */
	public static ArrayList<String> darIdDeportista(int idEvento) {
		conection=ConexionBBDD.getConnection();
		String select="SELECT id_deportista FROM Participacion WHERE id_evento=?";
		ArrayList<String> lst=new ArrayList<String>();
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setInt(1, idEvento);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				conection.commit();
				lst.add(rs.getString("id_deportista"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}

	
	/**
	 * Crear modelo participacion.
	 *
	 * @param idDeportista the id deportista
	 * @param idEvento the id evento
	 * @return the modelo participacion
	 */
	public static ModeloParticipacion crearModeloParticipacion(int idDeportista,int idEvento) {
		conection=ConexionBBDD.getConnection();
		String select="SELECT id_equipo,edad,medalla FROM Participacion WHERE id_deportista=? AND id_evento=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setInt(1,idDeportista);
			pstmt.setInt(2, idEvento);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				conection.commit();
				return new ModeloParticipacion(DaoDeportista.crearModeloDeportista(idDeportista+""),DaoEvento.crearPorId(idEvento),DaoEquipo.crearModeloEquipo(rs.getInt("id_equipo")), rs.getInt("edad"),rs.getString("medalla"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Editar medalla.
	 *
	 * @param idDeportista the id deportista
	 * @param idEvento the id evento
	 * @param nuevaMedalla the nueva medalla
	 */
	public static void editarMedalla(int idDeportista,int idEvento,String nuevaMedalla) {
		conection=ConexionBBDD.getConnection();
		String update="UPDATE Participacion SET medalla=? WHERE id_deportista=? AND id_evento=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(update);
			pstmt.setString(1,nuevaMedalla);
			pstmt.setInt(2,idDeportista);
			pstmt.setInt(3, idEvento);
			pstmt.executeUpdate();
			conection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Eliminar participacion.
	 *
	 * @param idDeportista the id deportista
	 * @param idEvento the id evento
	 */
	public static void eliminarParticipacion(int idDeportista,int idEvento) {
		conection=ConexionBBDD.getConnection();
		String delete="DELETE FROM Participacion WHERE id_deportista=? AND id_evento=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(delete);
			pstmt.setInt(1,idDeportista);
			pstmt.setInt(2, idEvento);
			pstmt.executeUpdate();
			conection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
