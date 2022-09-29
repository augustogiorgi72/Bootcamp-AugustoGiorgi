package practica_semana6_nuevo;

import accionesDB.AccionMap;
import accionesDB.IAccion;
import inMemoryDB.Articulo;
import inMemoryDB.InMemoryDB;
import inMemoryDB.Libro;

public class MainAccion {

	public static void main(String[] args) {

		/*
		 * Simulamos que el usuario ingresa las opciones 
		 * 1-Alta 
		 * 2-Baja 
		 * 3-Modificar
		 * 4-Listar
		 */

		// creacion de articulo para prueba

		Articulo articulo1 = new Libro(001l, "Harry Potter", "J. K. Rowling", 3000d, "isbn222");
		Articulo articulo2 = new Libro(002l, "The Maze Runner", "James Dashner", 2500d, "isbn333");
		Articulo articulo3 = new Libro(003l, "Batman", "Bruce Wayne", 2000d, "isbn111");
		Articulo articulo4 = new Libro(null, null, null, null, null);

		Integer op;
		IAccion accion;

		// ingreso de articulos a la DB para las pruebas con la opcion 1-Alta
		accion = seleccionOpcion(1);
		try {
			accion.exec(articulo1);
			accion.exec(articulo2);
		} catch (Exception e) {
			System.err.println(e.getCause().getMessage());
		}

		// simulacion de la opcion 4-listar
		accion = seleccionOpcion(4);
		listar(accion, articulo2);

		// simulacion de ingreso de un articulo con Id null
		accion = seleccionOpcion(1);
		try {
			accion.exec(articulo4);
		} catch (Exception e) {
			System.err.println(e.getCause().getMessage());
		}

		// simulacion de la opcion 2-Baja de un articulo que no existe en la DB
		accion = seleccionOpcion(2);
		try {
			accion.exec(articulo3);
		} catch (Exception e) {
			System.err.println(e.getCause().getMessage());
		}

		// simulacion de la opcion 3-Modificar (se cambia el importe del articulo)
		articulo2 = new Libro(001l, "Harry Potter", "J. K. Rowling", 5000d, "isbn222");

		accion = seleccionOpcion(3);
		try {
			accion.exec(articulo2);
		} catch (Exception e) {
			System.err.println(e.getCause().getMessage());
		}

		accion = seleccionOpcion(4);
		listar(accion, articulo2);

		// simulacion de la opcion 3-Modificar (articulo null)
		articulo2 = null;
		accion = seleccionOpcion(3);
		try {
			accion.exec(articulo2);
		} catch (Exception e) {
			System.err.println(e.getCause().getMessage());
		}

		// Elimino todos los elementos de la DB
		InMemoryDB.vaciar();

		// se vuelve a listar con la opcion 4 (para ver excepcion por base de datos vacia)
		accion = seleccionOpcion(4);
		listar(accion, articulo2);

	}

	private static IAccion seleccionOpcion(int op) {
		IAccion accion = AccionMap.getAccion(op);
		return accion;
	}

	private static void listar(IAccion accion, Articulo articulo) {
		try {
			accion.exec(articulo);
		} catch (Exception e) {
			System.err.println(e.getCause().getMessage());
		}

	}

}