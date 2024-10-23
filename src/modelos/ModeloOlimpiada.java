package modelos;

import java.util.Objects;

/**
 * Clase ModeloOlimpiada.
 */
public class ModeloOlimpiada {
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(anio, ciudad, nombreOlimpiada, temporada);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModeloOlimpiada other = (ModeloOlimpiada) obj;
		return anio == other.anio && Objects.equals(ciudad, other.ciudad)
				&& Objects.equals(nombreOlimpiada, other.nombreOlimpiada) && Objects.equals(temporada, other.temporada);
	}

	/** El nombre de la olimpiada. */
	private String nombreOlimpiada;
	
	/** El anio. */
	private int anio;
	
	/** La temporada. */
	private String temporada;
	
	/** La ciudad. */
	private String ciudad;
	
	/**
	 * Getter del nombre de la olimpiada.
	 *
	 * @return the nombre olimpiada
	 */
	public String getNombreOlimpiada() {
		return nombreOlimpiada;
	}

	/**
	 * Getter del anio.
	 *
	 * @return the anio
	 */
	public int getAnio() {
		return anio;
	}

	/**
	 * Getter de la temporada.
	 *
	 * @return the temporada
	 */
	public String getTemporada() {
		return temporada;
	}

	/**
	 * Getter de la ciudad.
	 *
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Instantiates a new modelo olimpiada.
	 *
	 * @param nombreOlimpiada the nombre olimpiada
	 * @param anio the anio
	 * @param temporada the temporada
	 * @param ciudad the ciudad
	 */
	public ModeloOlimpiada(String nombreOlimpiada, int anio, String temporada, String ciudad) {
		this.nombreOlimpiada = nombreOlimpiada;
		this.anio = anio;
		this.temporada = temporada;
		this.ciudad = ciudad;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return this.nombreOlimpiada+","+this.anio+","+this.temporada+","+this.ciudad;
	}
	
}
