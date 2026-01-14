package red.gui.validaciones;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import red.aplicacion.Coordinador;
import red.modelo.TipoPuerto;

public class ValidacionesTipoPuerto {

    public static String validarTipoPuerto(JTextField codigoT, JTextField descripcionT,
            JTextField velocidadT, Coordinador coordinador, boolean esPuerto) {

        String codigo = codigoT.getText();
        String descripcion = descripcionT.getText();
        String vel = velocidadT.getText();

        if (codigo == null || codigo.isEmpty()) {
            return "El código está vacío o nulo";
        }

        if (esPuerto) {
            TipoPuerto tipoPuerto = coordinador.getRed().buscarTipoPuertoPorCodigo(codigo);
            if (tipoPuerto != null) {
                return "El puerto ya existe";
            }
        }

        if (descripcion == null || descripcion.isEmpty()) {
            return "La descripción está vacía o nula";
        }

        if (!esNumerico(vel)) {
            return "La velocidad debe ser un número";
        }

        try {
            int velocidad = Integer.parseInt(vel);
            if (velocidad < 0) {
                return "La velocidad no puede ser negativa";
            }
        } catch (NumberFormatException e) {
            return "La velocidad no es válida (fuera de rango)";
        }

        return null;
    }

    private static boolean esNumerico(String velString) {
        return velString != null && velString.matches("-?[0-9]+");
    }

    public static String validarModificarTipoPuerto(JTextField tfDescripcion, JTextField tfVelocidad) {
        String descripcion = tfDescripcion.getText();
        String velocidad = tfVelocidad.getText();

        if (descripcion == null || descripcion.isEmpty()) {
            return "La descripción está vacía o nula";
        }

        if (velocidad == null || velocidad.isEmpty()) {
            return "La velocidad está vacía o nula";
        }

        if (!esNumerico(velocidad)) {
            return "La velocidad debe ser un número";
        }

        int velocidadInt = Integer.parseInt(velocidad);
        if (velocidadInt <= 0) {
            return "La velocidad debe ser mayor a 0";
        }

        return null;
    }
}
