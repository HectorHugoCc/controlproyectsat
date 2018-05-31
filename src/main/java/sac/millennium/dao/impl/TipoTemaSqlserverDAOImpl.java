package sac.millennium.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import sac.millennium.dao.ITipoTemaDAO;
import sac.millennium.model.TipoTema;
import sac.millennium.util.Conexion;

public class TipoTemaSqlserverDAOImpl implements ITipoTemaDAO {

	private Connection cx;
	private ResultSet rs = null;
	private PreparedStatement pstm = null;

	public TipoTemaSqlserverDAOImpl() {
		cx = Conexion.conectar();
	}

	@Override
	public List<TipoTema> findAll() {
		List<TipoTema> lista = new ArrayList<>();
		try {
			TipoTema obj;
			String sql = " select * from tipo_tema";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				obj = new TipoTema();
				obj.setIdTipoTema(rs.getString("id_tipo_tema"));
				obj.setNombreTipoTema(rs.getString("nombre_tipo_tema"));
				obj.setSiglasTipoTema(rs.getString("siglas_tipo_tema"));

				lista.add(obj);
			}
			cerrarRecursos();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public int create(TipoTema obj) {
		int estado = -1;
		try {
			String sql = "insert into tipo_tema values(?,?,?)";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, obj.getIdTipoTema());
			pstm.setString(2, obj.getNombreTipoTema());
			pstm.setString(3, obj.getSiglasTipoTema());
			estado = pstm.executeUpdate();
			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public int update(TipoTema obj) {
		int estado = -1;
		try {
			String sql = "update tipo_tema set nombre_tipo_tema = ?, siglas_tipo_tema = ? where id_tipo_tema = ?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, obj.getNombreTipoTema());
			pstm.setString(2, obj.getSiglasTipoTema());
			pstm.setString(3, obj.getIdTipoTema());

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
			String sql = "delete from tipo_tema where id_tipo_tema=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, key);
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public TipoTema findById(String key) {
		TipoTema obj = null;
		try {
			String sql = "select * from tipo_tema where id_tipo_tema=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, key);
			rs = pstm.executeQuery();

			if (rs.next()) {
				obj = new TipoTema();
				obj.setIdTipoTema(rs.getString("id_tipo_tema"));
				obj.setNombreTipoTema(rs.getString("nombre_tipo_tema"));
				obj.setSiglasTipoTema(rs.getString("siglas_tipo_tema"));
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
			String sql = "select id_tipo_tema from tipo_tema order by id_tipo_tema desc;";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next()) {
				id = String.valueOf(rs.getString("id_tipo_tema") + 1);
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
