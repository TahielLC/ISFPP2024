
package cargadatosDao;


import java.util.List;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import datosDao.TipoEquipoDao;
import modelo.TipoEquipo;

public class TipoEquipoSecuencialDao implements TipoEquipoDao {

    private List<TipoEquipo> list;
    private String nombre;
    private boolean actualizar;

    public TipoEquipoSecuencialDao() {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        nombre = rb.getString("tipoEquipo");
        actualizar = true;
    }

    private List<TipoEquipo> leerDesdeArchivo(String file) {
        List<TipoEquipo> list = new ArrayList<>();
        Scanner inFile = null;
        try {
            inFile = new Scanner(new File(file));
            inFile.useDelimiter("\\s*;\\s*");
            while (inFile.hasNext()) {
                TipoEquipo tEquipo = new TipoEquipo();
                tEquipo.setCodigo(inFile.next());
                tEquipo.setDescripcion(inFile.next());
                list.add(tEquipo);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Error al abrir el archivo, tipoEquipo");
        } catch (NoSuchElementException noSuchElementException) {
            System.err.println("Error en la estructura del archivo ");
        } catch (IllegalStateException illegalStateException) {
            System.err.println("Error al leer el archivo");
        } finally {
            if (inFile != null) {
                inFile.close();
            }
        }

        return list;
    }

    private void escrituraDarchivo(List<TipoEquipo> list, String file) {
        Formatter archivoSalida = null;
        try {
            archivoSalida = new Formatter(file);
            for (TipoEquipo tipoEquipo : list) {
                archivoSalida.format("%s;%s;\n", tipoEquipo.getCodigo(), tipoEquipo.getDescripcion());
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
    public void insertar(TipoEquipo tipoEquipo) {
        list.add(tipoEquipo);
        escrituraDarchivo(list, nombre);
        actualizar = true;
    }

    @Override
    public void actualizar(TipoEquipo tipoEquipo) {
        int pos = list.indexOf(tipoEquipo);
        list.set(pos, tipoEquipo);
        escrituraDarchivo(list, nombre);
        actualizar = true;
    }

    @Override
    public void borrar(TipoEquipo tipoEquipo) {
        list.remove(tipoEquipo);
        escrituraDarchivo(list, nombre);
        actualizar = true;
    }

    @Override
    public List<TipoEquipo> buscarTodTipoEquipos() {
        if (actualizar) {
            list = leerDesdeArchivo(nombre);
            actualizar = false;
        }
        return list;
    }

}
