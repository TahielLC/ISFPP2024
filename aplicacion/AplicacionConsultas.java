package aplicacion;

import java.util.List;
import java.util.Map;

import org.jgrapht.graph.DefaultWeightedEdge;

import interfaz.Interfaz;
import modelo.Conexion;
import modelo.Equipo;
import negocio.Calculo;
import negocio.Red;

public class AplicacionConsultas {
    // logica
    private Red red;
    private Calculo calculo;
    // vista
    private Interfaz interfaz;
    // controlador
    private Coordinador coordinador;

    public static void main(String[] args) {
        AplicacionConsultas miAplicacion = new AplicacionConsultas();
        miAplicacion.iniciar(); // iniciar aca
        miAplicacion.consultar(); // consultar
    }

    private void iniciar() {
        // Se instacia la clase
        red = Red.getRed();
        calculo = new Calculo();
        interfaz = new Interfaz();
        coordinador = new Coordinador();
        // establecer relaciones entre clases

        calculo.setCoordinador(coordinador);
        interfaz.setCoordinador(coordinador);

        // se establecen relaciones con la clase coordinador

        coordinador.setRed(red);
        coordinador.setCalculo(calculo);
        calculo.cargarDatos(coordinador.listarEquipos(),
                coordinador.listarConexiones());

    }

    private void consultar() {
        // 1. Obtener la opcion del usuario

        int opcion = interfaz.opcion();

        // 2. Obtener la lista de equipos
        List<Equipo> equipos = coordinador.listarEquipos();

        if (opcion == 1) { // Opcion para hacer un ping
            int subOpcion = interfaz.subOpcion(); // Sub-opcion d1el ping

            if (subOpcion == 1) {
                // Ping a un solo equipo
                Equipo equipo = interfaz.ingresarEquipo(equipos);
                if (equipo != null) {
                    boolean estado = calculo.ping(equipo.getCodigo()); // Usar el metodo ping de Calculo
                    interfaz.mostrarPing(equipo, estado);
                } else {
                    System.out.println("Equipo no encontrado.");
                }

            } else if (subOpcion == 2) {
                // Ping a un rango de equipos
                Equipo equipo1 = interfaz.ingresarEquipo(equipos);
                Equipo equipo2 = interfaz.ingresarEquipo(equipos);

            } else if (subOpcion == 3) {
                // Mostrar el estado de todos los equipos
                Map<Equipo, Boolean> estadoEquipos = calculo.mapaEstadoEquipos(); // Usar mapaEstadoEquipos de Calculo
                interfaz.mostrarMapaEstadoEquipos(estadoEquipos);
            }

        } else if (opcion == 2) { // Opcion para hacer un traceroute
            // Ingresar los equipos de origen y destino
            Equipo origen = interfaz.ingresarEquipo(equipos);
            Equipo destino = interfaz.ingresarEquipo(equipos);

            if (origen != null && destino != null) {
                // Calcular el traceroute entre los equipos
                // List<Conexion> conexiones = calculo.tracerouter(origen, destino); // Usar
                // tracerouter de Calculo
                // interfaz.resultadoTracerouter(conexiones);
            } else {
                System.out.println("Uno o ambos equipos no fueron encontrados.");
            }
        } else {
            System.out.println("Opción no válida.");
        }
    }

}
