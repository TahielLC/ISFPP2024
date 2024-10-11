package redUni.datosDao;

import java.util.List;

import redUni.modelo.TipoPuerto;

public interface TipoPuertoDao {

    void insertar(TipoPuerto tipoPuertoDao);

    void actualizar(TipoPuerto tipoPuertoDao);

    void borrar(TipoPuerto tipoPuertoDao);

    List<TipoPuerto> buscarTodTipoPuertos();
}
