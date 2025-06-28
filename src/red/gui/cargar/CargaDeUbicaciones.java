package red.gui.cargar;

import java.util.List;

import javax.swing.JTextField;

import red.aplicacion.Coordinador;
import red.modelo.Ubicacion;

public class CargaDeUbicaciones {
    public static Ubicacion cargarUbicacion(JTextField codigoT, JTextField descrpcionT) {
        String codigo = codigoT.getText();
        String descripcion = descrpcionT.getText();
        return new Ubicacion(codigo, descripcion);
    }

    /**
     * Devuelve un array de los codigos de las ubicaciones
     * existentes en la red
     * 
     * @param coordinador
     * @return
     */
    public static String[] codigosUbicaciones(Coordinador coordinador) {
        List<Ubicacion> ubicaciones = coordinador.listarUbicaciones();

        String[] codigos = new String[ubicaciones.size()];

        for (int i = 0; i < ubicaciones.size(); i++) {
            codigos[i] = ubicaciones.get(i).getCodigo();
        }
        return codigos;
    }
}
