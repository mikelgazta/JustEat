package mikel.restaurantes.facades;

import java.util.List;

import mikel.restaurantes.beans.Categoria;

public interface CategoriasFacade {
	
	List<Categoria> getCategorias();

	void agregarCategoria(Categoria categoria);
	
	void eliminarCategoria(int id_categorias);

	Categoria getCategoriaById(int id_categorias);

	void actualizarCategoria(Categoria categoria);

}
