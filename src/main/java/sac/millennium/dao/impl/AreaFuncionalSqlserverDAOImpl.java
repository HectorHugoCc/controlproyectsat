package sac.millennium.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import sac.millennium.dao.IAreaFuncionalDAO;
import sac.millennium.model.AreaFuncional;
import sac.millennium.model.Gerencia;
import sac.millennium.model.GerenciaCentral;
import sac.millennium.util.Conexion;

public class AreaFuncionalSqlserverDAOImpl implements IAreaFuncionalDAO {

	private Connection cx;
	private ResultSet rs = null;
	private PreparedStatement pstm = null;

	public AreaFuncionalSqlserverDAOImpl() {
		cx = Conexion.conectar();
	}

	@Override
	public List<AreaFuncional> findAll() {

		List<AreaFuncional> lista = new ArrayList<>();
		AreaFuncional obj = null;
		try {
			String sql = "select * from area_funcional";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				obj = new AreaFuncional();

				GerenciaCentral gc = new GerenciaCentral();
				gc.setId(rs.getString("id_gerencia_c"));

				Gerencia ge = new Gerencia();
				ge.setId(rs.getString("id_gerencia"));
				ge.setGerenciaCentral(gc);

				obj.setGerencia(ge);

				obj.setId(rs.getString("id_area_funcional"));
				obj.setCodigoPropio(rs.getString("codigo_area_func_centro_costo_propio"));
				obj.setDescripcion(rs.getString("descripcion_area_func"));
				obj.setDescripcionCorta(rs.getString("descripcion_corta_area_func"));
				obj.setEstado(rs.getString("estado_area_func"));
				lista.add(obj);
			}
			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<AreaFuncional> findAllDescripcion() {
		List<AreaFuncional> lista = new ArrayList<>();
		AreaFuncional obj = null;
		try {
			String sql = "\r\n" + "  select gc.descripcion_gerencia_c,g.descripcion_gerencia,are.id_area_funcional,\r\n"
					+ "		are.codigo_area_func_centro_costo_propio,are.descripcion_area_func,\r\n"
					+ "		are.descripcion_corta_area_func,are.estado_area_func \r\n"
					+ "		from area_funcional are inner join gerencia_central gc\r\n"
					+ "  on are.id_gerencia_c=gc.id_gerencia_c inner join gerencia g\r\n"
					+ "  on are.id_gerencia=g.id_gerencia";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				obj = new AreaFuncional();

				GerenciaCentral gc = new GerenciaCentral();
				gc.setDescripcion(rs.getString("descripcion_gerencia_c"));

				Gerencia ge = new Gerencia();
				ge.setDescripcion(rs.getString("descripcion_gerencia"));
				ge.setGerenciaCentral(gc);

				obj.setGerencia(ge);

				obj.setId(rs.getString("id_area_funcional"));
				obj.setCodigoPropio(rs.getString("codigo_area_func_centro_costo_propio"));
				obj.setDescripcion(rs.getString("descripcion_area_func"));
				obj.setDescripcionCorta(rs.getString("descripcion_corta_area_func"));
				obj.setEstado(rs.getString("estado_area_func"));
				lista.add(obj);
			}
			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public int create(AreaFuncional obj) {
		int estado = -1;
		try {
			String sql = "insert into area_funcional values(?,?,?,?,?,?,?)";
			pstm = cx.prepareStatement(sql);

			pstm.setString(1, obj.getGerencia().getGerenciaCentral().getId());
			pstm.setString(2, obj.getGerencia().getId());
			pstm.setString(3, obj.getId());
			pstm.setString(4, obj.getCodigoPropio());
			pstm.setString(5, obj.getDescripcion());
			pstm.setString(6, obj.getDescripcionCorta());
			pstm.setString(7, obj.getEstado());
			estado = pstm.executeUpdate();
			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public int update(AreaFuncional obj) {
		int estado = -1;
		try {
			String sql = "update area_funcional set id_gerencia_c=?, id_gerencia=?, codigo_area_func_centro_costo_propio=?, descripcion_area_func=?, descripcion_corta_area_func=?, estado_area_func=? where id_area_funcional = ?";
			pstm = cx.prepareStatement(sql);

			pstm.setString(1, obj.getGerencia().getGerenciaCentral().getId());
			pstm.setString(2, obj.getGerencia().getId());
			pstm.setString(3, obj.getCodigoPropio());
			pstm.setString(4, obj.getDescripcion());
			pstm.setString(5, obj.getDescripcionCorta());
			pstm.setString(6, obj.getEstado());
			pstm.setString(7, obj.getId());

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
			String sql = "delete from area_funcional where id_area_funcional=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, key);
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public AreaFuncional findById(String key) {
		AreaFuncional obj = null;
		try {
			String sql = "select * from area_funcional where id_area_funcional=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, key);
			rs = pstm.executeQuery();

			if (rs.next()) {
				obj = new AreaFuncional();

				GerenciaCentral gc = new GerenciaCentral();
				gc.setId(rs.getString("id_gerencia_c"));

				Gerencia ge = new Gerencia();
				ge.setId(rs.getString("id_gerencia"));
				ge.setGerenciaCentral(gc);

				obj.setGerencia(ge);

				obj.setId(rs.getString("id_area_funcional"));
				obj.setCodigoPropio(rs.getString("codigo_area_func_centro_costo_propio"));
				obj.setDescripcion(rs.getString("descripcion_area_func"));
				obj.setDescripcionCorta(rs.getString("descripcion_corta_area_func"));
				obj.setEstado(rs.getString("estado_area_func"));

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
			String sql = "select id_area_funcional from area_funcional order by id_area_funcional desc;";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next()) {
				id = String.valueOf(fmt.format("%04d", Integer.parseInt(rs.getString("id_area_funcional")) + 1));
			}
			cerrarRecursos();
			fmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<AreaFuncional> findByGerencia(Gerencia gerencia) {
		List<AreaFuncional> lista = new ArrayList<>();
		try {
			AreaFuncional obj;
			String sql = " select * from area_funcional where id_gerencia=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, gerencia.getId());
			rs = pstm.executeQuery();
			while (rs.next()) {
				obj = new AreaFuncional();

				GerenciaCentral gc = new GerenciaCentral();
				gc.setId(rs.getString("id_gerencia_c"));

				Gerencia ge = new Gerencia();
				ge.setId(rs.getString("id_gerencia"));
				ge.setGerenciaCentral(gc);

				obj.setGerencia(ge);

				obj.setId(rs.getString("id_area_funcional"));
				obj.setCodigoPropio(rs.getString("codigo_area_func_centro_costo_propio"));
				obj.setDescripcion(rs.getString("descripcion_area_func"));
				obj.setDescripcionCorta(rs.getString("descripcion_corta_area_func"));
				obj.setEstado(rs.getString("estado_area_func"));

				lista.add(obj);
			}
			cerrarRecursos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}
