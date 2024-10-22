package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbdd.ConexionBBDD;

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
}
