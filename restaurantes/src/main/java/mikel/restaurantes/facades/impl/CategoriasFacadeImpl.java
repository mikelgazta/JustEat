package mikel.restaurantes.facades.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mikel.restaurantes.beans.Categoria;
import mikel.restaurantes.daos.CategoriasDao;
import mikel.restaurantes.facades.CategoriasFacade;

@Service
public class CategoriasFacadeImpl implements CategoriasFacade{
	@Autowired
	private CategoriasDao categoriasDao;

	@Override
	public List<Categoria> getCategorias() {
		return categoriasDao.getCategorias();
	}

	@Override
	public void agregarCategoria(Categoria categoria) {
		categoriasDao.agregarCategoria(categoria);
	}
	
	@Override
	public void eliminarCategoria(int id_categorias) {
		categoriasDao.eliminarCategoria(id_categorias);
	}

	@Override
	public Categoria getCategoriaById(int id_categorias) {
		return categoriasDao.getCategoriaById(id_categorias);
	}

	@Override
	public void actualizarCategoria(Categoria categoria) {
		categoriasDao.actualizarCategoria(categoria);
		
	}

}
