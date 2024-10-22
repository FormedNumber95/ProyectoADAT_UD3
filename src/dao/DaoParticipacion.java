package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bbdd.ConexionBBDD;
import modelos.ModeloParticipacion;

public class DaoParticipacion {
	
	private static Connection conection;
	
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
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
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
				pstmt.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static ArrayList<String> darIdEvento(int idDeportista) {
		conection=ConexionBBDD.getConnection();
		String select="SELECT id_evento FROM Participaciones WHERE id_deportista=?";
		ArrayList<String> lst=new ArrayList<String>();
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setInt(1, idDeportista);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				conection.commit();
				pstmt.close();
				lst.add(rs.getString("id_evento"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	//TODO ACABAR
	public static ModeloParticipacion crearModeloParticipacion(int idDeportista,int idEvento) {
		conection=ConexionBBDD.getConnection();
		String select="SELECT  FROM Participaciones WHERE id_deportista=?";
	}
}
