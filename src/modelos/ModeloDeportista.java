package modelos;

/**
 * Clase ModeloDeportista.
 */
public class ModeloDeportista {
	
	/** El nombre del deportista. */
	private String nombreDeportista;
	
	/** El sexo. */
	private char sexo;
	
	/** La altura. */
	private int altura;
	
	/** El peso. */
	private float peso;
	
	/**
	 * Instantiates a new modelo deportista.
	 *
	 * @param nombreDeportista the nombre deportista
	 * @param sexo the sexo
	 * @param altura the altura
	 * @param peso the peso
	 */
	public ModeloDeportista(String nombreDeportista, char sexo, int altura, float peso) {
		this.nombreDeportista = nombreDeportista;
		this.sexo = sexo;
		this.altura = altura;
		this.peso = peso;
	}

	/**
	 * Getter del nombre del deportista.
	 *
	 * @return the nombre deportista
	 */
	public String getNombreDeportista() {
		return nombreDeportista;
	}

	/**
	 * Getter del sexo.
	 *
	 * @return the sexo
	 */
	public char getSexo() {
		return sexo;
	}

	/**
	 * Getter de la altura.
	 *
	 * @return the altura
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * Getter del peso.
	 *
	 * @return the peso
	 */
	public float getPeso() {
		return peso;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return this.nombreDeportista+","+this.sexo+","+this.altura+","+this.peso;
	}
	
}
