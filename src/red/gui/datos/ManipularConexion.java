package red.gui.datos;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import red.aplicacion.Coordinador;

public class ManipularConexion {

	public void panelAgregar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {
		campo.removeAll();
	
		modificar.setVisible(false);
		borrar.setVisible(false);
		
		JLabel equipo1L = new JLabel();
		equipo1L.setText("Equipo 1: ");
		equipo1L.setBounds(50, 0, 100, 100);
		campo.add(equipo1L);
		
		JComboBox<String> equipo1C = new JComboBox<String>(coordinador.getManipular().obtenerListaCodigo());
		equipo1C.setBounds(140, 40, 120, 20);
		campo.add(equipo1C);
		
		
		JLabel equipo2L = new JLabel();
		equipo2L.setText("Equipo 2: ");
		equipo2L.setBounds(50, 40, 100, 100);
		campo.add(equipo2L);
		
		JComboBox<String> equipo2C = new JComboBox<String>(coordinador.getManipular().obtenerListaCodigo());
		equipo2C.setBounds(140, 80, 120, 20);
		campo.add(equipo2C);
		
		JLabel tipoPuerto1L = new JLabel();
		tipoPuerto1L.setText("Puerto 1: ");
		tipoPuerto1L.setBounds(50, 80, 100, 100);
		campo.add(tipoPuerto1L);
		
		JComboBox<String> tipoPuerto1C = new JComboBox<>(coordinador.getManipular().obtenerTipoPuertos()); // crear un metodo para obtener una lista de puertos
		tipoPuerto1C.setBounds(140, 120, 120, 20);
		campo.add(tipoPuerto1C);
		
		
		JLabel tipoPuerto2L = new JLabel();
		tipoPuerto2L.setText("Puerto 2: ");
		tipoPuerto2L.setBounds(50, 120, 100, 100);
		campo.add(tipoPuerto2L);
		
		JComboBox<String> tipoPuerto2C = new JComboBox<>(coordinador.getManipular().obtenerTipoPuertos()); // crear un metodo para obtener una lista de puertos
		tipoPuerto2C.setBounds(140, 160, 120, 20);
		campo.add(tipoPuerto2C);
		
		JLabel tipoCableL = new JLabel();
		tipoCableL.setText("Cable: ");
		tipoCableL.setBounds(50, 160, 100, 100);
		campo.add(tipoCableL);
		
		JComboBox<String> tipoCableC = new JComboBox<>(coordinador.getManipular().obtenerTipoCable()); // crear un metodo para obtener una lista de tipo cable
		tipoCableC.setBounds(140, 200, 120, 20);
		campo.add(tipoCableC);
		
		panelInferior.add(cargar);
		
		cargar.setVisible(true);
		
		cargar.addActionListener(e -> {
			
		});
		
		campo.revalidate();
		campo.repaint();
	}

	public void panelModificar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {
		
		
	}

	public void panelBorrar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {
		
		
	}

}
