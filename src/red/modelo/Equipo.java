package red.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equipo {
	// atributos
	private String codigo;
	private String descripcion;
	private String marca;
	private String modelo;
	private List<String> direccionIP;
	private Ubicacion ubicacion;
	private TipoEquipo tipoEquipo;
	private List<Puerto> puertos;
	private boolean estado;

	// Constructores
	public Equipo() {
		this.direccionIP = new ArrayList<String>();
		this.puertos = new ArrayList<Puerto>();
	}

	public Equipo(String codigo, String descripcion, String marca, String modelo,
			Ubicacion ubicacion, TipoEquipo tipoEquipo, boolean estado) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.marca = marca;
		this.modelo = modelo;
		this.ubicacion = ubicacion;
		this.tipoEquipo = tipoEquipo;
		this.estado = estado;
		this.direccionIP = new ArrayList<String>();
		this.puertos = new ArrayList<Puerto>();
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public TipoEquipo getTipoEquipo() {
		return tipoEquipo;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setDireccionIP(List<String> direccionIP) {
		this.direccionIP = direccionIP;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public void setTipoEquipo(TipoEquipo tipoEquipo) {
		this.tipoEquipo = tipoEquipo;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<String> getIPs() {
		return new ArrayList<>(direccionIP);
	}

	public Puerto agregarPuerto(TipoPuerto tipoPuerto, int cantidad) {
		if (tipoPuerto == null) {
			System.out.println("Error: tipoPuerto es null al intentar agregar un puerto.");
		}
		Puerto puerto = new Puerto(tipoPuerto, cantidad);
		puertos.add(puerto);
		return puerto;
	}

	public List<String> getPuertos() {
		List<String> listaPuertos = new ArrayList<>();

		for (Puerto puerto : puertos) {
			StringBuilder sb = new StringBuilder();
			if (puerto.tipoPuerto != null) {
				sb.append(puerto.tipoPuerto.getCodigo())
						.append(",")
						.append(puerto.tipoPuerto.getDescripcion())
						.append(",")
						.append(puerto.tipoPuerto.getVelocidad())
						.append(":").append(puerto.cantidad);
				listaPuertos.add(sb.toString());
			}
		}
		return listaPuertos;
	}

	public List<String> getPuertosParaArchivo() {
		List<String> listaPuertos = new ArrayList<>();
	
		for (Puerto puerto : puertos) {
			if (puerto.tipoPuerto != null) {
				String codigo = puerto.tipoPuerto.getCodigo();
				int cantidad = puerto.cantidad;
				listaPuertos.add(codigo + "," + cantidad);
			}
		}
		return listaPuertos;
	}
	

	public void setPuertos(String[] puertos) {

		List<Puerto> nuevosPuertos = new ArrayList<>();
		for (String puertoStr : puertos) {
			String[] partes = puertoStr.split(":");

			String tipoPuertoDatos = partes[0];
			int cantidad;

			cantidad = Integer.parseInt(partes[1].trim());

			String[] tipoPuertoPartes = tipoPuertoDatos.split(",");

			// Crear TipoPuerto y Puerto
			String codigo = tipoPuertoPartes[0].trim();
			String descripcion = tipoPuertoPartes[1].trim();
			int velocidad;

			velocidad = Integer.parseInt(tipoPuertoPartes[2].trim());

			// Crear el TipoPuerto y Puerto
			TipoPuerto tipoPuerto = new TipoPuerto(codigo, descripcion, velocidad);
			Puerto puerto = new Puerto(tipoPuerto, cantidad);

			// Añadir el nuevo puerto a la lista
			nuevosPuertos.add(puerto);
		}
		this.puertos = nuevosPuertos;
	}

	public int getCantPuertos(String codPuerto) {
		for (Puerto puerto : puertos) {
			if (codPuerto.equals(puerto.tipoPuerto.getCodigo()))
				return puerto.cantidad;
		}
		return 0;
	}

	public String agregarIp(String ip) {
		direccionIP.add(ip);
		return ip;
	}

	@Override
	public String toString() {
		return "Equipo [codigo=" + codigo + ", descripcion=" + descripcion + ", marca=" + marca + ", modelo=" + modelo
				+ ", direccionIP=" + direccionIP + ", ubicacion=" + ubicacion + ", tipoEquipo=" + tipoEquipo
				+ ", puertos=" + puertos + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Equipo other = (Equipo) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	// Clase privada de Equipo
	private class Puerto {
		private TipoPuerto tipoPuerto;
		private int cantidad;

		public Puerto(TipoPuerto tipoPuerto, int cantidad) {
			this.tipoPuerto = tipoPuerto;
			this.cantidad = cantidad;
		}

	}
}
