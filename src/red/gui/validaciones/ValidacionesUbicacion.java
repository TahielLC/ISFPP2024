package red.gui.validaciones;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import red.aplicacion.Coordinador;
import red.modelo.Ubicacion;

public class ValidacionesUbicacion {
    public static boolean validarUbicacion(JTextField codigoT,
            JTextField descripcionT, Coordinador coordinador, boolean esUbicacion) {

        String codigo = codigoT.getText();
        String descripcion = descripcionT.getText();

        /* System.out.println("codigo: " + codigo); */
        if (codigo == null || codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "El codigo esta vacio o nulo ", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (descripcion == null || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "La descripcion esta vacio o nulo ", "Error: ",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        Ubicacion ubicacion = coordinador.getRed().buscarUbicacionPorCodigo(codigo);
        // Si esta trabajando con una ubicacion
        if (esUbicacion) {
            if (ubicacion != null) {
                JOptionPane.showMessageDialog(null,
                        "La ubicacion ya existe ", "Error:",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
}
