package redUni.modelo;

public class TipoCable {
	private String codigo;
	private String descripcion;
	private int velocidad;
	
	public TipoCable() {
	
	}
	
	public TipoCable(String codigo, String descripcion, int velocidad) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.velocidad = velocidad;
	}

	public String getCodigo() {return codigo;}

	public void setCodigo(String codigo) {this.codigo = codigo;}

	public String getDescripcion() {return descripcion;}

	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

	public int getVelocidad() {return velocidad;}

	public void setVelocidad(int velocidad) {this.velocidad = velocidad;}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoCable other = (TipoCable) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "TipoCable [codigo=" + codigo + ", descripcion=" + descripcion + ", velocidad=" + velocidad + "]";
	}
}
