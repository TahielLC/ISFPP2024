package red.negocio;

import java.util.ArrayList;
import java.util.List;

import red.modelo.Conexion;
import red.modelo.Equipo;
import red.modelo.Ubicacion;
import red.servicios.SvcConexion;
import red.servicios.SvcEquipo;
import red.servicios.SvcUbicacion;
import red.servicios.itf.Servicios;
import red.excepciones.EquipoExisteException;

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

	public void agregarEquipo(Equipo equipo) {
		if(equipos.contains(equipo))
			throw new EquipoExisteException();
		equipos.add(equipo);
		svcEquipo.insertar(equipo);
	}
	
	public void modificarEquipo(Equipo equipo) {
		int pos = equipos.indexOf(equipo);
		equipos.set(pos, equipo);
		svcEquipo.actualizar(equipo);
	}
	
	public void borrarEquipo(Equipo equipo) {
		Equipo e = buscarEquipo(equipo);
		equipos.remove(e);
		svcEquipo.borrar(e);
	}
	public Equipo buscarEquipo(Equipo equipo) {
		int pos = equipos.indexOf(equipo);
		if(pos == -1) {
			return null;
		}
		return equipos.get(pos);
	}
	
	public Equipo buscarEquipoPorCodigo(String codigo) {
		for(Equipo e: equipos) {
			if(e.getCodigo().equals(codigo)) {
				return e;
			}
		}
		return null;
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
