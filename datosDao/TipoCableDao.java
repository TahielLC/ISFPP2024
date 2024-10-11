package redUni.datosDao;

import java.util.List;

import redUni.modelo.TipoCable;

public interface TipoCableDao {
    void insertar(TipoCable tipoCable);

    void actualizar(TipoCable tipoCable);

    void borrar(TipoCable tipoCable);

    List<TipoCable> buscarTodTipoCables();
}
