package sac.millennium.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import sac.millennium.dao.IGerenciaCentralDAO;
import sac.millennium.model.GerenciaCentral;
import sac.millennium.util.Conexion;

public class GerenciaCentralSqlserverDAOImpl implements IGerenciaCentralDAO {

	private Connection cx;
	private ResultSet rs;
	private PreparedStatement pstm;

	public GerenciaCentralSqlserverDAOImpl() {
		cx = Conexion.conectar();
	}

	@Override
	public List<GerenciaCentral> findAll() {
		List<GerenciaCentral> lista = new ArrayList<>();
		try {
			GerenciaCentral obj;
			String sql = "select * from gerencia_central";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				obj = new GerenciaCentral();
				obj.setId(rs.getString("id_gerencia_c"));
				obj.setCodigoPropio(rs.getString("codigo_gerencia_c_propio"));
				obj.setDescripcion(rs.getString("descripcion_gerencia_c"));
				obj.setDescripcionCorta(rs.getString("descripcion_corta_gerencia_c"));
				obj.setEstado(rs.getString("estado_gerencia_c"));

				lista.add(obj);
			}
			cerrarRecursos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public int create(GerenciaCentral obj) {
		int estado = -1;
		try {
			String sql = "insert into gerencia_central values(?,?,?,?,?)";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, obj.getId());
			pstm.setString(2, obj.getCodigoPropio());
			pstm.setString(3, obj.getDescripcion());
			pstm.setString(4, obj.getDescripcionCorta());
			pstm.setString(5, obj.getEstado());
			estado = pstm.executeUpdate();
			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public int update(GerenciaCentral obj) {
		int estado = -1;
		try {
			String sql = "update gerencia_central set codigo_gerencia_c_propio=?, descripcion_gerencia_c=?, descripcion_corta_gerencia_c=?, estado_gerencia_c=? where id_gerencia_c = ?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, obj.getCodigoPropio());
			pstm.setString(2, obj.getDescripcion());
			pstm.setString(3, obj.getDescripcionCorta());
			pstm.setString(4, obj.getEstado());
			pstm.setString(5, obj.getId());

			estado = pstm.executeUpdate();
			cerrarRecursos();
			System.out.println("GC_DAO: " + estado + " registros afectados.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public int delete(String key) {
		int estado = -1;
		try {
			String sql = "delete from gerencia_central where id_gerencia_c=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, key);
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public GerenciaCentral findById(String key) {
		GerenciaCentral obj = null;
		try {
			String sql = "select * from gerencia_central where id_gerencia_c=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, key);
			rs = pstm.executeQuery();

			if (rs.next()) {
				obj = new GerenciaCentral();
				obj.setId(rs.getString("id_gerencia_c"));
				obj.setCodigoPropio(rs.getString("codigo_gerencia_c_propio"));
				obj.setDescripcion(rs.getString("descripcion_gerencia_c"));
				obj.setDescripcionCorta(rs.getString("descripcion_corta_gerencia_c"));
				obj.setEstado(rs.getString("estado_gerencia_c"));
			}
			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public String generarId() {
		String id = "";
		Formatter fmt = new Formatter();
		try {
			String sql = "select id_gerencia_c from gerencia_central order by id_gerencia_c desc;";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next()) {
				id = String.valueOf(fmt.format("%04d", Integer.parseInt(rs.getString("id_gerencia_c")) + 1));
			}
			cerrarRecursos();
			fmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	private void cerrarRecursos() {
		try {
			if (rs != null)
				rs.close();
			if (pstm != null)
				pstm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
