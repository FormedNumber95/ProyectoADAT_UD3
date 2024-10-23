package modelos;

/**
 * Clase ModeloEquipo.
 */
public class ModeloEquipo {
	
	/** El nombre del equipo. */
	private String nombreEquipo;
	
	/** Las iniciales. */
	private String iniciales;
	
	/**
	 * Getter del nombre equipo.
	 *
	 * @return the nombre equipo
	 */
	public String getNombreEquipo() {
		return nombreEquipo;
	}

	/**
	 * Getter de las iniciales.
	 *
	 * @return the iniciales
	 */
	public String getIniciales() {
		return iniciales;
	}

	/**
	 * Instantiates a new modelo equipo.
	 *
	 * @param nombreEquipo the nombre equipo
	 * @param iniciales the iniciales
	 */
	public ModeloEquipo(String nombreEquipo, String iniciales) {
		this.nombreEquipo = nombreEquipo;
		this.iniciales = iniciales;
	}
	
}
