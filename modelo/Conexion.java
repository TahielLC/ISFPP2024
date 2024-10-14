package modelo;

public class Conexion {

	private Equipo equipo1;
	private Equipo equipo2;
	private TipoPuerto tipoPuerto1;
	private TipoPuerto tipoPuerto2;
	private TipoCable tipoCable;

	public Conexion() {

	}

	public Conexion(Equipo equipo1, TipoPuerto tipoPuerto1, Equipo equipo2, TipoPuerto tipoPuerto2,
			TipoCable tipoClable) {
		super();
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.tipoPuerto1 = tipoPuerto1;
		this.tipoPuerto2 = tipoPuerto2;
		this.tipoCable = tipoClable;
	}

	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}

	public TipoCable getTipoCable() {
		return tipoCable;
	}

	public void setTipoCable(TipoCable tipoClable) {
		this.tipoCable = tipoClable;
	}

	public TipoPuerto getTipoPuerto1() {
		return tipoPuerto1;
	}

	public void setTipoPuerto1(TipoPuerto tipoPuerto1) {
		this.tipoPuerto1 = tipoPuerto1;
	}

	public TipoPuerto getTipoPuerto2() {
		return tipoPuerto2;
	}

	public void setTipoPuerto2(TipoPuerto tipoPuerto2) {
		this.tipoPuerto2 = tipoPuerto2;
	}

	// Metodo para obtener la velocidad minima
	public int obtenerVelocidadMinima() {
		int velocidadPuerto1 = tipoPuerto1.getVelocidad();
		int velocidadPuerto2 = tipoPuerto2.getVelocidad();
		int velocidadCable = tipoCable.getVelocidad();

		return Math.min(velocidadCable, Math.min(velocidadPuerto1, velocidadPuerto2));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equipo1 == null) ? 0 : equipo1.hashCode());
		result = prime * result + ((equipo2 == null) ? 0 : equipo2.hashCode());
		result = prime * result + ((tipoPuerto1 == null) ? 0 : tipoPuerto1.hashCode());
		result = prime * result + ((tipoPuerto2 == null) ? 0 : tipoPuerto2.hashCode());
		result = prime * result + ((tipoCable == null) ? 0 : tipoCable.hashCode());
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
		Conexion other = (Conexion) obj;
		if (equipo1 == null) {
			if (other.equipo1 != null)
				return false;
		} else if (!equipo1.equals(other.equipo1))
			return false;
		if (equipo2 == null) {
			if (other.equipo2 != null)
				return false;
		} else if (!equipo2.equals(other.equipo2))
			return false;
		if (tipoPuerto1 == null) {
			if (other.tipoPuerto1 != null)
				return false;
		} else if (!tipoPuerto1.equals(other.tipoPuerto1))
			return false;
		if (tipoPuerto2 == null) {
			if (other.tipoPuerto2 != null)
				return false;
		} else if (!tipoPuerto2.equals(other.tipoPuerto2))
			return false;
		if (tipoCable == null) {
			if (other.tipoCable != null)
				return false;
		} else if (!tipoCable.equals(other.tipoCable))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Conexion [equipo1: " + equipo1
				+ ", tipoPuerto1: " + tipoPuerto1
				+ ", equipo2: " + equipo2
				+ ", tipoPuerto2: " + tipoPuerto2
				+ ", tipoCable: " + tipoCable + "]";
	}

}
