package accionesDB;

import Excepciones.ExceptionBase;
import inMemoryDB.Articulo;
import inMemoryDB.InMemoryDB;

public class AccionAlta implements IAccion {

	public void exec(Articulo art) throws ExceptionBase {
		InMemoryDB.save(art.getId(), art);	
	}

}