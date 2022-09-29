package ar.com.educacionit.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.com.educacionit.daos.CategoriaDao;
import ar.com.educacionit.daos.db.AdministradorDeConexiones;
import ar.com.educacionit.daos.db.exceptions.GenericException;
import ar.com.educacionit.domain.Articulo;
import ar.com.educacionit.domain.Categoria;


public class CategoriaDaoMysqlImpl extends JDBCBaseDaoImpl <Categoria>implements CategoriaDao {
	
	public CategoriaDaoMysqlImpl() {
		super("CATEGORIAS");
	}

	public Categoria fromResultSetToEntity(ResultSet rs) throws SQLException {	
		Long idCategoria = rs.getLong("id");
		String descripcion = rs.getString("descripcion");
		Long habilitada = rs.getLong("habilitada");
		return new Categoria(idCategoria,descripcion,habilitada);
	}

	@Override
	protected void setSave(Categoria entity, PreparedStatement st) throws SQLException {
			st.setString(1, entity.getDescripcion());
			st.setLong(2, entity.getHabilitada());
		}

	@Override
	public String getUpdateSQL(Categoria entity) {
		StringBuffer sql = new StringBuffer();	
		if(entity.getDescripcion()!=null) {
			sql.append("descripcion=?").append(",");
		}	
		if(entity.getHabilitada()!=null) {
			sql.append("habilitada=?").append(",");		
		}	
		sql = new StringBuffer(sql.substring(0,sql.length()-1));
		return sql.toString();	
	}

	@Override
	public void setUpdate(Categoria entity, PreparedStatement st) throws SQLException {
		if(entity.getDescripcion()!=null) {
			st.setString(1, entity.getDescripcion());
		}
		
		if(entity.getHabilitada()!=null) {
			st.setLong(2, entity.getHabilitada());
		}
	}

}