package red.gui.datos;

import java.util.Arrays;
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

import red.aplicacion.Coordinador;
import red.gui.cargar.CargarDatos;
import red.gui.validaciones.ValidacionesTipoEquipo;
import red.modelo.Equipo;
import red.modelo.TipoEquipo;

@SuppressWarnings("unused")
public class ManipularTipoEquipo {
	JLabel codigoL;
	JLabel descripcionL;

	JTextField codigoT;
	JTextField descripcionT;

	JComboBox<String> codigoCB;

	public void panelAgregar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {

		// Limpia el panel antes de agregar nuevos componentes
		campo.removeAll();

		panelInferior.removeAll();

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

		panelInferior.add(cargar);
		cargar.setVisible(true);

		cargar.addActionListener(e -> {
			try {

				boolean esCorrecto = ValidacionesTipoEquipo.validarTipoEquipo(codigoT, descripcionT, coordinador,
						true);
				// Verificar si el equipo cumple con las validaciones
				if (esCorrecto) {
					TipoEquipo tipoEquipo = CargarDatos.crearTipoEquipo(codigoT, descripcionT);
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

	public void panelModificar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {
		campo.removeAll();

		panelInferior.removeAll();

		cargar.setVisible(false);

		borrar.setVisible(false);

		// campo 1 (codigo)
		codigoL = new JLabel();
		codigoL.setText("Codigo:");
		codigoL.setBounds(50, 0, 100, 100);
		campo.add(codigoL);

		codigoCB = new JComboBox<>(coordinador.getManipular().obtenerListaCodigo());
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

		codigoCB.addActionListener(e -> {
			String codigoSeleccionado = (String) codigoCB.getSelectedItem();
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

				boolean esCorrecto = ValidacionesTipoEquipo.validarModificarTipoEquipo(descripcionT);
				if (esCorrecto) {
					String codigoTipoEquipo = (String) codigoCB.getSelectedItem();

					TipoEquipo tipoEquipo = coordinador.getRed().buscarTipoEquipoPorCodigo(codigoTipoEquipo);
					// Modifica el tipo de equipo
					coordinador.modificarTipoEquipo(tipoEquipo);
					// Modificamos los equipos que tengan el tipo de equipo asociado
					modificarEquipos(coordinador, tipoEquipo);
					// Limpiamos los campos
					limpiarCampos(false);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error en el formato de entrada: " + ex.getMessage(),
						"Error de Formato",
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

		codigoCB = new JComboBox<>(coordinador.getManipular().obtenerListaCodigoTipoEquipo());
		codigoCB.setBounds(140, 40, 120, 20);
		campo.add(codigoCB);

		// campo 2 (descripcion)
		descripcionL = new JLabel();
		descripcionL.setText("Descripcion:");
		descripcionL.setBounds(50, 40, 100, 100);
		campo.add(descripcionL);

		descripcionT = new JTextField(15);
		descripcionT.setBounds(140, 80, 120, 20);
		campo.add(descripcionT);

		codigoCB.addActionListener(e -> {
			String codigoSeleccionado = (String) codigoCB.getSelectedItem();
			TipoEquipo tipoEquipo = coordinador.getRed().buscarTipoEquipoPorCodigo(codigoSeleccionado);
			// Verificar si se encontró el equipo
			if (tipoEquipo != null) {
				descripcionT.setText("");
				descripcionT.setText(tipoEquipo.getDescripcion());

			} else {
				JOptionPane.showMessageDialog(null, "El equipo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		panelInferior.add(borrar);
		borrar.setVisible(true);

		borrar.addActionListener(e -> {
			try {
				String codigo = (String) codigoCB.getSelectedItem();
				TipoEquipo tipoEquipo = coordinador.getRed().buscarTipoEquipoPorCodigo(codigo);
				List<Equipo> equipos = coordinador.getRed().obtenerEquiposPorTipoEquipos(tipoEquipo);
				if (equipos.size() > 0) {
					JOptionPane.showMessageDialog(null,
							"No se puede eliminar el tipo de equipo porque tiene conexiones asociadas. Elimina las conexiones o modifique el tipo de equipo.",
							"Error al borrar",
							JOptionPane.ERROR_MESSAGE);
					limpiarCampos(false);
					return;
				} else {
					int respuesta = JOptionPane.showConfirmDialog(null,
							"¿Está seguro de que desea eliminar el tipo de equipo: " + codigo + "?",
							"Confirmar eliminacion", JOptionPane.YES_NO_OPTION);

					if (respuesta == JOptionPane.YES_NO_OPTION) {
						coordinador.borrarTipoEquipo(tipoEquipo);
						limpiarCampos(false);
					} else {
						JOptionPane.showMessageDialog(null, "El tipo de equipo no ha sido eliminado.",
								"Eliminacion Cancelada",
								JOptionPane.INFORMATION_MESSAGE);
						limpiarCampos(false);
					}
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

	private void modificarEquipos(Coordinador coordinador, TipoEquipo tipoEquipo) {
		int respuesta = JOptionPane.showConfirmDialog(null,
				"¿Está seguro de que desea modificar el tipo de equipo: " + tipoEquipo.getCodigo() + "?",
				"Confirmar Modificacion", JOptionPane.YES_NO_OPTION);

		if (respuesta == JOptionPane.YES_OPTION) {
			try {
				List<Equipo> equipos = coordinador.getRed().obtenerEquiposPorTipoEquipos(tipoEquipo);
				for (Equipo equipo : equipos) {
					if (equipo.getTipoEquipo().equals(tipoEquipo)) {
						equipo.setTipoEquipo(tipoEquipo);
					}
					coordinador.modificarEquipo(equipo);
				}
				JOptionPane.showMessageDialog(null, "El tipo de equipo ha sido modificado con exito.",
						"Modificacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al modificar el tipo de equipo: " + ex.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "El tipo de equipo no ha sido modificado.", "Modificacion Cancelada",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void limpiarCampos(boolean esInsertar) {
		if (esInsertar) {
			codigoT.setText("");
			descripcionT.setText("");
		} else {
			codigoCB.setSelectedIndex(-1);
			descripcionT.setText("");
		}
	}

	public void mostrarTabla(Coordinador coordinador) {
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
