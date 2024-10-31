package red.gui.datos.consulta;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Dimension;

import java.util.HashMap;

import com.mxgraph.view.mxGraph;

import red.modelo.Equipo;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.Map;

public class GraphIlustrator extends JFrame {
    private final int DEFAULT_WIDTH = 600;    
    private final int DEFAULT_HEIGHT = 450;

    public GraphIlustrator(Graph<Equipo, DefaultWeightedEdge> red) {
        //this.windowParamInit();
        this.drawGraph(red);
    }

    /*
    public void windowParamInit() {
        this.setTitle("Grafo de la Red");
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    */

    /**
     * Recibe el grafo JGraphT de la red y genera una ilustracion del mismo, la cual 
     * se agre en una JPanel
     * @param red
    */
    public void drawGraph(Graph<Equipo, DefaultWeightedEdge> red) {
        // Crea un nuevo grafo
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        // Comenzar a construir el grafo
        graph.getModel().beginUpdate();
        try {
            // Guarda los vertices de JgraphX utilizando como llave  su homonimo de JgraphT
            Map<Equipo, Object> vertexMap = new HashMap<>();

            // Recorre los vertices del JGraphT
            for(Equipo equipo : red.vertexSet()) {
                // si el equipo esta activo
                if(equipo.getEstado()) {
                    // agrega un vertice verde en JGraphX
                    Object vertice = graph.insertVertex(parent, null, equipo.getCodigo(), 20, 20, 80, 50, "strokeColor=black;fillColor=green;fontStyle=1;fontSize=13");
                    // Guarda en vertice en vertex map para luego poder hacer las conexiones
                    vertexMap.put(equipo, vertice);
                }
                else {
                    // agrega un vertice rojo en JGraphX
                    Object vertice = graph.insertVertex(parent, null, equipo.getCodigo(), 20, 20, 80, 50, "strokeColor=black;fillColor=red;fontStyle=1;fontSize=13");
                    // Guarda en vertice en vertex map para luego poder hacer las conexiones
                    vertexMap.put(equipo, vertice);
                }
            }

            // Recorre las aristas del JGraphT
            for(DefaultWeightedEdge edge : red.edgeSet()) {
                // obtengo el origen y destino de la conexion de JGraphT
                Equipo source = red.getEdgeSource(edge);
                Equipo target = red.getEdgeTarget(edge);

                // obtengo los homonimos de fuente y origen correspondientes a JGraphX
                Object src = vertexMap.get(source);
                Object tgt = vertexMap.get(target);

                // conecto los vertices de JGraphX
                graph.insertEdge(parent, null, "texto arista", src, tgt, "strokeWidth=2;fontStyle=1;fontSize=13");
            }
        }
        finally {
            // Termina de construir el grafo
            graph.getModel().endUpdate();
        }

        // Instancio el layout que voy a usar
        mxIGraphLayout layout = new mxHierarchicalLayout(graph);
        layout.execute(parent); // aplico el layout en parent

        // Crear un componente para mostrar el grafo
        mxGraphComponent graphComponent = new mxGraphComponent(graph);

        // evito que se hagan conexiones desde el grafico
        graphComponent.setConnectable(false);

        // Cambio el color del fondo a negro
        graphComponent.getViewport().setOpaque(true);
        graphComponent.getViewport().setBackground(Color.GRAY);
        
        this.getContentPane().add(graphComponent);
    }
}