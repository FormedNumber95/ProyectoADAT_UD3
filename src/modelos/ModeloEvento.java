package modelos;

import java.util.Objects;

/**
 * Clase ModeloEvento.
 */
public class ModeloEvento {
	
	/** El nombre del evento. */
	private String nombreEvento;
	
	/** El deporte. */
	private ModeloDeporte deporte;
	
	/** La olimpiada. */
	private ModeloOlimpiada olimpiada;
	
	/**
	 * Instantiates a new modelo evento.
	 *
	 * @param nombreEvento the nombre evento
	 * @param deporte the deporte
	 * @param olimpiada the olimpiada
	 */
	public ModeloEvento(String nombreEvento, ModeloDeporte deporte, ModeloOlimpiada olimpiada) {
		this.nombreEvento = nombreEvento;
		this.deporte = deporte;
		this.olimpiada = olimpiada;
	}

	/**
	 * Getter del nombre del evento.
	 *
	 * @return the nombre evento
	 */
	public String getNombreEvento() {
		return nombreEvento;
	}

	/**
	 * Getter del deporte.
	 *
	 * @return the deporte
	 */
	public ModeloDeporte getDeporte() {
		return deporte;
	}

	/**
	 * Getter de la olimpiada.
	 *
	 * @return the olimpiada
	 */
	public ModeloOlimpiada getOlimpiada() {
		return olimpiada;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return this.nombreEvento+","+this.deporte.getNombreDeporte()+","+this.olimpiada.getNombreOlimpiada();
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(deporte, nombreEvento, olimpiada);
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
		ModeloEvento other = (ModeloEvento) obj;
		return Objects.equals(deporte, other.deporte) && Objects.equals(nombreEvento, other.nombreEvento)
				&& Objects.equals(olimpiada, other.olimpiada);
	}
	
	
	
}
