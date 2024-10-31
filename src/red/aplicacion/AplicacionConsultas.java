package red.aplicacion;

import red.gui.datos.consulta.Consultar;
import red.gui.datos.consulta.Ventana;
import red.gui.datos.datos.Manipular;
import red.negocio.Calculo;
import red.negocio.Red;

public class AplicacionConsultas {
    // logica
    private Red red;
    private Calculo calculo;

    //vista
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
        ventana = new Ventana();
        consultar = new Consultar();
        manipular = new Manipular();
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
        
        calculo.cargarDatos(coordinador.listarEquipos(),coordinador.listarConexiones());
        ventana.setVisible(true);

    }
}
