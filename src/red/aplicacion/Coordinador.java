package  red.aplicacion;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import red.gui.datos.consulta.Consultar;
import red.gui.datos.consulta.Ventana;
import red.gui.datos.datos.Manipular;
import red.interfaz.Interfaz;
import red.modelo.Conexion;
import red.modelo.Equipo;
import red.modelo.Ubicacion;
import red.negocio.Calculo;
import red.negocio.Red;

public class Coordinador {
	
	private Red red;
	private Calculo calculo;
	private Interfaz interfaz;
	
	private Ventana ventana;
	private Consultar consultar;
	private Manipular manipular;
	
	public Red getRed() {
		return red;
	}
	
	public Red setRed(Red red) {
		this.red = red;
	    System.out.println("Red asignada en Coordinador: " + (this.red != null));

		return this.red;
	}
	
	public Calculo getCalculo() {
		return calculo;
	}
	
	public Calculo setCalculo(Calculo calculo) {
		return this.calculo = calculo;
		
	}
	
	public Interfaz getInterfaz() {
		return interfaz;
	}

	public void setInterfaz(Interfaz interfaz) {
		this.interfaz = interfaz;
	}
	
	public Ventana getVentana() {
		return ventana;
	}
	
	public void setVentana(Ventana ventana) {
		this.ventana = ventana;
	}
	
	public Consultar getConsultar() {
		return consultar;
	}
	
	public void setConsultar(Consultar consultar){
		this.consultar = consultar;
	}
	
	public Manipular getManipular() {
		return manipular;
	}
	
	public void setManipular(Manipular manipular) {
		this.manipular = manipular;
	} 
	
	public Graph<Equipo, DefaultWeightedEdge> ObtenerGrafo() {
		return calculo.getgrafoRed();
	}
	
	public List<Conexion> listarConexiones(){
		return red.getConexiones();
	}
	
	public List<Equipo> listarEquipos(){
		return red.getEquipos();
	}
	
	public List<Ubicacion> listarUbicaciones(){
		return red.getUbicaciones();
	}
	
	public void insertarEquipo(Equipo equipo) {
		red.agregarEquipo(equipo);
		
	}
	
	public void modificarEquipo(Equipo equipo) {
		red.modificarEquipo(equipo);
		
	}
	
	public void borrarEquipo(Equipo equipo) {
		red.borrarEquipo(equipo);
		
	}	
}
