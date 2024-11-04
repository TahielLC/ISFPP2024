package red.datos.dao;

import java.util.List;

import red.modelo.Equipo;

public interface EquipoDao {
    void insertar(Equipo equipo);

    void actualizar(Equipo equipo);

    void borrar(Equipo equipo);

    List<Equipo> buscarTodEquipos();

}
