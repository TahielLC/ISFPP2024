package interfaz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

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

	public void mostrarPingRango(List<Boolean> estados, List<DefaultWeightedEdge> conexiones) {
	    
		//System.out.println("tamaño estados: " + estados.size() + " tamaño conexiones: " + conexiones.size());
	    Graph<Equipo, DefaultWeightedEdge> grafo = coordinador.ObtenerGrafo();
	    
	    // Usamos una list para obtener equipos únicos
	    List<Equipo> equipos = new ArrayList<>();

	    // Agregamos el primer equipo manualmente (source de la primera conexión)
	    if (!conexiones.isEmpty()) {
	        Equipo equipoInicial = grafo.getEdgeSource(conexiones.get(0));
	        equipos.add(equipoInicial);
	    }

	    // Recorremos las conexiones para obtener los equipos de cada conexión
	    for (DefaultWeightedEdge conexion : conexiones) {
	        Equipo equipoTarget = grafo.getEdgeTarget(conexion);
	        equipos.add(equipoTarget);
	    }
	    //for(Equipo e: equipos) {
	    //	System.out.println("Equipo: " + e.getCodigo() + " - " + e.getDescripcion());
	    //}
	   
	    //System.out.println("tamaño estados: " + estados.size() + " tamaño conexiones: " + equipos.size());
	    
	    // Ahora comparamos la cantidad de equipos únicos con la cantidad de estados
	    if (estados.size() != equipos.size()) {
	        throw new IllegalArgumentException("La cantidad de estados no coincide con la cantidad de equipos.");
	    }

	    // Mostrar el estado de cada equipo (en el orden de las conexiones)
	    int i = 0;
	    for (Equipo equipo : equipos) {
	        System.out.println(equipo.getCodigo() + " - " 
	                         + equipo.getDescripcion() + " - "
	                         + equipo.getUbicacion()
	                         + " - Estado: " + (estados.get(i) ? "Activo" : "Inactivo"));
	        i++;
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

	public void resultadoTracerouter(List<DefaultWeightedEdge> conexiones) {
		Graph<Equipo, DefaultWeightedEdge> grafo = coordinador.ObtenerGrafo();
		if (conexiones.isEmpty()) {
	        System.out.println("No se encontraron conexiones entre los equipos.");
	        return;
	    }
		System.out.println("Ruta del traceroute:");
	    for (DefaultWeightedEdge c : conexiones) {
	        Equipo equipo1 = grafo.getEdgeSource(c); // Obtener el equipo origen
	        Equipo equipo2 = grafo.getEdgeTarget(c); // Obtener el equipo destino

	        // Mostrar la conexión
	        System.out.println(equipo1.getCodigo() + " (" + equipo1.getDescripcion() + ") -- "
	                         + equipo2.getCodigo() + " (" + equipo2.getDescripcion() + ")");
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
