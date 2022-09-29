package accionesDB;

import Excepciones.ExceptionBase;
import inMemoryDB.Articulo;
import inMemoryDB.InMemoryDB;

public class AccionListar implements IAccion {

	public void exec(Articulo art) throws ExceptionBase {
		InMemoryDB.listAll();
		
	}

}