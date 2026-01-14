package red.gui.cargar;

import java.util.List;

import javax.swing.JTextField;

import red.aplicacion.Coordinador;
import red.modelo.Ubicacion;

public class CargaDeUbicaciones {

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
