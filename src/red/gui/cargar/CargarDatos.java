package red.gui.cargar;

import javax.swing.JTextField;

import red.modelo.TipoCable;
import red.modelo.TipoEquipo;
import red.modelo.TipoPuerto;

public class CargarDatos {

    public static TipoCable crearTipoCable(JTextField codigoT, JTextField descripcionT,
            JTextField velocidadT) {

        // Obtenemos los valores de los campos de texto
        String codigo = codigoT.getText();
        String descripcion = descripcionT.getText();
        int velocidad = Integer.parseInt(velocidadT.getText());

        return new TipoCable(codigo, descripcion, velocidad);
    }

    public static TipoPuerto crearTipoPuerto(JTextField codigoT, JTextField descripcionT,
            JTextField velocidadT) {

        // Obtenemos los valores de los campos de texto
        String codigo = codigoT.getText();
        String descripcion = descripcionT.getText();
        int velocidad = Integer.parseInt(velocidadT.getText());

        return new TipoPuerto(codigo, descripcion, velocidad);
    }

    public static TipoEquipo crearTipoEquipo(JTextField codigoT, JTextField descripcionT) {

        // Obtenemos los valores de los campos de texto
        String codigo = codigoT.getText();
        String descripcion = descripcionT.getText();

        return new TipoEquipo(codigo, descripcion);
    }
}
