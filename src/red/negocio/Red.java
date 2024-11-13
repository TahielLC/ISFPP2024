package negocio;

import java.util.ArrayList;
import java.util.List;

import excepciones.EquipoExisteException;
import modelo.Conexion;
import modelo.Equipo;
import modelo.TipoCable;
import modelo.TipoEquipo;
import modelo.TipoPuerto;
import modelo.Ubicacion;
import servicios.SvcConexion;
import servicios.SvcEquipo;
import servicios.SvcUbicacion;
import servicios.SvcTipoCable;
import servicios.SvcTipoEquipo;
import servicios.SvcTipoPuerto;

import servicios.itf.Servicios;

// se implementó el patrón de diseño Singleton
public class Red {

	private static Red redapp = null;

	private String nombre;

	private List<Conexion> conexiones;
	private List<Equipo> equipos;
	private List<Ubicacion> ubicaciones;
	private List<TipoCable> tipoCables;
	private List<TipoEquipo> tipoEquipos;
	private List<TipoPuerto> tipoPuertos;
	
	private Servicios<Conexion> svcConexion;
	private Servicios<Equipo> svcEquipo;
	private Servicios<Ubicacion> svcUbicacion;
	private Servicios<TipoCable> svcTipoCable;
	private Servicios<TipoEquipo> svcTipoEquipo;
	private Servicios<TipoPuerto> svcTipoPuerto;

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
		
		tipoCables = new ArrayList<>();
		svcTipoCable = new SvcTipoCable();
		tipoCables.addAll(svcTipoCable.buscarTodos());
		
		tipoEquipos = new ArrayList<>();
		svcTipoEquipo = new SvcTipoEquipo();
		tipoEquipos.addAll(svcTipoEquipo.buscarTodos());
		
		tipoPuertos = new ArrayList<>();
		svcTipoPuerto = new SvcTipoPuerto();
		tipoPuertos.addAll(svcTipoPuerto.buscarTodos());
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
		
	public List<TipoCable> getTipoCables() {
		return tipoCables;
	}

	public List<TipoEquipo> getTipoEquipos() {
		return tipoEquipos;
	}

	public List<TipoPuerto> getTipoPuertos() {
		return tipoPuertos;
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
	public void modificarConexion(Conexion conexion) {
		int pos = conexiones.indexOf(conexion);
		conexiones.set(pos, conexion);
		svcConexion.actualizar(conexion);
	}
	
	public void borrarConexion(Conexion conexion) {
		Conexion c = buscarConexion(conexion);
		conexiones.remove(c);
		svcConexion.borrar(c);
	}
	
	public Conexion buscarConexion(Conexion conexion) {
		int pos = conexiones.indexOf(conexion);
		if(pos == -1) {
			return null;
		}
		return conexiones.get(pos);
	}
	public Ubicacion agregarUbicacion(Ubicacion ubicacion) {
		ubicaciones.add(ubicacion);
		svcUbicacion.insertar(ubicacion);
		return ubicacion;
	}
	
	public void modificarUbicacion(Ubicacion ubicacion) {
		int pos = ubicaciones.indexOf(ubicacion);
		ubicaciones.set(pos, ubicacion);
		svcUbicacion.actualizar(ubicacion);
	}
	
	public void borrarUbicacion(Ubicacion ubicacion) {
		Ubicacion u = buscarUbicacion(ubicacion);
		ubicaciones.remove(u);
		svcUbicacion.borrar(u);
	}
	
	public Ubicacion buscarUbicacion(Ubicacion ubicacion) {
		int pos= ubicaciones.indexOf(ubicacion);
		if(pos == -1) {
			return null;
		}
		return ubicaciones.get(pos);
	}
	
	public TipoCable agregarTipoCable(TipoCable tipoCable) {
		tipoCables.add(tipoCable);
		svcTipoCable.insertar(tipoCable);
		return tipoCable;
	}
	
	public void modificarTipoCable(TipoCable tipoCable) {
		int pos = tipoCables.indexOf(tipoCable);
		tipoCables.set(pos, tipoCable);
		svcTipoCable.actualizar(tipoCable);
	}
	
	public void borrarTipoCable(TipoCable tipoCable) {
		TipoCable tc = buscarTipoCable(tipoCable);
		tipoCables.remove(tc);
		svcTipoCable.borrar(tc);
	}
	
	public TipoCable buscarTipoCable(TipoCable tipoCable) {
		int pos= tipoCables.indexOf(tipoCable);
		if(pos == -1) {
			return null;
		}
		return tipoCables.get(pos);
	}
	
	public TipoEquipo agregarTipoEquipo(TipoEquipo tipoEquipo) {
		tipoEquipos.add(tipoEquipo);
		svcTipoEquipo.insertar(tipoEquipo);
		return tipoEquipo;
	}
	
	public void modificarTipoEquipo(TipoEquipo tipoEquipo) {
		int pos = tipoEquipos.indexOf(tipoEquipo);
		tipoEquipos.set(pos, tipoEquipo);
		svcTipoEquipo.actualizar(tipoEquipo);
	}
	
	public void borrarTipoEquipo(TipoEquipo tipoEquipo) {
		TipoEquipo te = buscarTipoEquipo(tipoEquipo);
		tipoEquipos.remove(te);
		svcTipoEquipo.borrar(te);
	}
	
	public TipoEquipo buscarTipoEquipo(TipoEquipo tipoEquipo) {
		int pos= tipoEquipos.indexOf(tipoEquipo);
		if(pos == -1) {
			return null;
		}
		return tipoEquipos.get(pos);
	}
	
	public TipoPuerto agregarTipoPuerto(TipoPuerto tipoPuerto) {
		tipoPuertos.add(tipoPuerto);
		svcTipoPuerto.insertar(tipoPuerto);
		return tipoPuerto;
	}
	
	public void modificarTipoPuerto(TipoPuerto tipoPuerto) {
		int pos = tipoPuertos.indexOf(tipoPuerto);
		tipoPuertos.set(pos, tipoPuerto);
		svcTipoPuerto.actualizar(tipoPuerto);
	}
	
	public void borrarTipoPuerto(TipoPuerto tipoPuerto) {
		TipoPuerto tp = buscarTipoPuerto(tipoPuerto);
		tipoPuertos.remove(tp);
		svcTipoPuerto.borrar(tp);
	}
	
	public TipoPuerto buscarTipoPuerto(TipoPuerto tipoPuerto) {
		int pos= tipoPuertos.indexOf(tipoPuerto);
		if(pos == -1) {
			return null;
		}
		return tipoPuertos.get(pos);
	}
}
