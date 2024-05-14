package mikel.restaurantes.beans;

public class Categoria {
	private int id_categorias;
	private String tipo;
	public Categoria(int id_categoria, String tipo) {
		super();
		this.id_categorias = id_categoria;
		this.tipo = tipo;
	}
	public Categoria() {
		super();
	}
	public int getId_categorias() {
		return id_categorias;
	}
	public void setId_categorias(int id_categoria) {
		this.id_categorias = id_categoria;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "Categorias [id_categoria=" + id_categorias + ", tipo=" + tipo + "]";
	}
	
	
	
}
