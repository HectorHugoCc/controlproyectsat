package sac.millennium.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import sac.millennium.dao.IPerfilDAO;
import sac.millennium.model.Perfil;
import sac.millennium.util.Conexion;

public class PerfilSqlserverDAOImpl implements IPerfilDAO {

	private Connection cx;
	private ResultSet rs;
	private PreparedStatement pstm;

	public PerfilSqlserverDAOImpl() {
		cx = Conexion.conectar();
	}

	@Override
	public List<Perfil> findAll() {
		List<Perfil> lista = new ArrayList<>();
		try {
			Perfil obj;
			String sql = "select * from perfil";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				obj = new Perfil();
				obj.setId(rs.getString("id_perfil"));
				obj.setDescripcion(rs.getString("descripcion_perfil"));
				obj.setDescripcionCorta(rs.getString("descripcion_corta_perfil"));
				obj.setEstado(rs.getString("estado_perfil"));

				lista.add(obj);
			}
			cerrarRecursos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public int create(Perfil obj) {
		int estado = -1;
		try {
			String sql = "insert into perfil values(?,?,?,?)";
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

		System.out.println("Nuevo Perfil registrado >>> " + obj.getId() + "--" + obj.getDescripcion() + "--"
				+ obj.getDescripcionCorta());

		return estado;
	}

	@Override
	public int update(Perfil obj) {
		int estado = -1;
		try {
			String sql = "update perfil set descripcion_perfil = ?,descripcion_corta_perfil = ?,estado_perfil = ? where id_perfil = ?";
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
			String sql = "delete from perfil where id_perfil=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, key);
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public Perfil findById(String key) {
		Perfil obj = null;
		try {
			String sql = "select * from perfil where id_perfil=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, key);
			rs = pstm.executeQuery();

			if (rs.next()) {
				obj = new Perfil();
				obj.setId(rs.getString("id_perfil"));
				obj.setDescripcion("descripcion_perfil");
				obj.setDescripcionCorta("descripcion_corta_perfil");
				obj.setEstado("estado_perfil");
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
			String sql = "select id_perfil from perfil order by id_perfil desc;";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next()) {
				id = String.valueOf(fmt.format("%04d", Integer.parseInt(rs.getString("id_perfil")) + 1));
			}
			cerrarRecursos();
			fmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

}
