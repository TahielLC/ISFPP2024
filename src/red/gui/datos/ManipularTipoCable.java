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
import red.modelo.TipoCable;


public class ManipularTipoCable {

	public void panelAgregar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {

		// Limpia el panel antes de agregar nuevos componentes
		campo.removeAll();

		modificar.setVisible(false);

		borrar.setVisible(false);
		// codigo tipoEquipo
		JLabel codigoL = new JLabel();
		codigoL.setText("Codigo:");
		codigoL.setBounds(50, 0, 100, 100);
		campo.add(codigoL);

		JTextField codigoT = new JTextField(15);
		codigoT.setBounds(140, 40, 120, 20);
		campo.add(codigoT);

		// campo 2 (descripcion)
		JLabel descripcionL = new JLabel();
		descripcionL.setText("Descripcion:");
		descripcionL.setBounds(50, 40, 100, 100);
		campo.add(descripcionL);

		JTextField descripcionT = new JTextField(15);
		descripcionT.setBounds(140, 80, 120, 20);
		campo.add(descripcionT);

		// campo 3 (Velocidad)
		JLabel velocidadL = new JLabel();
		velocidadL.setText("Marca:");
		velocidadL.setBounds(50, 80, 100, 100);
		campo.add(velocidadL);

		JTextField velocidadT = new JTextField(15);
		velocidadT.setBounds(140, 120, 120, 20);
		campo.add(velocidadT);

		panelInferior.add(cargar);
		cargar.setVisible(true);

		cargar.addActionListener(e -> {
			try {
				// Recuperar los datos y crear el objeto TipoEquipo
				TipoCable tipoCable = cargarTipoCable(codigoT, descripcionT, velocidadT);

				// Verificar si el equipo cumple con las validaciones
				if (validarAgregar(tipoCable)) {
					coordinador.insertarTipoCable(tipoCable);
					JOptionPane.showMessageDialog(null, "Equipo agregado exitosamente.");
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

	public TipoCable cargarTipoCable(JTextField codigoT, JTextField descripcionT, JTextField velocidadT) {
		// Obtener los valores ingresados
		String codigo = codigoT.getText().trim();
		String descripcion = descripcionT.getText().trim();

		int velocidad;
		try {
			velocidad = Integer.parseInt(velocidadT.getText().trim());
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Velocidad debe ser un número entero.");
		}

		return new TipoCable(codigo, descripcion, velocidad);
	}

	private static final List<String> CODIGOS_VALIDOS = Arrays.asList("C5", "C5E", "C6", "FOM");
	private static final List<Integer> VELOCIDADES_VALIDAS = Arrays.asList(100, 1000, 10000);

	private boolean validarAgregar(TipoCable tipoCable) {

		// Lista de codigos válidos
		String codigo = tipoCable.getCodigo();
		String descripcion = tipoCable.getDescripcion();
		int velocidad = tipoCable.getVelocidad();

		// Validaciones
		if (codigo == null || codigo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "El código no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!CODIGOS_VALIDOS.contains(codigo)) {
			JOptionPane.showMessageDialog(null, "Código inválido. Debe ser uno de: " + CODIGOS_VALIDOS, "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (descripcion == null || descripcion.isEmpty()) {
			JOptionPane.showMessageDialog(null, "La descripción no puede estar vacía.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!VELOCIDADES_VALIDAS.contains(velocidad)) {
			JOptionPane.showMessageDialog(null, "La velocidad no es válida. Valores permitidos: " + VELOCIDADES_VALIDAS,
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public void panelModificar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {
		campo.removeAll();

		cargar.setVisible(false);

		borrar.setVisible(false);

		// campo 1 (codigo)
		JLabel codigoL = new JLabel();
		codigoL.setText("Codigo:");
		codigoL.setBounds(50, 0, 100, 100);
		campo.add(codigoL);

		JComboBox<String> codigoT = new JComboBox<>(coordinador.getManipular().obtenerListaCodigo());
		codigoT.setBounds(140, 40, 120, 20);
		codigoT.setEditable(true);
		campo.add(codigoT);

		// campo 2 (descripcion)
		JLabel descripcionL = new JLabel();
		descripcionL.setText("Descripcion:");
		descripcionL.setBounds(50, 40, 100, 100);
		campo.add(descripcionL);

		JTextField descripcionT = new JTextField(15);
		descripcionT.setBounds(140, 80, 120, 20);
		campo.add(descripcionT);

		// campo 3 (Velocidad)
		JLabel velocidadL = new JLabel();
		velocidadL.setText("Marca:");
		velocidadL.setBounds(50, 80, 100, 100);
		campo.add(velocidadL);

		JTextField velocidadT = new JTextField(15);
		velocidadT.setBounds(140, 120, 120, 20);
		campo.add(velocidadT);

		codigoT.addActionListener(e -> {
			String codigoSeleccionado = (String) codigoT.getSelectedItem();
			TipoCable tipoCable = coordinador.getRed().buscarTipoCablePorCodigo(codigoSeleccionado);
			// Verificar si se encontró el equipo
			if (tipoCable != null) {

				descripcionT.setText("");
				descripcionT.setText(tipoCable.getDescripcion());

			} else {
				JOptionPane.showMessageDialog(null, "El Tipo equipo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		panelInferior.add(modificar);
		modificar.setVisible(true);

		codigoT.addActionListener(e -> {
			String codigoSeleccionado = (String) codigoT.getSelectedItem();
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
				String codigo = (String) codigoT.getSelectedItem();
				String descripcion = descripcionT.getText().trim();
				String velocidadTexto = velocidadT.getText().trim();

				// Validar campos vacíos
				if (codigo == null || codigo.isEmpty() || descripcion.isEmpty() || velocidadTexto.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Validar que la velocidad sea un número
				int velocidad;
				try {
					velocidad = Integer.parseInt(velocidadTexto);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "La velocidad debe ser un numero entero.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Crear el objeto TipoCable
				TipoCable tipoCable = new TipoCable(codigo, descripcion, velocidad);

				// Validar y modificar
				if (validarModificar(tipoCable)) {
					coordinador.modificarTipoCable(tipoCable);
					JOptionPane.showMessageDialog(null, "Tipo de cable modificado exitosamente.");
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

	}

	private boolean validarModificar(TipoCable tipoCable) {
		// Reutilizamos las mismas validaciones de agregar
		return validarAgregar(tipoCable);
	}

	public void panelBorrar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {
		campo.removeAll();

		cargar.setVisible(false);

		modificar.setVisible(false);

		// campo 1 (codigo)
		JLabel codigoL = new JLabel();
		codigoL.setText("Codigo:");
		codigoL.setBounds(50, 0, 100, 100);
		campo.add(codigoL);

		JComboBox<String> codigoT = new JComboBox<>(coordinador.getManipular().obtenerListaCodigoTipoEquipo());
		codigoT.setBounds(140, 40, 120, 20);
		campo.add(codigoT);

		// campo 2 (descripcion)
		JLabel descripcionL = new JLabel();
		descripcionL.setText("Descripcion:");
		descripcionL.setBounds(50, 40, 100, 100);
		campo.add(descripcionL);

		JTextField descripcionT = new JTextField(15);
		descripcionT.setBounds(140, 80, 120, 20);
		campo.add(descripcionT);

		// campo 3 (Velocidad)
		JLabel velocidadL = new JLabel();
		velocidadL.setText("Marca:");
		velocidadL.setBounds(50, 80, 100, 100);
		campo.add(velocidadL);

		JTextField velocidadT = new JTextField(15);
		velocidadT.setBounds(140, 120, 120, 20);
		campo.add(velocidadT);

		// Acción al seleccionar un código
		codigoT.addActionListener(e -> {
			String codigoSeleccionado = (String) codigoT.getSelectedItem();
			TipoCable tipoCable = coordinador.getRed().buscarTipoCablePorCodigo(codigoSeleccionado);

			if (tipoCable != null) {
				descripcionT.setText(tipoCable.getDescripcion());
				velocidadT.setText(String.valueOf(tipoCable.getVelocidad())); // Convertir velocidad a String
			} else {
				descripcionT.setText("");
				velocidadT.setText("");
				JOptionPane.showMessageDialog(null, "El tipo de equipo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		// Botón de borrar
		panelInferior.add(borrar);
		borrar.setVisible(true);

		borrar.addActionListener(e -> {
			String codigoSeleccionado = (String) codigoT.getSelectedItem();
			if (codigoSeleccionado == null || codigoSeleccionado.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Seleccione un codigo valido.", "Error",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			TipoCable tipoCable = coordinador.getRed().buscarTipoCablePorCodigo(codigoSeleccionado);
			if (tipoCable == null) {
				JOptionPane.showMessageDialog(null, "El tipo de equipo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			int respuesta = JOptionPane.showConfirmDialog(null,
					"¿Está seguro de que desea eliminar el tipo de equipo: " + codigoSeleccionado + "?",
					"Confirmar Eliminacion", JOptionPane.YES_NO_OPTION);

			if (respuesta == JOptionPane.YES_OPTION) {
				try {
					coordinador.borrarTipoCable(tipoCable);
					JOptionPane.showMessageDialog(null, "El tipo de equipo ha sido eliminado con exito.",
							"Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
					// Actualizar lista de códigos en el combo box
					codigoT.setModel(
							new DefaultComboBoxModel<>(coordinador.getManipular().obtenerListaCodigoTipoEquipo()));
					descripcionT.setText("");
					velocidadT.setText("");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error al eliminar el tipo de equipo: " + ex.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
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


}
