package servicios;

import java.util.List;

import datosDao.EquipoDao;
import fabricas.DAOfactory;
import modelo.Equipo;
import servicios.itf.Servicios;

public class SvcEquipo implements Servicios<Equipo> {
    private EquipoDao equipoDAO;

    public SvcEquipo() {
        equipoDAO = (EquipoDao) DAOfactory.getInstance("EQUIPO");
        if (equipoDAO == null) {
            throw new IllegalStateException("No se pudo inicializar EquipoDao desde DAOfactory.");
        }
    }

    @Override
    public void insertar(Equipo equipo) {
        equipoDAO.insertar(equipo);
    }

    @Override
    public void actualizar(Equipo equipo) {
        equipoDAO.actualizar(equipo);
    }

    @Override
    public void borrar(Equipo equipo) {
        equipoDAO.borrar(equipo);
    }

    @Override
    public List<Equipo> buscarTodos() {
        return equipoDAO.buscarTodEquipos();
    }

}