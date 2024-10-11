package redUni.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import redUni.aplicacion.Coordinador;
import redUni.modelo.Conexion;
import redUni.modelo.Equipo;


public class Calculo {
	
	private Coordinador coordinador;
	private Graph<Equipo, Conexion> red;
	private Map<String,Equipo> equipos;
	
	// Constructor
	public Calculo() {
		equipos = new TreeMap<String, Equipo>();
		red = new DefaultUndirectedWeightedGraph<>(Conexion.class);	
	}
	
	/**
	 * Método que nos permite cargar los equipos y sus conexiones
	 * en el grafo y en el mapa
	 * @param tiene como primer parametro una lista de equipo
	 * @param tiene como segundo parametro una lista de conexiones
	 */
	public void cargarDatos(List<Equipo> equip, List<Conexion> conn) {	
		
		// Carga los equipos
		for(Equipo e : equip) {
			equipos.put(e.getCodigo(), e);
			red.addVertex(e); //Agrega cada equipo como un vertice en el grafo
		}
		
		// Carga las conexiones
		for(Conexion c : conn) {
			red.addEdge(c.getEquipo1(), c.getEquipo2(), c);
		}
	}
	
	/**
	 * 
	 * @param ip
	 * @return
	 */
	public boolean ping(String ip) {
		Equipo equipo = equipos.get(ip);
		if(equipo == null) {
			throw new IllegalArgumentException("El equipo "+ ip +" no existe o no esta conectado en la red");
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
		List<Conexion> ruta = tracerouter(equipo1, equipo2);
		
		List<Boolean> estados = new ArrayList<>();
		
		estados.add(ping(equipo1.getCodigo()));
		
		for(Conexion c: ruta) {
			estados.add(ping(c.getEquipo1().getCodigo()));
			estados.add(ping(c.getEquipo2().getCodigo()));
		}
		estados.add(ping(equipo2.getCodigo()));
		return estados;
	}
	
	/**
	 * Recorremos todos los equipos de la red para obtener
	 * el estado actual de cada equipo
	 * @return retorna un mapa de equipos con su estado
	 */
	public Map<Equipo, Boolean> mapaEstadoEquipos() {
	    Map<Equipo, Boolean> estadoEquipos = new TreeMap<>();
	    
	    Equipo equipoInicial = red.vertexSet().iterator().next();
	    
	    DepthFirstIterator<Equipo, Conexion> iterator = new DepthFirstIterator<>(red,equipoInicial);
	    // agregamos en el Map el el equipo con su estado
	    while (iterator.hasNext()) {
	        Equipo equipo = iterator.next();
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
	public List<Conexion> tracerouter(Equipo equipo1, Equipo equipo2){
		//Instanciamos al algoritmo de Dijkstra para el grafo red
	    DijkstraShortestPath<Equipo, Conexion> dijkstraAlg = new DijkstraShortestPath<>(red);

	    // Encuentra el camino más corto entre equipo1 y equipo2
	    GraphPath<Equipo, Conexion> camino = dijkstraAlg.getPath(equipo1, equipo2);
	    // Si no hay camino, devuelve una lista vacia
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
