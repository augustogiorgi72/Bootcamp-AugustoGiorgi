package ar.com.educacionit.daos.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ar.com.educacionit.daos.GenericDao;
import ar.com.educacionit.daos.db.AdministradorDeConexiones;
import ar.com.educacionit.daos.db.exceptions.DuplicatedException;
import ar.com.educacionit.daos.db.exceptions.GenericException;
import ar.com.educacionit.domain.Entity;


public abstract class JDBCBaseDaoImpl<T extends Entity> implements GenericDao<T>{

	protected String tabla;
	
	public JDBCBaseDaoImpl(String tabla) {
		if(tabla == null) {
			throw new IllegalArgumentException("Debe indicar la tabla del DAO");
		}
		this.tabla = tabla;
	}

	@Override
	public T getByPK(Long id) throws GenericException {
		String sql = "SELECT * FROM " +this.tabla+ " WHERE ID = " + id;
		
		try(Connection con = AdministradorDeConexiones.obtenerConexion();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);){ 
					T entity = null;
					if(rs.next()) {
						entity = fromResultSetToEntity(rs);
					}
					return entity;
		} catch (SQLException e) {
			throw new GenericException("No se pudo obtener el entity " +this.tabla+ " id:"+id, e);
		}
	}

	@Override
	public List<T> findAll() throws GenericException {
	String sql = "SELECT * FROM " +this.tabla;
	
	try(Connection con = AdministradorDeConexiones.obtenerConexion();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		){
		List<T> listado = new ArrayList<>();
			while(rs.next()) {
			T entity;
			entity = fromResultSetToEntity(rs);
			listado.add(entity);
		}
		return listado;
		} catch (SQLException se) {
			throw new GenericException("Error realizando la consulta: "+sql, se);
		}	 
	}
	
	@Override
	public List<T> findPageable(Integer currentPage, Integer size) throws GenericException {
	String sql = "SELECT * FROM " +this.tabla+" LIMIT "+size+" OFFSET "+currentPage;
	
	try(Connection con = AdministradorDeConexiones.obtenerConexion();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		){
		List<T> listado = new ArrayList<>();
			while(rs.next()) {
			T entity;
			entity = fromResultSetToEntity(rs);
			listado.add(entity);
		}
		return listado;
		} catch (SQLException se) {
			throw new GenericException("Error realizando la consulta: "+sql, se);
		}	 
	}

	public void delete(Long id) throws GenericException {
		String sql = "DELETE FROM "+this.tabla+ " WHERE ID = " + id;
		try (
			Connection con = AdministradorDeConexiones.obtenerConexion();		
			Statement st = con.createStatement();){
				st.executeUpdate(sql);//alt+shift+m		
		}catch(SQLException se) {
			throw new GenericException(sql, se);
		}
	}
		
	public abstract T fromResultSetToEntity(ResultSet rs) throws SQLException;

	//public abstract String getSaveSQL();
	
	public String getSaveSQL2(T entity) {
		StringBuffer sql = new StringBuffer("(");
		StringBuffer sqlEnd = new StringBuffer();
		Field[] atributos = entity.getClass().getDeclaredFields();
		for(Field f: atributos) {
			f.setAccessible(true);
			if(!f.getName().equals("id")) {
				sql.append(formatName(f.getName())).append(",");
				sqlEnd.append("?").append(",");
			}
		}
		sql = new StringBuffer(sql.substring(0,sql.length()-1));
		sqlEnd = new StringBuffer(sqlEnd.substring(0,sqlEnd.length()-1));
		sql.append(")VALUES(").append(sqlEnd).append(")");
		return sql.toString();
	}
		
	public String formatName(String name) {
		String nameFormat="";
		for (Character c: name.toCharArray()) {
			if(Character.isUpperCase(c)) {
				nameFormat+="_"+c;	
			}
			else {
				nameFormat+=c;
			}
		}
		return nameFormat.toUpperCase();
	}

	public void save(T entity) throws GenericException, DuplicatedException {
		//StringBuffer sql = new StringBuffer("INSERT INTO ").append(this.tabla).append(" ").append(this.getSaveSQL());
		StringBuffer sql = new StringBuffer("INSERT INTO ").append(this.tabla).append(" ").append(this.getSaveSQL2(entity));
		
		try(Connection con = AdministradorDeConexiones.obtenerConexion()){
			try(PreparedStatement st = con.prepareStatement(sql.toString(),PreparedStatement.RETURN_GENERATED_KEYS)){
				this.setSave(entity, st);
				st.execute();
				try(ResultSet rs = st.getGeneratedKeys()){
					if(rs.next()) {
						Long id= rs.getLong(1);
						entity.setId(id);
					}
				}	
			}
			
		} catch (SQLException se) {
			if(se instanceof SQLIntegrityConstraintViolationException) {
				throw new DuplicatedException("No se ha podido insertar el articulo, integridad de datos violada",se);
			}
			throw new GenericException(se.getMessage(),se);
			
		}
	}
	
	protected abstract void setSave(T entity, PreparedStatement st) throws SQLException;

	public abstract String getUpdateSQL(T entity);
	
	public abstract void setUpdate(T entity, PreparedStatement st ) throws SQLException;
	
	@Override
	public void update(T entity) throws GenericException {
		StringBuffer sql = new StringBuffer("UPDATE "+this.tabla+" SET "+this.getUpdateSQL(entity)+" WHERE ID=?");
		try (Connection con = AdministradorDeConexiones.obtenerConexion();) {
				try (PreparedStatement st = con.prepareStatement(sql.toString());){
					this.setUpdate(entity, st);
					int numId= this.count(sql.toString());	
					st.setLong(numId, entity.getId());
					st.execute();	
				}
	
		} catch(SQLException se) {
		throw new GenericException(se.getMessage(), se);
		}
	}

	public int count(String sql) {
		int cantidad=0;
		for(char c:sql.toCharArray()) {
			if(c=='?') {
				cantidad++;
			}
		}
		return cantidad;
	}
		
}