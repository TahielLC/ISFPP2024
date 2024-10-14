package datosDao;

import java.util.List;

import modelo.Conexion;

public interface ConexionDao {
    void insertar(Conexion conexion);

    void actualizar(Conexion conexion);

    void borrar(Conexion conexion);

    List<Conexion> bucarConexiones();
}
