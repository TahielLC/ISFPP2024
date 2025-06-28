package red.gui.validaciones;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import red.aplicacion.Coordinador;
import red.modelo.TipoPuerto;

public class ValidacionesTipoPuerto {

    public static boolean validarTipoPuerto(JTextField codigoT, JTextField descripcionT,
            JTextField velocidadT, Coordinador coordinador, boolean esPuerto) {

        String codigo = codigoT.getText();
        String descripcion = descripcionT.getText();
        String vel = velocidadT.getText();

        if (codigo == null || codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "El codigo esta vacio o nulo ", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (esPuerto) {
            TipoPuerto tipoPuerto = coordinador.getRed().buscarTipoPuertoPorCodigo(codigo);
            if (tipoPuerto != null) {
                JOptionPane.showMessageDialog(null, "El puerto ya existe", "Error:", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        if (descripcion == null || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "La descripcion esta vacio o nulo ", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!esNumerico(vel)) {
            JOptionPane.showMessageDialog(null,
                    "La velocidad es nulo o NO es numerico", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int velocidad = Integer.parseInt(vel);
            if (velocidad < 0) {
                JOptionPane.showMessageDialog(null,
                        "La velocidad no puede ser negativa", "Error: ",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "La velocidad no es válida (fuera de rango)", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private static boolean esNumerico(String velString) {
        return velString != null && velString.matches("-?[0-9]+");
    }

    public static boolean validarModificarTipoPuerto(JTextField tfDescripcion, JTextField tfVelocidad) {
        String descripcion = tfDescripcion.getText();
        String velocidad = tfVelocidad.getText();

        if (descripcion == null || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripcion esta vacio o nulo ", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (velocidad == null || velocidad.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La velocidad esta vacio o nulo ", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Verificar si la velocidad es un numero
        if (!esNumerico(velocidad)) {
            JOptionPane.showMessageDialog(null,
                    "La velocidad es nulo o NO es numerico", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        int velocidadInt = Integer.parseInt(velocidad);
        if (velocidadInt <= 0) {
            JOptionPane.showMessageDialog(null, "La velocidad debe ser mayor a 0 ", "Error: ",
                    JOptionPane.ERROR_MESSAGE);

            return false;
        }

        return true;
    }
}
