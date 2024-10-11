package redUni.modelo;

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
	public Equipo () {
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
	}

	
	public String getCodigo() {return codigo;}

	public String getDescripcion() {return descripcion;}

	public String getMarca() {return marca;}

	public String getModelo() {return modelo;}

	public Ubicacion getUbicacion() {return ubicacion;}
	
	public TipoEquipo getTipoEquipo() {return tipoEquipo;}
	
	public boolean getEstado() {return estado;}
	
	public void setCodigo(String codigo) {this.codigo = codigo;}
	
	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
	
	public void setMarca(String marca) {this.marca = marca;}
	
	public void setModelo(String modelo) {this.modelo = modelo;}
	
	public void setDireccionIP(List<String> direccionIP) {this.direccionIP = direccionIP;}
	
	public void setUbicacion(Ubicacion ubicacion) {this.ubicacion = ubicacion;}
	
	public void setTipoEquipo(TipoEquipo tipoEquipo) {this.tipoEquipo = tipoEquipo;}
	
	public void setEstado(boolean estado) {this.estado = estado;}
	
	public List<String> getIPs() { return new ArrayList<>(direccionIP);}
	
	public Puerto agregarPuerto(TipoPuerto tipoPuerto, int cantidad) {
		Puerto puerto = new Puerto(tipoPuerto, cantidad);
		puertos.add(puerto);
		return puerto;
	}
	
	public List<String> getPuertos() {
		List<String> listaPuertos = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for (Puerto puerto : puertos) {
			sb.append(puerto.tipoPuerto.getCodigo() + "," + puerto.cantidad);
			listaPuertos.add(sb.toString());
		}
		return listaPuertos;
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return Objects.equals(codigo, other.codigo);
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

