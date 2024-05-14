package mikel.restaurantes.daos;

import java.util.List;

import mikel.restaurantes.beans.Restaurante;


public interface RestaurantesDao {
	List<Restaurante> getRestaurantes();
	
	List<Restaurante> getRestaurantesVisitados();
	
	List<Restaurante> getRestaurantesNoVisitados();
	
	void agregarRestaurante(Restaurante restaurante);
	
	void eliminarRestaurante(int id_restaurante);

	void actualizarRestaurante(Restaurante restaurante);

	Restaurante getRestauranteById(int id_restaurante);

	List<Restaurante> getRestaurantePorCategoria(String categoria);




}
