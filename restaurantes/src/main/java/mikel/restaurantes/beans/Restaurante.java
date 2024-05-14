package mikel.restaurantes.beans;


public class Restaurante {
	private int id_restaurante;
	private String nombre;
	private int puntuacion;
	private String categoria;
	private String municipio;
	private String direccion;
	private String observaciones;
	private boolean visitado;
	
	public Restaurante(int id_restaurante, String nombre, int puntuacion, String categoria, String municipio,
			String direccion, String observaciones, boolean visitado) {
		super();
		this.id_restaurante = id_restaurante;
		this.nombre = nombre;
		this.puntuacion = puntuacion;
		this.categoria = categoria;
		this.municipio = municipio;
		this.direccion = direccion;
		this.observaciones = observaciones;
	}


	public Restaurante() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId_restaurante() {
		return id_restaurante;
	}

	public void setId_restaurante(int id_restaurante) {
		this.id_restaurante = id_restaurante;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	@Override
	public String toString() {
		return "Restaurante [id_restaurante=" + id_restaurante + ", nombre=" + nombre + ", puntuacion=" + puntuacion
				+ ", categoria=" + categoria + ", municipio=" + municipio + ", direccion=" + direccion
				+ ", observaciones=" + observaciones + "]";
	}
	
	
}
