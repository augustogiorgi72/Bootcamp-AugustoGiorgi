package accionesDB;

import Excepciones.ExceptionBase;
import inMemoryDB.Articulo;

public interface IAccion {

	public void exec (Articulo ctx) throws ExceptionBase;
	
}