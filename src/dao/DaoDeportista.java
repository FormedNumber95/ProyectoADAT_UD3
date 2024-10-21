package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbdd.ConexionBBDD;

public class DaoDeportista {
	
	private static Connection conection;
	
	public static void aniadirDeportista(String nombreDeportista,char sexo,float peso,int altura) {
		conection=ConexionBBDD.getConnection();
		String insertar="INSERT INTO Deportista (nombre,sexo,peso,altura) VALUES (?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt=conection.prepareStatement(insertar);
			pstmt.setString(1, nombreDeportista);
			pstmt.setString(2, sexo+"");
			pstmt.setFloat(3, peso);
			pstmt.setInt(4, altura);
			pstmt.executeUpdate();
			conection.commit();
			pstmt.close();
			conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
