package red.gui.datos;

import java.util.Arrays;
import java.util.List;

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

import red.aplicacion.*;
import red.gui.cargar.CargarDatos;
import red.gui.validaciones.ValidacionesTipoCable;
import red.modelo.Conexion;
import red.modelo.TipoCable;

@SuppressWarnings("unused")
public class ManipularTipoCable {
	JLabel codigoL;
	JLabel descripcionL;
	JLabel velocidadL;

	JTextField codigoT;
	JTextField descripcionT;
	JTextField velocidadT;

	JComboBox<String> codigoCB;

	public void panelAgregar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {

		// Limpia el panel antes de agregar nuevos componentes
		campo.removeAll();

		modificar.setVisible(false);

		borrar.setVisible(false);
		// codigo tipoEquipo
		codigoL = new JLabel();
		codigoL.setText("Codigo:");
		codigoL.setBounds(50, 0, 100, 100);
		campo.add(codigoL);

		codigoT = new JTextField(15);
		codigoT.setBounds(140, 40, 120, 20);
		campo.add(codigoT);

		// campo 2 (descripcion)
		descripcionL = new JLabel();
		descripcionL.setText("Descripcion:");
		descripcionL.setBounds(50, 40, 100, 100);
		campo.add(descripcionL);

		descripcionT = new JTextField(15);
		descripcionT.setBounds(140, 80, 120, 20);
		campo.add(descripcionT);

		// campo 3 (Velocidad)
		velocidadL = new JLabel();
		velocidadL.setText("Marca:");
		velocidadL.setBounds(50, 80, 100, 100);
		campo.add(velocidadL);

		velocidadT = new JTextField(15);
		velocidadT.setBounds(140, 120, 120, 20);
		campo.add(velocidadT);

		panelInferior.add(cargar);
		cargar.setVisible(true);

		cargar.addActionListener(e -> {
			try {
				// Verificar si el equipo cumple con las validaciones
				boolean datosCorrectos = ValidacionesTipoCable.validarAgregarTipoCable(codigoT, descripcionT,
						velocidadT, coordinador);

				if (datosCorrectos) {
					TipoCable tipoCable = CargarDatos.crearTipoCable(codigoT, descripcionT, velocidadT);

					coordinador.insertarTipoCable(tipoCable);

					JOptionPane.showMessageDialog(null, "Equipo agregado exitosamente.");

					// Limpiar los campos
					limpiarCampos(true);
				} else {
					JOptionPane.showMessageDialog(null, "Error: Verifica los datos del equipo.", "Error de validación",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error en el formato de entrada: " + ex.getMessage(),
						"Error de Formato",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		campo.revalidate();
		campo.repaint();

	}

	public void panelModificar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {
		campo.removeAll();

		cargar.setVisible(false);

		borrar.setVisible(false);

		// campo 1 (codigo)
		codigoL = new JLabel();
		codigoL.setText("Codigo:");
		codigoL.setBounds(50, 0, 100, 100);
		campo.add(codigoL);

		codigoCB = new JComboBox<>(coordinador.getManipular().obtenerListaTipoCable());
		codigoCB.setBounds(140, 40, 120, 20);
		codigoCB.setEditable(true);
		campo.add(codigoCB);

		// campo 2 (descripcion)
		descripcionL = new JLabel();
		descripcionL.setText("Descripcion:");
		descripcionL.setBounds(50, 40, 100, 100);
		campo.add(descripcionL);

		descripcionT = new JTextField(15);
		descripcionT.setBounds(140, 80, 120, 20);
		campo.add(descripcionT);

		// campo 3 (Velocidad)
		velocidadL = new JLabel();
		velocidadL.setText("Marca:");
		velocidadL.setBounds(50, 80, 100, 100);
		campo.add(velocidadL);

		velocidadT = new JTextField(15);
		velocidadT.setBounds(140, 120, 120, 20);
		campo.add(velocidadT);

		panelInferior.add(modificar);
		modificar.setVisible(true);

		codigoCB.addActionListener(e -> {
			String codigoSeleccionado = (String) codigoCB.getSelectedItem();
			TipoCable tipoCable = coordinador.getRed().buscarTipoCablePorCodigo(codigoSeleccionado);
			if (tipoCable != null) {
				descripcionT.setText(tipoCable.getDescripcion());
				velocidadT.setText(String.valueOf(tipoCable.getVelocidad()));
			} else {
				JOptionPane.showMessageDialog(null, "El Tipo de cable no existe.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		panelInferior.add(modificar);
		modificar.setVisible(true);

		// Acción al presionar el botón modificar
		modificar.addActionListener(e -> {
			try {
				boolean datosCorrectos = ValidacionesTipoCable.validarModificarTipoCable(descripcionT, velocidadT);
				// Validar y modificar
				if (datosCorrectos) {
					TipoCable tipoCable = CargarDatos.crearTipoCable(
							(JTextField) codigoCB.getEditor().getEditorComponent(),
							descripcionT, velocidadT);
					// Modificar el tipo de cable
					coordinador.modificarTipoCable(tipoCable);
					// Modificar las conexiones asociadas al tipo de cable
					modificarConexion(coordinador, tipoCable);
					// Limpiar los campos
					limpiarCampos(false);

					JOptionPane.showMessageDialog(null, "Tipo de cable modificado exitosamente.");
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

	}

	public void panelBorrar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {
		campo.removeAll();

		panelInferior.removeAll();

		cargar.setVisible(false);

		modificar.setVisible(false);

		// campo 1 (codigo)
		codigoL = new JLabel();
		codigoL.setText("Codigo:");
		codigoL.setBounds(50, 0, 100, 100);
		campo.add(codigoL);

		codigoCB = new JComboBox<>(coordinador.getManipular().obtenerListaTipoCable());
		codigoT.setBounds(140, 40, 120, 20);
		campo.add(codigoT);

		// campo 2 (descripcion)
		descripcionL = new JLabel();
		descripcionL.setText("Descripcion:");
		descripcionL.setBounds(50, 40, 100, 100);
		campo.add(descripcionL);

		descripcionT = new JTextField(15);
		descripcionT.setBounds(140, 80, 120, 20);
		campo.add(descripcionT);

		// campo 3 (Velocidad)
		velocidadL = new JLabel();
		velocidadL.setText("Marca:");
		velocidadL.setBounds(50, 80, 100, 100);
		campo.add(velocidadL);

		velocidadT = new JTextField(15);
		velocidadT.setBounds(140, 120, 120, 20);
		campo.add(velocidadT);

		// Acción al seleccionar un código
		codigoCB.addActionListener(e -> {
			String codigoSeleccionado = (String) codigoCB.getSelectedItem();
			TipoCable tipoCable = coordinador.getRed().buscarTipoCablePorCodigo(codigoSeleccionado);

			if (tipoCable != null) {
				descripcionT.setText(tipoCable.getDescripcion());
				velocidadT.setText(String.valueOf(tipoCable.getVelocidad())); // Convertir velocidad a String
			} else {
				limpiarCampos(false);
				JOptionPane.showMessageDialog(null, "El tipo de equipo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		// Botón de borrar
		panelInferior.add(borrar);
		borrar.setVisible(true);

		borrar.addActionListener(e -> {
			String codigoSeleccionado = (String) codigoCB.getSelectedItem();
			if (codigoSeleccionado == null || codigoSeleccionado.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Seleccione un codigo valido.", "Error",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			TipoCable tipoCable = coordinador.getRed().buscarTipoCablePorCodigo(codigoSeleccionado);
			if (tipoCable == null) {
				JOptionPane.showMessageDialog(null, "El tipo de equipo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
				limpiarCampos(false);
				return;
			}

			boolean tieneConexiones = coordinador.getRed().tieneConexionesConTipoCable(tipoCable);

			// Verificar si el tipo de cable tiene conexiones asociadas
			if (tieneConexiones) {
				JOptionPane.showMessageDialog(null,
						"No se puede eliminar el tipo de cable porque tiene conexiones asociadas.", "Error",
						JOptionPane.ERROR_MESSAGE);
				limpiarCampos(false);
				return;
			}

			int respuesta = JOptionPane.showConfirmDialog(null,
					"¿Está seguro de que desea eliminar el tipo de equipo: " + codigoSeleccionado + "?",
					"Confirmar Eliminacion", JOptionPane.YES_NO_OPTION);

			if (respuesta == JOptionPane.YES_OPTION) {
				try {
					coordinador.borrarTipoCable(tipoCable);
					JOptionPane.showMessageDialog(null, "El tipo de cable ha sido eliminado con exito.",
							"Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
					limpiarCampos(false);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error al eliminar el tipo de cable: " + ex.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "El tipo de equipo no ha sido borrado", "Eliminacion cancelada",
						JOptionPane.CANCEL_OPTION);
			}
		});

		campo.revalidate();
		campo.repaint();

	}

	public void mostrarTabla(Coordinador coordinador) {
		JFrame ventanaEmergente = new JFrame("Lista de Tipo de Equipos");
		ventanaEmergente.setSize(800, 400);
		ventanaEmergente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		List<TipoCable> listaTipocCables = coordinador.listarTipoCable();
		String[] nombreColumnas = { "Codigo", "Descripcion", "Velocidad" };
		String[][] dato = new String[listaTipocCables.size()][nombreColumnas.length];

		for (int i = 0; i < listaTipocCables.size(); i++) {
			TipoCable tipoCable = listaTipocCables.get(i);
			dato[i][0] = tipoCable.getCodigo();
			dato[i][1] = tipoCable.getDescripcion();
			dato[i][2] = String.valueOf(tipoCable.getVelocidad());
		}
		JTable tabla = new JTable(dato, nombreColumnas);
		JScrollPane scrollPane = new JScrollPane(tabla);
		ventanaEmergente.add(scrollPane);
		ventanaEmergente.setVisible(true);
	}

	/**
	 * Limpia los campos de texto y actualiza la lista de códigos en el combo box.
	 * 
	 * @param codigoT      ComboBox de códigos
	 * @param descripcionT Campo de descripción
	 * @param velocidadT   Campo de velocidad
	 * @param coordinador  Coordinador de la aplicación
	 */
	private void limpiarCampos(boolean esInsertar) {
		// Actualizar lista de códigos en el combo box
		if (esInsertar) {
			codigoT.setText("");
			descripcionT.setText("");
			velocidadT.setText("");
		} else {
			codigoCB.setSelectedIndex(-1);
			descripcionT.setText("");
			velocidadT.setText("");
		}

	}

	// Modifica el tipo de cable de las conexiones asociadas al tipo de cable.
	private void modificarConexion(Coordinador coordinador, TipoCable tipoCable) {
		int respuesta = JOptionPane.showConfirmDialog(null,
				"¿Está seguro de que desea modificar el tipo de cable: " + tipoCable.getCodigo() + "?",
				"Confirmar Modificación", JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			try {
				List<Conexion> conexiones = coordinador.getRed().obtenerConexionPorTipoCable(tipoCable);
				for (Conexion conexion : conexiones) {
					conexion.setTipoCable(tipoCable);
					coordinador.modificarConexion(conexion);
				}
				JOptionPane.showMessageDialog(null, "El tipo de cable ha sido modificado con exito.",
						"Modificación Exitosa", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al modificar el tipo de cable: " + ex.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "El tipo de cable no ha sido modificado.", "Modificación Cancelada",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
