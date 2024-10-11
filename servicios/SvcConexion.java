package redUni.servicios;

import java.util.List;

import redUni.datosDao.ConexionDao;
import redUni.fabricas.DAOfactory;
import redUni.modelo.Conexion;
import redUni.servicios.itf.Servicios;

public class SvcConexion implements Servicios<Conexion> {
    private ConexionDao conexionDAO;

    public SvcConexion() {
        conexionDAO = (ConexionDao) DAOfactory.getInstance("CONEXION");
    }

    @Override
    public void insertar(Conexion conexion) {
        conexionDAO.insertar(conexion);
    }

    @Override
    public void actualizar(Conexion conexion) {
        conexionDAO.actualizar(conexion);
    }

    @Override
    public void borrar(Conexion conexion) {
        conexionDAO.borrar(conexion);
    }

    @Override
    public List<Conexion> buscarTodos() {
        return conexionDAO.bucarConexiones();
    }
    
}