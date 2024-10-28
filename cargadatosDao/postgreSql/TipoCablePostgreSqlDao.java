package cargadatosDao.postgreSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import conexion.DBConexion;
import datosDao.TipoCableDao;
import modelo.TipoCable;

public class TipoCablePostgreSqlDao implements TipoCableDao {

    @Override
    public void insertar(TipoCable tipoCable) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBConexion.getConexion();
            String sql = "";
            sql += "INSERT INTO poo2024.tipocables (codigo, descripcion, velocidad) ";
            sql += "VALUES(?,?,?) ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, tipoCable.getCodigo());
            pstm.setString(2, tipoCable.getDescripcion());
            pstm.setInt(3, tipoCable.getVelocidad());
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
    public void actualizar(TipoCable tipoCable) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBConexion.getConexion();
            String sql = "UPDATE poo2024.tipocables ";
            sql += "SET descripcion = ? , velocidad = ? ";
            sql += "WHERE codigo = ? ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, tipoCable.getDescripcion());
            pstm.setInt(2, tipoCable.getVelocidad());
            pstm.setString(3, tipoCable.getCodigo());
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
    public void borrar(TipoCable tipoCable) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBConexion.getConexion();
            String sql = "";
            sql += "DELETE FROM poo2024.tipocable WHERE codigo = ? ";
            pstm = con.prepareStatement(sql);

            pstm.setString(1, tipoCable.getCodigo());

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
    public List<TipoCable> buscarTodTipoCables() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBConexion.getConexion();
            String sql = "SELECT codigo, descripcion, velocidad FROM poo2024.tipocables ";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            List<TipoCable> ret = new ArrayList<TipoCable>();
            while (rs.next()) {
                ret.add(new TipoCable(rs.getString("codigo"), rs.getString("descripcion"),
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
