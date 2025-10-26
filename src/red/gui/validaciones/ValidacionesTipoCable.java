package red.gui.validaciones;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import red.aplicacion.Coordinador;
import red.modelo.TipoCable;

public class ValidacionesTipoCable {

    public static String validarAgregarTipoCable(JTextField codigoT, JTextField descripcionT, JTextField velocidadT,
            Coordinador coordinador) {
        String codigo = codigoT.getText();
        String descripcion = descripcionT.getText();
        String velocidad = velocidadT.getText();

        if (codigo == null || codigo.isEmpty()) {
            return "El código está vacío o nulo";
        }

        TipoCable tipoCable = coordinador.getRed().buscarTipoCablePorCodigo(codigo);
        if (tipoCable != null) {
            return "El tipo de cable ya existe";
        }

        if (descripcion == null || descripcion.isEmpty()) {
            return "La descripción está vacía o nula";
        }

        if (velocidad == null || velocidad.isEmpty()) {
            return "La velocidad está vacía o nula";
        }

        if (!velocidad.matches("[0-9]+")) {
            return "La velocidad debe ser un número";
        }

        int velocidadInt = Integer.parseInt(velocidad);
        if (velocidadInt <= 0) {
            return "La velocidad debe ser mayor a 0";
        }

        return null;
    }

    public static String validarModificarTipoCable(JTextField descripcionT, JTextField velocidadT) {
        String descripcion = descripcionT.getText();
        String velocidad = velocidadT.getText();

        if (descripcion == null || descripcion.isEmpty()) {
            return "La descripción está vacía o nula";
        }

        if (velocidad == null || velocidad.isEmpty()) {
            return "La velocidad está vacía o nula";
        }

        if (!velocidad.matches("[0-9]+")) {
            return "La velocidad debe ser un número";
        }

        int velocidadInt = Integer.parseInt(velocidad);
        if (velocidadInt <= 0) {
            return "La velocidad debe ser mayor a 0";
        }

        return null;
    }
}
