package cargadatosDao;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;

import datosDao.TipoPuertoDao;
import modelo.TipoPuerto;

public class TipoPuertoSecuencialDao implements TipoPuertoDao {

    private List<TipoPuerto> list;
    private String nombre;
    private boolean actualizar;

    public TipoPuertoSecuencialDao() {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        nombre = rb.getString("tipoPuerto");
        actualizar = true;

    }

    private List<TipoPuerto> leerDeArchivo(String file) {
        List<TipoPuerto> list = new ArrayList<>();
        Scanner inFile = null;
        try {
            inFile = new Scanner(new File(file));
            inFile.useDelimiter("\\s*;\\s*");
            while (inFile.hasNext()) {
                TipoPuerto tPuerto = new TipoPuerto();
                tPuerto.setCodigo(inFile.next());
                tPuerto.setDescripcion(inFile.next());
                tPuerto.setVelocidad(Integer.parseInt(inFile.next()));
                list.add(tPuerto);
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

    private void escrituraDeArchivo(List<TipoPuerto> list, String file) {
        Formatter archivoSalida = null;
        try {
            archivoSalida = new Formatter(file);
            for (TipoPuerto tipoPuerto : list) {
                archivoSalida.format("%s;%s;%d;\n", tipoPuerto.getCodigo(), tipoPuerto.getDescripcion(),
                        tipoPuerto.getVelocidad());
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
    public void insertar(TipoPuerto tipoPuerto) {
        list.add(tipoPuerto);
        escrituraDeArchivo(list, nombre);

        actualizar = true;
    }

    @Override
    public void actualizar(TipoPuerto tipoPuerto) {
        int pos = list.indexOf(tipoPuerto);
        list.set(pos, tipoPuerto);
        escrituraDeArchivo(list, nombre);
        actualizar = true;
    }

    @Override
    public void borrar(TipoPuerto tipoPuerto) {
        list.remove(tipoPuerto);
        escrituraDeArchivo(list, nombre);
        actualizar = true;
    }

    @Override
    public List<TipoPuerto> buscarTodTipoPuertos() {
        if (actualizar) {
            list = leerDeArchivo(nombre);
            actualizar = true;
        }
        return list;
    }

}
