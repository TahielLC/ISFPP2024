package red.gui.datos;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

import red.aplicacion.Coordinador;
import red.gui.cargar.CargarDatos;
import red.gui.validaciones.ValidacionesTipoEquipo;
import red.gui.validaciones.ValidacionesTipoPuerto;
import red.modelo.Conexion;
import red.modelo.TipoCable;
import red.modelo.TipoPuerto;

@SuppressWarnings("unused")
public class ManipularTipoPuerto {
    JLabel lbCodigoNuevoPuerto;
    JLabel lbDescripcion;
    JLabel lbVelocidadPuerto;

    private JTextField tfCodNuevoPuerto;
    private JTextField tfDescripcion;
    private JTextField tfVelPuerto;
    private JComboBox<String> cbCodNuevoPuerto;

    public void panelAgregar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
            Coordinador coordinador) {
        campo.removeAll();

        modificar.setVisible(false);
        borrar.setVisible(false);

        // Etiqueta y campo para "Código del Puerto"
        lbCodigoNuevoPuerto = new JLabel("Código Puerto:");
        lbCodigoNuevoPuerto.setBounds(30, 20, 120, 25);
        campo.add(lbCodigoNuevoPuerto);

        tfCodNuevoPuerto = new JTextField();
        tfCodNuevoPuerto.setBounds(160, 20, 150, 25);
        campo.add(tfCodNuevoPuerto);

        // Etiqueta y campo para "Descripción"
        lbDescripcion = new JLabel("Descripción:");
        lbDescripcion.setBounds(30, 60, 120, 25);
        campo.add(lbDescripcion);

        tfDescripcion = new JTextField();
        tfDescripcion.setBounds(160, 60, 150, 25);
        campo.add(tfDescripcion);

        // Etiqueta y campo para "Velocidad del Puerto"
        lbVelocidadPuerto = new JLabel("Velocidad del puerto:");
        lbVelocidadPuerto.setBounds(30, 100, 150, 25);
        campo.add(lbVelocidadPuerto);

        tfVelPuerto = new JTextField();
        tfVelPuerto.setBounds(160, 100, 150, 25);
        campo.add(tfVelPuerto);

        panelInferior.add(cargar);
        cargar.setVisible(true);
        cargar.addActionListener(e -> {
            try {
                boolean esCorrecto = ValidacionesTipoPuerto.validarTipoPuerto(tfCodNuevoPuerto, tfDescripcion,
                        tfVelPuerto,
                        coordinador, true);
                if (esCorrecto) {
                    TipoPuerto tipoPuerto = CargarDatos.crearTipoPuerto(tfCodNuevoPuerto, tfDescripcion, tfVelPuerto);
                    coordinador.insertarTipoPuerto(tipoPuerto);
                    JOptionPane.showMessageDialog(null, "El puerto se ha agregado correctamente", "Exito",
                            JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos(coordinador, true);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al insertar el puerto", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        campo.revalidate();
        campo.repaint();
    }

    public void panelModificar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
            Coordinador coordinador) {
        campo.removeAll();
        panelInferior.removeAll();
        cargar.setVisible(false);
        borrar.setVisible(false);

        // Etiqueta y campo para "Codigo del Puerto"
        lbCodigoNuevoPuerto = new JLabel("Código Puerto:");
        lbCodigoNuevoPuerto.setBounds(30, 20, 120, 25);
        campo.add(lbCodigoNuevoPuerto);

        String[] listaTipoPuerto = coordinador.getManipular().obtenerTipoPuertos();
        cbCodNuevoPuerto = new JComboBox<>(listaTipoPuerto);
        cbCodNuevoPuerto.setBounds(160, 20, 150, 25);
        campo.add(cbCodNuevoPuerto);

        // Etiqueta y campo para "Descripcion"
        lbDescripcion = new JLabel("Descripcion:");
        lbDescripcion.setBounds(30, 60, 120, 25);
        campo.add(lbDescripcion);

        tfDescripcion = new JTextField();
        tfDescripcion.setBounds(160, 60, 150, 25);
        campo.add(tfDescripcion);

        // Etiqueta y campo para "Velocidad"
        lbVelocidadPuerto = new JLabel("Velocidad:");
        lbVelocidadPuerto.setBounds(30, 100, 120, 25);
        campo.add(lbVelocidadPuerto);

        tfVelPuerto = new JTextField();
        tfVelPuerto.setBounds(160, 100, 150, 25);
        campo.add(tfVelPuerto);

        // Obtener los datos del puerto seleccionado
        cbCodNuevoPuerto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String codTipoPuerto = (String) cbCodNuevoPuerto.getSelectedItem();
                TipoPuerto tipoPuerto = coordinador.getRed().buscarTipoPuertoPorCodigo(codTipoPuerto);

                if (tipoPuerto != null) {
                    tfDescripcion.setText(tipoPuerto.getDescripcion());
                    tfVelPuerto.setText(String.valueOf(tipoPuerto.getVelocidad()));
                } else {
                    tfDescripcion.setText("");
                    tfVelPuerto.setText("");
                    JOptionPane.showMessageDialog(null, "El puerto no existe", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        panelInferior.add(modificar);
        modificar.setVisible(true);

        modificar.addActionListener(e -> {
            try {
                boolean esCorrecto = ValidacionesTipoPuerto.validarModificarTipoPuerto(tfDescripcion, tfVelPuerto);
                if (esCorrecto) {
                    TipoPuerto tipoPuerto = CargarDatos.crearTipoPuerto(
                            (JTextField) cbCodNuevoPuerto.getEditor().getEditorComponent(), tfDescripcion,
                            tfCodNuevoPuerto);

                    // Modificar el tipo de puerto
                    coordinador.modificarTipoPuerto(tipoPuerto);

                    // Modificar las conexiones asociadas al tipo de puerto
                    modificarConexion(coordinador, tipoPuerto);

                    JOptionPane.showMessageDialog(null, "El puerto se ha modificado correctamente", "Exito",
                            JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos(coordinador, false);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al modificar el puerto", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void panelBorrar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
            Coordinador coordinador) {
        campo.removeAll();
        panelInferior.removeAll();
        cargar.setVisible(false);
        modificar.setVisible(false);

        // Etiqueta y campo para "Codigo del Puerto"
        lbCodigoNuevoPuerto = new JLabel("Código Puerto:");
        lbCodigoNuevoPuerto.setBounds(30, 20, 120, 25);
        campo.add(lbCodigoNuevoPuerto);

        String[] listaTipoPuerto = coordinador.getManipular().obtenerTipoPuertos();
        cbCodNuevoPuerto = new JComboBox<>(listaTipoPuerto);
        cbCodNuevoPuerto.setBounds(160, 20, 150, 25);
        campo.add(cbCodNuevoPuerto);

        // Etiqueta y campo para "Descripcion"
        lbDescripcion = new JLabel("Descripcion:");
        lbDescripcion.setBounds(30, 60, 120, 25);
        campo.add(lbDescripcion);

        tfDescripcion = new JTextField();
        tfDescripcion.setBounds(160, 60, 150, 25);
        campo.add(tfDescripcion);

        // Etiqueta y campo para "Velocidad"
        lbVelocidadPuerto = new JLabel("Velocidad:");
        lbVelocidadPuerto.setBounds(30, 100, 120, 25);
        campo.add(lbVelocidadPuerto);

        tfVelPuerto = new JTextField();
        tfVelPuerto.setBounds(160, 100, 150, 25);
        campo.add(tfVelPuerto);

        // Obtener los datos del puerto seleccionado
        cbCodNuevoPuerto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String codTipoPuerto = (String) cbCodNuevoPuerto.getSelectedItem();
                TipoPuerto tipoPuerto = coordinador.getRed().buscarTipoPuertoPorCodigo(codTipoPuerto);

                if (tipoPuerto != null) {
                    tfDescripcion.setText(tipoPuerto.getDescripcion());
                    tfVelPuerto.setText(String.valueOf(tipoPuerto.getVelocidad()));
                } else {
                    limpiarCampos(coordinador, false);
                    JOptionPane.showMessageDialog(null, "El puerto no existe", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        panelInferior.add(borrar);
        borrar.setVisible(true);

        borrar.addActionListener(e -> {
            try {
                String codigo = (String) cbCodNuevoPuerto.getSelectedItem();
                TipoPuerto tipoPuerto = coordinador.getRed().buscarTipoPuertoPorCodigo(codigo);
                List<Conexion> conexiones = coordinador.getRed().obtenerConexionPorTipoPuerto(tipoPuerto);
                // Si tiene conexiones asociadas, no se puede borrar, caso contrario, procede a
                // eliminar el puerto
                if (conexiones.size() > 0) {
                    JOptionPane.showMessageDialog(null,
                            "No se puede eliminar el tipo de puerto porque tiene conexiones asociadas. Elimina las conexiones o modifique el tipo de puerto.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    limpiarCampos(coordinador, false);
                    return;
                } else {
                    int respuesta = JOptionPane.showConfirmDialog(null,
                            "¿Está seguro de que desea eliminar el tipo de puerto: " + codigo + "?",
                            "Confirmar eliminacion", JOptionPane.YES_NO_OPTION);

                    if (respuesta == JOptionPane.YES_OPTION) {

                        coordinador.borrarTipoPuerto(tipoPuerto);
                        JOptionPane.showMessageDialog(null, "El tipo de puerto ha sido eliminado con exito.", "Exito",
                                JOptionPane.INFORMATION_MESSAGE);

                        limpiarCampos(coordinador, false);
                    } else {
                        JOptionPane.showMessageDialog(null, "El tipo de puerto no ha sido eliminado.",
                                "Eliminacion Cancelada",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al borrar el puerto", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Modifica el tipo de cable de las conexiones asociadas al tipo de cable.
    private void modificarConexion(Coordinador coordinador, TipoPuerto tipoPuerto) {
        int respuesta = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea modificar el tipo de puerto: " + tipoPuerto.getCodigo() + "?",
                "Confirmar Modificacion", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                List<Conexion> conexiones = coordinador.getRed().obtenerConexionPorTipoPuerto(tipoPuerto);
                for (Conexion conexion : conexiones) {
                    if (conexion.getTipoPuerto1().getCodigo().equals(tipoPuerto.getCodigo())) {
                        conexion.setTipoPuerto1(tipoPuerto);
                    } else if (conexion.getTipoPuerto2().getCodigo().equals(tipoPuerto.getCodigo())) {
                        conexion.setTipoPuerto2(tipoPuerto);
                    }
                    coordinador.modificarConexion(conexion);
                }
                JOptionPane.showMessageDialog(null, "El tipo de puerto ha sido modificado con exito.",
                        "Modificacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al modificar el tipo de puerto: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El tipo de puerto no ha sido modificado.", "Modificacion Cancelada",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void mostrarTabla(Coordinador coordinador) {
        JFrame ventanaEmergente = new JFrame("Lista de Tipo de Puertos");
        ventanaEmergente.setSize(800, 400);
        ventanaEmergente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        List<TipoPuerto> tipoPuertos = coordinador.listarTipoPuerto();
        String[] columnas = { "Codigo", "Descripcion", "Velocidad" };
        Object[][] datos = new Object[tipoPuertos.size()][3];

        for (int i = 0; i < tipoPuertos.size(); i++) {
            datos[i][0] = tipoPuertos.get(i).getCodigo();
            datos[i][1] = tipoPuertos.get(i).getDescripcion();
            datos[i][2] = String.valueOf(tipoPuertos.get(i).getVelocidad());
        }
        JTable tabla = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(tabla);
        ventanaEmergente.add(scrollPane);
        ventanaEmergente.setVisible(true);
    }

    // Método para limpiar los campos de los paneles de agregar, modificar y borrar
    private void limpiarCampos(Coordinador coordinador, boolean esInsertar) {
        if (esInsertar) {
            tfCodNuevoPuerto.setText("");
            tfDescripcion.setText("");
            tfVelPuerto.setText("");
        } else {
            cbCodNuevoPuerto.setSelectedIndex(-1);
            tfDescripcion.setText("");
            tfVelPuerto.setText("");
        }
    }
}
