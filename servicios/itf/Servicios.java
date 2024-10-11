package redUni.servicios.itf;

import java.util.List;

public interface Servicios<O> {
    void insertar(O o);

    void actualizar(O o);

    void borrar(O o);

    List<O> buscarTodos();
}