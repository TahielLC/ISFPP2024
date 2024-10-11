package redUni.servicios;

import java.util.List;

import redUni.datosDao.EquipoDao;
import redUni.fabricas.DAOfactory;
import redUni.modelo.Equipo;
import redUni.servicios.itf.Servicios;

public class SvcEquipo implements Servicios<Equipo> {
    private EquipoDao equipoDAO;

    public SvcEquipo() {
        equipoDAO = (EquipoDao) DAOfactory.getInstance("EQUIPO");
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