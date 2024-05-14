package mikel.restaurantes.daos.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import mikel.restaurantes.beans.Categoria;
import mikel.restaurantes.daos.CategoriasDao;

@Repository
public class CategoriasDaoImpl implements CategoriasDao{

	private final JdbcTemplate jdbcTemplate;
	
    @Autowired
    public CategoriasDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	
	@Override
	public List<Categoria> getCategorias() {
      String sql = "SELECT * FROM categorias";
      RowMapper<Categoria> rowMapper = new BeanPropertyRowMapper<>(Categoria.class);
      return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public void agregarCategoria(Categoria categoria) {
        String sql = "INSERT INTO categorias" +
                "VALUES (:tipo)";

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("tipo", categoria.getTipo());

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        namedParameterJdbcTemplate.update(sql, parametros);
	}

	@Override
	public void eliminarCategoria(int id_categorias) {
		String sql = "DELETE FROM categorias WHERE id_categorias= ?";
        jdbcTemplate.update(sql, id_categorias);
	}

	@Override
	public void actualizarCategoria(Categoria categoriaActualizada) {
		   Categoria categoriaExistente = getCategoriaById(categoriaActualizada.getId_categorias());
	        if (categoriaExistente == null) {
	            throw new IllegalArgumentException("La ubicación no existe");
	        }

	        categoriaExistente.setTipo(categoriaActualizada.getTipo());
	        String sql = "UPDATE categorias SET tipo = ?, WHERE id = ?";
	        jdbcTemplate.update(sql, categoriaExistente.getTipo());
	}

	@SuppressWarnings("deprecation")
	@Override
	public Categoria getCategoriaById(int id_categorias) {
		String sql ="SELECT * FROM categorias WHERE id_categorias= ?";
		Categoria categoria= jdbcTemplate.queryForObject(sql, new Object[] {id_categorias}, new BeanPropertyRowMapper<>(Categoria.class));
		return categoria;
	}
}
