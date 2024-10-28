package cargadatosDao.postgreSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import conexion.DBConexion;
import datosDao.ConexionDao;
import datosDao.EquipoDao;
import datosDao.TipoCableDao;
import datosDao.TipoPuertoDao;
import modelo.Conexion;
import modelo.Equipo;
import modelo.TipoCable;
import modelo.TipoPuerto;

public class ConexionPostgreSqlDao implements ConexionDao {

    private Hashtable<String, Equipo> equipos;
    private Hashtable<String, TipoCable> tiposCable;
    private Hashtable<String, TipoPuerto> tipoPuertos;

    public ConexionPostgreSqlDao() {
        equipos = cargarEquipos();
        tiposCable = cargarCables();
        tipoPuertos = cargarPuertos();
    }

    @Override
    public void insertar(Conexion conexion) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBConexion.getConexion();
            String sql = "";
            sql += "INSERT INTO poo2024.conexiones (codigo_equipo1, codigo_puerto1 ,codigo_equipo2 , codigo_puerto2 , codigo_TipoCable ) ";
            sql += "VALUES(?,?,?,?,?) ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, conexion.getEquipo1().getCodigo());
            pstm.setString(2, conexion.getTipoPuerto1().getCodigo());
            pstm.setString(3, conexion.getEquipo2().getCodigo());
            pstm.setString(4, conexion.getTipoPuerto2().getCodigo());
            pstm.setString(5, conexion.getTipoCable().getCodigo());

            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstm != null)
                    pstm.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }

    // agregrarle un codigo de conexion a conexion ?
    @Override
    public void actualizar(Conexion conexion) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBConexion.getConexion();
            String sql = "UPDATE poo2024.conexiones ";
            sql += "SET codigo_equipo1 = ?, codigo_puerto1 = ? , codigo_equipo2 = ? ,codigo_puerto2 = ? , codigo_TipoCable = ?";
            sql += "WHERE codigo_equipo1 = ? AND codigo_puerto1 = ? AND codigo_equipo2 = ? AND codigo_puerto2 = ? AND codigo_TipoCable = ? ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, conexion.getEquipo1().getCodigo());
            pstm.setString(2, conexion.getTipoPuerto1().getCodigo());
            pstm.setString(3, conexion.getEquipo2().getCodigo());
            pstm.setString(4, conexion.getTipoPuerto2().getCodigo());
            pstm.setString(5, conexion.getTipoCable().getCodigo());
            // codigos para el where
            pstm.setString(6, conexion.getEquipo1().getCodigo());
            pstm.setString(7, conexion.getTipoPuerto1().getCodigo());
            pstm.setString(8, conexion.getEquipo2().getCodigo());
            pstm.setString(9, conexion.getTipoPuerto2().getCodigo());
            pstm.setString(10, conexion.getTipoCable().getCodigo());

            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstm != null)
                    pstm.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void borrar(Conexion conexion) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBConexion.getConexion();
            String sql = "";
            sql += "DELETE FROM poo2024.conexiones WHERE codigo_equipo1 = ? AND codigo_puerto1 = ? AND codigo_equipo2 = ? AND codigo_puerto2 = ? AND codigo_TipoCable = ?  ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, conexion.getEquipo1().getCodigo());
            pstm.setString(2, conexion.getTipoPuerto1().getCodigo());
            pstm.setString(3, conexion.getEquipo2().getCodigo());
            pstm.setString(4, conexion.getTipoPuerto2().getCodigo());
            pstm.setString(5, conexion.getTipoCable().getCodigo());

            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstm != null)
                    pstm.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public List<Conexion> bucarConexiones() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBConexion.getConexion();
            String sql = "SELECT codigo_equipo1, codigo_puerto1 ,codigo_equipo2 , codigo_puerto2 , codigo_TipoCable FROM poo2024.conexiones ";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            List<Conexion> ret = new ArrayList<Conexion>();
            while (rs.next()) {
                ret.add(new Conexion(equipos.get(rs.getString("codigo_equipo1")),
                        tipoPuertos.get(rs.getString("codigo_puerto1")),
                        equipos.get(rs.getString("codigo_equipo2")),
                        tipoPuertos.get(rs.getString("codigo_puerto2")),
                        tiposCable.get(rs.getString("codigo_TipoCable"))));
            }
            return ret;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstm != null)
                    pstm.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }

    private Hashtable<String, Equipo> cargarEquipos() {
        Hashtable<String, Equipo> equipos = new Hashtable<String, Equipo>();
        EquipoDao equipoDao = new EquipoPostgreSqlDao();
        List<Equipo> ds = equipoDao.buscarTodEquipos();
        for (Equipo equipo : ds) {
            equipos.put(equipo.getCodigo(), equipo);
        }
        return equipos;
    }

    private Hashtable<String, TipoCable> cargarCables() {
        Hashtable<String, TipoCable> tipoCables = new Hashtable<String, TipoCable>();
        TipoCableDao tipoCableDao = new TipoCablePostgreSqlDao();
        List<TipoCable> ds = tipoCableDao.buscarTodTipoCables();
        for (TipoCable cable : ds) {
            tipoCables.put(cable.getCodigo(), cable);

        }
        return tipoCables;
    }

    private Hashtable<String, TipoPuerto> cargarPuertos() {
        Hashtable<String, TipoPuerto> tipoPuertos = new Hashtable<String, TipoPuerto>();
        TipoPuertoDao tipoPuertoDao = new TipoPuertoPostgreSqlDao();
        List<TipoPuerto> ds = tipoPuertoDao.buscarTodTipoPuertos();
        for (TipoPuerto puerto : ds) {
            tipoPuertos.put(puerto.getCodigo(), puerto);
        }
        return tipoPuertos;
    }
}
