package red.aplicacion;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import red.gui.consulta.Consultar;
import red.gui.consulta.Ventana;
import red.gui.datos.Manipular;
import red.interfaz.Interfaz;
import red.modelo.Conexion;
import red.modelo.Equipo;
import red.modelo.TipoCable;
import red.modelo.TipoEquipo;
import red.modelo.TipoPuerto;
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

	public void setConsultar(Consultar consultar) {
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

	public List<Conexion> listarConexiones() {
		return red.getConexiones();
	}

	public List<Equipo> listarEquipos() {
		return red.getEquipos();
	}

	public List<Ubicacion> listarUbicaciones() {
		return red.getUbicaciones();
	}

	public List<TipoCable> listarTipoCable() {
		return red.getTipoCables();
	}

	public List<TipoEquipo> listarTipoEquipo() {
		return red.getTipoEquipos();
	}

	public List<TipoPuerto> listarTipoPuerto() {
		return red.getTipoPuertos();
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

	public void insertarConexion(Conexion conexion) {
		red.agregarConexion(conexion);
	}

	public void modificarConexion(Conexion conexion) {
		red.modificarConexion(conexion);
	}

	public void borrarConexion(Conexion conexion) {
		red.borrarConexion(conexion);
	}

	public void insertarUbicacion(Ubicacion ubicacion) {
		red.agregarUbicacion(ubicacion);
	}

	public void modificarUbicacion(Ubicacion ubicacion) {
		red.modificarUbicacion(ubicacion);
	}

	public void borrarUbicacion(Ubicacion ubicacion) {
		red.borrarUbicacion(ubicacion);
	}

	public void insertarTipoCable(TipoCable tipoCable) {
		red.agregarTipoCable(tipoCable);
	}

	public void modificarTipoCable(TipoCable tipoCable) {
		red.modificarTipoCable(tipoCable);
	}

	public void borrarTipoCable(TipoCable tipoCable) {
		red.borrarTipoCable(tipoCable);
	}

	public void insertarTipoEquipo(TipoEquipo tipoEquipo) {
		red.agregarTipoEquipo(tipoEquipo);
	}

	public void modificarTipoEquipo(TipoEquipo tipoEquipo) {
		red.modificarTipoEquipo(tipoEquipo);
	}

	public void borrarTipoEquipo(TipoEquipo tipoEquipo) {
		red.borrarTipoEquipo(tipoEquipo);
	}

	public void insertarTipoPuerto(TipoPuerto tipoPuerto) {
		red.agregarTipoPuerto(tipoPuerto);
	}

	public void modificarTipoPuerto(TipoPuerto tipoPuerto) {
		red.modificarTipoPuerto(tipoPuerto);
	}

	public void borrarTipoPuerto(TipoPuerto tipoPuerto) {
		red.borrarTipoPuerto(tipoPuerto);
	}
}
