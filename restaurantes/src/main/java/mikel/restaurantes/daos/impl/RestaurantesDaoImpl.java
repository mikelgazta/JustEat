package mikel.restaurantes.daos.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import mikel.restaurantes.beans.Restaurante;
import mikel.restaurantes.daos.RestaurantesDao;

@Repository
public class RestaurantesDaoImpl implements RestaurantesDao{

	private final JdbcTemplate jdbcTemplate;
	
    @Autowired
    public RestaurantesDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
	@Override
	public List<Restaurante> getRestaurantes() {
        String sql = "SELECT * FROM restaurantes";
        RowMapper<Restaurante> rowMapper = new BeanPropertyRowMapper<>(Restaurante.class);
        return jdbcTemplate.query(sql, rowMapper);
	}
	
	@Override
	public List<Restaurante> getRestaurantesVisitados() {
        String sql = "SELECT * FROM restaurantes WHERE visitado = true";
        RowMapper<Restaurante> rowMapper = new BeanPropertyRowMapper<>(Restaurante.class);
        return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Restaurante> getRestaurantesNoVisitados() {
	    String sql = "SELECT * FROM restaurantes WHERE visitado = false";
	    RowMapper<Restaurante> rowMapper = new BeanPropertyRowMapper<>(Restaurante.class);
	    return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public void agregarRestaurante(Restaurante restaurante) {
	    String sql = "INSERT INTO restaurantes (nombre, puntuacion, categoria, municipio, direccion, observaciones, visitado) " +
	            "VALUES (:nombre, :puntuacion, :categoria, :municipio, :direccion, :observaciones, :visitado)";

	    Map<String, Object> parametros = new HashMap<>();
	    parametros.put("nombre", restaurante.getNombre());
	    parametros.put("puntuacion", restaurante.getPuntuacion());
	    parametros.put("categoria", restaurante.getCategoria());
	    parametros.put("municipio", restaurante.getMunicipio());
	    parametros.put("direccion", restaurante.getDireccion());
	    parametros.put("observaciones", restaurante.getObservaciones());
	    parametros.put("visitado", restaurante.isVisitado()); // Agregar el valor de visitado

	    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	    namedParameterJdbcTemplate.update(sql, parametros);    
	}

	@Override
	public void eliminarRestaurante(int id_restaurante) {
        String sql = "DELETE FROM restaurantes WHERE id_restaurante= ?";
        jdbcTemplate.update(sql, id_restaurante);
	}
	
	@Override
	public void actualizarRestaurante(Restaurante restauranteActualizado) {
	    Restaurante restauranteExistente = getRestauranteById(restauranteActualizado.getId_restaurante());
	    if (restauranteExistente == null) {
	        throw new IllegalArgumentException("El restaurante no existe");
	    }

	    restauranteExistente.setNombre(restauranteActualizado.getNombre());
	    restauranteExistente.setPuntuacion(restauranteActualizado.getPuntuacion());
	    restauranteExistente.setCategoria(restauranteActualizado.getCategoria());
	    restauranteExistente.setMunicipio(restauranteActualizado.getMunicipio());
	    restauranteExistente.setDireccion(restauranteActualizado.getDireccion());
	    restauranteExistente.setObservaciones(restauranteActualizado.getObservaciones());
	    restauranteExistente.setVisitado(restauranteActualizado.isVisitado());

	    String sql = "UPDATE restaurantes SET nombre = :nombre, puntuacion = :puntuacion, categoria = :categoria, " +
	            "municipio = :municipio, direccion = :direccion, observaciones = :observaciones, visitado = :visitado " +
	            "WHERE id_restaurante = :id";

	    Map<String, Object> parametros = new HashMap<>();
	    parametros.put("nombre", restauranteExistente.getNombre());
	    parametros.put("puntuacion", restauranteExistente.getPuntuacion());
	    parametros.put("categoria", restauranteExistente.getCategoria());
	    parametros.put("municipio", restauranteExistente.getMunicipio());
	    parametros.put("direccion", restauranteExistente.getDireccion());
	    parametros.put("observaciones", restauranteExistente.getObservaciones());
	    parametros.put("visitado", restauranteExistente.isVisitado());
	    parametros.put("id", restauranteExistente.getId_restaurante());

	    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	    namedParameterJdbcTemplate.update(sql, parametros);
	}


	@SuppressWarnings("deprecation")
	@Override
	public Restaurante getRestauranteById(int id_restaurante) {
        String sql = "SELECT * FROM restaurantes WHERE id_restaurante = ?";
		Restaurante restaurante= jdbcTemplate.queryForObject(sql, new Object[]{id_restaurante}, new BeanPropertyRowMapper<>(Restaurante.class));
        return restaurante;
	}

	@Override
	public List<Restaurante> getRestaurantePorCategoria(String categoria) {
	    List<Restaurante> restaurantes = getRestaurantes(); // Obtener todos los restaurantes
	    
	    List<Restaurante> restaurantesPorCategoria = new ArrayList<>();
	    
	    for (Restaurante restaurante : restaurantes) {
	        if (restaurante.getCategoria().equals(categoria)) {
	            restaurantesPorCategoria.add(restaurante);
	        }
	    }
	    
	    return restaurantesPorCategoria;
	}





}
