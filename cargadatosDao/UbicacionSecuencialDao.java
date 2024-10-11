package redUni.cargadatosDao;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.List;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;

import redUni.datosDao.UbicacionDao;
import redUni.modelo.Ubicacion;

public class UbicacionSecuencialDao implements UbicacionDao {

    private List<Ubicacion> list;
    private String nombre;
    private boolean actualizar;

    public UbicacionSecuencialDao() {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        nombre = rb.getString("ubicacion");
        actualizar = true;
    }

    private List<Ubicacion> leerDesdeArchivo(String file) {
        List<Ubicacion> list = new ArrayList<>();
        Scanner inFile = null;
        try {
            inFile = new Scanner(new File(file));
            inFile.useDelimiter("\\s*;\\s*");
            while (inFile.hasNext()) {
                Ubicacion ubi = new Ubicacion();
                ubi.setCodigo(inFile.next());
                ubi.setDescripcion(inFile.next());
                list.add(ubi);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Error al abrir el archivo, Ubicacion");
        } catch (NoSuchElementException noSuchElementException) {
            System.err.println("Error en la estructura del archivo");
        } catch (IllegalStateException illegalStateException) {
            System.err.println("Error al leer el archivo");
        } finally {
            if (inFile != null) {
                inFile.close();
            }
        }

        return list;
    }

    private void escrituraDarchivo(List<Ubicacion> list, String file) {
        Formatter archivoSalida = null;
        try {
            archivoSalida = new Formatter(file);
            for (Ubicacion ubi : list) {
                archivoSalida.format("%s;%s;\n", ubi.getCodigo(), ubi.getDescripcion());
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
    public void insertar(Ubicacion ubicacion) {
        list.add(ubicacion);
        escrituraDarchivo(list, nombre);
        actualizar = true;
    }

    @Override
    public void actualizar(Ubicacion ubicacion) {
        int pos = list.indexOf(ubicacion);
        list.set(pos, ubicacion);
        escrituraDarchivo(list, nombre);
        actualizar = true;
    }

    @Override
    public void borrar(Ubicacion ubicacion) {
        list.remove(ubicacion);
        escrituraDarchivo(list, nombre);
        actualizar = true;
    }

    @Override
    public List<Ubicacion> buscarTdasUbicaciones() {
        if (actualizar) {
            list = leerDesdeArchivo(nombre);
            actualizar = false;
        }
        return list;
    }

}
