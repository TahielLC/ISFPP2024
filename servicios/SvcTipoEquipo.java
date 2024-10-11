package redUni.servicios;

import java.util.List;

import redUni.datosDao.TipoEquipoDao;
import redUni.fabricas.DAOfactory;
import redUni.modelo.TipoEquipo;
import redUni.servicios.itf.Servicios;

public class SvcTipoEquipo implements Servicios<TipoEquipo> {
    private TipoEquipoDao tipoEquipoDAO;

    public SvcTipoEquipo() {
        tipoEquipoDAO = (TipoEquipoDao) DAOfactory.getInstance("TIPOEQUIPO");
    }

    @Override
    public void insertar(TipoEquipo tipoEquipo) {
        tipoEquipoDAO.insertar(tipoEquipo);
    }

    @Override
    public void actualizar(TipoEquipo tipoEquipo) {
        tipoEquipoDAO.actualizar(tipoEquipo);
    }

    @Override
    public void borrar(TipoEquipo tipoEquipo) {
        tipoEquipoDAO.borrar(tipoEquipo);
    }

    @Override
    public List<TipoEquipo> buscarTodos() {
        return tipoEquipoDAO.buscarTodTipoEquipos();
    }
    
}