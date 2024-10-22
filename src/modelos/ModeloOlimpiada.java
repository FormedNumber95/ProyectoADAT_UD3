package modelos;

public class ModeloOlimpiada {
	
	private String nombreOlimpiada;
	private int anio;
	private String temporada;
	private String ciudad;
	
	public String getNombreOlimpiada() {
		return nombreOlimpiada;
	}

	public int getAnio() {
		return anio;
	}

	public String getTemporada() {
		return temporada;
	}

	public String getCiudad() {
		return ciudad;
	}

	public ModeloOlimpiada(String nombreOlimpiada, int anio, String temporada, String ciudad) {
		this.nombreOlimpiada = nombreOlimpiada;
		this.anio = anio;
		this.temporada = temporada;
		this.ciudad = ciudad;
	}
	
}
