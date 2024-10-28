package negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import aplicacion.Coordinador;
import modelo.Conexion;
import modelo.Equipo;

public class Calculo {

	private Coordinador coordinador;
	private Graph<Equipo, DefaultWeightedEdge> red;
	private Map<String, Equipo> equipos;

	// Constructor
	public Calculo() {
		equipos = new TreeMap<String, Equipo>();
		red = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	}

	/**
	 * Método que nos permite cargar los equipos y sus conexiones
	 * en el grafo y en el mapa
	 * 
	 * @param equip lista de equipos
	 * @param conn  lista de conexiones
	 */
	public void cargarDatos(List<Equipo> equip, List<Conexion> conn) {
		Set<Equipo> equipoSet = new HashSet<>(equip);
		if (equipoSet.size() != equip.size()) {
			System.out.println("Advertencia: Hay equipos duplicados en la lista.");
		}

		// Carga los equipos
		for (Equipo e : equip) {
			equipos.put(e.getCodigo(), e);
			red.addVertex(e); // Agrega cada equipo como un vértice en el grafo
		}
		
		// Carga las conexiones y les asigna su peso
		for (Conexion c : conn) {
			Equipo equipo1 = c.getEquipo1();
			Equipo equipo2 = c.getEquipo2();

			// Verificar si ambos equipos existen en el grafo
			if (!red.containsVertex(equipo1)) {
				System.out.println("Error: El equipo " + equipo1.getCodigo() + " no está en el grafo.");
				continue;
			}
			if (!red.containsVertex(equipo2)) {
				System.out.println("Error: El equipo " + equipo2.getCodigo() + " no está en el grafo.");
				continue;
			}

			DefaultWeightedEdge arco = red.addEdge(equipo1, equipo2);
			if (arco != null) {
				double peso = c.obtenerVelocidadMinima();
			    red.setEdgeWeight(arco, 1/peso);
			    //System.out.println("Arista entre " + equipo1.getCodigo() + " y " + equipo2.getCodigo() + " con peso: " + peso);
			}
		}
	}
	/*
	 * Metodo para obtener el grafo
	 * Este metodo me permitira en la clase interfaz mostrar los datos de la red
	 */
	public Graph<Equipo, DefaultWeightedEdge> getgrafoRed() {
	    return red;
	}
	/**
	 * Método para verificar si un equipo está activo mediante su IP
	 * 
	 * @param ip la IP del equipo
	 * @return true si el equipo está activo, false si no lo está
	 */
	public boolean ping(String ip) {
		Equipo equipo = equipos.get(ip);
		if (equipo == null) {
			throw new IllegalArgumentException("El equipo " + ip + " no existe o no está conectado en la red.");
		}
		return equipo.getEstado(); // Devuelve el estado del equipo
	}

	/**
	 * Verifica el estado de los equipos en el camino más corto entre dos Equipos
	 * 
	 * @param equipo1 primer equipo
	 * @param equipo2 segundo equipo
	 * @return una lista con los estados de los equipos en el camino
	 */
	public List<Boolean> pingRango(Equipo equipo1, Equipo equipo2) {

	    if (equipo1 == null || equipo2 == null) {
	        throw new IllegalArgumentException("Una de las IPs no existe en la red.");
	    }

	    // Encontrar el camino más corto entre los dos equipos
	    List<DefaultWeightedEdge> ruta = tracerouter(equipo1, equipo2);

	    List<Boolean> estados = new ArrayList<>();
	    estados.add(ping(equipo1.getCodigo())); // Estado del equipo inicial

	    // Verificar el estado de los equipos en la ruta
	    for (DefaultWeightedEdge edge : ruta) {
	        Equipo target = red.getEdgeTarget(edge); // Solo pinguea el equipo de destino
	        estados.add(ping(target.getCodigo()));   // Añadir el estado del equipo de destino
	    }

	    return estados; // Devuelve los estados de todos los equipos en la ruta
	}

	/**
	 * Recorremos todos los equipos de la red para obtener el estado actual de cada
	 * equipo
	 * 
	 * @return un mapa con cada equipo y su estado
	 */
	public Map<Equipo, Boolean> mapaEstadoEquipos() {
		Map<Equipo, Boolean> estadoEquipos = new HashMap<>();

		// Usamos un iterador en profundidad
		DepthFirstIterator<Equipo, DefaultWeightedEdge> iterator = new DepthFirstIterator<>(red);
		while (iterator.hasNext()) {
			Equipo equipo = iterator.next();
			Boolean estado = ping(equipo.getCodigo());
			estadoEquipos.put(equipo, estado);
		}
		return estadoEquipos;
	}

	/**
	 * Encuentra el camino más corto entre dos equipos usando el algoritmo de
	 * Dijkstra
	 * 
	 * @param equipo1 equipo de inicio
	 * @param equipo2 equipo de destino
	 * @return lista de aristas que forman el camino más corto
	 */
	public List<DefaultWeightedEdge> tracerouter(Equipo equipo1, Equipo equipo2) {
		// Instanciamos el algoritmo de Dijkstra
		DijkstraShortestPath<Equipo, DefaultWeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(red);

		// Encuentra el camino más corto entre equipo1 y equipo2
		GraphPath<Equipo, DefaultWeightedEdge> camino = dijkstraAlg.getPath(equipo1, equipo2);

		// Si no hay camino, devuelve una lista vacía
		if (camino == null) {
			return new ArrayList<>();
		}
		return camino.getEdgeList(); // Devuelve la lista de conexiones en el camino más corto
	}

	// Setter para el coordinador
	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}
}
