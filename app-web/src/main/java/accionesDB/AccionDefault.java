package accionesDB;

import inMemoryDB.Articulo;

public class AccionDefault implements IAccion {

	public void exec(Articulo art) {
		System.out.println("No existe la opcion seleccionada");

	}

}