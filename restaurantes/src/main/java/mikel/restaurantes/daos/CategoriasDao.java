package mikel.restaurantes.daos;

import java.util.List;

import mikel.restaurantes.beans.Categoria;

public interface CategoriasDao {

	List<Categoria> getCategorias();

	void agregarCategoria(Categoria categoria);

	void actualizarCategoria(Categoria categoria);

	Categoria getCategoriaById(int id_categorias);

	void eliminarCategoria(int id_categorias);

}
