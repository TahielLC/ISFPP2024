package servicios;

import java.util.List;

import datosDao.ConexionDao;
import fabricas.DAOfactory;
import modelo.Conexion;
import servicios.itf.Servicios;

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