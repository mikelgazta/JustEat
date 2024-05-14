package mikel.restaurantes.facades.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mikel.restaurantes.beans.Restaurante;
import mikel.restaurantes.daos.RestaurantesDao;
import mikel.restaurantes.facades.RestaurantesFacade;

@Repository
public class RestaurantesFacadeImpl implements RestaurantesFacade{

	@Autowired private RestaurantesDao restaurantesDao;
	
	@Override
	public List<Restaurante> getRestaurantes() {
		return restaurantesDao.getRestaurantes();
	}

	@Override
	public List<Restaurante> getRestaurantesVisitados() {
		return restaurantesDao.getRestaurantesVisitados();
	}
	
	@Override
	public List<Restaurante> getRestaurantesNoVisitados() {
		return restaurantesDao.getRestaurantesNoVisitados();
	}
	
	@Override
	public void agregarRestaurante(Restaurante restaurante) {
		restaurantesDao.agregarRestaurante(restaurante);
	}

	@Override
	public void eliminarRestaurante(int id_restaurante) {
		restaurantesDao.eliminarRestaurante(id_restaurante);
	}

	@Override
	public void actualizarRestaurante(Restaurante restaurante) {
		restaurantesDao.actualizarRestaurante(restaurante);
		
	}

	@Override
	public Restaurante getRestauranteById(int id_restaurante) {
		return restaurantesDao.getRestauranteById(id_restaurante);
	}

	@Override
	public List<Restaurante> getRestaurantesPorCategoria(String categoria) {
		return restaurantesDao.getRestaurantePorCategoria(categoria);
	}

}
