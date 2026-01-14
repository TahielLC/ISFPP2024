package red.gui.consulta;

import javax.swing.JFrame;
import javax.swing.JButton;

import red.aplicacion.Coordinador;
import red.gui.datos.Manipular;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame {
	private Coordinador coordinador;
    private GraphIlustrator ventanaGraficoRed;
    
    public Ventana(int ancho, int alto) {
        new JFrame();
        this.setTitle("Red de Computadoras");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(ancho, alto); // Tamaño inicial de la ventana
        this.setVisible(true);
        // Crear los botones
        //JButton botonVentana = new JButton("Ventana Principal");
        JButton botonCargarDatos = new JButton("Cargar Datos");
        JButton botonMostrarRed = new JButton("Mostrar Red");
        JButton botonConsultar = new JButton("Consultar Red");
        JButton botonSalir = new JButton("Salir");

        // Configurar el layout de la ventana usando GridBagLayout
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configuraciones básicas para centrar los componentes
        gbc.fill = GridBagConstraints.NONE; // No expandir los botones
        gbc.anchor = GridBagConstraints.CENTER; // Centrarlos
        gbc.insets = new Insets(10, 10, 10, 10); // Margen alrededor de los botones

        // Agregar los botones a la ventana en posiciones centradas
        gbc.gridx = 0;
      
        gbc.gridy = 1; // Primera fila
        this.add(botonCargarDatos, gbc);

        gbc.gridy = 2; // Segunda fila
        this.add(botonMostrarRed, gbc);

        gbc.gridy = 3; // Tercera fila
        this.add(botonConsultar, gbc);
        
        gbc.gridy = 4; // Cuata fila
        this.add(botonSalir, gbc);
        

       
        botonCargarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Manipular cargarDatos = coordinador.getManipular(); // Clase que se encarga de cargar datos
                cargarDatos.mostrar();
                setVisible(false);
            }
        });

        botonMostrarRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 // Verificar si la ventana del grafo ya fue creada
                 if (ventanaGraficoRed == null) {
                    // Crear y mostrar la ventana con el grafo solo una vez
                    ventanaGraficoRed = new GraphIlustrator(coordinador.ObtenerGrafo());
                    ventanaGraficoRed.setSize(400, 320);
                    ventanaGraficoRed.setVisible(true);
                } else {
                    // Si ya está creada, simplemente la hacemos visible
                    ventanaGraficoRed.setVisible(true);
                }
            }
        });

        botonConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Consultar consultar = coordinador.getConsultar();
                consultar.mostrar();
                setVisible(false);
            }
        });
        
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Esto cerrará la aplicación
            }
        });
        
    }
    
    public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}
}

