package negocio;

import java.util.ArrayList;
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

import aplicacion.Coordinador;
import modelo.Conexion;
import modelo.Equipo;

public class Calculo {

	private Coordinador coordinador;
	private Graph<Equipo, DefaultWeightedEdge> red;
	private Map<String, Equipo> equipos;

	// Constructor
	public Calculo() {
		equipos = new TreeMap<>();
		red = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	}

	/**
	 * Método que nos permite cargar los equipos y sus conexiones
	 * en el grafo y en el mapa
	 * 
	 * @param tiene como primer parametro una lista de equipo
	 * @param tiene como segundo parametro una lista de conexiones
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

		for (Conexion c : conn) {
			Equipo equipo1 = c.getEquipo1();
			Equipo equipo2 = c.getEquipo2();

			// Verificar si ambos equipos existen en el grafo
			if (!red.containsVertex(equipo1)) {
				System.out.println("Error: El equipo " + equipo1.getCodigo() + " no está en el grafo.");
				continue; // Evitar intentar agregar una conexión con un equipo faltante
			}

			if (!red.containsVertex(equipo2)) {
				System.out.println("Error: El equipo " + equipo2.getCodigo() + " no está en el grafo.");
				continue; // Evitar intentar agregar una conexión con un equipo faltante
			}

			DefaultWeightedEdge arco = red.addEdge(equipo1, equipo2);
			if (arco != null) {
				red.setEdgeWeight(arco, 1); // Ajustar el peso si es necesario
			}
		}
	}

	/**
	 * 
	 * @param ip
	 * @return
	 */
	public boolean ping(String ip) {
		Equipo equipo = equipos.get(ip);
		if (equipo == null) {
			throw new IllegalArgumentException("El equipo " + ip + " no existe o no está conectado en la red");
		}
		return equipo.getEstado();
	}

	/**
	 * 
	 * @param ip1
	 * @param ip2
	 * @return
	 */
	public List<Boolean> pingRango(String ip1, String ip2) {
		Equipo equipo1 = equipos.get(ip1);
		Equipo equipo2 = equipos.get(ip2);

		if (equipo1 == null || equipo2 == null) {
			throw new IllegalArgumentException("Una de las IPs no existe en la red.");
		}

		List<DefaultWeightedEdge> ruta = tracerouter(equipo1, equipo2);
		List<Boolean> estados = new ArrayList<>();

		estados.add(ping(equipo1.getCodigo()));

		for (DefaultWeightedEdge edge : ruta) {
			Equipo equipoEnRuta1 = red.getEdgeSource(edge);
			Equipo equipoEnRuta2 = red.getEdgeTarget(edge);
			estados.add(ping(equipoEnRuta1.getCodigo()));
			estados.add(ping(equipoEnRuta2.getCodigo()));
		}

		estados.add(ping(equipo2.getCodigo()));
		return estados;
	}

	/**
	 * Recorremos todos los equipos de la red para obtener
	 * el estado actual de cada equipo
	 * 
	 * @return retorna un mapa de equipos con su estado
	 */
	public Map<Equipo, Boolean> mapaEstadoEquipos() {
		Map<Equipo, Boolean> estadoEquipos = new TreeMap<>();

		for (Equipo equipo : red.vertexSet()) {
			Boolean estado = ping(equipo.getCodigo());
			estadoEquipos.put(equipo, estado);
		}
		return estadoEquipos;
	}

	/**
	 * 
	 * @param equipo1
	 * @param equipo2
	 * @return
	 */
	public List<DefaultWeightedEdge> tracerouter(Equipo equipo1, Equipo equipo2) {
		// Instanciamos al algoritmo de Dijkstra para el grafo red
		DijkstraShortestPath<Equipo, DefaultWeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(red);

		// Encuentra el camino más corto entre equipo1 y equipo2
		GraphPath<Equipo, DefaultWeightedEdge> camino = dijkstraAlg.getPath(equipo1, equipo2);

		// Si no hay camino, devuelve una lista vacía
		if (camino == null) {
			return new ArrayList<>();
		}

		// Devuelve la lista de conexiones en el camino más corto
		return camino.getEdgeList();
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}
}
