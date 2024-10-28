package datosDao;

import java.util.List;

import modelo.Ubicacion;

public interface UbicacionDao {
    void insertar(Ubicacion ubicacion);

    void actualizar(Ubicacion ubicacion);

    void borrar(Ubicacion ubicacion);

    List<Ubicacion> buscarTdasUbicaciones();

}
