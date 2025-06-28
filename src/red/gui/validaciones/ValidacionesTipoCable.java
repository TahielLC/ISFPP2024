package red.gui.validaciones;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import red.aplicacion.Coordinador;
import red.modelo.TipoCable;

public class ValidacionesTipoCable {

    public static boolean validarAgregarTipoCable(JTextField codigoT, JTextField descripcionT, JTextField velocidadT,
            Coordinador coordinador) {
        String codigo = codigoT.getText();
        String descripcion = descripcionT.getText();
        String velocidad = velocidadT.getText();
        boolean validar = true;

        if (codigo == null || codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El codigo esta vacio o nulo ", "Error: ", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        TipoCable tipoCable = coordinador.getRed().buscarTipoCablePorCodigo(codigo);
        if (tipoCable != null) {
            JOptionPane.showMessageDialog(null, "El tipo de cable ya existe ", "Error: ", JOptionPane.ERROR_MESSAGE);
            validar = false;
            return validar;
        }

        if (descripcion == null || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripcion esta vacio o nulo ", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            validar = false;
            return validar;
        }

        if (velocidad == null || velocidad.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La velocidad esta vacio o nulo ", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            validar = false;
            return validar;
        }
        // Verificar si la velocidad es un numero
        if (!velocidad.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "La velocidad debe ser un numero ", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            validar = false;
            return validar;
        }

        int velocidadInt = Integer.parseInt(velocidad);
        if (velocidadInt <= 0) {
            JOptionPane.showMessageDialog(null, "La velocidad debe ser mayor a 0 ", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            validar = false;
            return validar;
        }
        return validar;
    }

    public static boolean validarModificarTipoCable(JTextField descripcionT, JTextField velocidadT) {
        String descripcion = descripcionT.getText();
        String velocidad = velocidadT.getText();
        boolean validar = true;

        if (descripcion == null || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripcion esta vacio o nulo ", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            validar = false;
            return validar;
        }

        if (velocidad == null || velocidad.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La velocidad esta vacio o nulo ", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            validar = false;
            return validar;
        }
        // Verificar si la velocidad es un numero
        if (!velocidad.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "La velocidad debe ser un numero ", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            validar = false;
            return validar;
        }

        int velocidadInt = Integer.parseInt(velocidad);
        if (velocidadInt <= 0) {
            JOptionPane.showMessageDialog(null, "La velocidad debe ser mayor a 0 ", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            validar = false;
            return validar;
        }

        return validar;
    }
}
