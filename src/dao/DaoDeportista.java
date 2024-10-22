package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbdd.ConexionBBDD;
import modelos.ModeloDeportista;

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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String conseguirIdDeportista(String nombreDeportista,char sexo,float peso,int altura) {
		conection=ConexionBBDD.getConnection();
		String select="SELECT id_deportista FROM Deportista WHERE nombre=? AND sexo=? AND peso=? AND altura=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setString(1,nombreDeportista);
			pstmt.setString(2,sexo+"");
			pstmt.setFloat(3,peso);
			pstmt.setInt(4,altura);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String id=rs.getString("id_deportista");
				conection.commit();
				pstmt.close();
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ModeloDeportista crearModeloDeportista(String id) {
		conection=ConexionBBDD.getConnection();
		String select="SELECT nombre,sexo,peso,altura FROM Deportista WHERE id_deportista=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setString(1,id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				conection.commit();
				pstmt.close();
				return new ModeloDeportista(rs.getString("nombre"), rs.getString("sexo").charAt(0), rs.getInt("altura"), rs.getInt("peso"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
