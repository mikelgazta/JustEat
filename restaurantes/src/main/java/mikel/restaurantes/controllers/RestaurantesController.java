package mikel.restaurantes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mikel.restaurantes.beans.Restaurante;
import mikel.restaurantes.facades.RestaurantesFacade;

@Controller
public class RestaurantesController {
	
	@Autowired private RestaurantesFacade restaurantesFacade;
	
	@GetMapping("/")
	public String index() {
		return "redirect:/restaurantes";
	}
	
    @GetMapping("/restaurantes")
    public String listarRestaurantes(Model model) {
        List<Restaurante> restaurantes= restaurantesFacade.getRestaurantes();
        model.addAttribute("restaurantes", restaurantes);
        return "restaurantes";
    }
	
    @GetMapping("/restaurantes/visitados")
    public String listarRestaurantesVisitados(Model model) {
        List<Restaurante> restaurantesVisitados = restaurantesFacade.getRestaurantesVisitados();
        model.addAttribute("restaurantes", restaurantesVisitados);
        return "restaurantes";
    }
    
    @GetMapping("/restaurantes/noVisitados")
    public String listarRestaurantesNoVisitados(Model model) {
        List<Restaurante> restaurantesNoVisitados = restaurantesFacade.getRestaurantesNoVisitados();
        model.addAttribute("restaurantes", restaurantesNoVisitados);
        return "restaurantes";
    }
    
    @GetMapping("/restaurantes/nuevo")
    public String nuevoRestaurante(Model model) {
        model.addAttribute("restaurante", new Restaurante());
        return "formNuevoRestaurante";
    }
    
    @PostMapping("/restaurantes/guardar")
    public String guardarRestaurante(@ModelAttribute("restaurante") Restaurante restaurante, Model model) {
        boolean visitado = restaurante.isVisitado(); // Obtener el valor del campo visitado

        if (visitado) {
            restaurante.setVisitado(true); // Marcar como visitado si el checkbox está marcado
        } else {
            restaurante.setVisitado(false); // Marcar como no visitado si el checkbox no está marcado
        }

        restaurantesFacade.agregarRestaurante(restaurante);
        return "redirect:/restaurantes";
    }

    
    @GetMapping("/restaurantes/{id}/editar")
    public String editarRestaurante(@PathVariable("id") int id, Model model) {
        Restaurante restaurante= restaurantesFacade.getRestauranteById(id);
	    if (restaurante == null) {
	        return "error";
	    }
        model.addAttribute("restaurante", restaurante);
        return "formEditRestaurante";
    }

    @PostMapping("/restaurantes/{id}/editar")
    public String actualizarRestaurante(@PathVariable("id") int id, @ModelAttribute("restaurante") Restaurante restauranteActualizado) {
        restauranteActualizado.setId_restaurante(id);
        restaurantesFacade.actualizarRestaurante(restauranteActualizado);
        return "redirect:/restaurantes";
    }
    
    @GetMapping("/restaurantes/{id}/eliminar")
    public String eliminarRestaurante(@PathVariable("id") int id, Model model) {
        restaurantesFacade.eliminarRestaurante(id);
        return "redirect:/restaurantes";
    }
    
    @GetMapping("/restaurantes/filtrarPorCategoria")
    public String filtrarRestaurantesPorCategoria(@RequestParam("categoria") String categoria, Model model) {
        List<Restaurante> restaurantesFiltrados = restaurantesFacade.getRestaurantesPorCategoria(categoria);
        model.addAttribute("restaurantes", restaurantesFiltrados);
        return "restaurantes";
    }

	
}
