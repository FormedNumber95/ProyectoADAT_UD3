package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbdd.ConexionBBDD;
import modelos.ModeloOlimpiada;

public class DaoOlimpiada {
	
	private static Connection conection;
	
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
	
	
	
}
