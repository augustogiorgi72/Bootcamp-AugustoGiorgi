package ar.com.educacionit.daos;

import ar.com.educacionit.daos.db.exceptions.GenericException;
import ar.com.educacionit.domain.Articulo;

public interface ArticuloDao extends GenericDao<Articulo> {

	//este es el unico metodo que es particular de Articulo
	public Articulo getByCode(String code) throws GenericException;
	
 }