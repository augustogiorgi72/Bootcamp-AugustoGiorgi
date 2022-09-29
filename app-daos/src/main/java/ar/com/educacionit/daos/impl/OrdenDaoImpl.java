package ar.com.educacionit.daos.impl;

import ar.com.educacionit.daos.OrdenDao;
import ar.com.educacionit.daos.db.ConexionDB;
import ar.com.educacionit.daos.db.ConnectionException;
import ar.com.educacionit.domain.Orden;

	

public class OrdenDaoImpl implements OrdenDao {

	//declaro una conexion a la DB
	private ConexionDB con;
	
	public OrdenDaoImpl () {
		//cuando nace la implementacion, nace mi conexion
		this.con = new ConexionDB("root","1234");
	}
	
	@Override
	public Orden create(Orden orden) {
		//necesito una conexion abierta
		try(ConexionDB con = this.con.open();){
			//logica
			orden.setId(1L); // seteamos un valor para la prueba
		} catch (ConnectionException ce) {
			//Hacer algo con el error si necesito
		}
		return orden;
	}

	@Override
	public Orden read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orden update(Orden ordenToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orden delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}