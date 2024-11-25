package gui.datos.datos;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import aplicacion.Coordinador;

public class ManipularTipoPuerto {
    private JTextField tfCodNuevoPuerto;
    private JTextField tfDescripcion;
    private JTextField tfVelPuerto;

	public void panelAgregar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {
		campo.removeAll();

        modificar.setVisible(false);
		borrar.setVisible(false);

        // Etiqueta y campo para "Código del Puerto"
        JLabel lbCodigoNuevoPuerto = new JLabel("Código Puerto:");
        lbCodigoNuevoPuerto.setBounds(30, 20, 120, 25);
        campo.add(lbCodigoNuevoPuerto);

        tfCodNuevoPuerto = new JTextField();
        tfCodNuevoPuerto.setBounds(160, 20, 150, 25);
        campo.add(tfCodNuevoPuerto);

        // Etiqueta y campo para "Descripción"
        JLabel lbDescripcion = new JLabel("Descripción:");
        lbDescripcion.setBounds(30, 60, 120, 25);
        campo.add(lbDescripcion);

        tfDescripcion = new JTextField();
        tfDescripcion.setBounds(160, 60, 150, 25);
        campo.add(tfDescripcion);

        // Etiqueta y campo para "Velocidad del Puerto"
        JLabel lbVelocidadPuerto = new JLabel("Velocidad del puerto:");
        lbVelocidadPuerto.setBounds(30, 100, 150, 25);
        campo.add(lbVelocidadPuerto);

        tfVelPuerto = new JTextField();
        tfVelPuerto.setBounds(160, 100, 150, 25);
        campo.add(tfVelPuerto);

        // Añadir ActionListener a los JTextField
        TxtFieldDriver driver = new TxtFieldDriver();
        tfCodNuevoPuerto.addActionListener(driver);
        tfDescripcion.addActionListener(driver);
        tfVelPuerto.addActionListener(driver);
    
        panelInferior.add(cargar);
        cargar.setVisible(true);
        cargar.addActionListener(e -> {
			
		});

        campo.revalidate();
		campo.repaint();
	}

    private class TxtFieldDriver implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == tfCodNuevoPuerto) {
                
            }
            if (event.getSource() == tfDescripcion) {
                
            }
            if (event.getSource() == tfVelPuerto) {

            }
            
        }
        
    }

	public void panelModificar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {
		// TODO Auto-generated method stub
		
	}

	public void panelBorrar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {
		// TODO Auto-generated method stub
		
	}

}
