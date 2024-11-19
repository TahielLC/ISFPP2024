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

import red.aplicacion.Coordinador;
import red.modelo.Conexion;
import red.modelo.Equipo;
import red.modelo.TipoCable;
import red.modelo.TipoPuerto;

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
			String codigo1 = equipo1C.toString();
			Equipo equipo1 = coordinador.getRed().buscarEquipoPorCodigo(codigo1);
			
			String codigo2 = equipo2C.toString();
			Equipo equipo2 = coordinador.getRed().buscarEquipoPorCodigo(codigo2);
			
			String puerto1 = tipoPuerto1C.toString();
			TipoPuerto tipoPuerto1 = coordinador.getRed().buscarTipoPuertoPorCodigo(puerto1);
			
			String puerto2 = tipoPuerto2C.toString();
			TipoPuerto tipoPuerto2 = coordinador.getRed().buscarTipoPuertoPorCodigo(puerto2);
			
			String cable = tipoCableC.toString();
			TipoCable tipoCable = coordinador.getRed().buscarTipoCablePorCodigo(cable);
			
			Conexion conexion = new Conexion(equipo1,tipoPuerto1,equipo2,tipoPuerto2,tipoCable);
			
			coordinador.insertarConexion(conexion);
			
		});
		
		campo.revalidate();
		campo.repaint();
	}

	public void panelModificar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {
		
		campo.removeAll();
		
		cargar.setVisible(false);
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
		
		
		JComboBox<String> equipo2C = new JComboBox<String>();
		equipo2C.setBounds(140, 80, 120, 20);
		campo.add(equipo2C);
		
		JLabel tipoPuerto1L = new JLabel();
		tipoPuerto1L.setText("Puerto 1: ");
		tipoPuerto1L.setBounds(50, 80, 100, 100);
		campo.add(tipoPuerto1L);
		
		JTextField tipoPuerto1C = new JTextField();
		tipoPuerto1C.setBounds(140, 120, 120, 20);
		campo.add(tipoPuerto1C);
		
		
		JLabel tipoPuerto2L = new JLabel();
		tipoPuerto2L.setText("Puerto 2: ");
		tipoPuerto2L.setBounds(50, 120, 100, 100);
		campo.add(tipoPuerto2L);
		
		JTextField tipoPuerto2C = new JTextField();
		tipoPuerto2C.setBounds(140, 160, 120, 20);
		campo.add(tipoPuerto2C);
		
		JLabel tipoCableL = new JLabel();
		tipoCableL.setText("Cable: ");
		tipoCableL.setBounds(50, 160, 100, 100);
		campo.add(tipoCableL);
		
		JTextField tipoCableC = new JTextField();
		tipoCableC.setBounds(140, 200, 120, 20);
		campo.add(tipoCableC);
		
		equipo1C.addActionListener(e -> {
		    String codigoEquipo1 = (String) equipo1C.getSelectedItem();
		    Equipo equipo1 = coordinador.getRed().buscarEquipoPorCodigo(codigoEquipo1);
		    String[] conexiones = coordinador.getManipular().obtenerListaConexiones(equipo1);

		    equipo2C.removeAllItems();
		    for (String conexion : conexiones) {
		        equipo2C.addItem(conexion);
		    }

		    // Listener para actualizar los detalles de la conexión seleccionada
		    equipo2C.addActionListener(e2 -> {
		        String codigoEquipo2 = (String) equipo2C.getSelectedItem();
		        if (codigoEquipo2 != null) {
		            Equipo equipo2 = coordinador.getRed().buscarEquipoPorCodigo(codigoEquipo2);
		            Conexion conexion = coordinador.getRed().obtenerConexion(equipo1, equipo2);

		            if (conexion != null) {
		                // Rellenar los campos de los puertos y el cable
		                tipoPuerto1C.setText(conexion.getEquipo1().getCodigo()+","+conexion.getTipoPuerto1().getDescripcion() + ":" + conexion.getTipoPuerto1().getVelocidad());
		                tipoPuerto2C.setText(conexion.getEquipo2().getCodigo()+","+conexion.getTipoPuerto2().getDescripcion() + ":" + conexion.getTipoPuerto2().getVelocidad());
		                tipoCableC.setText(conexion.getTipoCable().getCodigo()+","+conexion.getTipoCable().getDescripcion() + ":" + conexion.getTipoCable().getVelocidad());
		            }
		        }
		    });
		});		
		modificar.setVisible(true);
		
		modificar.addActionListener(e ->{
			String codigoEquipo1 = (String) equipo1C.getSelectedItem();
		    Equipo equipo1 = coordinador.getRed().buscarEquipoPorCodigo(codigoEquipo1); 
		    
		    String codigoEquipo2 = (String) equipo2C.getSelectedItem();
		    Equipo equipo2 = coordinador.getRed().buscarEquipoPorCodigo(codigoEquipo2);
		    
		    if (equipo1 == null || equipo2 == null) {
		        JOptionPane.showMessageDialog(null, "Uno o ambos equipos no son válidos.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }
		    
		    String[] puerto1Partes = tipoPuerto1C.getText().split(",");
		    String codigo1 = puerto1Partes[0];
		    String[] descripcionVelocidad1 = puerto1Partes[1].split(":");
		    String descripcionPuerto1 = descripcionVelocidad1[0];
		    int velocidadPuerto1 = Integer.parseInt(descripcionVelocidad1[1]);
		    
		    TipoPuerto tipoPuerto1 = new TipoPuerto(codigo1, descripcionPuerto1, velocidadPuerto1);
		    
		    
		    String[] puerto2Partes = tipoPuerto2C.getText().split(",");
		    String codigo2 = puerto2Partes[0];
		    String[] descripcionVelocidad2 = puerto2Partes[1].split(":");
		    String descripcionPuerto2 = descripcionVelocidad2[0];
		    int velocidadPuerto2 = Integer.parseInt(descripcionVelocidad2[1]);

		    TipoPuerto tipoPuerto2 = new TipoPuerto(codigo2, descripcionPuerto2, velocidadPuerto2);

		    
		    String[] cablePartes = tipoCableC.getText().split(",");
		    String codigoCable = cablePartes[0];
		    String[] descripcionVelocidadCable = cablePartes[1].split(":");
		    String descripcionCable = descripcionVelocidadCable[0];
		    int velocidadCable = Integer.parseInt(descripcionVelocidadCable[1]);

		    TipoCable tipoCable = new TipoCable(codigoCable, descripcionCable, velocidadCable);

		    
		    Conexion conexion = new Conexion(equipo1,tipoPuerto1,equipo2,tipoPuerto2,tipoCable);
		    
		    if(validarConexion(conexion)) {
		    	coordinador.modificarConexion(conexion);
		    }
		});
		campo.revalidate();
		campo.repaint();
	}
	
	public boolean validarConexion(Conexion conexion) {
		TipoPuerto tp1 = conexion.getTipoPuerto1();
		TipoPuerto tp2 = conexion.getTipoPuerto2();
		TipoCable tc = conexion.getTipoCable();
		
		if(tp1.getCodigo() == null && tp1.getDescripcion() == null && tp1.getVelocidad() <= 0) {
			JOptionPane.showMessageDialog(null, "El puerto 1 esta vacio o es nulo.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(tp2.getCodigo() == null && tp2.getDescripcion() == null && tp2.getVelocidad() <= 0) {
			JOptionPane.showMessageDialog(null, "El puerto 2 esta vacio o es nulo.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(tc.getCodigo() == null && tc.getDescripcion() == null && tc.getVelocidad() <= 0) {
			JOptionPane.showMessageDialog(null, "El cable esta vacio o es nulo.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	public void panelBorrar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {
		campo.removeAll();
		
		cargar.setVisible(false);
		modificar.setVisible(false);
		
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
		
		
		JComboBox<String> equipo2C = new JComboBox<String>();
		equipo2C.setBounds(140, 80, 120, 20);
		campo.add(equipo2C);
		
		JLabel tipoPuerto1L = new JLabel();
		tipoPuerto1L.setText("Puerto 1: ");
		tipoPuerto1L.setBounds(50, 80, 100, 100);
		campo.add(tipoPuerto1L);
		
		JTextField tipoPuerto1C = new JTextField();
		tipoPuerto1C.setBounds(140, 120, 120, 20);
		campo.add(tipoPuerto1C);
		
		
		JLabel tipoPuerto2L = new JLabel();
		tipoPuerto2L.setText("Puerto 2: ");
		tipoPuerto2L.setBounds(50, 120, 100, 100);
		campo.add(tipoPuerto2L);
		
		JTextField tipoPuerto2C = new JTextField();
		tipoPuerto2C.setBounds(140, 160, 120, 20);
		campo.add(tipoPuerto2C);
		
		JLabel tipoCableL = new JLabel();
		tipoCableL.setText("Cable: ");
		tipoCableL.setBounds(50, 160, 100, 100);
		campo.add(tipoCableL);
		
		JTextField tipoCableC = new JTextField();
		tipoCableC.setBounds(140, 200, 120, 20);
		campo.add(tipoCableC);
		
		equipo1C.addActionListener(e -> {
		    String codigoEquipo1 = (String) equipo1C.getSelectedItem();
		    Equipo equipo1 = coordinador.getRed().buscarEquipoPorCodigo(codigoEquipo1);
		    String[] conexiones = coordinador.getManipular().obtenerListaConexiones(equipo1);

		    equipo2C.removeAllItems();
		    for (String conexion : conexiones) {
		        equipo2C.addItem(conexion);
		    }

		    // Listener para actualizar los detalles de la conexión seleccionada
		    equipo2C.addActionListener(e2 -> {
		        String codigoEquipo2 = (String) equipo2C.getSelectedItem();
		        if (codigoEquipo2 != null) {
		            Equipo equipo2 = coordinador.getRed().buscarEquipoPorCodigo(codigoEquipo2);
		            Conexion conexion = coordinador.getRed().obtenerConexion(equipo1, equipo2);

		            if (conexion != null) {
		                // Rellenar los campos de los puertos y el cable
		                tipoPuerto1C.setText(conexion.getEquipo1().getCodigo()+","+conexion.getTipoPuerto1().getDescripcion() + ":" + conexion.getTipoPuerto1().getVelocidad());
		                tipoPuerto2C.setText(conexion.getEquipo2().getCodigo()+","+conexion.getTipoPuerto2().getDescripcion() + ":" + conexion.getTipoPuerto2().getVelocidad());
		                tipoCableC.setText(conexion.getTipoCable().getCodigo()+","+conexion.getTipoCable().getDescripcion() + ":" + conexion.getTipoCable().getVelocidad());
		            }
		        }
		    });
		});	
		
		
		borrar.setVisible(true);
		
		borrar.addActionListener(e ->{
			
			String codigoEquipo1 = (String) equipo1C.getSelectedItem();
		    Equipo equipo1 = coordinador.getRed().buscarEquipoPorCodigo(codigoEquipo1); 
		    
		    String codigoEquipo2 = (String) equipo2C.getSelectedItem();
		    Equipo equipo2 = coordinador.getRed().buscarEquipoPorCodigo(codigoEquipo2);
		    
		    if (equipo1 == null || equipo2 == null) {
		        JOptionPane.showMessageDialog(null, "Uno o ambos equipos no son válidos.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }
		    
		    String[] puerto1Partes = tipoPuerto1C.getText().split(",");
		    String codigo1 = puerto1Partes[0];
		    String[] descripcionVelocidad1 = puerto1Partes[1].split(":");
		    String descripcionPuerto1 = descripcionVelocidad1[0];
		    int velocidadPuerto1 = Integer.parseInt(descripcionVelocidad1[1]);
		    
		    TipoPuerto tipoPuerto1 = new TipoPuerto(codigo1, descripcionPuerto1, velocidadPuerto1);
		    
		    
		    String[] puerto2Partes = tipoPuerto2C.getText().split(",");
		    String codigo2 = puerto2Partes[0];
		    String[] descripcionVelocidad2 = puerto2Partes[1].split(":");
		    String descripcionPuerto2 = descripcionVelocidad2[0];
		    int velocidadPuerto2 = Integer.parseInt(descripcionVelocidad2[1]);

		    TipoPuerto tipoPuerto2 = new TipoPuerto(codigo2, descripcionPuerto2, velocidadPuerto2);

		    
		    String[] cablePartes = tipoCableC.getText().split(",");
		    String codigoCable = cablePartes[0];
		    String[] descripcionVelocidadCable = cablePartes[1].split(":");
		    String descripcionCable = descripcionVelocidadCable[0];
		    int velocidadCable = Integer.parseInt(descripcionVelocidadCable[1]);

		    TipoCable tipoCable = new TipoCable(codigoCable, descripcionCable, velocidadCable);

		    
		    Conexion conexion = new Conexion(equipo1,tipoPuerto1,equipo2,tipoPuerto2,tipoCable);
		    
		 // Mostrar mensaje de confirmación si tiene conexiones
            int confirmacion = JOptionPane.showConfirmDialog(
                null, 
                "Esta Conexion Puede afectar la relacion con otros equipos. ¿Estás seguro de querer borrarlo?", 
                "Confirmación", 
                JOptionPane.YES_NO_OPTION
            );

            // Si el usuario confirma, proceder con el borrado
            if (confirmacion == JOptionPane.YES_OPTION) {
                coordinador.borrarConexion(conexion);
                JOptionPane.showMessageDialog(null, "La conexion ha sido eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
            	JOptionPane.showMessageDialog(null, "Se ha cancelado la operacion borrar", "Cancelado", 
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
	    List<Conexion> listaConexiones = coordinador.listarConexiones();
	    String[] nombreColumnas = {"Equipo 1", "Puerto 1", "Equipo 2", "Puerto 2", "Cable"};
	    String[][] dato = new String[listaConexiones.size()][nombreColumnas.length];
	    
	    for(int i = 0; i < listaConexiones.size(); i++) {
	    	Conexion conexion = listaConexiones.get(i);
	    	dato[i][0] = conexion.getEquipo1().getCodigo();
	    	dato[i][1] = conexion.getTipoPuerto1().getCodigo();
	    	dato[i][2] = conexion.getEquipo2().getCodigo();
	    	dato[i][3] = conexion.getTipoPuerto2().getCodigo();
	    	dato[i][4] = conexion.getTipoCable().getCodigo();
	    }
	    JTable tabla = new JTable(dato, nombreColumnas);
	    JScrollPane scrollPane = new JScrollPane(tabla);
	    ventanaEmergente.add(scrollPane);
	    ventanaEmergente.setVisible(true);
	}
}
