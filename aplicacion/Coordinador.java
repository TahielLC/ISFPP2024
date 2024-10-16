package  aplicacion;

import java.util.List;

import  interfaz.Interfaz;
import  modelo.Conexion;
import  modelo.Equipo;
import  modelo.Ubicacion;
import  negocio.Calculo;
import  negocio.Red;

public class Coordinador {
	
	private Red red;
	private Calculo calculo;
	private Interfaz interfaz;
	public Red getRed() {return red;}
	
	public Red setRed(Red red) {return this.red = red;}
	
	public Calculo getCalculo() {return calculo;}
	
	public Calculo setCalculo(Calculo calculo) {return this.calculo = calculo;}
	
	
	public Interfaz getInterfaz() {return interfaz;}

	public void setInterfaz(Interfaz interfaz) {this.interfaz = interfaz;}

	
	public Conexion buscarConexion(Conexion conexion) {
		return null;
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
}
