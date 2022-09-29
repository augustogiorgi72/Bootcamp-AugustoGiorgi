package ar.com.educacionit.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.com.educacionit.daos.MarcaDao;
import ar.com.educacionit.daos.db.AdministradorDeConexiones;
import ar.com.educacionit.daos.db.exceptions.GenericException;
import ar.com.educacionit.domain.Categoria;
import ar.com.educacionit.domain.Marca;


public class MarcaDaoMysqlImpl extends JDBCBaseDaoImpl<Marca> implements MarcaDao {
	
	public MarcaDaoMysqlImpl() {
		super("MARCAS");
	}

	public Marca fromResultSetToEntity(ResultSet rs) throws SQLException {	
		Long idMarca = rs.getLong("id");
		String descripcion = rs.getString("descripcion");
		Long habilitada = rs.getLong("habilitada");
		String cuit = rs.getString("cuit");
		return new Marca(idMarca,descripcion,habilitada,cuit);
	}
	
	protected void setSave(Marca entity, PreparedStatement st) throws SQLException {
		st.setString(1, entity.getDescripcion());
		st.setLong(2, entity.getHabilitada());
		st.setString(3, entity.getCuit());
	}
	
	@Override
	public String getUpdateSQL(Marca entity) {
		StringBuffer sql = new StringBuffer();	
		if(entity.getDescripcion()!=null) {
			sql.append("descripcion=?").append(",");
		}	
		if(entity.getHabilitada()!=null) {
			sql.append("habilitada=?").append(",");		
		}	
		if(entity.getCuit()!=null) {
			sql.append("cuit=?").append(",");
		}	
		sql = new StringBuffer(sql.substring(0,sql.length()-1));
		return sql.toString();	
	}
	
	@Override
	public void setUpdate(Marca entity, PreparedStatement st) throws SQLException {
		if(entity.getDescripcion()!=null) {
			st.setString(1, entity.getDescripcion());
		}
		if(entity.getHabilitada()!=null) {
			st.setLong(2, entity.getHabilitada());
		}
		if(entity.getCuit()!=null) {
			st.setString(3, entity.getCuit());
		}
	}

}