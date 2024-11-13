package red.gui.datos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import red.aplicacion.Coordinador;
import red.modelo.Equipo;


public class Manipular extends JFrame{
	private JPanel panelSuperior;
	private JPanel campo;
	private JPanel panelInferior;
	
	private JButton volverInicio;
	private JButton botonAgregar;
	private JButton botonModificar;
	private JButton botonBorrar;

	private JButton cargar,modificar,borrar,botonVista;
	
	private JComboBox<String> opciones;
	
	private ManipularConexion mConexion;
	private ManipularEquipo mEquipo;
	private ManipularTipoCable mTipoCable;
	private ManipularTipoEquipo mTipoEquipo;
	private ManipularTipoPuerto mTipoPuerto;
	private ManipularUbicacion mUbicacion;
	
	
	
	private Coordinador coordinador;
	
	public Manipular() {
			
		mConexion = new ManipularConexion();
		mEquipo = new ManipularEquipo();
		mTipoCable = new ManipularTipoCable();
		mTipoEquipo = new ManipularTipoEquipo();
		mTipoPuerto = new ManipularTipoPuerto();
		mUbicacion = new ManipularUbicacion();
		
		// Crear ventana
        new JFrame("Red de Computadoras");
        this.setSize(800, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panelSuperior = new JPanel();   
        panelSuperior.setLayout(new FlowLayout());
        panelSuperior.setBackground(Color.GRAY);
        
        panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout());
        panelInferior.setBackground(Color.GRAY);
        
        campo = new JPanel();
        campo.setLayout(null);
        campo.setPreferredSize(new Dimension(750, 500));  // Tamaño preferido para el scroll
        
        volverInicio = new JButton("Volver al Inicio"); // Me debe de retornar a Ventana
        botonVista = new JButton("Vista");
        botonAgregar = new JButton("Agregar"); // habilita los campos para agregar equipos
        botonModificar = new JButton("Modificar"); // habilita los campos para modificar
        botonBorrar = new JButton("Borrar"); // habilita el campo del equipo a borrar
        
        String[] opcion = {"Conexion","Equipo","TipoCable","TipoEquipo","TipoPuerto","Ubicacion"};
        
        opciones = new JComboBox<String>(opcion);
        opciones.setVisible(true);
        
        
        cargar = new JButton("Cargar");
        cargar.setVisible(false);
        panelInferior.add(cargar);
        
        modificar = new JButton("Modificar");
        modificar.setVisible(false);
        panelInferior.add(modificar);
        
        borrar = new JButton("Borrar");
        borrar.setVisible(false);
        panelInferior.add(borrar);
        
        panelSuperior.add(volverInicio);
        panelSuperior.add(opciones);
        panelSuperior.add(botonVista);
        panelSuperior.add(botonAgregar);
        panelSuperior.add(botonModificar);
        panelSuperior.add(botonBorrar);
        
       // Crear el JScrollPane y agregar el panel "campo" dentro de él
       JScrollPane scroll = new JScrollPane(campo);
       scroll.setPreferredSize(new Dimension(780, 400));  // Tamaño para el JScrollPane
       scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       
        volverInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Volver a la ventana principal
            	coordinador.getVentana().setVisible(true);
                dispose();  // Cerrar la ventana actual
            }
        });
        
        opciones.addActionListener(e -> {
            // Obtener la opción seleccionada
            String opcionSeleccionada = (String) opciones.getSelectedItem();
        });
      
        botonVista.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String opcionSeleccionada = (String) opciones.getSelectedItem();
		        switch (opcionSeleccionada) {
		            case "Equipo":
		                mEquipo.mostrarTabla(coordinador);
		                break;
		        }
			}
        	
        });
        
        // Definir los ActionListeners solo una vez fuera del JComboBox
        botonAgregar.addActionListener(e -> {
            String opcionSeleccionada = (String) opciones.getSelectedItem();
            switch (opcionSeleccionada) {
                case "Conexion":
                    mConexion.panelAgregar(campo, panelInferior, cargar, modificar, borrar, coordinador);
                    break;
                case "Equipo":
                    mEquipo.panelAgregar(campo, panelInferior, cargar, modificar, borrar, coordinador);
                    break;
                case "TipoCable":
                    mTipoCable.panelAgregar(campo, panelInferior, cargar, modificar, borrar, coordinador);
                    break;
                case "TipoEquipo":
                    mTipoEquipo.panelAgregar(campo, panelInferior, cargar, modificar, borrar, coordinador);
                    break;
                case "TipoPuerto":
                    mTipoPuerto.panelAgregar(campo, panelInferior, cargar, modificar, borrar, coordinador);
                    break;
                case "Ubicacion":
                    mUbicacion.panelAgregar(campo, panelInferior, cargar, modificar, borrar, coordinador);
                    break;
            }
            campo.revalidate();
            campo.repaint();
        });

        botonModificar.addActionListener(e -> {
            String opcionSeleccionada = (String) opciones.getSelectedItem();
            switch (opcionSeleccionada) {
                case "Conexion":
                    mConexion.panelModificar(campo, panelInferior, cargar, modificar, borrar, coordinador);
                    break;
                case "Equipo":
                    mEquipo.panelModificar(campo, panelInferior, cargar, modificar, borrar, coordinador);
                    break;
                case "TipoCable":
                    mTipoCable.panelModificar(campo, panelInferior, cargar, modificar, borrar, coordinador);
                    break;
                case "TipoEquipo":
                    mTipoEquipo.panelModificar(campo, panelInferior, cargar, modificar, borrar, coordinador);
                    break;
                case "TipoPuerto":
                    mTipoPuerto.panelModificar(campo, panelInferior, cargar, modificar, borrar, coordinador);
                    break;
                case "Ubicacion":
                    mUbicacion.panelModificar(campo, panelInferior, cargar, modificar, borrar, coordinador);
                    break;
            }
            campo.revalidate();
            campo.repaint();
        });

        botonBorrar.addActionListener(e -> {
            String opcionSeleccionada = (String) opciones.getSelectedItem();
            switch (opcionSeleccionada) {
                case "Conexion":
                    mConexion.panelBorrar(campo, panelInferior, cargar, modificar, borrar, coordinador);
                    break;
                case "Equipo":
                    mEquipo.panelBorrar(campo, panelInferior, cargar, modificar, borrar, coordinador);
                    break;
                case "TipoCable":
                    mTipoCable.panelBorrar(campo, panelInferior, cargar, modificar, borrar, coordinador);
                    break;
                case "TipoEquipo":
                    mTipoEquipo.panelBorrar(campo, panelInferior, cargar, modificar, borrar, coordinador);
                    break;
                case "TipoPuerto":
                    mTipoPuerto.panelBorrar(campo, panelInferior, cargar, modificar, borrar, coordinador);
                    break;
                case "Ubicacion":
                    mUbicacion.panelBorrar(campo, panelInferior, cargar, modificar, borrar, coordinador);
                    break;
            }
            campo.revalidate();
            campo.repaint();
        });
        
		this.add(panelSuperior, BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);
        this.add(panelInferior, BorderLayout.SOUTH);
        
        
	}
	
	public String[] obtenerListaCodigo(){
		if (coordinador == null) {
	        throw new IllegalStateException("El objeto coordinador no ha sido inicializado.");
	    }
		List<Equipo> equipos = coordinador.listarEquipos();
		String[] equipo = new String[equipos.size()];
		int i = 0;
		for(Equipo e: equipos) {
			equipo[i] = e.getCodigo();
			i++;
		}
		return equipo;
	}
	
	/*public String[] obtenerListaIP(String codigo){
		// verificar que no se repita
		// para eso crea un metodo
		List<Equipo> equipos = coordinador.listarEquipos();
		String[] ips = new String[equipos.size()];
		int i = 0;
		for(Equipo e: equipos) {
			if(e.getCodigo().equals(codigo)) {
				for(String ip : e.getIPs()) {
					ips[i] = ip.toString();
					i++;
				}
			}
		}
		return ips;
	}*/
	
	public void mostrar() {
        this.setVisible(true);
    }
	
	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}
} 
