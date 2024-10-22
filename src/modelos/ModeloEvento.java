package modelos;

public class ModeloEvento {
	
	private String nombreEvento;
	private ModeloDeporte deporte;
	private ModeloOlimpiada olimpiada;
	
	public ModeloEvento(String nombreEvento, ModeloDeporte deporte, ModeloOlimpiada olimpiada) {
		this.nombreEvento = nombreEvento;
		this.deporte = deporte;
		this.olimpiada = olimpiada;
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public ModeloDeporte getDeporte() {
		return deporte;
	}

	public ModeloOlimpiada getOlimpiada() {
		return olimpiada;
	}
	
}
