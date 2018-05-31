package sac.millennium.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import sac.millennium.dao.IGerenciaDAO;
import sac.millennium.model.Gerencia;
import sac.millennium.model.GerenciaCentral;
import sac.millennium.util.Conexion;

public class GerenciaSqlserverDAOImpl implements IGerenciaDAO {

	private Connection cx;
	private ResultSet rs = null;
	private PreparedStatement pstm = null;

	public GerenciaSqlserverDAOImpl() {
		cx = Conexion.conectar();
	}

	@Override
	public List<Gerencia> findAll() {
		List<Gerencia> lista = new ArrayList<>();
		try {
			Gerencia obj;
			String sql = "select * from gerencia";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				obj = new Gerencia();

				GerenciaCentral gc = new GerenciaCentral();
				gc.setId(rs.getString("id_gerencia_c"));
				obj.setGerenciaCentral(gc);

				obj.setId(rs.getString("id_gerencia"));
				obj.setCodigoPropio(rs.getString("codigo_gerencia_propio"));
				obj.setDescripcion(rs.getString("descripcion_gerencia"));
				obj.setDescripcionCorta(rs.getString("descripcion_corta_gerencia"));
				obj.setEstado(rs.getString("estado_gerencia"));

				lista.add(obj);
			}
			cerrarRecursos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Gerencia> findAllDescripcion() {
		List<Gerencia> lista = new ArrayList<>();
		try {
			Gerencia obj;
			String sql = "  select gc.descripcion_gerencia_c,g.id_gerencia,g.codigo_gerencia_propio,\r\n"
					+ "		g.descripcion_gerencia,g.descripcion_corta_gerencia,g.estado_gerencia \r\n"
					+ "		from gerencia g inner join gerencia_central gc \r\n"
					+ "		 on g.id_gerencia_c=gc.id_gerencia_c";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				obj = new Gerencia();

				GerenciaCentral gc = new GerenciaCentral();
				gc.setDescripcion(rs.getString("descripcion_gerencia_c"));
				obj.setGerenciaCentral(gc);

				obj.setId(rs.getString("id_gerencia"));
				obj.setCodigoPropio(rs.getString("codigo_gerencia_propio"));
				obj.setDescripcion(rs.getString("descripcion_gerencia"));
				obj.setDescripcionCorta(rs.getString("descripcion_corta_gerencia"));
				obj.setEstado(rs.getString("estado_gerencia"));

				lista.add(obj);
			}
			cerrarRecursos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public int create(Gerencia obj) {
		int estado = -1;
		try {
			String sql = "insert into gerencia values(?,?,?,?,?,?)";
			pstm = cx.prepareStatement(sql);

			pstm.setString(1, obj.getGerenciaCentral().getId());
			pstm.setString(2, obj.getId());
			pstm.setString(3, obj.getCodigoPropio());
			pstm.setString(4, obj.getDescripcion());
			pstm.setString(5, obj.getDescripcionCorta());
			pstm.setString(6, obj.getEstado());
			estado = pstm.executeUpdate();
			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public int update(Gerencia obj) {
		int estado = -1;
		try {
			String sql = "update gerencia set id_gerencia_c=?, codigo_gerencia_propio=?, descripcion_gerencia=?, descripcion_corta_gerencia=?, estado_gerencia=? where id_gerencia = ?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, obj.getGerenciaCentral().getId());

			pstm.setString(2, obj.getCodigoPropio());
			pstm.setString(3, obj.getDescripcion());
			pstm.setString(4, obj.getDescripcionCorta());
			pstm.setString(5, obj.getEstado());
			pstm.setString(6, obj.getId());

			estado = pstm.executeUpdate();
			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public int delete(String key) {
		int estado = -1;
		try {
			String sql = "delete from gerencia where id_gerencia=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, key);
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public Gerencia findById(String key) {
		Gerencia obj = null;
		try {
			String sql = "select * from gerencia where id_gerencia=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, key);
			rs = pstm.executeQuery();

			if (rs.next()) {
				obj = new Gerencia();

				GerenciaCentral gc = new GerenciaCentral();
				gc.setId(rs.getString("id_gerencia_c"));
				obj.setGerenciaCentral(gc);

				obj.setId(rs.getString("id_gerencia"));
				obj.setCodigoPropio(rs.getString("codigo_gerencia_propio"));
				obj.setDescripcion(rs.getString("descripcion_gerencia"));
				obj.setDescripcionCorta(rs.getString("descripcion_corta_gerencia"));
				obj.setEstado(rs.getString("estado_gerencia"));

			}
			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
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

	@Override
	public String generarId() {
		String id = "";
		Formatter fmt = new Formatter();
		try {
			String sql = "select id_gerencia from gerencia order by id_gerencia desc;";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next()) {
				id = String.valueOf(fmt.format("%04d", Integer.parseInt(rs.getString("id_gerencia")) + 1));
			}
			cerrarRecursos();
			fmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<Gerencia> findByGerenciaCentral(GerenciaCentral gerenciaCentral) {
		List<Gerencia> lista = new ArrayList<>();
		try {
			Gerencia obj;
			String sql = " select * from  gerencia where id_gerencia_c=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, gerenciaCentral.getId());
			rs = pstm.executeQuery();
			while (rs.next()) {
				obj = new Gerencia();

				GerenciaCentral gc = new GerenciaCentral();
				gc.setId(rs.getString("id_gerencia_c"));
				obj.setGerenciaCentral(gc);

				obj.setId(rs.getString("id_gerencia"));
				obj.setCodigoPropio(rs.getString("codigo_gerencia_propio"));
				obj.setDescripcion(rs.getString("descripcion_gerencia"));
				obj.setDescripcionCorta(rs.getString("descripcion_corta_gerencia"));
				obj.setEstado(rs.getString("estado_gerencia"));

				lista.add(obj);
			}
			cerrarRecursos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}
