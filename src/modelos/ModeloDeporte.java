package modelos;

import java.util.Objects;

/**
 * Clase ModeloDeporte.
 */
public class ModeloDeporte {

	/** The nombre deporte. */
	private String nombreDeporte;
	
	/**
	 * Instantiates a new modelo deporte.
	 *
	 * @param nombre the nombre
	 */
	public ModeloDeporte(String nombre) {
		this.nombreDeporte=nombre;
	}
	
	/**
	 * Getter del nombre deporte.
	 *
	 * @return the nombre deporte
	 */
	public String getNombreDeporte() {
		return nombreDeporte;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return this.nombreDeporte;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(nombreDeporte);
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
		ModeloDeporte other = (ModeloDeporte) obj;
		return Objects.equals(nombreDeporte, other.nombreDeporte);
	}
	
}
