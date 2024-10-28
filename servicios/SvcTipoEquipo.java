package  servicios;

import java.util.List;

import  datosDao.TipoEquipoDao;
import  fabricas.DAOfactory;
import  modelo.TipoEquipo;
import  servicios.itf.Servicios;

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