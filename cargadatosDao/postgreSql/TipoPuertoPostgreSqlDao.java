package cargadatosDao.postgreSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import conexion.DBConexion;
import datosDao.TipoPuertoDao;
import modelo.TipoCable;
import modelo.TipoPuerto;

public class TipoPuertoPostgreSqlDao implements TipoPuertoDao {

    @Override
    public void insertar(TipoPuerto tipoPuertoDao) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBConexion.getConexion();
            String sql = "";
            sql += "INSERT INTO poo2024.tipopuerto (codigo, descripcion,velocidad) ";
            sql += "VALUES(?,?,?) ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, tipoPuertoDao.getCodigo());
            pstm.setString(2, tipoPuertoDao.getDescripcion());
            pstm.setInt(2, tipoPuertoDao.getVelocidad());
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
    public void actualizar(TipoPuerto tipoPuertoDao) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBConexion.getConexion();
            String sql = "UPDATE poo2024.tipopuerto ";
            sql += "SET descripcion = ? , velocidad = ? ";
            sql += "WHERE codigo = ? ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, tipoPuertoDao.getDescripcion());
            pstm.setInt(2, tipoPuertoDao.getVelocidad());
            pstm.setString(3, tipoPuertoDao.getCodigo());
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
    public void borrar(TipoPuerto tipoPuertoDao) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBConexion.getConexion();
            String sql = "";
            sql += "DELETE FROM poo2024.tipopuerto WHERE codigo = ? ";
            pstm = con.prepareStatement(sql);

            pstm.setString(1, tipoPuertoDao.getCodigo());

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
    public List<TipoPuerto> buscarTodTipoPuertos() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBConexion.getConexion();
            String sql = "SELECT codigo, descripcion, velocidad FROM poo2024.tipopuerto ";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            List<TipoPuerto> ret = new ArrayList<TipoPuerto>();
            while (rs.next()) {
                ret.add(new TipoPuerto(rs.getString("codigo"), rs.getString("descripcion"),
                        rs.getInt("velocidad")));
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

}
