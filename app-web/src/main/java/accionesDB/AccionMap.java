package accionesDB;

import java.util.HashMap;
import java.util.Map;

public class AccionMap {

	static Map<Integer, IAccion> mapa = construirAcciones();

	private static Map<Integer, IAccion> construirAcciones() {
		// usar mapa local

		Map<Integer, IAccion> mapaAcciones = new HashMap<Integer, IAccion>();
		mapaAcciones.put(1, new AccionAlta());
		mapaAcciones.put(2, new AccionBaja());
		mapaAcciones.put(3, new AccionModificar());
		mapaAcciones.put(4, new AccionListar());

		return mapaAcciones;

	}

	public static IAccion getAccion(Integer op) {
		IAccion accion = new AccionDefault();

		if (mapa.containsKey(op)) {
			accion = mapa.get(op);
		}

		return accion;

	}

}