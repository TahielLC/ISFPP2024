package redUni.interfaz;

import java.util.List;
import java.util.Scanner;
import java.util.Map;

import redUni.aplicacion.Coordinador;
import redUni.modelo.Conexion;
import redUni.modelo.Equipo;

public class Interfaz {

	private Coordinador coordinador;

	public Interfaz() {

	}

	/**
	 * 1 es el ping
	 * 2 es el tracerouter
	 * 
	 * @return
	 */
	public int opcion() {
		Scanner scanner = new Scanner(System.in);
		int opcion = scanner.nextInt();

		if (opcion == 1) {
			return 1;
		} else if (opcion == 2) {
			return opcion;
		} else {
			// Aqui deberia de lanzar una excepcion
		}
		return opcion;
	}

	// En el caso de que la opcion sea 1 elige la consulta de ping
	public int subOpcion() {
		Scanner scanner = new Scanner(System.in);
		int opcion = scanner.nextInt();

		if (opcion == 1) {
			return opcion;
		} else if (opcion == 2) {
			return opcion;
		} else if (opcion == 3) {
			return opcion;
		} else {
			// Aqui deberia de lanzar una excepcion
		}
		return opcion;
	}

	// Debe de ingresar la ip y retornar el equipo
	// si no se encuentra, retorna null
	public Equipo ingresarEquipo(List<Equipo> equipos) {
		Scanner scanner = new Scanner(System.in);
		String codigo = scanner.next();
		for (Equipo equipo : equipos) {
			if (equipo.getCodigo().equals(codigo)) {
				return equipo;
			}
		}
		return null;
	}

	public void mostrarPing(Equipo equipo, boolean estado) {
		if (estado) {
			System.out.println(equipo.getCodigo() + "Estado: " + "Activo");
		} else {
			System.out.println(equipo.getCodigo() + "Estado: " + "Inactivo");
		}
	}

	public void mostrarPingRango(List<Boolean> estados, List<Conexion> conexiones) {
		if (estados.size() != conexiones.size() + 1) {
			throw new IllegalArgumentException("La cantidad de estados no coincide con las conexiones.");
		}

		// Mostrar el estado del primer equipo
		Conexion primeraConexion = conexiones.get(0);
		System.out.println(primeraConexion.getEquipo1().getCodigo() + " - "
				+ primeraConexion.getEquipo1().getDescripcion() + " - Estado: "
				+ (estados.get(0) ? "Activo" : "Inactivo"));

		// Mostrar el estado de los equipos intermedios
		for (int i = 0; i < conexiones.size(); i++) {
			Conexion conexion = conexiones.get(i);
			System.out.println(conexion.getEquipo2().getCodigo() + " - "
					+ conexion.getEquipo2().getDescripcion() + " - Estado: "
					+ (estados.get(i + 1) ? "Activo" : "Inactivo"));
		}
	}

	public void mostrarMapaEstadoEquipos(Map<Equipo, Boolean> estadoEquipos) {
		System.out.println("Estado actual de los equipos en la red:");

		for (Map.Entry<Equipo, Boolean> entry : estadoEquipos.entrySet()) {
			Equipo equipo = entry.getKey();
			Boolean estado = entry.getValue();

			System.out.println("Equipo: " + equipo.getCodigo() + " - "
					+ equipo.getDescripcion() + " - Estado: "
					+ (estado ? "Activo" : "Inactivo"));
		}
	}

	public void resultadoTracerouter(List<Conexion> conexiones) {
		for (Conexion c : conexiones) {
			System.out.println(c.getEquipo1().getCodigo() + "-"
					+ c.getEquipo1().getDescripcion() + "-"
					+ c.getEquipo1().getMarca() + "-"
					+ c.getEquipo1().getModelo() + "-->"
					+ c.getEquipo2().getCodigo() + "-"
					+ c.getEquipo2().getDescripcion() + "-"
					+ c.getEquipo2().getMarca() + "-"
					+ c.getEquipo2().getModelo());
		}
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}
}
