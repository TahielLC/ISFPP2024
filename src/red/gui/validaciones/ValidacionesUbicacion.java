package red.gui.validaciones;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import red.aplicacion.Coordinador;
import red.modelo.Ubicacion;

public class ValidacionesUbicacion {
    /**
     * Valida los campos para dar de ALTA una nueva Ubicacion.
     * Verifica que los campos no estén vacíos y que el CÓDIGO no exista ya.
     */
    public static boolean validarAltaUbicacion(JTextField codigoT,
            JTextField descripcionT, Coordinador coordinador) {

        String codigo = codigoT.getText();
        String descripcion = descripcionT.getText();

        if (codigo == null || codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "El codigo esta vacio o nulo ", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (descripcion == null || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "La descripcion esta vacia o nula ", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Verificación de ALTA: El código NO debe existir
        Ubicacion ubicacion = coordinador.getRed().buscarUbicacionPorCodigo(codigo);
        if (ubicacion != null) {
            JOptionPane.showMessageDialog(null,
                    "La ubicacion con el código " + codigo + " ya existe.", "Error:",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * Valida los campos para MODIFICAR una Ubicacion existente.
     * Solo verifica que los campos a modificar (descripción) no estén vacíos.
     * NO verifica el código, porque se asume que existe.
     */
    public static boolean validarModificarUbicacion(JTextField descripcionT) {

        String descripcion = descripcionT.getText();

        if (descripcion == null || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "La descripcion esta vacia o nula ", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
