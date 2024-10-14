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

		// Carga las conexiones y les asigna un peso de 1
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
				red.setEdgeWeight(arco, 1); // Asignar peso
			}
		}
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
	 * Verifica el estado de los equipos en el camino más corto entre dos IPs
	 * 
	 * @param ip1 IP del primer equipo
	 * @param ip2 IP del segundo equipo
	 * @return una lista con los estados de los equipos en el camino
	 */
	public List<Boolean> pingRango(String ip1, String ip2) {

		Equipo equipo1 = equipos.get(ip1);
		Equipo equipo2 = equipos.get(ip2);

		if (equipo1 == null || equipo2 == null) {
			throw new IllegalArgumentException("Una de las IPs no existe en la red.");
		}

		// Encontrar el camino más corto entre los dos equipos
		List<DefaultWeightedEdge> ruta = tracerouter(equipo1, equipo2);

		List<Boolean> estados = new ArrayList<>();
		estados.add(ping(equipo1.getCodigo())); // Estado del equipo inicial

		// Verificar el estado de los equipos en la ruta
		for (DefaultWeightedEdge edge : ruta) {
			Equipo source = red.getEdgeSource(edge);
			Equipo target = red.getEdgeTarget(edge);
			estados.add(ping(source.getCodigo()));
			estados.add(ping(target.getCodigo()));
		}

		estados.add(ping(equipo2.getCodigo())); // Estado del equipo final
		return estados;
	}

	/**
	 * Recorremos todos los equipos de la red para obtener el estado actual de cada
	 * equipo
	 * 
	 * @return un mapa con cada equipo y su estado
	 */
	public Map<Equipo, Boolean> mapaEstadoEquipos() {
		Map<Equipo, Boolean> estadoEquipos = new TreeMap<>();

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
