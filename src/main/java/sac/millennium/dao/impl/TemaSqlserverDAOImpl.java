package sac.millennium.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import sac.millennium.dao.ITemaDAO;
import sac.millennium.model.GerenciaCentral;
import sac.millennium.model.Tema;
import sac.millennium.model.TipoTema;
import sac.millennium.util.Conexion;
import sac.millennium.util.Convert;

public class TemaSqlserverDAOImpl implements ITemaDAO {

	private Connection cx;
	private ResultSet rs = null;
	private PreparedStatement pstm = null;

	public TemaSqlserverDAOImpl() {
		cx = Conexion.conectar();
	}

	@Override
	public List<Tema> findAll() {
		List<Tema> lista = new ArrayList<>();
		try {
			Tema obj;
			String sql = "  select t.id_tema,t.nombre_tema,gc.descripcion_gerencia_c,t.naturaleza_tema,t.fecha_inicio_tema,t.fecha_fin_tema,\r\n"
					+ "			t.dias_utiles_tema,t.avance_completado_tema,t.avance_planeado_tema,t.estado_tema,tt.nombre_tipo_tema \r\n"
					+ "			from tema t \r\n"
					+ "			inner join gerencia_central gc on t.general_central_involucrada=gc.id_gerencia_c \r\n"
					+ "			inner join tipo_tema tt on t.id_tipo_tema=tt.id_tipo_tema ";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				obj = new Tema();
				obj.setId(rs.getString("id_tema"));
				obj.setNombre(rs.getString("nombre_tema"));
				GerenciaCentral gc = new GerenciaCentral();
				gc.setDescripcion(rs.getString("descripcion_gerencia_c"));
				obj.setGerenciaCentral(gc);
				obj.setNaturaleza(rs.getString("naturaleza_tema"));
				obj.setFechaInicio(rs.getDate("fecha_inicio_tema"));
				obj.setFechaFin(rs.getDate("fecha_fin_tema"));
				obj.setDiasUtiles(rs.getString("dias_utiles_tema"));
				obj.setAvanceCompletado(rs.getDouble("avance_completado_tema"));
				obj.setAvancePlaneado(rs.getDouble("avance_planeado_tema"));
				obj.setEstado(rs.getString("estado_tema"));
				TipoTema tt = new TipoTema();
				tt.setNombreTipoTema(rs.getString("nombre_tipo_tema"));
				obj.setTipoTema(tt);

				lista.add(obj);
			}
			cerrarRecursos();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public int create(Tema obj) {
		int estado = -1;
		try {
			String sql = "insert into tema values(?,?,?,?,?,?,?,?,?,?,?)";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, obj.getId());
			pstm.setString(2, obj.getNombre());
			pstm.setString(3, obj.getGerenciaCentral().getId());
			pstm.setString(4, obj.getNaturaleza());
			pstm.setDate(5, Convert.conertirUtilDate_SqlDate(obj.getFechaInicio()));
			pstm.setDate(6, Convert.conertirUtilDate_SqlDate(obj.getFechaFin()));
			pstm.setString(7, obj.getDiasUtiles());
			pstm.setDouble(8, obj.getAvanceCompletado());
			pstm.setDouble(9, obj.getAvancePlaneado());
			pstm.setString(10, obj.getEstado());
			pstm.setString(11, obj.getTipoTema().getIdTipoTema());

			estado = pstm.executeUpdate();
			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public int update(Tema obj) {
		int estado = -1;
		try {
			String sql = "update tema set nombre_tema = ?, general_central_involucrada = ?, naturaleza_tema = ?, fecha_inicio_tema = ?, fecha_fin_tema = ?, dias_utiles_tema = ?, avance_completado_tema = ?, avance_planeado_tema = ?, estado_tema = ?, id_tipo_tema = ? where id_tema = ?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getGerenciaCentral().getId());
			pstm.setString(3, obj.getNaturaleza());
			pstm.setDate(4, Convert.conertirUtilDate_SqlDate(obj.getFechaInicio()));
			pstm.setDate(5, Convert.conertirUtilDate_SqlDate(obj.getFechaFin()));
			pstm.setString(6, obj.getDiasUtiles());
			pstm.setDouble(7, obj.getAvanceCompletado());
			pstm.setDouble(8, obj.getAvancePlaneado());
			pstm.setString(9, obj.getEstado());
			pstm.setString(10, obj.getTipoTema().getIdTipoTema());
			pstm.setString(11, obj.getId());

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
			String sql = "delete from tema where id_tema=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, key);
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public Tema findById(String key) {
		Tema obj = null;
		try {
			String sql = "select * from tema where id_tema=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, key);
			rs = pstm.executeQuery();

			if (rs.next()) {
				obj = new Tema();
				obj.setId(rs.getString("id_tema"));
				obj.setNombre(rs.getString("nombre_tema"));
				GerenciaCentral gc = new GerenciaCentral();
				gc.setId(rs.getString("general_central_involucrada"));
				obj.setGerenciaCentral(gc);
				obj.setNaturaleza(rs.getString("naturaleza_tema"));
				obj.setFechaInicio(rs.getDate("fecha_inicio_tema"));
				obj.setFechaFin(rs.getDate("fecha_fin_tema"));
				obj.setDiasUtiles(rs.getString("dias_utiles_tema"));
				obj.setAvanceCompletado(rs.getDouble("avance_completado_tema"));
				obj.setAvancePlaneado(rs.getDouble("avance_planeado_tema"));
				obj.setEstado(rs.getString("estado_tema"));
				TipoTema tt = new TipoTema();
				tt.setIdTipoTema(rs.getString("id_tipo_tema"));
				obj.setTipoTema(tt);
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
			String sql = "select SUBSTRING(id_tema,7,10) as id from tema order by id desc;";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next()) {
				id = String.valueOf(fmt.format("%04d", Integer.parseInt(rs.getString("id")) + 1));

			}
			cerrarRecursos();
			fmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Tema_DAO===>" + id);
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
