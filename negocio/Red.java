package negocio;

import java.util.ArrayList;
import java.util.List;

import modelo.Conexion;
import modelo.Equipo;
import modelo.Ubicacion;
import servicios.SvcConexion;
import servicios.SvcEquipo;
import servicios.SvcUbicacion;
import servicios.itf.Servicios;

// se implementó el patrón de diseño Singleton
public class Red {

	private static Red redapp = null;

	private String nombre;

	private List<Conexion> conexiones;
	private List<Equipo> equipos;
	private List<Ubicacion> ubicaciones;

	private Servicios<Conexion> svcConexion;
	private Servicios<Equipo> svcEquipo;
	private Servicios<Ubicacion> svcUbicacion;

	public static Red getRed() {
		if (redapp == null)
			redapp = new Red();
		return redapp;
	}

	public Red() {
		equipos = new ArrayList<>();
		svcEquipo = new SvcEquipo();
		equipos.addAll(svcEquipo.buscarTodos());

		conexiones = new ArrayList<>();
		svcConexion = new SvcConexion();
		conexiones.addAll(svcConexion.buscarTodos());

		ubicaciones = new ArrayList<>();
		svcUbicacion = new SvcUbicacion();
		ubicaciones.addAll(svcUbicacion.buscarTodos());
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public List<Conexion> getConexiones() {
		return conexiones;
	}

	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public Equipo agregarEquipo(Equipo equipo) {

		equipos.add(equipo);
		svcEquipo.insertar(equipo);
		return equipo;
	}

	public Conexion agregarConexion(Conexion conexion) {

		conexiones.add(conexion);
		svcConexion.insertar(conexion);
		return conexion;
	}

	public Ubicacion agregarUbicacion(Ubicacion ubicacion) {

		ubicaciones.add(ubicacion);
		svcUbicacion.insertar(ubicacion);
		return ubicacion;
	}
}
