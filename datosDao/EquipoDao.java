package datosDao;

import java.util.List;

import modelo.Equipo;

public interface EquipoDao {
    void insertar(Equipo equipo);

    void actualizar(Equipo equipo);

    void borrar(Equipo equipo);

    List<Equipo> buscarTodEquipos();

}
