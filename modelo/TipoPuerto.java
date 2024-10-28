package modelo;

import java.util.Objects;

public class TipoPuerto {
	private String codigo;
	private String descripcion;
	private int velocidad;
	
	public TipoPuerto() {
	
	}
	public TipoPuerto(String codigo, String descripcion, int velocidad) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.velocidad = velocidad;
	}

	public String getCodigo() {return codigo;}

	public String getDescripcion() {return descripcion;}

	public int getVelocidad() {return velocidad;}

	public void setCodigo(String codigo) {
		this.codigo = codigo;	
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoPuerto other = (TipoPuerto) obj;
		return Objects.equals(codigo, other.codigo);
	}	
	
	@Override
	public String toString() {
		return "TipoPuerto [codigo=" + codigo + ", descripcion=" + descripcion + ", velocidad=" + velocidad + "]";
	}
}
