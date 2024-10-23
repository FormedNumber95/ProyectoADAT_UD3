package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbdd.ConexionBBDD;
import modelos.ModeloEquipo;

/**
 * Clase DaoEquipo.
 */
public class DaoEquipo {
	
	/** The conection. */
	private static Connection conection;
	
	/**
	 * Aniadir equipo.
	 *
	 * @param nombre the nombre
	 * @param iniciales the iniciales
	 */
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Conseguir id equipo.
	 *
	 * @param nombre the nombre
	 * @param iniciales the iniciales
	 * @return the string
	 */
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
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Crear modelo equipo.
	 *
	 * @param id the id
	 * @return the modelo equipo
	 */
	public static ModeloEquipo crearModeloEquipo(int id) {
		conection=ConexionBBDD.getConnection();
		String select="SELECT nombre,iniciales FROM Equipo WHERE id_equipo=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				conection.commit();
				return new ModeloEquipo(rs.getString("nombre"), rs.getString("iniciales"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
