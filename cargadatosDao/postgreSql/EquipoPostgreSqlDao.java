package cargadatosDao.postgreSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile;

import cargadatosDao.TipoEquipoSecuencialDao;
import cargadatosDao.TipoPuertoSecuencialDao;
import cargadatosDao.UbicacionSecuencialDao;
import conexion.DBConexion;
import datosDao.EquipoDao;
import datosDao.TipoEquipoDao;
import datosDao.TipoPuertoDao;
import datosDao.UbicacionDao;
import modelo.Conexion;
import modelo.Equipo;
import modelo.TipoEquipo;
import modelo.TipoPuerto;
import modelo.Ubicacion;

public class EquipoPostgreSqlDao implements EquipoDao {

    private Hashtable<String, Ubicacion> ubicaciones;
    private Hashtable<String, TipoEquipo> tipoEquipos;
    private Hashtable<String, TipoPuerto> tiposPuertos;

    public EquipoPostgreSqlDao() {
        ubicaciones = cargarUbicaciones();
        tipoEquipos = cargarTipoEquipos();
        tiposPuertos = cargarTipoPuertos();
    }

    @Override
    public void insertar(Equipo equipo) {
        String sql = "INSERT INTO hEquipo (codigo, descripcion, marca, modelo, ubicacion, tipo_equipo, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        ResultSet rs = null;
        PreparedStatement pstm = null;
        PreparedStatement stmtIP = null;
        PreparedStatement stmtPuerto = null;
        String sqlIP = "INSERT INTO hEquipo_ips (equipo, ip) VALUES (?, ?)";
        String sqlPuerto = "INSERT INTO hEquipo_puertos (equipo, puerto, cantidad) VALUES (?, ?, ?)";

        try (Connection conn = DBConexion.getConexion();) {
            pstm = conn.prepareStatement(sql);
            stmtIP = conn.prepareStatement(sqlIP);
            stmtPuerto = conn.prepareStatement(sqlPuerto);
            pstm.setString(1, equipo.getCodigo());
            pstm.setString(2, equipo.getDescripcion());
            pstm.setString(3, equipo.getMarca());
            pstm.setString(4, equipo.getModelo());
            pstm.setString(5, equipo.getUbicacion().getCodigo());
            pstm.setString(6, equipo.getTipoEquipo().getCodigo());
            pstm.setBoolean(7, equipo.getEstado());

            // Insertar las direcciones IP asociadas
            for (String ip : equipo.getIPs()) {
                stmtIP.setString(1, equipo.getCodigo());
                stmtIP.setString(2, ip);
                stmtIP.executeUpdate();
            }

            // Insertar los puertos asociados
            for (String puerto : equipo.getPuertos()) {
                String[] datosPuerto = puerto.split(",");
                stmtPuerto.setString(1, equipo.getCodigo());
                stmtPuerto.setString(2, datosPuerto[0]); // Código del tipo de puerto
                stmtPuerto.setInt(3, Integer.parseInt(datosPuerto[1])); // Cantidad de puertos
                stmtPuerto.executeUpdate();
            }

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
    public void actualizar(Equipo equipo) {
        String sql = "UPDATE hEquipo SET descripcion = ?, marca = ?, modelo = ?, ubicacion = ?, tipo_equipo = ?, estado = ? "
                + "WHERE codigo = ?";
        String sqlDeleteIPs = "DELETE FROM hEquipo_ips WHERE codigo = ?";
        String sqlDeletePuertos = "DELETE FROM hEquipo_puertos WHERE codigo = ?";
        ResultSet rs = null;
        PreparedStatement pstm = null;
        PreparedStatement stmtIP = null;
        PreparedStatement stmtPuerto = null;
        PreparedStatement stmtDeletePuertos = null;
        PreparedStatement stmtDeleteIPs = null;
        String sqlIP = "INSERT INTO hEquipo_ips (codigo, ip) VALUES (?, ?)";
        String sqlPuerto = "INSERT INTO hEquipo_puertos (codigo, puerto, cantidad) VALUES (?, ?, ?)";

        try (Connection conn = DBConexion.getConexion();) {
            pstm = conn.prepareStatement(sql);
            stmtIP = conn.prepareStatement(sqlIP);
            stmtPuerto = conn.prepareStatement(sqlPuerto);
            stmtDeleteIPs = conn.prepareStatement(sqlDeleteIPs);
            stmtDeletePuertos = conn.prepareStatement(sqlDeletePuertos);

            pstm.setString(1, equipo.getDescripcion());
            pstm.setString(2, equipo.getMarca());
            pstm.setString(3, equipo.getModelo());
            pstm.setString(4, equipo.getUbicacion().getCodigo()); // Suponiendo que `Ubicacion` tiene un método
            pstm.setString(5, equipo.getTipoEquipo().getCodigo()); // Similar para `Tipo_equipo`
            pstm.setBoolean(6, equipo.getEstado());
            pstm.setString(7, equipo.getCodigo());

            // Borrar direcciones IP y puertos anteriores
            stmtDeleteIPs.setString(1, equipo.getCodigo());
            stmtDeleteIPs.executeUpdate();

            stmtDeletePuertos.setString(1, equipo.getCodigo());
            stmtDeletePuertos.executeUpdate();
            // Insertar las nuevas direcciones IP
            for (String ip : equipo.getIPs()) {
                stmtIP.setString(1, equipo.getCodigo());
                stmtIP.setString(2, ip);
                stmtIP.executeUpdate();
            }

            // Insertar los nuevos puertos
            for (String puerto : equipo.getPuertos()) {
                String[] datosPuerto = puerto.split(",");
                stmtPuerto.setString(1, equipo.getCodigo());
                stmtPuerto.setString(2, datosPuerto[0]); // Código del tipo de puerto
                stmtPuerto.setInt(3, Integer.parseInt(datosPuerto[1])); // Cantidad de puertos
                stmtPuerto.executeUpdate();
            }
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
    public void borrar(Equipo equipo) {
        Connection con = null;
        PreparedStatement pstm = null;
        PreparedStatement stmtDeleteIPs = null;
        PreparedStatement stmtDeletePuertos = null;
        String sqlDeleteIPs = "DELETE FROM hEquipo_ips WHERE codigo = ?";
        String sqlDeletePuertos = "DELETE FROM hEquipo_puertos WHERE codigo = ?";
        ResultSet rs = null;
        try {
            con = DBConexion.getConexion();
            String sql = "";
            sql += "DELETE FROM poo2024.hhEquipos WHERE codigo = ? ";
            stmtDeleteIPs = con.prepareStatement(sqlDeleteIPs);
            stmtDeletePuertos = con.prepareStatement(sqlDeletePuertos);
            pstm = con.prepareStatement(sql);

            stmtDeleteIPs.setString(1, equipo.getCodigo());
            stmtDeleteIPs.executeUpdate();

            stmtDeletePuertos.setString(1, equipo.getCodigo());
            stmtDeletePuertos.executeUpdate();

            pstm.setString(1, equipo.getCodigo());
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
    public List<Equipo> buscarTodEquipos() {
        Connection con = null;
        PreparedStatement pstm = null;
        PreparedStatement stmtIps = null;
        PreparedStatement stmtPuertos = null;
        ResultSet rs = null;
        try {
            con = DBConexion.getConexion();
            String sqlIPs = "SELECT ip FROM hEquipo_ips WHERE codigo = ?";
            String sqlPuertos = "SELECT tipo_puerto, cantidad FROM hEquipo_puertos WHERE codigo = ?";
            String sql = "SELECT codigo , descripcion , marca , modelo , ubicacion , tipo_equipo , estado FROM poo2024.hEquipos ";
            pstm = con.prepareStatement(sql);
            stmtIps = con.prepareStatement(sqlIPs);
            stmtPuertos = con.prepareStatement(sqlPuertos);
            rs = pstm.executeQuery();
            List<Equipo> ret = new ArrayList<Equipo>();
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setCodigo(rs.getString("codigo"));
                equipo.setDescripcion(rs.getString("descripcion"));
                equipo.setMarca(rs.getString("marca"));
                equipo.setModelo(rs.getString("modelo"));
                equipo.setUbicacion(ubicaciones.get(rs.getString("ubicacion")));
                equipo.setTipoEquipo(tipoEquipos.get(rs.getString("tipo_equipo")));
                equipo.setEstado(rs.getBoolean("estado"));
                // Obtener las direcciones IP del equipo
                stmtIps.setString(1, equipo.getCodigo());
                try (ResultSet rsIPs = stmtIps.executeQuery()) {
                    while (rsIPs.next()) {
                        equipo.agregarIp(rsIPs.getString("ip"));
                    }
                }

                // Obtener los puertos del equipo
                stmtPuertos.setString(1, equipo.getCodigo());
                try (ResultSet rsPuertos = stmtPuertos.executeQuery()) {
                    while (rsPuertos.next()) {
                        TipoPuerto tipoPuerto = new TipoPuerto(
                                rsPuertos.getString("tipo_puerto"), // Este debe coincidir con la columna de la tabla
                                                                    // hEquipo_puertos
                                rs.getString("descripcion"), // Asigna la descripción como corresponda (quizás de otra
                                                             // tabla)
                                rs.getInt("velocidad") // Asigna la velocidad como corresponda (quizás de otra tabla o
                                                       // campo)
                        );
                        equipo.agregarPuerto(tipoPuerto, rsPuertos.getInt("cantidad"));
                    }
                }
                ret.add(equipo);
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

    private Hashtable<String, Ubicacion> cargarUbicaciones() {
        Hashtable<String, Ubicacion> ubicaciones = new Hashtable<String, Ubicacion>();
        UbicacionDao ubicacionDao = new UbicacionPostgreSqlDao();
        List<Ubicacion> ds = ubicacionDao.buscarTdasUbicaciones();
        for (Ubicacion d : ds) {
            ubicaciones.put(d.getCodigo(), d);
        }

        return ubicaciones;
    }

    private Hashtable<String, TipoEquipo> cargarTipoEquipos() {
        Hashtable<String, TipoEquipo> tipohEquipos = new Hashtable<String, TipoEquipo>();
        TipoEquipoDao tipo_equipoDao = new TipoEquipoPostgreSql();
        List<TipoEquipo> ds = tipo_equipoDao.buscarTodTipoEquipos();
        for (TipoEquipo tipo_equipo : ds) {
            tipohEquipos.put(tipo_equipo.getCodigo(), tipo_equipo);
        }

        return tipohEquipos;
    }

    private Hashtable<String, TipoPuerto> cargarTipoPuertos() {
        Hashtable<String, TipoPuerto> puertos = new Hashtable<>();
        TipoPuertoDao objTipoPuertoDAO = new TipoPuertoPostgreSqlDao();
        for (TipoPuerto port : objTipoPuertoDAO.buscarTodTipoPuertos()) {
            puertos.put(port.getCodigo(), port);
        }
        return puertos;
    }

}
