package red.gui.validaciones;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import red.aplicacion.Coordinador;
import red.modelo.Equipo;

public class ValidacionesEquipo {
	public static boolean validarAgregarEquipo(JTextField codigoT, JTextField descripcionT, JTextField marcaT,
			JTextField modeloT, JComboBox<String> direccionipCB, JTextField ubicacionT, JTextField tipoEquipoT,
			JComboBox<String> puertosCB, Coordinador coordinador) {

		String codigo = codigoT.getText();
		String descripcion = descripcionT.getText();
		String marca = marcaT.getText();
		String modelo = modeloT.getText();
		boolean validar = true;
		if (codigo == null || codigo.isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"El codigo esta vacio o nulo ", "Error: ",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// Verificar si ya existe el equipo
		Equipo equipo = coordinador.getRed().buscarEquipoPorCodigo(codigo);
		if (equipo != null) {
			JOptionPane.showMessageDialog(null,
					"El equipo ya existe ", "Error: ",
					JOptionPane.ERROR_MESSAGE);
			validar = false;
			return validar;
		}

		if (descripcion == null || descripcion.isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"La descripcion esta vacio o nulo ", "Error: ",
					JOptionPane.ERROR_MESSAGE);
			validar = false;
			return validar;
		}

		String[] ubicacionDatos = ubicacionT.getText().split(",");

		JTextField codigoUbicacion = new JTextField(ubicacionDatos[0]);
		JTextField descripcionUbicacion = new JTextField(ubicacionDatos[1]);

		validar = ValidacionesUbicacion.validarUbicacion(codigoUbicacion, descripcionUbicacion, coordinador, false);

		if (!validar) {
			return validar;
		}

		if (marca == null || marca.isEmpty()) {
			JOptionPane.showMessageDialog(null, "La marca esta vacio o nulo ", "Error: ", JOptionPane.ERROR_MESSAGE);
			validar = false;
			return validar;
		}

		if (modelo == null || modelo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "El modelo esta vacio o nulo ", "Error: ", JOptionPane.ERROR_MESSAGE);
			validar = false;
			return validar;
		}

		validar = validarDireccionIP(direccionipCB);
		if (!validar) {
			return validar;
		}

		validar = validarPuertos(puertosCB, coordinador);
		if (!validar) {
			return validar;
		}

		return validar;
	}

	public static boolean validarModificarEquipo(JTextField descripcionT, JTextField marcaT,
			JTextField modeloT, JComboBox<String> direccionipCB, JTextField ubicacionT, JTextField tipoEquipoT,
			JComboBox<String> puertosCB, Coordinador coordinador) {

		String descripcion = descripcionT.getText();
		String marca = marcaT.getText();
		String modelo = modeloT.getText();
		boolean validar = true;
		if (descripcion == null || descripcion.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No especifica la descripcion del equipo. ", "Error: ",
					JOptionPane.ERROR_MESSAGE);
			validar = false;
			return validar;
		}

		if (marca == null || marca.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No especifica la marca del equipo. ", "Error: ",
					JOptionPane.ERROR_MESSAGE);
			validar = false;
			return validar;
		}

		if (modelo == null || modelo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No especifica el modelo del equipo. ", "Error: ",
					JOptionPane.ERROR_MESSAGE);
			validar = false;
			return validar;
		}

		String[] ubicacionDatos = ubicacionT.getText().split(",");

		JTextField codigoUbicacion = new JTextField(ubicacionDatos[0]);
		JTextField descripcionUbicacion = new JTextField(ubicacionDatos[1]);

		validar = ValidacionesUbicacion.validarUbicacion(codigoUbicacion, descripcionUbicacion, coordinador, false);
		if (!validar) {
			return validar;
		}
		// Verificamos la direccion ip
		validar = validarDireccionIP(direccionipCB);
		if (!validar) {
			return validar;
		}
		// verificamos el tipo de equipo
		validar = validarElTipoEquipo(tipoEquipoT, coordinador);
		if (!validar) {
			return validar;
		}
		// Verificamos los puertos
		validar = validarPuertos(puertosCB, coordinador);
		if (!validar) {
			return validar;
		}
		// Si todo salio bien, retorna true
		return validar;
	}

	private static boolean validarDireccionIP(JComboBox<String> direccionipCB) {
		String[] direccionIPString = new String[direccionipCB.getItemCount()];
		for (int i = 0; i < direccionipCB.getItemCount(); i++) {
			direccionIPString[i] = direccionipCB.getItemAt(i);
		}
		// verificar que haya direcciones ip cargados
		if (direccionIPString.length == 0) {
			JOptionPane.showMessageDialog(null, "No hay direccion ip cargados", "Error: ",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// se verifica si el formato es correcto
		for (int i = 0; i < direccionIPString.length; i++) {
			String direccionIP = direccionIPString[i];
			if (!validarIP(direccionIP)) {
				JOptionPane.showMessageDialog(null,
						"Formato incorrecto de la direccion ip EJ: 255.255.255.255 " + direccionIP, "Error: ",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		// verificar que no haya direcciones ip repetidas
		for (int i = 0; i < direccionIPString.length; i++) {
			String direccionIP = direccionIPString[i];
			for (int j = i + 1; j < direccionIPString.length; j++) {
				if (direccionIP.equals(direccionIPString[j])) {
					JOptionPane.showMessageDialog(null, "La direccion " + direccionIP + " Esta repetido",
							"Error: ",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}
		return true;
	}

	private static boolean validarIP(String ip) {
		String[] partes = ip.split("\\.");
		if (partes.length != 4) {
			return false;
		}
		for (String parte : partes) {
			// Verifica que la parte sea numérica
			if (!parte.matches("^[0-9]+$") || (parte.startsWith("0") && parte.length() > 1)) {
				return false;
			}

			int numero = Integer.parseInt(parte);

			if (numero < 0 || numero > 255) {
				return false;
			}
		}
		return true;
	}

	private static boolean validarLosPuertos(String[] puertos, Coordinador coordinador) {
		for (String puerto : puertos) {
			String[] partes = puerto.split(":");
			String[] tipoPuerto = partes[0].split(",");

			if (partes.length != 2 || tipoPuerto.length != 3) {
				JOptionPane.showMessageDialog(null,
						"Formato incorrecto del puerto. Debe ser 'codigo,descripcion,velocidad:cantidad'",
						"Error: ", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			JTextField codigoPuero = new JTextField(tipoPuerto[0].trim());
			JTextField descripcionPuerto = new JTextField(tipoPuerto[1].trim());
			JTextField velocidadPuerto = new JTextField(tipoPuerto[2].trim());

			boolean validar = ValidacionesTipoPuerto.validarTipoPuerto(codigoPuero, descripcionPuerto, velocidadPuerto,
					coordinador, false);
			if (!validar) {
				return false;
			}
			String cantidad = partes[1];
			if (cantidad == null || cantidad.matches("[a-zA-Z ]+")) {
				JOptionPane.showMessageDialog(null,
						"La cantidad no puede ser caracteres",
						"Error: ", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}

	private static boolean validarElTipoEquipo(JTextField tipoEquipoT, Coordinador coordinador) {
		boolean validar;
		String[] tipoEquipoDatos = tipoEquipoT.getText().split(",");
		if (tipoEquipoDatos.length != 2) {
			JOptionPane.showMessageDialog(null,
					"Formato incorrecto del tipo de equipo. Debe ser 'codigo,descripcion'",
					"Error: ", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String codigoTipoEquipo = tipoEquipoDatos[0];
		String descripcionTipoEquipo = tipoEquipoDatos[1];

		JTextField c = new JTextField(codigoTipoEquipo);
		JTextField s = new JTextField(descripcionTipoEquipo);

		validar = ValidacionesTipoEquipo.validarTipoEquipo(c, s, coordinador, false);
		if (!validar) {
			return false;
		}
		return true;
	}

	private static boolean validarPuertos(JComboBox<String> puertosCB, Coordinador coordinador) {

		String[] puertos = new String[puertosCB.getItemCount()];
		boolean validar = true;
		for (int i = 0; i < puertosCB.getItemCount(); i++) {
			puertos[i] = puertosCB.getItemAt(i);
		}

		if (!validarLosPuertos(puertos, coordinador)) {
			validar = false;
			return validar;
		}
		return validar;
	}
}
