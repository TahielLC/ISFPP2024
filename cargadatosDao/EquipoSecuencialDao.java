package redUni.cargadatosDao;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Formatter;
import java.util.FormatterClosedException;

import redUni.datosDao.EquipoDao;
import redUni.datosDao.TipoEquipoDao;
import redUni.datosDao.TipoPuertoDao;
import redUni.datosDao.UbicacionDao;
import redUni.modelo.Equipo;
import redUni.modelo.TipoEquipo;
import redUni.modelo.TipoPuerto;
import redUni.modelo.Ubicacion;

public class EquipoSecuencialDao implements EquipoDao {

    private String nombre;
    private List<Equipo> list;
    private Hashtable<String, Ubicacion> ubicaciones;
    private Hashtable<String, TipoEquipo> tipoEquipos;
    private Hashtable<String, TipoPuerto> tiposPuertos;
    private boolean actualizar;

    public EquipoSecuencialDao() {
        ubicaciones = cargarUbicaciones();
        tipoEquipos = cargarTipoEquipos();
        tiposPuertos = cargarTipoPuertos();
        // tipoPuertos = cargarTipoPuertos();
        ResourceBundle rb = ResourceBundle.getBundle("config");
        nombre = rb.getString("equipo");
        actualizar = true;

    }
    
    private List<Equipo> leerDeArchivo(String file) {
        List<Equipo> list = new ArrayList<>();
        Scanner inFile = null;

        try {
            inFile = new Scanner(new File(file));
            inFile.useDelimiter("\\s*;\\s*"); // delimitador para los campos

            while (inFile.hasNext()) {
                Equipo eq = new Equipo();
                eq.setCodigo(inFile.next());
                eq.setDescripcion(inFile.next());
                eq.setMarca(inFile.next());
                eq.setModelo(inFile.next());
                // Asignar el tipo de equipo y la ubicación usando los mapas precargados
                eq.setTipoEquipo(tipoEquipos.get(inFile.next()));
                eq.setUbicacion(ubicaciones.get(inFile.next()));
                
                // Leo el campo Puertos
                String infoPuertos = inFile.next();
                // Puertos: puede haber varios puertos en una línea separados por comas
                String[] puertosPartes = infoPuertos.split(",");
                for (int i = 0; i < puertosPartes.length; i += 2) {
                    String subcampo1_tipoPuerto = puertosPartes[1];
                    int subcampo2_cantPuertos = Integer.parseInt(puertosPartes[i + 1]);
                    eq.agregarPuerto(tiposPuertos.get(subcampo1_tipoPuerto), subcampo2_cantPuertos);
                }

                // Leo el campo IP y lo divido si hay más de una IP
                String infoIPs = inFile.next();
                String ips[] = infoIPs.split(","); // Dividir las IPs por coma
                for (String ip : ips) {
                    eq.agregarIp(ip.trim()); // Agregar cada IP a la lista
                }
                
                eq.setEstado(Boolean.parseBoolean(inFile.next()));
                // Agregar equipo a la lista
                list.add(eq);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Error al abrir el archivo");
            fileNotFoundException.printStackTrace();
        } catch (NoSuchElementException noSuchElementException) {
            System.err.println("Error en la estructura del archivo");
            noSuchElementException.printStackTrace();
        } catch (IllegalStateException illegalStateException) {
            System.err.println("Error al leer el archivo");
            illegalStateException.printStackTrace();
        } finally {
            if (inFile != null)
                inFile.close();
        }
        return list;
    }

    private void escrituraDeArchivo(List<Equipo> list, String file) {
        Formatter archivoSalida = null;
        try {
            archivoSalida = new Formatter(file);
            for (Equipo equipo : list) {
                StringBuilder sbPuertos = new StringBuilder();
                Iterator<String> i = equipo.getPuertos().iterator();

                // Creo un String de puertos
                while (i.hasNext()) {
                    String p = i.next();
                    sbPuertos.append(p);
                    if (i.hasNext())
                        sbPuertos.append(",");
                }

                // Creo un string de IPs
                StringBuilder sbIP = new StringBuilder();
                i = equipo.getIPs().iterator();
                while (i.hasNext()) {
                    String p = i.next();
                    sbPuertos.append(p);
                    if (i.hasNext())
                        sbPuertos.append(",");
                }

                Boolean status = equipo.getEstado();
                archivoSalida.format("%s;%s;%s;%s;%s;%s;%s;%s;%s",
                        equipo.getCodigo(),
                        equipo.getDescripcion(),
                        equipo.getMarca(),
                        equipo.getModelo(),
                        sbPuertos.toString(),
                        sbIP.toString(),
                        equipo.getTipoEquipo().getCodigo(),
                        equipo.getUbicacion().getCodigo(),
                        status.toString());
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
    public void insertar(Equipo equipo) {
        list.add(equipo);
        escrituraDeArchivo(list, nombre);
        actualizar = true;
    }

    @Override
    public void actualizar(Equipo equipo) {
        int pos = list.indexOf(equipo);
        list.set(pos, equipo);
        escrituraDeArchivo(list, nombre);
        actualizar = true;
    }

    @Override
    public void borrar(Equipo equipo) {
        list.remove(equipo);
        escrituraDeArchivo(list, nombre);
        actualizar = true;
    }

    @Override
    public List<Equipo> buscarTodEquipos() {
        if (actualizar) {
            list = leerDeArchivo(nombre);
            actualizar = false;
        }
        return list;
    }

    private Hashtable<String, Ubicacion> cargarUbicaciones() {
        Hashtable<String, Ubicacion> ubicaciones = new Hashtable<String, Ubicacion>();
        UbicacionDao ubicacionDao = new UbicacionSecuencialDao();
        List<Ubicacion> ds = ubicacionDao.buscarTdasUbicaciones();
        for (Ubicacion d : ds) {
            ubicaciones.put(d.getCodigo(), d);
        }

        return ubicaciones;
    }

    private Hashtable<String, TipoEquipo> cargarTipoEquipos() {
        Hashtable<String, TipoEquipo> tipoEquipos = new Hashtable<String, TipoEquipo>();
        TipoEquipoDao tipoEquipoDao = new TipoEquipoSecuencialDao();
        List<TipoEquipo> ds = tipoEquipoDao.buscarTodTipoEquipos();
        for (TipoEquipo tipoEquipo : ds) {
            tipoEquipos.put(tipoEquipo.getCodigo(), tipoEquipo);
        }

        return tipoEquipos;
    }
    
    private Hashtable<String, TipoPuerto> cargarTipoPuertos() {
        Hashtable<String, TipoPuerto> puertos = new Hashtable<>();
        TipoPuertoDao objTipoPuertoDAO = new TipoPuertoSecuencialDao();
        for (TipoPuerto port : objTipoPuertoDAO.buscarTodTipoPuertos()) {
            puertos.put(port.getCodigo(), port);
        }
        return puertos;
    }

}
