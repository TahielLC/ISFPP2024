package red.gui.datos;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import red.gui.cargar.CargaDeEquipos;
import red.gui.validaciones.ValidacionesEquipo;
import red.modelo.Conexion;
import red.modelo.Equipo;
import red.modelo.TipoEquipo;
import red.modelo.TipoPuerto;
import red.modelo.Ubicacion;

@SuppressWarnings("unused")
public class ManipularEquipo {

	private Manipular manipular;

	public ManipularEquipo() {

	}

	/**
	 * Metodo que me permite agregar un nuevo equipo a la red
	 * 
	 * @param campo
	 * @param panelInferior
	 * @param cargar
	 * @param modificar
	 * @param borrar
	 * @param coordinador
	 */
	public void panelAgregar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {
		// Limpia el panel antes de agregar nuevos componentes
		campo.removeAll();

		modificar.setVisible(false);

		borrar.setVisible(false);

		// campo 1 (codigo)
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

		// campo 3 (marca)
		JLabel marcaL = new JLabel();
		marcaL.setText("Marca:");
		marcaL.setBounds(50, 80, 100, 100);
		campo.add(marcaL);

		JTextField marcaT = new JTextField(15);
		marcaT.setBounds(140, 120, 120, 20);
		campo.add(marcaT);

		// campo 4 (modelo)
		JLabel modeloL = new JLabel();
		modeloL.setText("Modelo:");
		modeloL.setBounds(50, 120, 100, 100);
		campo.add(modeloL);

		JTextField modeloT = new JTextField(15);
		modeloT.setBounds(140, 160, 120, 20);
		campo.add(modeloT);

		// campo 5 (direccion IP)
		JLabel direccionipL = new JLabel();
		direccionipL.setText("Direccion IP (Separados por coma):");
		direccionipL.setBounds(50, 160, 210, 100);
		campo.add(direccionipL);

		JComboBox<String> direccionipCB = new JComboBox<>();
		direccionipCB.setBounds(50, 240, 350, 20);
		campo.add(direccionipCB);

		JButton agregarDireccionIP = new JButton("Agregar Direccion IP");
		agregarDireccionIP.setBounds(400, 240, 150, 20);
		campo.add(agregarDireccionIP);

		agregarDireccionIP.addActionListener(e -> {
			JFrame frame = new JFrame("Agregar Direccion IP");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setLayout(new FlowLayout());

			JTextField direccionIPField = new JTextField(20);
			frame.add(direccionIPField);

			JButton confirmar = new JButton("Confirmar");
			confirmar.addActionListener(e1 -> {
				String direccionIP = direccionIPField.getText();
				if (direccionIP != null && !direccionIP.trim().isEmpty()) {
					direccionipCB.addItem(direccionIP);
					System.out.println("Direccion IP agregada: " + direccionIP); // Debug
				} else {
					System.out.println("Direccion IP vacía o inválida"); // Debug
				}
				frame.dispose();
			});
			frame.add(confirmar);

			JButton cancelar = new JButton("Cancelar");
			cancelar.addActionListener(e1 -> frame.dispose());
			frame.add(cancelar);

			frame.pack();
			frame.setVisible(true);
		});

		// campo 6 (Ubicacion)
		JLabel ubicacionL = new JLabel();
		ubicacionL.setText("Ubicacion (Cada ubicacion como'codigo,descripcion'):");
		ubicacionL.setBounds(50, 220, 310, 100);
		campo.add(ubicacionL);

		JTextField ubicacionT = new JTextField();
		ubicacionT.setBounds(50, 300, 350, 20);
		campo.add(ubicacionT);

		// campo 7 (TipoEquipo)
		JLabel tipoEquipoL = new JLabel();
		tipoEquipoL.setText("Tipo de Equipo (Cada equipo como 'codigo,descripcion'):");
		tipoEquipoL.setBounds(50, 300, 320, 100);
		campo.add(tipoEquipoL);

		JTextField tipoEquipoT = new JTextField(15);
		tipoEquipoT.setBounds(50, 360, 350, 20);
		campo.add(tipoEquipoT);

		// campo 7 (Puertos)
		JLabel puertoL = new JLabel();
		puertoL.setText("Tipo Puerto (Cada puerto como 'codigo,descripcion,velocidad:cantidad'):");
		puertoL.setBounds(50, 360, 410, 100);
		campo.add(puertoL);

		JComboBox<String> puertoCB = new JComboBox<>();
		puertoCB.setBounds(50, 440, 350, 20);
		campo.add(puertoCB);

		JButton agregarPuerto = new JButton("Agregar Puerto");
		agregarPuerto.setBounds(400, 440, 150, 20);
		campo.add(agregarPuerto);

		agregarPuerto.addActionListener(e -> {
			JFrame frame = new JFrame("Agregar Puerto");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setLayout(new FlowLayout());

			JTextField puertoField = new JTextField(20);
			frame.add(puertoField);

			JButton confirmar = new JButton("Confirmar");
			confirmar.addActionListener(e1 -> {
				String puerto = puertoField.getText();
				puertoCB.addItem(puerto);
				frame.dispose();
			});
			frame.add(confirmar);

			JButton cancelar = new JButton("Cancelar");
			cancelar.addActionListener(e1 -> frame.dispose());
			frame.add(cancelar);

			frame.pack();
			frame.setVisible(true);
		});

		JLabel activoL = new JLabel();
		activoL.setText("Estado:");
		activoL.setBounds(50, 480, 140, 20);
		campo.add(activoL);

		String[] estado = { "Activo", "Inactivo" };
		JComboBox<String> activoT = new JComboBox<>(estado);
		activoT.setBounds(120, 480, 120, 20);
		campo.add(activoT);

		panelInferior.add(cargar);
		cargar.setVisible(true);

		cargar.addActionListener(e -> {

			boolean datosCorrectos = ValidacionesEquipo.validarAgregarEquipo(codigoT, descripcionT, marcaT, modeloT,
					direccionipCB, ubicacionT, tipoEquipoT, puertoCB, coordinador);
			// Verificar si el equipo cumple con las validaciones
			if (datosCorrectos) {
				// Recuperar los datos y crear el objeto equipo
				Equipo equipo = CargaDeEquipos.cargarEquipo(codigoT, descripcionT, marcaT, modeloT,
						direccionipCB, ubicacionT, tipoEquipoT, puertoCB, activoT,
						coordinador);
				coordinador.insertarEquipo(equipo);
				JOptionPane.showMessageDialog(null, "Equipo agregado exitosamente.");
			} else {
				JOptionPane.showMessageDialog(null, "Error: Verifica los datos del equipo.", "Error de validación",
						JOptionPane.ERROR_MESSAGE);
			}

		});

		campo.revalidate();
		campo.repaint();
	}

	/**
	 * @param campo
	 * @param panelInferior
	 * @param cargar
	 * @param modificar
	 * @param borrar
	 * @param coordinador
	 */
	public void panelModificar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {
		// Limpia el panel antes de agregar nuevos componentes
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
		campo.add(codigoT);

		// campo 2 (descripcion)
		JLabel descripcionL = new JLabel();
		descripcionL.setText("Descripcion:");
		descripcionL.setBounds(50, 40, 100, 100);
		campo.add(descripcionL);

		JTextField descripcionT = new JTextField(15);
		descripcionT.setBounds(140, 80, 120, 20);
		campo.add(descripcionT);

		// campo 3 (marca)
		JLabel marcaL = new JLabel();
		marcaL.setText("Marca:");
		marcaL.setBounds(50, 80, 100, 100);
		campo.add(marcaL);

		JTextField marcaT = new JTextField(15);
		marcaT.setBounds(140, 120, 120, 20);
		campo.add(marcaT);

		// campo 4 (modelo)
		JLabel modeloL = new JLabel();
		modeloL.setText("Modelo:");
		modeloL.setBounds(50, 120, 100, 100);
		campo.add(modeloL);

		JTextField modeloT = new JTextField(15);
		modeloT.setBounds(140, 160, 120, 20);
		campo.add(modeloT);

		// campo 5 (direccion IP)
		JLabel direccionipL = new JLabel();
		direccionipL.setText("Direccion IP (Separados por coma):");
		direccionipL.setBounds(50, 160, 210, 100);
		campo.add(direccionipL);

		JComboBox<String> direccionipT = new JComboBox<>();
		direccionipT.setBounds(50, 240, 350, 20);
		campo.add(direccionipT);

		JButton editarDireccionesIP = new JButton("Editar Direcciones IP");
		editarDireccionesIP.setBounds(380, 240, 160, 20);
		campo.add(editarDireccionesIP);

		// campo 6 (Ubicacion)
		JLabel ubicacionL = new JLabel();
		ubicacionL.setText("Ubicacion (formato: 'codigo,descripcion'):");
		ubicacionL.setBounds(50, 220, 310, 100);
		campo.add(ubicacionL);

		JTextField ubicacionT = new JTextField();
		ubicacionT.setBounds(50, 300, 350, 20);
		campo.add(ubicacionT);

		// campo 7 (tipoEquipo)
		JLabel tipoEquipoL = new JLabel();
		tipoEquipoL.setText("Tipo de Equipo ( formato: 'codigo,descripcion'):");
		tipoEquipoL.setBounds(50, 300, 320, 100);
		campo.add(tipoEquipoL);

		JTextField tipoEquipoT = new JTextField(15);
		tipoEquipoT.setBounds(50, 360, 350, 20);
		campo.add(tipoEquipoT);

		// campo 8 (Puertos)
		JLabel puertoL = new JLabel();
		puertoL.setText("Tipo Puerto (Cada puerto como 'codigo,descripcion,velocidad:cantidad'):");
		puertoL.setBounds(50, 360, 410, 100);
		campo.add(puertoL);

		JComboBox<String> puertoT = new JComboBox<>();
		puertoT.setBounds(50, 440, 350, 20);
		campo.add(puertoT);

		JButton editarPuerto = new JButton("Editar Puertos");
		editarPuerto.setBounds(380, 440, 160, 20);
		campo.add(editarPuerto);

		codigoT.addActionListener(e -> {
			String codigoSeleccionado = (String) codigoT.getSelectedItem();
			Equipo equipo = coordinador.getRed().buscarEquipoPorCodigo(codigoSeleccionado);
			// Verificar si se encontró el equipo
			if (equipo != null) {
				descripcionT.setText("");
				descripcionT.setText(equipo.getDescripcion());

				marcaT.setText("");
				marcaT.setText(equipo.getMarca());

				modeloT.setText("");
				modeloT.setText(equipo.getModelo());

				direccionipT.removeAllItems();
				for (String ip : equipo.getIPs()) {
					direccionipT.addItem(ip);
				}

				ubicacionT.setText("");
				String cubicacion = equipo.getUbicacion().getCodigo();
				String dubicacion = equipo.getUbicacion().getDescripcion();
				ubicacionT.setText(cubicacion + "," + dubicacion);

				tipoEquipoT.setText("");
				String cTipoEquipo = equipo.getTipoEquipo().getCodigo();
				String dTipoEquipo = equipo.getTipoEquipo().getDescripcion();
				tipoEquipoT.setText(cTipoEquipo + "," + dTipoEquipo);

				puertoT.removeAllItems();
				List<String> puertos = equipo.getPuertos();
				for (String puerto : puertos) {
					puertoT.addItem(puerto);
				}

			} else {
				JOptionPane.showMessageDialog(null, "El equipo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		editarDireccionesIP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Editar Direcciones IP");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setSize(400, 200);
				frame.setLayout(null);
				JLabel label = new JLabel("Ingrese las direcciones IP separadas por coma:");
				label.setBounds(50, 50, 300, 20);
				frame.add(label);

				JTextField textField = new JTextField();
				for (int i = 0; i < direccionipT.getItemCount(); i++) {
					textField.setText(textField.getText() + direccionipT.getItemAt(i) + ",");
				}
				textField.setText(textField.getText().replaceAll(",$", ""));
				textField.setBounds(50, 80, 300, 20);
				frame.add(textField);

				JButton aceptar = new JButton("Aceptar");
				aceptar.setBounds(50, 110, 100, 20);
				frame.add(aceptar);

				JButton borrar = new JButton("Borrar");
				borrar.setBounds(160, 110, 100, 20);
				frame.add(borrar);

				JButton cancelar = new JButton("Cancelar");
				cancelar.setBounds(270, 110, 100, 20);
				frame.add(cancelar);

				JTextField textFieldBorrar = new JTextField();
				textFieldBorrar.setBounds(50, 140, 300, 20);
				frame.add(textFieldBorrar);

				aceptar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String direccionesIP = textField.getText();
						if (direccionesIP != null) {
							direccionipT.removeAllItems();
							String[] ips = direccionesIP.split(",");
							for (String ip : ips) {
								direccionipT.addItem(ip);
							}
						}
						frame.dispose();
					}
				});

				borrar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String ipBorrar = textFieldBorrar.getText();
						String direccionesIP = textField.getText();
						String[] ips = direccionesIP.split(",");
						String nuevaDireccionIP = "";
						for (String ip : ips) {
							if (!ip.equals(ipBorrar)) {
								nuevaDireccionIP += ip + ",";
							}
						}
						nuevaDireccionIP = nuevaDireccionIP.replaceAll(",$", "");
						textField.setText(nuevaDireccionIP);
						direccionipT.removeAllItems();
						String[] ipsNuevas = nuevaDireccionIP.split(",");
						for (String ip : ipsNuevas) {
							direccionipT.addItem(ip);
						}
					}
				});

				cancelar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
					}
				});
				frame.setVisible(true);
			}
		});

		editarPuerto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Editar Puertos");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setSize(400, 200);
				frame.setLayout(null);
				JLabel label = new JLabel("Ingrese los puertos separados por coma:");
				label.setBounds(50, 50, 300, 20);
				frame.add(label);

				JTextField textField = new JTextField();
				for (int i = 0; i < puertoT.getItemCount(); i++) {
					textField.setText(textField.getText() + puertoT.getItemAt(i) + ",");
				}
				textField.setText(textField.getText().replaceAll(",$", ""));
				textField.setBounds(50, 80, 300, 20);
				frame.add(textField);

				JButton aceptar = new JButton("Aceptar");
				aceptar.setBounds(50, 110, 100, 20);
				frame.add(aceptar);

				JButton borrar = new JButton("Borrar");
				borrar.setBounds(160, 110, 100, 20);
				frame.add(borrar);

				JButton cancelar = new JButton("Cancelar");
				cancelar.setBounds(270, 110, 100, 20);
				frame.add(cancelar);

				JTextField textFieldBorrar = new JTextField();
				textFieldBorrar.setBounds(50, 140, 300, 20);
				frame.add(textFieldBorrar);

				aceptar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String puertos = textField.getText();
						if (puertos != null) {
							puertoT.removeAllItems();
							String[] p = puertos.split(",");
							for (String sp : p) {
								puertoT.addItem(sp);
							}
						}
						frame.dispose();
					}
				});

				borrar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String puertoBorrar = textFieldBorrar.getText();
						String puerto = textField.getText();
						String[] puertos = puerto.split(",");
						String nuevopuerto = "";
						for (String p : puertos) {
							if (!p.equals(puertoBorrar)) {
								nuevopuerto += p + ",";
							}
						}
						nuevopuerto = nuevopuerto.replaceAll(",$", "");
						textField.setText(nuevopuerto);
						puertoT.removeAllItems();
						String[] pNuevos = nuevopuerto.split(",");
						for (String p : pNuevos) {
							puertoT.addItem(p);
						}
					}
				});

				cancelar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
					}
				});
				frame.setVisible(true);
			}

		});

		JLabel activoL = new JLabel();
		activoL.setText("Estado:");
		activoL.setBounds(50, 480, 140, 20);
		campo.add(activoL);

		String[] estado = { "Activo", "Inactivo" };
		JComboBox<String> activoT = new JComboBox<>(estado);
		activoT.setBounds(120, 480, 120, 20);
		campo.add(activoT);

		panelInferior.add(modificar);
		modificar.setVisible(true);

		modificar.addActionListener(e -> {
			try {
				boolean modificado = ValidacionesEquipo.validarModificarEquipo(descripcionT, marcaT, modeloT,
						direccionipT, ubicacionT, tipoEquipoT, puertoT, coordinador);
				if (modificado) {
					Equipo equipo = CargaDeEquipos.cargarEquipo(tipoEquipoT, descripcionT, marcaT, modeloT,
							direccionipT, ubicacionT, tipoEquipoT, puertoT, activoT, coordinador);
					coordinador.modificarEquipo(equipo); // recibe un equipo
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error en el formato de entrada: " + ex.getMessage(),
						"Error de Formato",
						JOptionPane.ERROR_MESSAGE);

			}
		});
		direccionipT.removeAllItems();
		puertoT.removeAllItems();

		campo.revalidate();
		campo.repaint();
	}

	/**
	 * @param campo
	 * @param panelInferior
	 * @param cargar
	 * @param modificar
	 * @param borrar
	 * @param coordinador
	 */
	public void panelBorrar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {
		// Limpia el panel antes de agregar nuevos componentes
		campo.removeAll();

		cargar.setVisible(false);

		modificar.setVisible(false);

		// campo 1 (codigo)
		JLabel codigoL = new JLabel();
		codigoL.setText("Codigo:");
		codigoL.setBounds(50, 0, 100, 100);
		campo.add(codigoL);

		JComboBox<String> codigoT = new JComboBox<>(coordinador.getManipular().obtenerListaCodigo());
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

		// campo 3 (marca)
		JLabel marcaL = new JLabel();
		marcaL.setText("Marca:");
		marcaL.setBounds(50, 80, 100, 100);
		campo.add(marcaL);

		JTextField marcaT = new JTextField(15);
		marcaT.setBounds(140, 120, 120, 20);
		campo.add(marcaT);

		// campo 4 (modelo)
		JLabel modeloL = new JLabel();
		modeloL.setText("Modelo:");
		modeloL.setBounds(50, 120, 100, 100);
		campo.add(modeloL);

		JTextField modeloT = new JTextField(15);
		modeloT.setBounds(140, 160, 120, 20);
		campo.add(modeloT);

		// campo 5 (direccion IP)
		JLabel direccionipL = new JLabel();
		direccionipL.setText("Direccion IP (Separados por coma):");
		direccionipL.setBounds(50, 160, 210, 100);
		campo.add(direccionipL);

		JComboBox<String> direccionipT = new JComboBox<>(); // Recibe una lista de direciones IPs
		direccionipT.setBounds(50, 240, 350, 20);
		campo.add(direccionipT);
		// campo 6 (Ubicacion) aqui tendremos un campo para el codigo y otro para la
		// descripcion

		JLabel ubicacionL = new JLabel();
		ubicacionL.setText("Ubicacion (Cada ubicacion como'codigo,descripcion'):");
		ubicacionL.setBounds(50, 220, 310, 100);
		campo.add(ubicacionL);

		JTextField ubicacionT = new JTextField();
		ubicacionT.setBounds(50, 300, 350, 20);
		campo.add(ubicacionT);

		// campo 7 (tipoEquipo)
		JLabel tipoEquipoL = new JLabel();
		tipoEquipoL.setText("Tipo de Equipo (Cada equipo como 'codigo,descripcion'):");
		tipoEquipoL.setBounds(50, 300, 320, 100);
		campo.add(tipoEquipoL);

		JTextField tipoEquipoT = new JTextField(15);
		tipoEquipoT.setBounds(50, 360, 350, 20);
		campo.add(tipoEquipoT);

		// campo 8 (Puertos)
		JLabel puertoL = new JLabel();
		puertoL.setText("Tipo Puerto (Cada puerto como 'codigo,descripcion,velocidad:cantidad'):");
		puertoL.setBounds(50, 360, 410, 100);
		campo.add(puertoL);

		JComboBox<String> puertoT = new JComboBox<>();
		puertoT.setBounds(50, 440, 350, 20);
		campo.add(puertoT);

		codigoT.addActionListener(e -> {
			String codigoSeleccionado = (String) codigoT.getSelectedItem();
			Equipo equipo = coordinador.getRed().buscarEquipoPorCodigo(codigoSeleccionado);
			// Verificar si se encontró el equipo
			if (equipo != null) {
				descripcionT.setText("");
				descripcionT.setText(equipo.getDescripcion());

				marcaT.setText("");
				marcaT.setText(equipo.getMarca());

				modeloT.setText("");
				modeloT.setText(equipo.getModelo());

				direccionipT.removeAllItems();
				for (String ip : equipo.getIPs()) {
					direccionipT.addItem(ip);
				}

				ubicacionT.setText("");
				String cubicacion = equipo.getUbicacion().getCodigo();
				String dubicacion = equipo.getUbicacion().getDescripcion();
				ubicacionT.setText(cubicacion + "," + dubicacion);

				tipoEquipoT.setText("");
				String cTipoEquipo = equipo.getTipoEquipo().getCodigo();
				String dTipoEquipo = equipo.getTipoEquipo().getDescripcion();
				tipoEquipoT.setText(cTipoEquipo + "," + dTipoEquipo);

				// puertoT.removeAllItems();
				List<String> puertos = equipo.getPuertos();
				for (String puerto : puertos) {
					puertoT.addItem(puerto);
				}

				List<Conexion> conexiones = coordinador.getRed().obtenerConexionesDeEquipo(equipo);
				if (conexiones.size() > 0) {
					for (Conexion conexion : conexiones) {
						System.out.println(conexion);
					}
				} else {
					System.out.println("No tiene conexiones");
				}

			} else {
				JOptionPane.showMessageDialog(null, "El equipo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		panelInferior.add(borrar);
		borrar.setVisible(true);

		borrar.addActionListener(e -> {
			try {
				// Obtener el equipo seleccionado
				String codigoSeleccionado = (String) codigoT.getSelectedItem();
				Equipo equipo = coordinador.getRed().buscarEquipoPorCodigo(codigoSeleccionado);

				// Verificar si el equipo tiene conexiones
				boolean tieneConexiones = coordinador.getCalculo().tieneConexion(equipo);
				System.out.println("¿tiene conexiones? " + tieneConexiones);
				if (tieneConexiones) {
					// Mostrar mensaje de confirmación si tiene conexiones
					int confirmacion = JOptionPane.showConfirmDialog(
							null,
							"Este equipo tiene conexiones. ¿Estás seguro de querer borrarlo?",
							"Confirmación",
							JOptionPane.YES_NO_OPTION);

					// Si el usuario confirma, proceder con el borrado
					if (confirmacion == JOptionPane.YES_OPTION) {
						// Obtenemos una lista de las conexiones relacionado al equipo

						List<Conexion> conexiones = coordinador.getRed().obtenerConexionesDeEquipo(equipo);
						for (Conexion conexion : conexiones) {
							System.out.println(conexion);
						}
						// Borramos las conexiones del equipo
						for (Conexion conexion : conexiones) {
							coordinador.borrarConexion(conexion);
						}
						// Borramos el equipo
						coordinador.borrarEquipo(equipo);
						JOptionPane.showMessageDialog(null, "El equipo ha sido eliminado con éxito.", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					// Si no tiene conexiones, eliminar directamente
					coordinador.borrarEquipo(equipo);
					JOptionPane.showMessageDialog(null, "El equipo ha sido eliminado con éxito.", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (Exception ex) {
				// Mostrar mensaje de error si algo falla
				JOptionPane.showMessageDialog(null, "Error en el formato de entrada: " + ex.getMessage(),
						"Error de Formato",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		campo.revalidate();
		campo.repaint();
	}

	public void mostrarTabla(Coordinador coordinador) {
		JFrame ventanaEmergente = new JFrame("Lista de Equipos");
		ventanaEmergente.setSize(800, 400);
		ventanaEmergente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		List<Equipo> listaEquipos = coordinador.listarEquipos();
		String[] nombreColumnas = { "Codigo", "Descripcion", "Marca", "Modelo", "Direccion IP", "Ubicacion",
				"Tipo de Equipo", "Puerto", "Estado" };
		String[][] dato = new String[listaEquipos.size()][nombreColumnas.length];

		for (int i = 0; i < listaEquipos.size(); i++) {
			Equipo equipo = listaEquipos.get(i);
			dato[i][0] = equipo.getCodigo();
			dato[i][1] = equipo.getDescripcion();
			dato[i][2] = equipo.getMarca();
			dato[i][3] = equipo.getModelo();
			dato[i][4] = String.join(", ", equipo.getIPs());
			dato[i][5] = (equipo.getUbicacion() != null)
					? equipo.getUbicacion().getCodigo() + "," + equipo.getUbicacion().getDescripcion()
					: "No hay ubicacion";
			dato[i][6] = (equipo.getTipoEquipo() != null)
					? equipo.getTipoEquipo().getCodigo() + "," + equipo.getTipoEquipo().getDescripcion()
					: "No hay tipo de equipo";
			dato[i][7] = String.join("; ", equipo.getPuertos());
			dato[i][8] = equipo.getEstado() ? "Activo" : "Inactivo";

		}

		JTable tabla = new JTable(dato, nombreColumnas);
		JScrollPane scrollPane = new JScrollPane(tabla);
		ventanaEmergente.add(scrollPane);
		ventanaEmergente.setVisible(true);
	}
}
