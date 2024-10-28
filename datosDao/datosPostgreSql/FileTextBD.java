package datosDao.datosPostgreSql;

import cargadatosDao.ConexionSecuencialDao;
import cargadatosDao.EquipoSecuencialDao;
import cargadatosDao.TipoCableSecuencialDao;
import cargadatosDao.TipoEquipoSecuencialDao;
import cargadatosDao.TipoPuertoSecuencialDao;
import cargadatosDao.UbicacionSecuencialDao;
import cargadatosDao.postgreSql.ConexionPostgreSqlDao;
import cargadatosDao.postgreSql.TipoCablePostgreSqlDao;
import cargadatosDao.postgreSql.UbicacionPostgreSqlDao;
import datosDao.ConexionDao;
import datosDao.EquipoDao;
import datosDao.TipoCableDao;
import datosDao.TipoEquipoDao;
import datosDao.TipoPuertoDao;
import datosDao.UbicacionDao;
import modelo.Conexion;
import modelo.Equipo;
import modelo.TipoCable;
import modelo.TipoEquipo;
import modelo.TipoPuerto;
import modelo.Ubicacion;

public class FileTextBD {

    public static void main(String[] args) {
        ConexionDao conexionSecuencialDao = new ConexionSecuencialDao();
        ConexionDao conexionPostgreSqlDao = new ConexionPostgreSqlDao();

        for (Conexion c : conexionSecuencialDao.bucarConexiones()) {
            conexionPostgreSqlDao.insertar(c);
        }
        EquipoDao equipoSecuencialDao = new EquipoSecuencialDao();
        EquipoDao equipoPostgreSqlDao = new EquipoSecuencialDao();
        for (Equipo e : equipoSecuencialDao.buscarTodEquipos()) {
            equipoPostgreSqlDao.insertar(e);
        }
        TipoCableDao tipoCableSecuencialDao = new TipoCableSecuencialDao();
        TipoCableDao tipoCablePostgreSqlDao = new TipoCablePostgreSqlDao();
        for (TipoCable ca : tipoCableSecuencialDao.buscarTodTipoCables()) {

            tipoCablePostgreSqlDao.insertar(ca);
        }
        TipoEquipoDao tipoEquipoSecuencialDao = new TipoEquipoSecuencialDao();
        TipoEquipoDao tipoEquipoPostgreSqlDao = new TipoEquipoSecuencialDao();

        for (TipoEquipo te : tipoEquipoSecuencialDao.buscarTodTipoEquipos()) {
            tipoEquipoPostgreSqlDao.insertar(te);
        }
        TipoPuertoDao tipoPuertoSecuencialDao = new TipoPuertoSecuencialDao();
        TipoPuertoDao tipoPuertoPostgreSqlDao = new TipoPuertoSecuencialDao();
        for (TipoPuerto tp : tipoPuertoSecuencialDao.buscarTodTipoPuertos()) {
            tipoPuertoPostgreSqlDao.insertar(tp);
        }
        UbicacionDao ubicacionSecuencialDao = new UbicacionSecuencialDao();
        UbicacionDao ubicacionPostgreSqlDao = new UbicacionPostgreSqlDao();
        for (Ubicacion ub : ubicacionSecuencialDao.buscarTdasUbicaciones()) {
            ubicacionPostgreSqlDao.insertar(ub);
        }
    }
}
