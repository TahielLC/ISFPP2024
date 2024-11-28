package red.gui.consulta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import red.aplicacion.Coordinador;
import red.modelo.Equipo;

public class Consultar extends JFrame {
	private Coordinador coordinador;

	private JComboBox<String> consultaBox, pingBox, equipoBox1, equipoBox2;
	private JTextArea resultadoArea;
	private JButton hacerPingButton, volverInicio, calcularTraza;

	private JProgressBar barraProgreso;

	public Consultar(int ancho, int alto) {
		new JFrame("Red de Computadoras");
		this.setSize(ancho, alto);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		// Panel superior
		JPanel barraSuperior = new JPanel();
		barraSuperior.setLayout(new FlowLayout());
		barraSuperior.setBackground(Color.GRAY);

		// Botón Volver
		volverInicio = new JButton("Volver al Inicio");
		volverInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Volver a la ventana principal

				coordinador.getVentana().setVisible(true);
				dispose(); // Cerrar la ventana actual
			}
		});
		barraSuperior.add(volverInicio);
		JPanel panelInferior = new JPanel();
		// JComboBox principal
		consultaBox = new JComboBox<>(new String[] { "Ping", "Tracerouter" });
		consultaBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccion = (String) consultaBox.getSelectedItem();
				if (seleccion.equals("Ping")) {
					mostrarOpcionesPing(barraSuperior, panelInferior);
				} else if (seleccion.equals("Tracerouter")) {
					mostrarOpcionesTracerouter(barraSuperior, panelInferior);
				}
			}
		});
		barraSuperior.add(consultaBox);
		add(barraSuperior, BorderLayout.NORTH);

		// Área de resultados
		resultadoArea = new JTextArea(10, 30);
		resultadoArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(resultadoArea);
		add(scrollPane, BorderLayout.CENTER);

		// Panel inferior para botón de acción

		hacerPingButton = new JButton("Hacer Ping");
		hacerPingButton.setVisible(false);
		panelInferior.add(hacerPingButton);

		calcularTraza = new JButton("Calcular ruta");
		calcularTraza.setVisible(false);
		panelInferior.add(calcularTraza);
		add(panelInferior, BorderLayout.SOUTH);

		// Agrega la barra de progreso al panel inferior
		barraProgreso = new JProgressBar(0, 100);
		barraProgreso.setStringPainted(true); // Mostrar porcentaje
		panelInferior.add(barraProgreso);
		barraProgreso.setVisible(false); // Inicialmente oculta
		barraProgreso.setPreferredSize(new Dimension(300, 25));
		barraProgreso.setForeground(Color.BLUE);
	}

	private void mostrarOpcionesPing(JPanel barraSuperior, JPanel panelInferior) {
		// Limpiar cualquier componente anterior del panel superior
		barraSuperior.removeAll();
		// volvemos a agregar el boton para volver al inicio
		barraSuperior.add(volverInicio);

		// Volver a agregar el JComboBox de consulta
		barraSuperior.add(consultaBox);

		panelInferior.removeAll();

		// Crear JComboBox para seleccionar tipo de Ping
		pingBox = new JComboBox<>(new String[] { "Ping a un equipo", "Ping entre equipos", "Mapeo de equipo" });
		pingBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccionPing = (String) pingBox.getSelectedItem();
				// Según la selección, habilitar diferentes componentes
				switch (seleccionPing) {
					case "Ping a un equipo":
						habilitarPingAUnEquipo(barraSuperior, panelInferior);
						break;
					case "Ping entre equipos":
						habilitarPingEntreEquipos(barraSuperior, panelInferior);
						break;
					case "Mapeo de equipo":
						habilitarMapeoEquipo(barraSuperior, panelInferior);
						break;
				}
			}
		});
		barraSuperior.add(pingBox);
		panelInferior.add(hacerPingButton);
		panelInferior.add(calcularTraza);
		hacerPingButton.setVisible(false);
		calcularTraza.setVisible(false);
		// Actualizar la interfaz
		revalidate();
		repaint();
	}

	private void habilitarPingAUnEquipo(JPanel barraSuperior, JPanel panelInferior) {
		barraSuperior.removeAll();
		barraSuperior.add(volverInicio);
		barraSuperior.add(consultaBox);
		barraSuperior.add(pingBox);
		panelInferior.removeAll();

		equipoBox1 = new JComboBox<>(obtenerListaEquipos());
		barraSuperior.add(equipoBox1);

		equipoBox1.setVisible(true); // tiene que ser true

		// Ocultar otros componentes que no se necesiten
		if (equipoBox2 != null)
			equipoBox2.setVisible(false);

		// Crear JButton para hacer el Ping
		panelInferior.add(hacerPingButton);
		hacerPingButton.setVisible(true); // tiene que ser true
		hacerPingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llamar al método que realiza el Ping y muestra los resultados
				String equipoSeleccionado = (String) equipoBox1.getSelectedItem();
				// llamamos a al metodo que esta en Calculo
				Equipo equipo = coordinador.getRed().buscarEquipoPorCodigo(equipoSeleccionado);
				String resultadoPing = coordinador.getCalculo().ping(equipoSeleccionado) ? "Activo" : "Inactivo";
				String resultadoEquipo = obtenerDetallesEquipo(equipo);
				realizarConsultaPing(resultadoEquipo, resultadoPing);
			}
		});

		calcularTraza.setVisible(false); // Ocultar botón de calcular ruta

		// Actualizar la interfaz
		revalidate();
		repaint();
	}

	private void habilitarPingEntreEquipos(JPanel barraSuperior, JPanel panelInferior) {
		barraSuperior.removeAll();
		panelInferior.removeAll();
		barraSuperior.add(volverInicio);
		barraSuperior.add(consultaBox);
		barraSuperior.add(pingBox);

		equipoBox1 = new JComboBox<>(obtenerListaEquipos());
		barraSuperior.add(equipoBox1);

		equipoBox2 = new JComboBox<>(obtenerListaEquipos());
		barraSuperior.add(equipoBox2);

		equipoBox1.setVisible(true);
		equipoBox2.setVisible(true);

		hacerPingButton.setVisible(true);// tiene que ser true
		panelInferior.add(hacerPingButton);
		for (ActionListener al : hacerPingButton.getActionListeners()) {
			hacerPingButton.removeActionListener(al);
		}
		hacerPingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llamar al método que realiza el Ping entre equipos y muestra los resultados
				// esto se cambiaria a Equipo ya que pingRango recibe como paramentro
				// dos equipos
				resultadoArea.setText("");
				String equipo1 = (String) equipoBox1.getSelectedItem();
				String equipo2 = (String) equipoBox2.getSelectedItem();
				// llama al metodo para realizar el mapeo entre equipos
				Equipo e1 = coordinador.getRed().buscarEquipoPorCodigo(equipo1);
				Equipo e2 = coordinador.getRed().buscarEquipoPorCodigo(equipo2);
				List<DefaultWeightedEdge> resultado = coordinador.getCalculo().tracerouter(e1, e2);
				List<Boolean> estados = coordinador.getCalculo().pingRango(e1, e2);
				String estadoPingRango = formatearEstadoPingRango(estados, resultado,
						coordinador.getCalculo().getgrafoRed());
				resultadoArea.setText(estadoPingRango);
				resultadoArea.revalidate();
				resultadoArea.repaint();
			}
		});

		calcularTraza.setVisible(false);
		// Actualizar la interfaz
		revalidate();
		repaint();
	}

	private void habilitarMapeoEquipo(JPanel barraSuperior, JPanel panelInferior) {
		barraSuperior.removeAll();

		panelInferior.removeAll();

		barraSuperior.add(volverInicio);

		barraSuperior.add(consultaBox);

		barraSuperior.add(pingBox);

		panelInferior.add(hacerPingButton);

		hacerPingButton.setVisible(true);

		hacerPingButton.setText("Hacer Mapeo");
		hacerPingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				realizarMapeoConHilos();
			}
		});

		if (equipoBox1 != null)
			equipoBox1.setVisible(false);
		if (equipoBox2 != null)
			equipoBox2.setVisible(false);

		calcularTraza.setVisible(false);

		barraProgreso.setVisible(false);

		revalidate();
		repaint();
	}

	private void realizarMapeoConHilos() {
		// Muestra la barra de progreso
		barraProgreso.setVisible(true);
		barraProgreso.setValue(0);
		barraProgreso.revalidate();
		barraProgreso.repaint();

		// Crear un SwingWorker para realizar la tarea en segundo plano
		SwingWorker<String, Integer> worker = new SwingWorker<String, Integer>() {
			@Override
			protected String doInBackground() throws Exception {
				Map<Equipo, Boolean> mapeoEstadoEquipo = coordinador.getCalculo().mapaEstadoEquipos();
				int totalEquipos = mapeoEstadoEquipo.size();
				int contador = 0;

				StringBuilder resultado = new StringBuilder();

				for (Map.Entry<Equipo, Boolean> entry : mapeoEstadoEquipo.entrySet()) {
					Equipo equipo = entry.getKey();
					String estado = entry.getValue() ? "Activo" : "Inactivo";
					resultado.append(equipo.getCodigo())
							.append(" - ")
							.append(equipo.getDescripcion())
							.append(" - Estado: ")
							.append(estado)
							.append("\n");

					// Simula tiempo de procesamiento para cada equipo (opcional)
					Thread.sleep(100);

					// Actualiza el progreso
					contador++;
					int progreso = (contador * 100) / totalEquipos;
					publish(progreso); // Enviar el progreso al hilo de la interfaz
				}
				return resultado.toString();
			}

			@Override
			protected void process(List<Integer> chunks) {
				// Actualizar la barra de progreso en la interfaz
				int progreso = chunks.get(chunks.size() - 1); // Obtener el último progreso
				barraProgreso.setValue(progreso);
				barraProgreso.revalidate();
				barraProgreso.repaint();
			}

			@Override
			protected void done() {
				try {
					// Obtener el resultado final y mostrarlo en el JTextArea
					String resultadoFinal = get();
					resultadoArea.setText(resultadoFinal);
				} catch (Exception e) {
					resultadoArea.setText("Ocurrió un error al realizar el mapeo.");
					e.printStackTrace();
				} finally {
					// Ocultar la barra de progreso al finalizar
					barraProgreso.setVisible(false);
				}
			}
		};

		// Ejecutar el SwingWorker
		worker.execute();
	}

	private String[] obtenerListaEquipos() {

		List<Equipo> equipos = coordinador.listarEquipos();
		String[] lista = new String[equipos.size()];
		for (int i = 0; i < equipos.size(); i++) {
			lista[i] = equipos.get(i).getCodigo();
		}
		return lista;
	}

	private void mostrarOpcionesTracerouter(JPanel barraSuperior, JPanel panelInferior) {
		/// Limpiar cualquier componente anterior del panel superior
		barraSuperior.removeAll();
		panelInferior.removeAll();
		// volvemos a agregar el boton para volver al inicio
		barraSuperior.add(volverInicio);

		// Volver a agregar el JComboBox de consulta
		barraSuperior.add(consultaBox);

		if (equipoBox1 == null) {
			equipoBox1 = new JComboBox<>(obtenerListaEquipos());
		}
		if (equipoBox2 == null) {
			equipoBox2 = new JComboBox<>(obtenerListaEquipos());
		}

		barraSuperior.add(new JLabel("Equipo origen:"));
		barraSuperior.add(equipoBox1);

		barraSuperior.add(new JLabel("Equipo destino:"));
		barraSuperior.add(equipoBox2);

		equipoBox1.setVisible(true);
		equipoBox2.setVisible(true);

		panelInferior.setVisible(true);
		panelInferior.add(calcularTraza);
		calcularTraza.setVisible(true); // Mostrar el botón de TraceRouter tiene que ser true
		calcularTraza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener los equipos seleccionados
				resultadoArea.setText("");
				String sEquipo1 = (String) equipoBox1.getSelectedItem();
				String sEquipo2 = (String) equipoBox2.getSelectedItem();
				Equipo equipo1 = coordinador.getRed().buscarEquipoPorCodigo(sEquipo1);
				Equipo equipo2 = coordinador.getRed().buscarEquipoPorCodigo(sEquipo2);
				// llama al metodo para calcular la ruta mas corta
				List<DefaultWeightedEdge> resultado = coordinador.getCalculo().tracerouter(equipo1, equipo2);
				String detallesRuta = formatearRutaTraceroute(resultado, equipo1, equipo2, coordinador.ObtenerGrafo());
				resultadoArea.setText(detallesRuta);
				resultadoArea.revalidate();
				resultadoArea.repaint();

			}
		});

		hacerPingButton.setVisible(false); // Ocultar el botón de Ping

		revalidate();
		repaint();
	}

	private void realizarConsultaPing(String resultadoEquipo, String ping) {
		// Ejecutar el método de ping y mostrar en JTextArea
		resultadoArea.setText("");
		resultadoArea.setText("Estado del equipo: " + ping + " Detalle del equipo: " + resultadoEquipo);
		resultadoArea.revalidate();
		resultadoArea.repaint();

	}

	private String obtenerDetallesEquipo(Equipo equipo) {
		if (equipo == null) {
			return "Equipo no encontrado.";
		}
		StringBuilder detalles = new StringBuilder();
		detalles.append("Código: ").append(equipo.getCodigo()).append("\n");
		detalles.append("Descripción: ").append(equipo.getDescripcion()).append("\n");
		detalles.append("Marca: ").append(equipo.getMarca()).append("\n");
		detalles.append("Modelo: ").append(equipo.getModelo()).append("\n");
		detalles.append("Ubicación: ").append(equipo.getUbicacion().getDescripcion()).append("\n");
		detalles.append("Tipo de Equipo: ").append(equipo.getTipoEquipo().getDescripcion()).append("\n");
		detalles.append("IPs: ").append(String.join(", ", equipo.getIPs())).append("\n");
		detalles.append("Puertos: ").append(String.join(", ", equipo.getPuertos())).append("\n");
		return detalles.toString();
	}

	private String formatearRutaTraceroute(List<DefaultWeightedEdge> resultado, Equipo equipo1, Equipo equipo2,
			Graph<Equipo, DefaultWeightedEdge> grafo) {
		if (resultado == null || resultado.isEmpty()) {
			return "No se encontró una ruta entre " + equipo1.getCodigo() + " y " + equipo2.getCodigo();
		}

		StringBuilder detalles = new StringBuilder();
		detalles.append("Ruta desde ").append(equipo1.getCodigo()).append(" hasta ").append(equipo2.getCodigo())
				.append(":\n");

		for (DefaultWeightedEdge edge : resultado) {
			Equipo origen = grafo.getEdgeSource(edge);
			Equipo destino = grafo.getEdgeTarget(edge);
			double peso = grafo.getEdgeWeight(edge);

			detalles.append("De ").append(origen.getCodigo())
					.append(" a ").append(destino.getCodigo())
					.append(" - Peso: ").append(peso)
					.append("\n");
		}

		return detalles.toString();
	}

	private String formatearEstadoPingRango(List<Boolean> estados, List<DefaultWeightedEdge> conexiones,
			Graph<Equipo, DefaultWeightedEdge> grafo) {
		StringBuilder resultado = new StringBuilder();

		List<Equipo> equipos = new ArrayList<>();
		if (!conexiones.isEmpty()) {
			Equipo equipoInicial = grafo.getEdgeSource(conexiones.get(0));
			equipos.add(equipoInicial);
		}

		for (DefaultWeightedEdge conexion : conexiones) {
			Equipo equipoTarget = grafo.getEdgeTarget(conexion);
			equipos.add(equipoTarget);
		}

		for (int i = 0; i < equipos.size(); i++) {
			Equipo equipo = equipos.get(i);
			String estado = estados.get(i) ? "Activo" : "Inactivo";
			resultado.append(equipo.getCodigo())
					.append(" - ")
					.append(equipo.getDescripcion())
					.append(" - Ubicación: ")
					.append(equipo.getUbicacion().getDescripcion())
					.append(" - Estado: ")
					.append(estado)
					.append("\n");
		}
		return resultado.toString();
	}

	private String formatearMapeoDeEstado(Map<Equipo, Boolean> estadoEquipos) {
		StringBuilder resultado = new StringBuilder();
		for (Map.Entry<Equipo, Boolean> entry : estadoEquipos.entrySet()) {
			Equipo equipo = entry.getKey();
			String estado = entry.getValue() ? "Activo" : "Inactivo";
			resultado.append(equipo.getCodigo())
					.append(" - ")
					.append(equipo.getDescripcion())
					.append(" - Estado: ")
					.append(estado)
					.append("\n");
		}
		return resultado.toString();
	}

	public void mostrar() {
		this.setVisible(true);
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}
}
