package sac.millennium.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import sac.millennium.dao.IPuestoDAO;
import sac.millennium.model.Puesto;
import sac.millennium.util.Conexion;

public class PuestoSqlserverDAOImpl implements IPuestoDAO {

	private Connection cx;
	private ResultSet rs;
	private PreparedStatement pstm;

	public PuestoSqlserverDAOImpl() {
		cx = Conexion.conectar();
	}

	@Override
	public List<Puesto> findAll() {
		List<Puesto> lista = new ArrayList<>();
		try {
			Puesto obj;
			String sql = "select * from puesto";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				obj = new Puesto();
				obj.setId(rs.getString("id_puesto"));
				obj.setDescripcion(rs.getString("descripcion_puesto"));
				obj.setDescripcionCorta(rs.getString("descripcion_corta_puesto"));
				obj.setEstado(rs.getString("estado_puesto"));

				lista.add(obj);
			}
			cerrarRecursos();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public int create(Puesto obj) {
		int estado = -1;
		try {
			String sql = "insert into puesto values(?,?,?,?)";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, obj.getId());
			pstm.setString(2, obj.getDescripcion());
			pstm.setString(3, obj.getDescripcionCorta());
			pstm.setString(4, obj.getEstado());
			estado = pstm.executeUpdate();
			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public int update(Puesto obj) {
		int estado = -1;
		try {
			String sql = "update puesto set descripcion_puesto = ?,descripcion_corta_puesto = ?,estado_puesto = ? where id_puesto = ?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, obj.getDescripcion());
			pstm.setString(2, obj.getDescripcionCorta());
			pstm.setString(3, obj.getEstado());
			pstm.setString(4, obj.getId());

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
			String sql = "delete from puesto where id_puesto=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, key);
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public Puesto findById(String key) {
		Puesto obj = null;
		try {
			String sql = "select * from puesto where id_puesto=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, key);
			rs = pstm.executeQuery();

			if (rs.next()) {
				obj = new Puesto();
				obj.setId(rs.getString("id_puesto"));
				obj.setDescripcion(rs.getString("descripcion_puesto"));
				obj.setDescripcionCorta(rs.getString("descripcion_corta_puesto"));
				obj.setEstado(rs.getString("estado_puesto"));
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
			String sql = "select id_puesto from puesto order by id_puesto desc;";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next()) {
				id = String.valueOf(fmt.format("%04d", Integer.parseInt(rs.getString("id_puesto")) + 1));
			}
			cerrarRecursos();
			fmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

}
