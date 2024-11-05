package  red.servicios;

import java.util.List;

import red.modelo.TipoCable;
import red.servicios.itf.Servicios;
import red.fabricas.DAOfactory;
import red.datos.dao.TipoCableDao;

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