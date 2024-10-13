package datosDao;

import java.util.List;

import modelo.TipoCable;

public interface TipoCableDao {
    void insertar(TipoCable tipoCable);

    void actualizar(TipoCable tipoCable);

    void borrar(TipoCable tipoCable);

    List<TipoCable> buscarTodTipoCables();
}
