package red.aplicacion;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import red.gui.consulta.Consultar;
import red.gui.consulta.Ventana;
import red.gui.datos.Manipular;
import red.negocio.Calculo;
import red.negocio.Red;

public class AplicacionConsultas {
    // parametos de ventana
    private static final int ANCHO_INICIAL = 900;
    private static final int ALTO_INICIAL = 500;

    private int anchoActual = ANCHO_INICIAL;
    private int altoActual = ALTO_INICIAL;
    // logica
    private Red red;
    private Calculo calculo;

    // vista
    private Ventana ventana;
    private Consultar consultar;
    private Manipular manipular;
    // controlador
    private Coordinador coordinador;

    public static void main(String[] args) {
        AplicacionConsultas miAplicacion = new AplicacionConsultas();
        miAplicacion.iniciar(); // iniciar aca
    }

    private void iniciar() {
        System.out.println("Aplicación iniciada y coordinador inicializado.");

        // Se instacia la clase
        red = Red.getRed();
        calculo = new Calculo();
        coordinador = new Coordinador();
        ventana = new Ventana(anchoActual, altoActual);
        consultar = new Consultar(anchoActual, altoActual);
        manipular = new Manipular(anchoActual, altoActual);
        // establecer relaciones entre clases

        calculo.setCoordinador(coordinador);
        ventana.setCoordinador(coordinador);
        manipular.setCoordinador(coordinador);
        consultar.setCoordinador(coordinador);

        // se establecen relaciones con la clase coordinador
        coordinador.setRed(red);
        coordinador.setCalculo(calculo);
        coordinador.setVentana(ventana);
        coordinador.setManipular(manipular);
        coordinador.setConsultar(consultar);

        calculo.cargarDatos(coordinador.listarEquipos(), coordinador.listarConexiones());

        // Agregar ComponentListener para capturar cambios de tamaño en cada ventana
        agregarListenerDeTamaño(ventana);
        agregarListenerDeTamaño(consultar);
        agregarListenerDeTamaño(manipular);

        ventana.setVisible(true);

    }

    private void agregarListenerDeTamaño(JFrame ventana) {
        ventana.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                anchoActual = ventana.getWidth();
                altoActual = ventana.getHeight();
            }
        });
    }

    public void cambiarPantalla(JFrame nuevaPantalla) {
        // Aplicar el tamaño actual a la nueva pantalla
        nuevaPantalla.setSize(anchoActual, altoActual);
        nuevaPantalla.setVisible(true);
    }

}
