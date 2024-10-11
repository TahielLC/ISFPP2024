package redUni.datosDao;

import java.util.List;

import redUni.modelo.Equipo;

public interface EquipoDao {
    void insertar(Equipo equipo);

    void actualizar(Equipo equipo);

    void borrar(Equipo equipo);

    List<Equipo> buscarTodEquipos();

}
