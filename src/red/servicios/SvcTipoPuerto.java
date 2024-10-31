package  src.red.servicios;

import java.util.List;

import red.modelo.TipoPuerto;
import red.servicios.itf.Servicios;
import red.fabricas.DAOfactory;
import red.datosDao.TipoPuertoDao;

public class SvcTipoPuerto implements Servicios<TipoPuerto> {
    private TipoPuertoDao tipoPuertoDAO;

    public SvcTipoPuerto() {
        tipoPuertoDAO = (TipoPuertoDao) DAOfactory.getInstance("TIPOPUERTO");
    }

    @Override
    public void insertar(TipoPuerto tipoPuerto) {
        tipoPuertoDAO.insertar(tipoPuerto);
    }

    @Override
    public void actualizar(TipoPuerto tipoPuerto) {
        tipoPuertoDAO.actualizar(tipoPuerto);
    }

    @Override
    public void borrar(TipoPuerto tipoPuerto) {
        tipoPuertoDAO.borrar(tipoPuerto);
    }

    @Override
    public List<TipoPuerto> buscarTodos() {
        return tipoPuertoDAO.buscarTodTipoPuertos();
    }
    
}