package redUni.servicios;

import java.util.List;

import redUni.datosDao.TipoCableDao;
import redUni.fabricas.DAOfactory;
import redUni.modelo.TipoCable;
import redUni.servicios.itf.Servicios;

public class SvcTipoCable implements Servicios<TipoCable> {
    private TipoCableDao TipoCableDAO;

    public SvcTipoCable() {
        TipoCableDAO = (TipoCableDao) DAOfactory.getInstance("TIPOCABLE");
    }

    @Override
    public void insertar(TipoCable TipoCable) {
        TipoCableDAO.insertar(TipoCable);
    }

    @Override
    public void actualizar(TipoCable TipoCable) {
        TipoCableDAO.actualizar(TipoCable);
    }

    @Override
    public void borrar(TipoCable TipoCable) {
        TipoCableDAO.borrar(TipoCable);
    }

    @Override
    public List<TipoCable> buscarTodos() {
        return TipoCableDAO.buscarTodTipoCables();
    }
    
}