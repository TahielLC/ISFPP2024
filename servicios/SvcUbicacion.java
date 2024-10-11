package redUni.servicios;

import java.util.List;

import redUni.datosDao.UbicacionDao;
import redUni.fabricas.DAOfactory;
import redUni.modelo.Ubicacion;
import redUni.servicios.itf.Servicios;

public class SvcUbicacion implements Servicios<Ubicacion> {
    private UbicacionDao ubicacionDAO;

    public SvcUbicacion() {
        ubicacionDAO = (UbicacionDao) DAOfactory.getInstance("UBICACION");
    }

    @Override
    public void insertar(Ubicacion ubicacion) {
        ubicacionDAO.insertar(ubicacion);
    }

    @Override
    public void actualizar(Ubicacion ubicacion) {
        ubicacionDAO.actualizar(ubicacion);
    }

    @Override
    public void borrar(Ubicacion ubicacion) {
        ubicacionDAO.borrar(ubicacion);
    }

    @Override
    public List<Ubicacion> buscarTodos() {
        return ubicacionDAO.buscarTdasUbicaciones();
    }
    
}