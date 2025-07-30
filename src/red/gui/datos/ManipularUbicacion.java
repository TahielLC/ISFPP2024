package red.gui.datos;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import red.aplicacion.Coordinador;
import red.gui.cargar.CargaDeUbicaciones;
import red.gui.cargar.CargarDatos;
import red.gui.validaciones.ValidacionesUbicacion;
import red.modelo.Equipo;
import red.modelo.Ubicacion;

public class ManipularUbicacion {
    private JLabel lbCodigoUbicacion;
    private JLabel lbDescripcionUbicacion;

    private JTextField tfCodUbicacion;
    private JTextField tfDescUbicacion;
    private JComboBox<String> cbCodigoUbicacion;

    public void panelAgregar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
            Coordinador coordinador) {
        campo.removeAll();
        modificar.setVisible(false);
        borrar.setVisible(false);
        panelInferior.removeAll();

        // Primer JLabel y JTextField
        lbCodigoUbicacion = new JLabel("Codigo:");
        lbCodigoUbicacion.setBounds(50, 20, 150, 20); // Ancho suficiente para el texto
        campo.add(lbCodigoUbicacion);

        tfCodUbicacion = new JTextField();
        tfCodUbicacion.setBounds(150, 20, 120, 20); // Alineado con el Label
        campo.add(tfCodUbicacion);

        // Segundo JLabel y JTextField
        lbDescripcionUbicacion = new JLabel("Descripcion:");
        lbDescripcionUbicacion.setBounds(50, 60, 150, 20); // Ancho suficiente para el texto
        campo.add(lbDescripcionUbicacion);

        tfDescUbicacion = new JTextField();
        tfDescUbicacion.setBounds(180, 60, 120, 20); // Alineado con el Label
        campo.add(tfDescUbicacion);

        panelInferior.add(cargar);
        cargar.setVisible(true);

        cargar.addActionListener(e -> {

            boolean datosCorrectos = ValidacionesUbicacion.validarUbicacion(tfCodUbicacion, tfDescUbicacion,
                    coordinador, true);
            if (datosCorrectos) {
                Ubicacion ubicacion = CargarDatos.cargarUbicacion(tfCodUbicacion, tfDescUbicacion);
                coordinador.insertarUbicacion(ubicacion);
                JOptionPane.showMessageDialog(null, "Ubicacion agregado exitosamente.", "Exito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error: Verifica los datos de la ubicacion.", "Error de validación",
                        JOptionPane.ERROR_MESSAGE);
            }
            // Limpiar campos
            limpiarCampos(true);
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

        lbCodigoUbicacion = new JLabel("codigo: ");
        lbCodigoUbicacion.setBounds(50, 20, 150, 20);
        campo.add(lbCodigoUbicacion);

        String[] ubicaciones = CargaDeUbicaciones.codigosUbicaciones(coordinador);
        cbCodigoUbicacion = new JComboBox<>(ubicaciones);
        cbCodigoUbicacion.setBounds(100, 20, 150, 20);
        campo.add(cbCodigoUbicacion);

        lbDescripcionUbicacion = new JLabel("Descripcion:");
        lbDescripcionUbicacion.setBounds(50, 60, 150, 20);
        campo.add(lbDescripcionUbicacion);

        tfDescUbicacion = new JTextField();
        tfDescUbicacion.setBounds(150, 60, 150, 20);
        campo.add(tfDescUbicacion);

        cbCodigoUbicacion.addActionListener(e -> {
            String ubicacion = (String) cbCodigoUbicacion.getSelectedItem();
            System.out.println("codigo ubicación: " + ubicacion);
            if (ubicacion == null || ubicacion.isEmpty()) {
                return; // No hacer nada si no hay una ubicación seleccionada
            }
            Ubicacion ubicacionSeleccionado = coordinador.getRed().buscarUbicacionPorCodigo(ubicacion);
            System.out.println("Datos recibido del metodo de Red: "+ ubicacionSeleccionado.getCodigo() + ";" + ubicacionSeleccionado.getDescripcion());
            if (ubicacionSeleccionado != null) {
                tfDescUbicacion.setText(ubicacionSeleccionado.getDescripcion());
            }
        });
        panelInferior.add(modificar);
        modificar.setVisible(true);

        modificar.addActionListener(e -> {
            JTextField codigoUbicacion = new JTextField();
            codigoUbicacion.setText((String) cbCodigoUbicacion.getSelectedItem());
            // Corregir metodo validarUbicacion
            boolean datosCorrectos = ValidacionesUbicacion.validarUbicacion(codigoUbicacion, tfDescUbicacion,
                    coordinador, false);
            if (datosCorrectos) {
                Ubicacion ubicacion = CargarDatos.cargarUbicacion(
                        (JTextField) cbCodigoUbicacion.getEditor().getEditorComponent(), tfDescUbicacion);
                
                boolean  tieneEquiposConUbicacion = coordinador.getRed().tieneEquiposConUbicacion(ubicacion);
                if(tieneEquiposConUbicacion){
                    JOptionPane.showMessageDialog(null, "Error al modificar: Hay equipos que tienen esta ubicación", "Error",
						JOptionPane.ERROR_MESSAGE);
					// Limpiamos los campos
					limpiarCampos(false);
                } else {
                    coordinador.modificarUbicacion(ubicacion);
                    JOptionPane.showMessageDialog(null, "ubicación modificada exitosamente.");
                    // Limpiar campos
                    limpiarCampos(false);
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Error: Verifica los datos de la ubicacion.",
                        "Error de validacion", JOptionPane.ERROR_MESSAGE);
            }
        });
        campo.revalidate();
        campo.repaint();
    }

    public void panelBorrar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
            Coordinador coordinador) {
        campo.removeAll();
        cargar.setVisible(false);
        modificar.setVisible(false);
        panelInferior.removeAll();

        JLabel lbCodigoUbicacion = new JLabel("codigo: ");
        lbCodigoUbicacion.setBounds(50, 20, 150, 20);
        campo.add(lbCodigoUbicacion);

        String[] ubicaciones = CargaDeUbicaciones.codigosUbicaciones(coordinador);
        JComboBox<String> cbCodigoUbicacion = new JComboBox<>(ubicaciones);
        cbCodigoUbicacion.setBounds(100, 20, 150, 20);
        campo.add(cbCodigoUbicacion);

        JLabel lbDescripcionUbicacion = new JLabel("Descripcion:");
        lbDescripcionUbicacion.setBounds(50, 60, 150, 20);
        campo.add(lbDescripcionUbicacion);

        JTextField tfDescricionUbicacion = new JTextField();
        tfDescricionUbicacion.setBounds(150, 60, 150, 20);
        campo.add(tfDescricionUbicacion);

        cbCodigoUbicacion.addActionListener(e -> {
            String ubicacion = (String) cbCodigoUbicacion.getSelectedItem();
            Ubicacion ubicacionSeleccionado = coordinador.getRed().buscarUbicacionPorCodigo(ubicacion);

            tfDescricionUbicacion.setText("");
            tfDescricionUbicacion.setText(ubicacionSeleccionado.getDescripcion());
        });
        panelInferior.add(borrar);
        borrar.setVisible(true);

        borrar.addActionListener(e -> {
            String codigoUbicacion = (String) cbCodigoUbicacion.getSelectedItem();
            Ubicacion ubicacion = coordinador.getRed().buscarUbicacionPorCodigo(codigoUbicacion);

            List<Equipo> equiposAsociados = coordinador.getRed().buscarEquipoPorUbicacion(ubicacion);

            if (!equiposAsociados.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se puede borrar la ubicacion: Hay Equipos asociados a la ubicación", "Error",
                        JOptionPane.ERROR_MESSAGE);
                    // Limpiar campos
                    limpiarCampos(false);           
            } else {
                coordinador.borrarUbicacion(ubicacion);
                JOptionPane.showMessageDialog(null, "Ubicación borrada exitosamente.", "Exito",
                        JOptionPane.INFORMATION_MESSAGE);
                // Limpiar campos
                limpiarCampos(false);
            }
        });
        campo.revalidate();
        campo.repaint();
    }

    public void mostrarTabla(Coordinador coordinador) {
        JFrame ventanaEmergente = new JFrame("Lista de Ubicaciones");
        ventanaEmergente.setSize(800, 400);
        ventanaEmergente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        List<Ubicacion> listaUbicaciones = coordinador.listarUbicaciones();
        String[] columnas = { "Código", "Descipción" };
        Object[][] datos = new Object[listaUbicaciones.size()][2];
        for (int i = 0; i < listaUbicaciones.size(); i++) {
            datos[i][0] = listaUbicaciones.get(i).getCodigo();
            datos[i][1] = listaUbicaciones.get(i).getDescripcion();
        }
        DefaultTableModel tablaNoEditable = new DefaultTableModel(datos, columnas){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        
        JTable tabla = new JTable(tablaNoEditable);
        JScrollPane scrollPane = new JScrollPane(tabla);
        ventanaEmergente.add(scrollPane);
        ventanaEmergente.setVisible(true);
    }

    private void limpiarCampos(boolean esInsertar) {
        if (esInsertar) {
            tfCodUbicacion.setText("");
            tfDescUbicacion.setText("");
        } else {
            cbCodigoUbicacion.setSelectedIndex(0);
            tfDescUbicacion.setText("");
        }

    }
}
