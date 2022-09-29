package inMemoryDB;

import java.util.HashMap;
import java.util.Map;

import Excepciones.ExceptionBase;
import Excepciones.ExceptionEmpty;
import Excepciones.ExceptionId;
import Excepciones.ExceptionNoId;

public class InMemoryDB {

	static private Map<Long, Articulo> db = new HashMap<Long, Articulo>();

	public static void save(Long key, Articulo valor) throws ExceptionBase {
		if (key!=null) {
			InMemoryDB.db.put(key, valor);
		} else {
			throw new ExceptionBase("Operacion invalida",
					new ExceptionId("Articulo no ingresado, valor Id null."));
		}
	}

	public static void update(Long key, Articulo newValue) throws ExceptionBase {
			InMemoryDB.db.replace(key, newValue);
	}

	public static void delete(Long key) throws ExceptionBase {
		Articulo articulo = InMemoryDB.getById(key);
		InMemoryDB.db.remove(key);
	}

	public static Articulo getById(Long key) throws ExceptionBase {
		Articulo value = null;
		if (InMemoryDB.db.containsKey(key)) {
			value = InMemoryDB.db.get(key);
		} else {
			throw new ExceptionBase("Operacion invalida", new ExceptionNoId("Accion no ejecutada. El Id no existe en la DB."));
		}
		return value;
	}

	public static void listAll() throws ExceptionBase {
		if (InMemoryDB.db.isEmpty()) {
			throw new ExceptionBase("Operacion invalida", new ExceptionEmpty("La base de datos esta vacia."));
		}
		System.out.println(InMemoryDB.db.values());

	}
	
	public static void vaciar() {
		InMemoryDB.db.clear();
	}
}