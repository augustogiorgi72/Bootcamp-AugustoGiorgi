package ar.com.educacionit.daos.db;

import java.sql.Connection;

public class AdministradorDeConexiones extends Exception {


	public AdministradorDeConexiones(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AdministradorDeConexiones(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public static Connection obtenerConexion() {
		// TODO Auto-generated method stub
		return null;
	}



}