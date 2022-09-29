package ar.com.educacionit.daos.db;

public class ConexionDB implements AutoCloseable {

	private boolean open;
	
	public ConexionDB (String username, String password) {
		System.out.println("Conectando a la db");
		//this.open = true; implementamos el metodo
	}
	
	public ConexionDB open() throws ConnectionException {
		if(!isOpen()) {
			this.open = true;
			return this; //retorno la conexion para usarlo en el try
		} else {
			throw new ConnectionException("La conexion ya esta abierta");
		}
		
		
	}
	
	public boolean isOpen() {
		return this.open;
	}
	
	public void close() {
		System.out.println("Se ejecuto AtoCloseable");
		if(this.isOpen()) {
			this.open = false;
		}
	}
	
	public void connect() {
		if (!isOpen()) {
			this.open = true;
		} else {
			System.err.println("Ya conectada..");
		}
	}
	
}