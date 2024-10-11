package redUni.cargadatosDao;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import redUni.datosDao.TipoCableDao;
import redUni.modelo.TipoCable;

public class TipoCableSecuencialDao implements TipoCableDao {
    private List<TipoCable> list;
    private String nombre;
    private boolean actualizar;

    public TipoCableSecuencialDao() {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        nombre = rb.getString("tipoCable");
        actualizar = true;
    }

    private List<TipoCable> leerDesdeArchivo(String file) {
        List<TipoCable> list = new ArrayList<>();
        Scanner inFile = null;
        try {
            inFile = new Scanner(new File(file));
            inFile.useDelimiter("\\s*;\\s*");
            while (inFile.hasNext()) {
                TipoCable tCable = new TipoCable();
                tCable.setCodigo(inFile.next());
                tCable.setDescripcion(inFile.next());
                tCable.setVelocidad(Integer.parseInt(inFile.next()));
                list.add(tCable);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Error al abrir el archivo");
            fileNotFoundException.printStackTrace();
        } catch (NoSuchElementException noSuchElementException) {
            System.err.println("Error sobre la estructura del archivo");
            noSuchElementException.printStackTrace();
        } catch (IllegalStateException illegalStateException) {
            System.err.println("Error al leer sobre el archivo");
            illegalStateException.printStackTrace();
        } finally {
            if (inFile != null)
                inFile.close();
        }
        return list;

    }

    private void escrituraDeArchivo(List<TipoCable> list, String file) {
        Formatter archivoSalida = null;
        try {
            archivoSalida = new Formatter(file);
            for (TipoCable tipoCable : list) {
                archivoSalida.format("%s;%s;%d;", tipoCable.getCodigo(), tipoCable.getDescripcion(),
                        tipoCable.getVelocidad());
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Error al crear el archivo.");
        } catch (FormatterClosedException formatterClosedException) {
            System.err.println("Error al escribir en el archivo.");
        } finally {
            if (archivoSalida != null)
                archivoSalida.close();
        }

    }

    @Override
    public void insertar(TipoCable tipoCable) {
        list.add(tipoCable);
        escrituraDeArchivo(list, nombre);
        actualizar = true;
    }

    @Override
    public void actualizar(TipoCable tipoCable) {
        int pos = list.indexOf(tipoCable);
        list.set(pos, tipoCable);
        escrituraDeArchivo(list, nombre);
        actualizar = true;
    }

    @Override
    public void borrar(TipoCable tipoCable) {
        list.remove(tipoCable);
        escrituraDeArchivo(list, nombre);
        actualizar = true;
    }

    @Override
    public List<TipoCable> buscarTodTipoCables() {
        if (actualizar) {
            list = leerDesdeArchivo(nombre);
            actualizar = false;
        }
        return list;
    }

}
