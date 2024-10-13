package  servicios;

import java.util.List;

import  datosDao.TipoPuertoDao;
import  fabricas.DAOfactory;
import  modelo.TipoPuerto;
import  servicios.itf.Servicios;

public class SvcTipoPuerto implements Servicios<TipoPuerto> {
    private TipoPuertoDao tipoPuertoDAO;

    public SvcTipoPuerto() {
        tipoPuertoDAO = (TipoPuertoDao) DAOfactory.getInstance("tipopuerto");
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