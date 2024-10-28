package  cargadatosDao;

import java.util.Hashtable;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.ResourceBundle;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.FormatterClosedException;

import java.io.FileNotFoundException;

import  datosDao.ConexionDao;
import  datosDao.EquipoDao;
import  datosDao.TipoCableDao;
import  datosDao.TipoPuertoDao;
import  modelo.Conexion;
import  modelo.Equipo;
import  modelo.TipoCable;
import  modelo.TipoPuerto;


public class ConexionSecuencialDao implements ConexionDao {

    private List<Conexion> list;
    private String nombre;
    private Hashtable<String, Equipo> equipos;
    private Hashtable<String, TipoCable> tiposCable;
    private Hashtable<String, TipoPuerto> tipoPuertos;
    private boolean actualizar;

    public ConexionSecuencialDao() {
        equipos = cargarEquipos();
        tiposCable = cargarCables();
        tipoPuertos = cargarPuertos();
        ResourceBundle rb = ResourceBundle.getBundle("config");
        nombre = rb.getString("conexion");
        actualizar = true;

    }
    
    private Hashtable<String, Equipo> cargarEquipos() {
        Hashtable<String, Equipo> equipos = new Hashtable<String, Equipo>();
        EquipoDao equipoDao = new EquipoSecuencialDao();
        List<Equipo> ds = equipoDao.buscarTodEquipos();
        for (Equipo equipo : ds) {
            equipos.put(equipo.getCodigo(), equipo);
        }
        return equipos;
    }

    private Hashtable<String, TipoCable> cargarCables() {
        Hashtable<String, TipoCable> tipoCables = new Hashtable<String, TipoCable>();
        TipoCableDao tipoCableDao = new TipoCableSecuencialDao();
        List<TipoCable> ds = tipoCableDao.buscarTodTipoCables();
        for (TipoCable cable : ds) {
            tipoCables.put(cable.getCodigo(), cable);

        }
        return tipoCables;
    }
    
    private Hashtable<String, TipoPuerto> cargarPuertos() {
        Hashtable<String, TipoPuerto> tipoPuertos = new Hashtable<String, TipoPuerto>();
        TipoPuertoDao tipoPuertoDao = new TipoPuertoSecuencialDao();
        List<TipoPuerto> ds = tipoPuertoDao.buscarTodTipoPuertos();
        for (TipoPuerto puerto : ds) {
            tipoPuertos.put(puerto.getCodigo(), puerto);
        }
        return tipoPuertos;
    }

    private List<Conexion> leerDeArchivo(String file) {
        List<Conexion> list = new ArrayList<>();
        Scanner inFile = null;
        try {
            inFile = new Scanner(new File(file));
            inFile.useDelimiter("\\s*;\\s*");
            while (inFile.hasNext()) {
                Conexion c = new Conexion();
                c.setEquipo1(equipos.get(inFile.next()));
                c.setTipoPuerto1(tipoPuertos.get(inFile.next()));
                c.setEquipo2(equipos.get(inFile.next()));
                c.setTipoPuerto2(tipoPuertos.get(inFile.next()));
                c.setTipoCable(tiposCable.get(inFile.next()));
                list.add(c);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Error opening file.");
            fileNotFoundException.printStackTrace();
        } catch (NoSuchElementException noSuchElementException) {
            System.err.println("Error in file record structure");
            noSuchElementException.printStackTrace();
        } catch (IllegalStateException illegalStateException) {
            System.err.println("Error reading from file.");
            illegalStateException.printStackTrace();
        } finally {
            if (inFile != null)
                inFile.close();
        }
        return list;

    }

    private void escrituraDeArchivo(List<Conexion> list, String file) {

    	Formatter archivoSalida = null;
        try {
            archivoSalida = new Formatter(file);
            for (Conexion conexion : list) {
                archivoSalida.format("%s;%s;%s;%s;%s;",
                        conexion.getEquipo1().getCodigo(),
                        conexion.getTipoPuerto1().getCodigo(),
                        conexion.getEquipo2().getCodigo(),
                        conexion.getTipoPuerto2().getCodigo(),
                        conexion.getTipoCable().getCodigo());
            }
        } catch (FileNotFoundException fileNotFoundException) {

            System.err.println("Error en la creacion del archivo");
        } catch (FormatterClosedException formatterClosedException) {
            System.err.println("Error en la escritura del archivo");
        } finally {
            if (archivoSalida != null) {
                archivoSalida.close();
            }
        }

    }

    @Override
    public void insertar(Conexion conexion) {
        list.add(conexion);
        escrituraDeArchivo(list, nombre);
        actualizar = true;
    }

    @Override
    public void actualizar(Conexion conexion) {
        int pos = list.indexOf(conexion);
        list.set(pos, conexion);
        escrituraDeArchivo(list, nombre);
        actualizar = true;

    }

    @Override
    public void borrar(Conexion conexion) {
        list.remove(conexion);
        escrituraDeArchivo(list, nombre);
        actualizar = true;

    }

    @Override
    public List<Conexion> bucarConexiones() {
        if (actualizar) {
            list = leerDeArchivo(nombre);
            actualizar = false;
        }
        return list;
    }

}
