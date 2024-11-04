package red.datosDao;

import java.util.List;

import red.modelo.TipoPuerto;

public interface TipoPuertoDao {

    void insertar(TipoPuerto tipoPuertoDao);

    void actualizar(TipoPuerto tipoPuertoDao);

    void borrar(TipoPuerto tipoPuertoDao);

    List<TipoPuerto> buscarTodTipoPuertos();
}
