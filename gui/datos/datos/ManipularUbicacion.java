package gui.datos.datos;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import aplicacion.Coordinador;

public class ManipularUbicacion {
    private JTextField tfCodUbicacion;
    private JTextField tfDescUbicacion;

	public void panelAgregar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar,
			Coordinador coordinador) {
        campo.removeAll();
		
		modificar.setVisible(false);
		borrar.setVisible(false);

        // Primer JLabel y JTextField
    JLabel lbCodigoUbicacion = new JLabel("Codigo de Ubicacion:");
    lbCodigoUbicacion.setBounds(50, 20, 150, 20); // Ancho suficiente para el texto
    campo.add(lbCodigoUbicacion);

    tfCodUbicacion = new JTextField();
    tfCodUbicacion.setBounds(180, 20, 120, 20); // Alineado con el Label
    campo.add(tfCodUbicacion);

    // Segundo JLabel y JTextField
    JLabel lbDescripcionUbicacion = new JLabel("Descripcion:");
    lbDescripcionUbicacion.setBounds(50, 60, 150, 20); // Ancho suficiente para el texto
    campo.add(lbDescripcionUbicacion);

    tfDescUbicacion = new JTextField();
    tfDescUbicacion.setBounds(180, 60, 120, 20); // Alineado con el Label
    campo.add(tfDescUbicacion);

    // Agregar ActionListeners
    TxtFieldDriver driver = new TxtFieldDriver();
    tfCodUbicacion.addActionListener(driver);
    tfDescUbicacion.addActionListener(driver);

	}

    private class TxtFieldDriver implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == tfCodUbicacion) {
                
            }
            if (event.getSource() == tfDescUbicacion) {
                
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
