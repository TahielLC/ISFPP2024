package red.datos.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import red.datos.conexion.DBConexion;
import red.datos.dao.TipoEquipoDao;
import red.modelo.TipoCable;
import red.modelo.TipoEquipo;

public class TipoEquipoPostgreSql implements TipoEquipoDao {

    @Override
    public void insertar(TipoEquipo tipoEquipo) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBConexion.getConexion();
            String sql = "";
            sql += "INSERT INTO poo2024.hTipo_equipo (codigo, descripcion) ";
            sql += "VALUES(?,?) ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, tipoEquipo.getCodigo());
            pstm.setString(2, tipoEquipo.getDescripcion());

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
    public void actualizar(TipoEquipo tipoEquipo) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBConexion.getConexion();
            String sql = "UPDATE poo2024.hTipo_equipo ";
            sql += "SET descripcion = ? ";
            sql += "WHERE codigo = ? ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, tipoEquipo.getDescripcion());

            pstm.setString(2, tipoEquipo.getCodigo());
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
    public void borrar(TipoEquipo tipoEquipo) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBConexion.getConexion();
            String sql = "";
            sql += "DELETE FROM poo2024.hTipo_equipo WHERE codigo = ? ";
            pstm = con.prepareStatement(sql);

            pstm.setString(1, tipoEquipo.getCodigo());

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
    public List<TipoEquipo> buscarTodTipoEquipos() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBConexion.getConexion();
            String sql = "SELECT codigo, descripcion, velocidad FROM poo2024.hTipo_equipo ";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            List<TipoEquipo> ret = new ArrayList<TipoEquipo>();
            while (rs.next()) {
                ret.add(new TipoEquipo(rs.getString("codigo"), rs.getString("descripcion")));
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
