package red.gui.cargar;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import red.aplicacion.Coordinador;
import red.modelo.Conexion;
import red.modelo.Equipo;
import red.modelo.TipoCable;
import red.modelo.TipoEquipo;
import red.modelo.TipoPuerto;
import red.modelo.Ubicacion;

public class CargarDatos {

    public static TipoCable crearTipoCable(String codigoT, JTextField descripcionT,
            JTextField velocidadT) {

        // Obtenemos los valores de los campos de texto
        String codigo = codigoT;
        String descripcion = descripcionT.getText();
        String vel = velocidadT.getText();
        int velocidad = Integer.parseInt(vel);

        return new TipoCable(codigo, descripcion, velocidad);
    }

    public static TipoPuerto crearTipoPuerto(String codigoT, JTextField descripcionT,
            JTextField velocidadT) {

        // Obtenemos los valores de los campos de texto
        String codigo = codigoT;
        String descripcion = descripcionT.getText();
        String vel = velocidadT.getText();
        int velocidad = Integer.parseInt(vel);
        System.out.println("Velocidad en crearTipoPuerto: "+ velocidad);
       
        return new TipoPuerto(codigo, descripcion, velocidad);
    }

    public static TipoEquipo crearTipoEquipo(String codigoT, JTextField descripcionT) {

        // Obtenemos los valores de los campos de texto
        String codigo = codigoT;
        String descripcion = descripcionT.getText();

        return new TipoEquipo(codigo, descripcion);
    }

    public static Equipo cargarEquipo(String codigoT, JTextField descripcionT, JTextField marcaT,
            JTextField modeloT, JComboBox<String> direccionipCB, String codigoUbicacion,
            String codigoTipoEquipo, JComboBox<String> puertosCB, JComboBox<String> activoT,
            Coordinador coordinador, boolean esAgregar) {
        String codigo = codigoT;
        String descripcion = descripcionT.getText();
        String marca = marcaT.getText();
        String modelo = modeloT.getText();
        Ubicacion ubicacion = new Ubicacion();
        TipoEquipo tipoEquipo = new TipoEquipo();
        if(esAgregar){
            ubicacion = coordinador.getRed().buscarUbicacionPorCodigo(codigoUbicacion);
            tipoEquipo = coordinador.getRed().buscarTipoEquipoPorCodigo(codigoTipoEquipo);
        } else {
            ubicacion = coordinador.getRed().buscarUbicacionPorCodigo(codigoUbicacion);
            if(ubicacion == null){
                return null;
            }
            tipoEquipo = coordinador.getRed().buscarTipoEquipoPorCodigo(codigoTipoEquipo);
            if(tipoEquipo == null){
                return null;
            }

        }
        // Obtenemos el estado seleccionado
        String a = (String) activoT.getSelectedItem();
        // Si esta Activo es true, de lo contrario es false
        boolean estado = "Activo".equals(a);

        // instanciamos un objeto de tipo Equipo
        Equipo equipo = new Equipo(codigo, descripcion, marca, modelo, ubicacion, tipoEquipo, estado);

        String[] direccionIPdatos = new String[direccionipCB.getItemCount()];
        // System.out.println("Cantidad de direcciones en direccionipCB: " +
        // direccionipCB.getItemCount());

        for (int i = 0; i < direccionipCB.getItemCount(); i++) {
            direccionIPdatos[i] = direccionipCB.getItemAt(i);
            // System.out.println("Direccion agregado: " + direccionIPdatos[i]);
        }

        for (int i = 0; i < direccionIPdatos.length; i++) {
            equipo.agregarIp(direccionIPdatos[i]);
            // System.out.println("Direccion: " + direccionIPdatos[i]);
        }

        String[] puertoDatos = new String[puertosCB.getItemCount()];
        for (int i = 0; i < puertosCB.getItemCount(); i++) {
            puertoDatos[i] = puertosCB.getItemAt(i);
        }

        for (String puertoData : puertoDatos) {
            String[] parts = puertoData.split(":");
            String[] tipoPuertoData = parts[0].split(",");

            try {
                // Crear el TipoPuerto
                String codigostr = tipoPuertoData[0].trim();
                String descripcionstr = tipoPuertoData[1].trim();
                int velocidad = Integer.parseInt(tipoPuertoData[2].trim());

                TipoPuerto tipoPuerto = new TipoPuerto(codigostr, descripcionstr, velocidad);

                // Convertir la cantidad y agregar puerto al equipo
                int cantidad = Integer.parseInt(parts[1].trim());
                equipo.agregarPuerto(tipoPuerto, cantidad);

                if (!coordinador.listarTipoPuerto().contains(tipoPuerto)) {
                    coordinador.insertarTipoPuerto(tipoPuerto);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error en el formato de los datos del puerto.",
                        "Formato Incorrecto", JOptionPane.ERROR_MESSAGE);
            }
        }
        return equipo;
    }

    public static Ubicacion cargarUbicacion(JTextField codigoT, JTextField descrpcionT) {
        String codigo = codigoT.getText();
        String descripcion = descrpcionT.getText();
        return new Ubicacion(codigo, descripcion);
    }

    public Conexion cargarConexion(Equipo equipo1, Equipo equipo2, TipoPuerto tipoPuerto1, TipoPuerto tipoPuerto2, TipoCable tipoCable) {

        return new Conexion(equipo1, tipoPuerto1, equipo2, tipoPuerto2, tipoCable);
    }
}
