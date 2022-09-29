package ar.com.educacionit.daos.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ar.com.educacionit.daos.ArticuloDao;
import ar.com.educacionit.daos.db.AdministradorDeConexiones;
import ar.com.educacionit.daos.db.exceptions.GenericException;
import ar.com.educacionit.domain.Articulo;

public class ArticuloDaoMysqlImpl extends JDBCBaseDaoImpl <Articulo> implements ArticuloDao {

	public ArticuloDaoMysqlImpl() {
		super("ARTICULOS");
	}
	
	public Articulo getByCode(String code) throws GenericException {
		
		try(Connection con2 = AdministradorDeConexiones.obtenerConexion()) {
			try (Statement st = con2.createStatement()) {
				System.out.println("SELECT * FROM ARTICULOS WHERE CODIGO = " + code);
				try(ResultSet rs = st.executeQuery("SELECT * FROM ARTICULOS WHERE CODIGO = " + code)) { 
					Articulo articulo = null;
					if(rs.next()) {
						articulo = fromResultSetToEntity(rs);
					}
					return articulo;
				}
			} catch (SQLException e) {
				throw new GenericException("No se pudo obtener el articulo codigo: "+code, e);
			}
		} catch (SQLException e) {
			throw new GenericException("No se pudo obtener el articulo id: "+code, e);
		}
	}
	
	public Articulo fromResultSetToEntity(ResultSet rs) throws SQLException {	
		Long idArticulo = rs.getLong("id");
		String titulo = rs.getString("titulo");
		String codigo = rs.getString("codigo");
		Date fechaCreacion = rs.getDate("fecha_creacion");
		Double precio = rs.getDouble("precio");
		Long stock = rs.getLong("stock");
		Long marcasId = rs.getLong("marcas_id");
		Long categoriasId = rs.getLong("categorias_id");
		return new Articulo(idArticulo, titulo, codigo, fechaCreacion, precio, stock, marcasId, categoriasId);
	}

	@Override
	public String getUpdateSQL(Articulo entity) {
		StringBuffer sql = new StringBuffer();
		if(entity.getTitulo()!=null) {
			sql.append("titulo=?").append(",");
		}
		
		if(entity.getCodigo()!=null) {
			sql.append("codigo=?").append(",");
			
		}
		
		if(entity.getPrecio()!=null) {
			sql.append("precio=?").append(",");
		}
		
		if(entity.getStock()!=null) {
			sql.append("stock=?").append(",");
		}
		if(entity.getCategoriasId()!=null) {
			sql.append("categorias_id=?").append(",");
		}
		if(entity.getMarcasId()!=null) {
			sql.append("marcas_id=?").append(",");
		}
		sql = new StringBuffer(sql.substring(0,sql.length()-1));
		
		return sql.toString();
	}

	@Override
	public void setUpdate(Articulo entity, PreparedStatement st) throws SQLException {
		if(entity.getTitulo()!=null) {
		st.setString(1, entity.getTitulo());
		}
		
		if(entity.getCodigo()!=null) {
			st.setString(2, entity.getCodigo());
		}
		
		if(entity.getPrecio()!=null) {
			st.setDouble(3, entity.getPrecio());
		}
		
		if(entity.getStock()!=null) {
			st.setLong(4, entity.getStock());
		}
		if(entity.getCategoriasId()!=null) {
			st.setLong(5, entity.getCategoriasId());
		}
		if(entity.getMarcasId()!=null) {
			st.setLong(6, entity.getMarcasId());
		}
			
	}

	/*@Override
	public String getSaveSQL() {
		return "(TITULO,CODIGO,PRECIO,CATEGORIAS_ID,MARCAS_ID,FECHA_CREACION,STOCK)VALUES(?,?,?,?,?,?,?)";
	}*/

	@Override
	protected void setSave(Articulo entity, PreparedStatement st) throws SQLException {
		st.setString(1, entity.getTitulo());
		st.setString(2, entity.getCodigo());
		st.setDate(3,new java.sql.Date(System.currentTimeMillis())); //lo tengo que convertir a sql, esta como java por defecto
		st.setDouble(4, entity.getPrecio());
		st.setLong(5,entity.getStock());
		st.setLong(6, entity.getCategoriasId());
		st.setLong(7, entity.getMarcasId());
	}


}