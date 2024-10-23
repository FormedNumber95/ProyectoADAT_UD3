package modelos;

import java.util.ArrayList;

/**
 * Clase ModeloParticipacion.
 */
public class ModeloParticipacion {
	
	/** El deportista. */
	private ModeloDeportista deportista;
	
	/** El evento. */
	private ModeloEvento evento;
	
	/** El equipo. */
	private ModeloEquipo equipo;
	
	/** La edad. */
	private int edad;
	
	/** La medalla. */
	private String medalla;
	
	/**
	 * Instantiates a new modelo participacion.
	 *
	 * @param deportista the deportista
	 * @param evento the evento
	 * @param equipo the equipo
	 * @param edad the edad
	 * @param medalla the medalla
	 */
	public ModeloParticipacion(ModeloDeportista deportista,ModeloEvento evento,ModeloEquipo equipo, int edad, String medalla) {
		this.deportista=deportista;
		this.evento=evento;
		this.equipo = equipo;
		this.edad = edad;
		this.medalla = medalla;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return this.deportista.getNombreDeportista()+","+this.deportista.getAltura()+","+this.deportista.getPeso()+
				","+this.edad+","+this.equipo.getNombreEquipo()+","+this.medalla;
	}

	/**
	 * Getter del deportista.
	 *
	 * @return the deportista
	 */
	public ModeloDeportista getDeportista() {
		return deportista;
	}

	/**
	 * Getter del evento.
	 *
	 * @return the evento
	 */
	public ModeloEvento getEvento() {
		return evento;
	}

	/**
	 * Getter del equipo.
	 *
	 * @return the equipo
	 */
	public ModeloEquipo getEquipo() {
		return equipo;
	}

	/**
	 * Getter de la edad.
	 *
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Getter de la medalla.
	 *
	 * @return the medalla
	 */
	public String getMedalla() {
		return medalla;
	}
	
}
