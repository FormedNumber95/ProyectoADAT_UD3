package proyecto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import bbdd.ConexionBBDD;
import dao.DaoCreaBBDD;
import dao.DaoDeportista;
import dao.DaoParticipacion;
import modelos.ModeloParticipacion;

public class ManejoDeConectores {
	
	public static void main(String[] args) {
		try {
			ConexionBBDD con=new ConexionBBDD();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner input=new Scanner(System.in);
		System.out.println("1. Crear BBDD MySQL");
		System.out.println("2. Listado de deportistas en diferentes deportes");
		System.out.println("3. Listado de deportistas participantes");
		System.out.println("4. Modificar medalla deportista");
		System.out.println("5. Añadir deportista/participación");
		System.out.println("6. Eliminar participación");
		int opcionMenu =input.nextInt();
		input.nextLine();
		switch (opcionMenu) {
		case 1:
			System.out.println("Dime la ruta del archivo csv");
			String ruta=input.nextLine();
			DaoCreaBBDD.crearBBDD(ruta);
			break;
		case 2:
			ListarDeportistas();
			break;
		case 3:
	
			break;
		case 4:
	
			break;
		case 5:
	
			break;
		case 6:
	
			break;
		default:
			System.out.println("Opcion no valida");
			break;
		}
	}

	private static void ListarDeportistas() {
		int i=10;
		while(DaoDeportista.crearModeloDeportista(i+"")!=null) {
			System.out.println(DaoDeportista.crearModeloDeportista(i+"")+":");
			ArrayList<ModeloParticipacion>lst=listaParticipaciones(i);
			for(ModeloParticipacion part:lst) {
				System.out.println(part.getEvento().getDeporte().getNombreDeporte()+","+
						part.getEdad()+","+part.getEvento().getNombreEvento()+","+
						part.getEquipo().getNombreEquipo()+","+part.getEvento().getOlimpiada().getNombreOlimpiada()+","+
						part.getMedalla());
			}
			i++;
		}
	}
	
	public static ArrayList<ModeloParticipacion> listaParticipaciones(int idDeportista){
		ArrayList<ModeloParticipacion> lista=new ArrayList<ModeloParticipacion>();
		for(String idEvento:DaoParticipacion.darIdEvento(idDeportista)) {
			lista.add(DaoParticipacion.crearModeloParticipacion(idDeportista, Integer.parseInt(idEvento)));
		}
		return lista;
	}
}
