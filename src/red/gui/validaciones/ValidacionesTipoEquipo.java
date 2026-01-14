package red.gui.validaciones;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import red.aplicacion.Coordinador;

import red.modelo.TipoEquipo;

public class ValidacionesTipoEquipo {

    public static boolean validarTipoEquipo(JTextField codigoT, JTextField descripcionT, Coordinador coordinador,
            boolean esTipoEquipo) {
        String codigo = codigoT.getText();
        String descripcion = descripcionT.getText();

        if (codigo == null || codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El codigo esta vacio o es nulo",
                    "Error: ", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (esTipoEquipo) {
            TipoEquipo tipoEquipo = coordinador.getRed().buscarTipoEquipoPorCodigo(codigo);
            if (tipoEquipo != null) {
                JOptionPane.showMessageDialog(null, "Este tipo de equipo ya existe",
                        "Error: ", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        if (descripcion == null || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripcion esta vacio o es nulo",
                    "Error: ", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean validarModificarTipoEquipo(JTextField descripcionT) {
        String descripcion = descripcionT.getText();

        if (descripcion == null || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripcion esta vacio o es nulo",
                    "Error: ", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}
