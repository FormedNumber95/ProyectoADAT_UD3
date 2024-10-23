package proyecto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import bbdd.ConexionBBDD;
import dao.DaoCreaBBDD;
import dao.DaoDeporte;
import dao.DaoDeportista;
import dao.DaoEvento;
import dao.DaoOlimpiada;
import dao.DaoParticipacion;
import modelos.ModeloDeporte;
import modelos.ModeloDeportista;
import modelos.ModeloEvento;
import modelos.ModeloOlimpiada;
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
			int resp=0;
			String temporada;
			int idDeporte;
			int idOlimpiada;
			int idEvento;
			do {
				System.out.println("Dime la tamporada:\n1 Winter\n2 Summer");
				resp=input.nextInt();
				input.nextLine();
			}while(resp!=1&&resp!=2);
			ArrayList<ModeloOlimpiada>lstOlimpiada=DaoOlimpiada.listaOlimpiadasPorTemporada(resp);
			do {
				System.out.println("Elige (usando el numero) la edicion olimpica que quieres seleccionar");
				for(int i=0;i<lstOlimpiada.size();i++) {
					System.out.println((i+1)+": "+lstOlimpiada.get(i).toString());
				}
				resp=input.nextInt();
				input.nextLine();
			}while(resp<1||resp>lstOlimpiada.size());
			temporada=lstOlimpiada.get(resp-1).getTemporada();
			idOlimpiada=Integer.parseInt(DaoOlimpiada.conseguirIdOlimpiada(lstOlimpiada.get(resp-1).getNombreOlimpiada(), lstOlimpiada.get(resp-1).getAnio(), temporada, lstOlimpiada.get(resp-1).getCiudad()));
			ArrayList<ModeloDeporte>lstDeporte=DaoDeporte.listaDeportesPorOlimpiada(Integer.parseInt(DaoOlimpiada.conseguirIdOlimpiada(lstOlimpiada.get(resp-1).getNombreOlimpiada(), lstOlimpiada.get(resp-1).getAnio(),lstOlimpiada.get(resp-1).getTemporada(),lstOlimpiada.get(resp-1).getCiudad())));
			do {
				System.out.println("Elige (usando el numero) el deporte");
				for(int i=0;i<lstDeporte.size();i++) {
					System.out.println((i+1)+": "+lstDeporte.get(i).toString());
				}
				resp=input.nextInt();
				input.nextLine();
			}while(resp<1||resp>lstDeporte.size());
			idDeporte=Integer.parseInt(DaoDeporte.conseguirIdDeporte(lstDeporte.get(resp-1).getNombreDeporte()));
			ArrayList<ModeloEvento>lstEventos=DaoEvento.crearListaModelosPorDeporteYOlimpiada(idDeporte, idOlimpiada);
			do {
				System.out.println("Elige (usando el numero) el evento");
				for(int i=0;i<lstEventos.size();i++) {
					System.out.println((i+1)+": "+lstEventos.get(i).toString());
				}
				resp=input.nextInt();
				input.nextLine();
			}while(resp<1||resp>lstEventos.size());
			idEvento=Integer.parseInt(DaoEvento.conseguirIdEvento(lstEventos.get(resp-1).getNombreEvento(), idOlimpiada, idDeporte));
			System.out.println("Resumen:\nTemporada: "+temporada+"\nOlimpiada: "+DaoOlimpiada.crearModeloOlimpiada(idOlimpiada)+
					"\nDeporte: "+DaoDeporte.crearModeloDeporte(idDeporte)+"\nEvento: "+DaoEvento.crearPorId(idEvento));
			System.out.println("Deportistas:");
			ArrayList<String>lstIdDeportistas=DaoParticipacion.darIdDeportista(idEvento);
			for(String deportista:lstIdDeportistas) {
				System.out.println(DaoParticipacion.crearModeloParticipacion(Integer.parseInt(deportista), idEvento));
			}
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
