package red.gui.validaciones;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import red.aplicacion.Coordinador;
import red.modelo.Conexion;
import red.modelo.Equipo;
import red.modelo.TipoCable;
import red.modelo.TipoPuerto;

public class ValidacionesConexion {

	public boolean validarPuertoConexion(String codigo, String descripcion, String velocidad, Coordinador coordinador) {
		JTextField codigoPuerto = new JTextField();
		codigoPuerto.setText(codigo);

		JTextField descripcionPuerto = new JTextField();
		descripcionPuerto.setText(descripcion);

		JTextField velocidadPuerto = new JTextField();
		velocidadPuerto.setText(velocidad);

		String mensajeError = ValidacionesTipoPuerto.validarTipoPuerto(codigoPuerto, descripcionPuerto, velocidadPuerto,
				coordinador, false);
		if (mensajeError != null) {
			JOptionPane.showMessageDialog(null, mensajeError, "Error de validación", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public boolean validarCableConexion(String codigo, String descripcion, String velocidad) {
		JTextField codigoCable = new JTextField();
		codigoCable.setText(codigo);

		JTextField descripcionCable = new JTextField();
		descripcionCable.setText(descripcion);

		JTextField velocidadCable = new JTextField();
		velocidadCable.setText(velocidad); // Corregido: usar velocidad en lugar de descripcion

		String mensajeError = ValidacionesTipoCable.validarModificarTipoCable(descripcionCable, velocidadCable);
		if (mensajeError != null) {
			// Si hay un mensaje de error, la validación falló
			JOptionPane.showMessageDialog(null, mensajeError, "Error de validación", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	public boolean conexionExiste(Equipo equipo1, Equipo equipo2, Coordinador coordinador) {

		Conexion conexion = coordinador.getRed().obtenerConexion(equipo1, equipo2);
		if (conexion != null) {
			JOptionPane.showMessageDialog(null, "Ya existe la conexión", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public boolean ExisteEquipo(String codigoEquipo1, String codigoEquipo2, Coordinador coordinador) {

		Equipo equipo1 = coordinador.getRed().buscarEquipoPorCodigo(codigoEquipo1);

		Equipo equipo2 = coordinador.getRed().buscarEquipoPorCodigo(codigoEquipo2);

		if (equipo1 == null || equipo2 == null) {
			return false;
		}

		return true;
	}

	public boolean validarConexion(Conexion conexion) {
		TipoPuerto tp1 = conexion.getTipoPuerto1();
		TipoPuerto tp2 = conexion.getTipoPuerto2();
		TipoCable tc = conexion.getTipoCable();

		if (tp1.getCodigo() == null && tp1.getDescripcion() == null && tp1.getVelocidad() <= 0) {
			JOptionPane.showMessageDialog(null, "El puerto 1 esta vacio o es nulo.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (tp2.getCodigo() == null && tp2.getDescripcion() == null && tp2.getVelocidad() <= 0) {
			JOptionPane.showMessageDialog(null, "El puerto 2 esta vacio o es nulo.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (tc.getCodigo() == null && tc.getDescripcion() == null && tc.getVelocidad() <= 0) {
			JOptionPane.showMessageDialog(null, "El cable esta vacio o es nulo.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
