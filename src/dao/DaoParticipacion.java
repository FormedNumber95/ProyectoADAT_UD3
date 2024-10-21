package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbdd.ConexionBBDD;

public class DaoParticipacion {
	
	private static Connection conection;
	
	public static void aniadirParticipacion(int idEvento,int idEquipo,int edad,String medalla) {
		conection=ConexionBBDD.getConnection();
		String insertar="INSERT INTO Participacion (id_evento,id_equipo,edad,medalla) VALUES (?,?,?,?)";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(insertar);
			pstmt.setInt(1, idEvento);
			pstmt.setInt(2,idEquipo);
			pstmt.setInt(3, edad);
			pstmt.setString(4, medalla);
			pstmt.executeUpdate();
			conection.commit();
			pstmt.close();
			conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
