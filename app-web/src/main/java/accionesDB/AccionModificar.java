package accionesDB;

import Excepciones.ExceptionBase;
import Excepciones.ExceptionNull;
import inMemoryDB.Articulo;
import inMemoryDB.InMemoryDB;

public class AccionModificar implements IAccion {

	public void exec(Articulo art) throws ExceptionBase {
		if (art != null) {
			InMemoryDB.update(art.getId(), art);
		} else { throw new ExceptionBase ("Operacion invalida", new ExceptionNull("Accion no ejecutada. Articulo null."));
		}
	}
}