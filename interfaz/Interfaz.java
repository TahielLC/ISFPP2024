package interfaz;

import java.util.List;
import java.util.Scanner;
import java.util.Map;

import aplicacion.Coordinador;
import modelo.Conexion;
import modelo.Equipo;

public class Interfaz {

	private Coordinador coordinador;
	private Scanner scanner; // Instancia de Scanner a nivel de clase

	public Interfaz() {
		scanner = new Scanner(System.in); // Instanciar scanner aquí
	}

	/**
	 * 1 es el ping 2 es el traceroute
	 * 
	 * @return
	 */
	public int opcion() {
		System.out.println("Por favor, seleccione una opción:");
		System.out.println("1: Realizar Ping");
		System.out.println("2: Realizar Traceroute");
	
		int opcion = scanner.nextInt();
	
		if (opcion == 1 || opcion == 2) {
			return opcion;
		} else {
			throw new IllegalArgumentException("Opción no válida. Debe ser 1 o 2.");
		}
	}
	

	// En el caso de que la opción sea 1 elige la consulta de ping
	public int subOpcion() {
		System.out.println("Seleccione una sub-opción:");
		System.out.println("1: Ping a un solo equipo");
		System.out.println("2: Ping a un rango de equipos");
		System.out.println("3: Ver el estado de todos los equipos"); // Esto puede listar equipos
		
		int opcion = scanner.nextInt();
	
		if (opcion == 1 || opcion == 2 || opcion == 3) {
			return opcion;
		} else {
			throw new IllegalArgumentException("Sub-opción no válida. Debe ser 1, 2 o 3.");
		}
	}
	
	public void mostrarEquipos(List<Equipo> equipos) {
		System.out.println("Lista de equipos disponibles:");
	
		for (Equipo equipo : equipos) {
			System.out.println("Código: " + equipo.getCodigo() + ", Descripción: " + equipo.getDescripcion());
		}
	}
	

	// Debe de ingresar la IP y retornar el equipo
	// si no se encuentra, retorna null
	public Equipo ingresarEquipo(List<Equipo> equipos) {
		// Mostrar equipos disponibles
		mostrarEquipos(equipos);
	
		System.out.println("Ingrese el código del equipo que desea seleccionar:");
		String codigo = scanner.next();
	
		// Buscar el equipo por código
		for (Equipo equipo : equipos) {
			if (equipo.getCodigo().equals(codigo)) {
				return equipo;
			}
		}
	
		System.out.println("Equipo no encontrado.");
		return null;
	}
	
	public void mostrarPing(Equipo equipo, boolean estado) {
		System.out.println(equipo.getCodigo() + " Estado: " + (estado ? "Activo" : "Inactivo"));
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

	// Método para cerrar el scanner al finalizar
	public void cerrarScanner() {
		if (scanner != null) {
			scanner.close();
		}
	}
}
