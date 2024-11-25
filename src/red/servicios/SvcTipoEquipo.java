package  red.servicios;

import java.util.List;

import red.modelo.TipoEquipo;
import red.servicios.itf.Servicios;
import red.fabricas.DAOfactory;
import red.datos.dao.TipoEquipoDao;

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