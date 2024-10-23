package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bbdd.ConexionBBDD;
import modelos.ModeloDeportista;

/**
 * Clase DaoDeportista.
 */
public class DaoDeportista {
	
	/** The conection. */
	private static Connection conection;
	
	/**
	 * Aniadir deportista.
	 *
	 * @param nombreDeportista the nombre deportista
	 * @param sexo the sexo
	 * @param peso the peso
	 * @param altura the altura
	 */
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Conseguir id deportista.
	 *
	 * @param nombreDeportista the nombre deportista
	 * @param sexo the sexo
	 * @param peso the peso
	 * @param altura the altura
	 * @return the string
	 */
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
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Crear modelo deportista.
	 *
	 * @param id the id
	 * @return the modelo deportista
	 */
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
				return new ModeloDeportista(rs.getString("nombre"), rs.getString("sexo").charAt(0), rs.getInt("altura"), rs.getInt("peso"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Buscar nombres de deportistas que contengan la cadena que se pasa como parametro.
	 *
	 * @param cadena the cadena
	 * @return the array list
	 */
	public static ArrayList<ModeloDeportista> buscarNombresDeportistas(String cadena){
		conection=ConexionBBDD.getConnection();
		ArrayList<ModeloDeportista>lst=new ArrayList<ModeloDeportista>();
		String select="SELECT id_deportista FROM Deportista WHERE nombre LIKE ?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setString(1,"%"+cadena+"%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				conection.commit();
				lst.add(DaoDeportista.crearModeloDeportista(rs.getInt("id_deportista")+""));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;	
	}
	
}
