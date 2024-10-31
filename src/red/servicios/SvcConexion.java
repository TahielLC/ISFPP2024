package red.servicios;

import java.util.List;

import red.modelo.Conexion;
import red.servicios.itf.Servicios;
import red.fabricas.DAOfactory;
import red.datosDao.ConexionDao;

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