package redUni.datosDao;

import java.util.List;

import redUni.modelo.TipoEquipo;

public interface TipoEquipoDao {
    void insertar(TipoEquipo tipoEquipo);

    void actualizar(TipoEquipo tipoEquipo);

    void borrar(TipoEquipo tipoEquipo);

    List<TipoEquipo> buscarTodTipoEquipos();
}
