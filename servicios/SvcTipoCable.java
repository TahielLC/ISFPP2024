package  servicios;

import java.util.List;

import  datosDao.TipoCableDao;
import  fabricas.DAOfactory;
import  modelo.TipoCable;
import  servicios.itf.Servicios;

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