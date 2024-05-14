package mikel.restaurantes.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mikel.restaurantes.beans.Categoria;
import mikel.restaurantes.facades.CategoriasFacade;

@Controller
public class CategoriasController {
	
	@Autowired private CategoriasFacade categoriasFacade;
	
	@GetMapping("/categorias")
	public String listaCategorias(Model model) {
	    List<Categoria> categoria = categoriasFacade.getCategorias();
	    model.addAttribute("categorias", categoria);
	    return "listaCategorias";
	}
    
	
	@PostMapping("/categorias/guardar")
	@ResponseBody
	public Map<String, Object> guardarCategoria(@ModelAttribute Categoria categoria) {
	    categoriasFacade.agregarCategoria(categoria);
	    Map<String, Object> response = new HashMap<>();
	    response.put("status", "success");
	    response.put("datos", categoria);
	    return response;
	}



	
    @GetMapping("/categorias/{id_categorias}/editar")
    @ResponseBody
    public Map<String, Object> editarCategoria(@PathVariable("id_categorias") int id_categorias) {
        Map<String, Object> response = new HashMap<>();
        Categoria categoria = categoriasFacade.getCategoriaById(id_categorias);
        if (categoria == null) {
            response.put("success", false);
            response.put("message", "Categoria no encontrada");
        } else {
            response.put("status", "success");
            response.put("categoria", categoria);
        }
        return response;
    }

    @PostMapping("/categorias/{id_categorias}/editar")
    @ResponseBody
    public Map<String, Object> actualizarCategoria(@PathVariable("id_categorias") String tipo, @ModelAttribute("categoria") Categoria categoriaActualizada) {
    	categoriaActualizada.setTipo(tipo);
    	categoriasFacade.actualizarCategoria(categoriaActualizada);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("success", true);
        respuesta.put("mensaje", "Categoria actualizada correctamente");
        respuesta.put("objeto", categoriaActualizada);
        return respuesta;
    }
    
    @GetMapping("/categorias/{id_categorias}/eliminar")
    public String eliminarUbicacion(@PathVariable("id_categorias") int id_categorias, Model model) {
        categoriasFacade.eliminarCategoria(id_categorias);
        return "redirect:/tipoUbicaciones";
    }
    
}
