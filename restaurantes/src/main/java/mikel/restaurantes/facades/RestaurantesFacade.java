package mikel.restaurantes.facades;

import java.util.List;

import mikel.restaurantes.beans.Restaurante;


public interface RestaurantesFacade {
	List<Restaurante> getRestaurantes();
	
	List<Restaurante> getRestaurantesVisitados();
	
	List<Restaurante> getRestaurantesNoVisitados();

	void agregarRestaurante(Restaurante restaurante);

	void eliminarRestaurante(int id_restaurante);

	void actualizarRestaurante(Restaurante restaurante);

	Restaurante getRestauranteById(int id_restaurante);

	List<Restaurante> getRestaurantesPorCategoria(String categoria);



}
