package red.datosDao;

import java.util.List;

import red.modelo.Ubicacion;

public interface UbicacionDao {
    void insertar(Ubicacion ubicacion);

    void actualizar(Ubicacion ubicacion);

    void borrar(Ubicacion ubicacion);

    List<Ubicacion> buscarTdasUbicaciones();

}
