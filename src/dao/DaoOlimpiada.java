package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bbdd.ConexionBBDD;
import modelos.ModeloOlimpiada;

/**
 * Clase DaoOlimpiada.
 */
public class DaoOlimpiada {
	
	/** The conection. */
	private static Connection conection;
	
	/**
	 * Aniadir olimpiada.
	 *
	 * @param nombre the nombre
	 * @param anio the anio
	 * @param temporada the temporada
	 * @param ciudad the ciudad
	 */
	public static void aniadirOlimpiada(String nombre, int anio,String temporada,String ciudad) {
		conection=ConexionBBDD.getConnection();
		String insertar="INSERT INTO Olimpiada (nombre,anio,temporada,ciudad) VALUES (?,?,?,?)";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(insertar);
			pstmt.setString(1,nombre);
			pstmt.setInt(2, anio);
			pstmt.setString(3,temporada);
			pstmt.setString(4,ciudad);
			pstmt.executeUpdate();
			conection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Conseguir id olimpiada.
	 *
	 * @param nombre the nombre
	 * @param anio the anio
	 * @param temporada the temporada
	 * @param ciudad the ciudad
	 * @return the string
	 */
	public static String conseguirIdOlimpiada(String nombre, int anio,String temporada,String ciudad) {
		conection=ConexionBBDD.getConnection();
		String select="SELECT id_olimpiada FROM Olimpiada WHERE nombre=? AND anio=? AND temporada=? AND ciudad=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setString(1,nombre);
			pstmt.setInt(2, anio);
			pstmt.setString(3, temporada);
			pstmt.setString(4,ciudad);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String id=rs.getString("id_olimpiada");
				conection.commit();
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Crear modelo olimpiada.
	 *
	 * @param id the id
	 * @return the modelo olimpiada
	 */
	public static ModeloOlimpiada crearModeloOlimpiada(int id) {
		conection=ConexionBBDD.getConnection();
		String select="SELECT nombre,anio,temporada,ciudad FROM Olimpiada WHERE id_olimpiada=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				conection.commit();
				return new ModeloOlimpiada(rs.getString("nombre"), rs.getInt("anio"),rs.getString("temporada"),rs.getString("ciudad"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Lista olimpiadas por temporada.
	 *
	 * @param temp the temp
	 * @return the array list
	 */
	public static ArrayList<ModeloOlimpiada> listaOlimpiadasPorTemporada(int temp){
		ArrayList<ModeloOlimpiada> lst=new ArrayList<ModeloOlimpiada>();
		String temporada="Winter";
		if(temp==2) {
			temporada="Summer";
		}
		conection=ConexionBBDD.getConnection();
		String select="SELECT id_olimpiada FROM Olimpiada WHERE temporada=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setString(1, temporada);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				conection.commit();
				lst.add(DaoOlimpiada.crearModeloOlimpiada(rs.getInt("id_olimpiada")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
}
