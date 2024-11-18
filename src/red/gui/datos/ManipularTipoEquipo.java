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

import red.aplicacion.Coordinador;

import red.modelo.TipoEquipo;

public class ManipularTipoEquipo {

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

		panelInferior.add(cargar);
		cargar.setVisible(true);

		cargar.addActionListener(e -> {
			try {
				// Recuperar los datos y crear el objeto TipoEquipo
				TipoEquipo tipoEquipo = cargarTipoEquipo(codigoT, descripcionT);

				// Verificar si el equipo cumple con las validaciones
				if (validarAgregar(tipoEquipo)) {
					coordinador.insertarTipoEquipo(tipoEquipo);
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

	public TipoEquipo cargarTipoEquipo(JTextField codigoT, JTextField descripcionT) {
		// Obtener los valores ingresados
		String codigo = codigoT.getText() != null ? codigoT.getText().trim() : "";
		String descripcion = descripcionT.getText() != null ? descripcionT.getText().trim() : "";

		// Crear y devolver el objeto TipoEquipo con los valores obtenidos
		return new TipoEquipo(codigo, descripcion);
	}

	private boolean validarAgregar(TipoEquipo tipoEquipo) {
		// Lista de codigos válidos
		List<String> codigosValidos = Arrays.asList("AP", "CAM", "COM", "IMP", "RJ", "NAS", "NVR", "RT", "SW");

		// Validar que el codigo no sea nulo, esté en mayusculas y sea un codigo valido
		String codigo = tipoEquipo.getCodigo();
		if (codigo == null || codigo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "El codigo no puede estar vacio o nulo.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!codigosValidos.contains(codigo)) {
			JOptionPane.showMessageDialog(null, "Codigo invalido. Debe ser uno de: " + codigosValidos, "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// Validar que la descripcion no sea nula ni vacia
		String descripcion = tipoEquipo.getDescripcion();
		if (descripcion == null || descripcion.isEmpty()) {
			JOptionPane.showMessageDialog(null, "La descripcion no puede estar vacia.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true; // Si pasa todas las validaciones, el equipo es valido
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

		codigoT.addActionListener(e -> {
			String codigoSeleccionado = (String) codigoT.getSelectedItem();
			TipoEquipo tipoEquipo = coordinador.getRed().buscarTipoEquipoPorCodigo(codigoSeleccionado);
			// Verificar si se encontró el equipo
			if (tipoEquipo != null) {

				descripcionT.setText("");
				descripcionT.setText(tipoEquipo.getDescripcion());

			} else {
				JOptionPane.showMessageDialog(null, "El Tipo equipo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		panelInferior.add(modificar);
		modificar.setVisible(true);

		modificar.addActionListener(e -> {
			try {
				TipoEquipo tipoEquipo;
				String codigo = (String) codigoT.getSelectedItem();
				String descripcion = descripcionT.getText();

				tipoEquipo = new TipoEquipo(codigo, descripcion);

				if (validarModificar(tipoEquipo)) {
					coordinador.modificarTipoEquipo(tipoEquipo); // recibe un Tipo Equipo
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error en el formato de entrada: " + ex.getMessage(),
						"Error de Formato",
						JOptionPane.ERROR_MESSAGE);

			}
		});

	}

	private boolean validarModificar(TipoEquipo tipoEquipo) {

		// Lista de códigos válidos
		List<String> codigosValidos = Arrays.asList("AP", "CAM", "COM", "IMP", "RJ", "NAS", "NVR", "RT", "SW");

		// Validar que el código no sea nulo, esté en mayúsculas y esté en la lista de
		// códigos válidos
		if (tipoEquipo.getCodigo() == null || tipoEquipo.getCodigo().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El código no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!codigosValidos.contains(tipoEquipo.getCodigo())) {
			JOptionPane.showMessageDialog(null, "Código inválido. Debe ser uno de: " + codigosValidos, "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// Validar que la descripción no sea nula ni vacía
		if (tipoEquipo.getDescripcion() == null || tipoEquipo.getDescripcion().isEmpty()) {
			JOptionPane.showMessageDialog(null, "La descripción no puede estar vacía.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	public void panelBorrar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {
		campo.removeAll();

		// Ocultar botones irrelevantes
		cargar.setVisible(false);
		modificar.setVisible(false);

		// Etiqueta y combo box para seleccionar código
		JLabel codigoL = new JLabel("Código:");
		codigoL.setBounds(50, 0, 100, 100);
		campo.add(codigoL);

		JComboBox<String> codigoT = new JComboBox<>(coordinador.getManipular().obtenerListaCodigoTipoEquipo());
		codigoT.setBounds(140, 40, 120, 20);
		campo.add(codigoT);

		// Etiqueta y campo para descripción
		JLabel descripcionL = new JLabel("Descripción:");
		descripcionL.setBounds(50, 40, 100, 100);
		campo.add(descripcionL);

		JTextField descripcionT = new JTextField(15);
		descripcionT.setBounds(140, 80, 120, 20);
		descripcionT.setEditable(false); // Solo lectura
		campo.add(descripcionT);

		// Acción al seleccionar un código
		codigoT.addActionListener(e -> {
			String codigoSeleccionado = (String) codigoT.getSelectedItem();
			TipoEquipo tipoEquipo = coordinador.getRed().buscarTipoEquipoPorCodigo(codigoSeleccionado);

			if (tipoEquipo != null) {
				descripcionT.setText(tipoEquipo.getDescripcion());
			} else {
				descripcionT.setText("");
				JOptionPane.showMessageDialog(null, "El tipo de equipo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		// Botón de borrar
		panelInferior.add(borrar);
		borrar.setVisible(true);

		borrar.addActionListener(e -> {
			String codigoSeleccionado = (String) codigoT.getSelectedItem();
			if (codigoSeleccionado == null || codigoSeleccionado.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Seleccione un código válido.", "Error",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			TipoEquipo tipoEquipo = coordinador.getRed().buscarTipoEquipoPorCodigo(codigoSeleccionado);
			if (tipoEquipo == null) {
				JOptionPane.showMessageDialog(null, "El tipo de equipo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			int respuesta = JOptionPane.showConfirmDialog(null,
					"¿Está seguro de que desea eliminar el tipo de equipo: " + codigoSeleccionado + "?",
					"Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

			if (respuesta == JOptionPane.YES_OPTION) {
				try {
					coordinador.borrarTipoEquipo(tipoEquipo);
					JOptionPane.showMessageDialog(null, "El tipo de equipo ha sido eliminado con exito.",
							"Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);

					// Actualizar lista de códigos en el combo box
					codigoT.setModel(
							new DefaultComboBoxModel<>(coordinador.getManipular().obtenerListaCodigoTipoEquipo()));
					descripcionT.setText("");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error al eliminar el tipo de equipo: " + ex.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		campo.revalidate();
		campo.repaint();
	}

	private boolean dudaBorrar(TipoEquipo tipo) {
		int confirmacion = JOptionPane.showConfirmDialog(null,
				"¿Está seguro de que desea eliminar este tipo de equipo?",
				"Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
		return confirmacion == JOptionPane.YES_OPTION;
	}

	public void mostrarTabla(JPanel campo, Coordinador coordinador) {
		JFrame ventanaEmergente = new JFrame("Lista de Tipo de Equipos");
		ventanaEmergente.setSize(800, 400);
		ventanaEmergente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		List<TipoEquipo> listaTipoEquipos = coordinador.listarTipoEquipo();
		String[] nombreColumnas = { "Codigo", "Descripcion" };
		String[][] dato = new String[listaTipoEquipos.size()][nombreColumnas.length];

		for (int i = 0; i < listaTipoEquipos.size(); i++) {
			TipoEquipo tipoEquipo = listaTipoEquipos.get(i);
			dato[i][0] = tipoEquipo.getCodigo();
			dato[i][1] = tipoEquipo.getDescripcion();

		}
		JTable tabla = new JTable(dato, nombreColumnas);
		JScrollPane scrollPane = new JScrollPane(tabla);
		ventanaEmergente.add(scrollPane);
		ventanaEmergente.setVisible(true);
	}
}
