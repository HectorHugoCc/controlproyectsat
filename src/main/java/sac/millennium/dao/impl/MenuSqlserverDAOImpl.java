package sac.millennium.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import sac.millennium.dao.IMenuDAO;
import sac.millennium.model.Menu;
import sac.millennium.model.Perfil;
import sac.millennium.util.Conexion;

public class MenuSqlserverDAOImpl implements IMenuDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private Connection cx;
	private ResultSet rs = null;
	private PreparedStatement pstm = null;

	public MenuSqlserverDAOImpl() {
		cx = Conexion.conectar();
	}

	@Override
	public List<Menu> findMenuByPerfil(Perfil perfil) {
		List<Menu> lista = new ArrayList<>();
		Menu obj = null;
		try {
			String sql = "select * from menu m join r_perfil_menu r on m.id_menu = r.id_menu where r.id_perfil=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, perfil.getId());
			rs = pstm.executeQuery();

			while (rs.next()) {
				obj = new Menu();
				obj.setId(rs.getString("id_menu"));
				obj.setContenedor(rs.getString("contenedor_menu"));
				obj.setOrdenAparicion(rs.getInt("orden_aparicion_menu"));
				obj.setNombreOpcion(rs.getString("nombre_opcion_menu"));
				obj.setFormularioAsociado(rs.getString("formulario_asociado_menu"));
				obj.setEstado(rs.getString("estado_registro_menu"));
				lista.add(obj);
			}
			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Menu> listaHermanos(Menu menu) {
		List<Menu> lista = new ArrayList<>();
		Menu obj = null;
		try {
			String sql = "select m.id_menu, mn.nombre_opcion_menu as contenedor_menu, m.orden_aparicion_menu,m.formulario_asociado_menu, m.nombre_opcion_menu, m.estado_registro_menu from menu m join menu mn on mn.id_menu = m.contenedor_menu where m.contenedor_menu = ? and m.contenedor_menu != m.id_menu order by m.orden_aparicion_menu asc;";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, menu.getContenedor());
			rs = pstm.executeQuery();

			while (rs.next()) {
				obj = new Menu();
				obj.setId(rs.getString("id_menu"));
				obj.setContenedor(rs.getString("contenedor_menu"));
				obj.setOrdenAparicion(rs.getInt("orden_aparicion_menu"));
				obj.setNombreOpcion(rs.getString("nombre_opcion_menu"));
				obj.setFormularioAsociado(rs.getString("formulario_asociado_menu"));
				obj.setEstado(rs.getString("estado_registro_menu"));
				lista.add(obj);
			}
			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Menu> findAll() {
		List<Menu> lista = new ArrayList<>();
		Menu obj = null;
		try {
			String sql = "select m.id_menu, mn.nombre_opcion_menu as contenedor_menu, m.orden_aparicion_menu,m.formulario_asociado_menu, m.nombre_opcion_menu, m.estado_registro_menu from menu m join menu mn on mn.id_menu = m.contenedor_menu;";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				obj = new Menu();
				obj.setId(rs.getString("id_menu"));
				obj.setContenedor(rs.getString("contenedor_menu"));
				obj.setOrdenAparicion(rs.getInt("orden_aparicion_menu"));
				obj.setNombreOpcion(rs.getString("nombre_opcion_menu"));
				obj.setFormularioAsociado(rs.getString("formulario_asociado_menu"));
				obj.setEstado(rs.getString("estado_registro_menu"));
				lista.add(obj);
			}
			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public int create(Menu obj) {
		int estado = -1;
		try {
			String sql = "INSERT INTO menu(id_menu, contenedor_menu, orden_aparicion_menu, nombre_opcion_menu, formulario_asociado_menu, estado_registro_menu)\r\n"
					+ "VALUES (?,?,?,?,?,?)";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, obj.getId());
			pstm.setString(2, obj.getContenedor());
			pstm.setInt(3, obj.getOrdenAparicion());
			pstm.setString(4, obj.getNombreOpcion());
			pstm.setString(5, obj.getFormularioAsociado());
			pstm.setString(6, obj.getEstado());
			estado = pstm.executeUpdate();
			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("MENU_DAO: Nuevo Menu registrado >>> " + obj);
		return estado;
	}

	@Override
	public int update(Menu obj) {
		int estado = -1;
		try {
			String sql = "update menu set contenedor_menu=?,orden_aparicion_menu=?,nombre_opcion_menu=?,formulario_asociado_menu=?,estado_registro_menu=? where id_menu=?;";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, obj.getContenedor());
			pstm.setInt(2, obj.getOrdenAparicion());
			pstm.setString(3, obj.getNombreOpcion());
			pstm.setString(4, obj.getFormularioAsociado());
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
			String sql = "delete from menu where id_menu='88'";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, key);
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public Menu findById(String key) {
		Menu obj = null;
		try {
			String sql = "select * from menu where id_menu=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, key);
			rs = pstm.executeQuery();

			if (rs.next()) {
				obj = new Menu();
				obj.setId(rs.getString("id_menu"));
				obj.setContenedor(rs.getString("contenedor_menu"));
				obj.setOrdenAparicion(rs.getInt("orden_aparicion_menu"));
				obj.setNombreOpcion(rs.getString("nombre_opcion_menu"));
				obj.setFormularioAsociado(rs.getString("formulario_asociado_menu"));
				obj.setEstado(rs.getString("estado_registro_menu"));
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
			String sql = "select id_menu from menu order by id_menu desc;";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next()) {
				id = String.valueOf(fmt.format("%04d", Integer.parseInt(rs.getString("id_menu")) + 1));
			}
			cerrarRecursos();
			fmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<Menu> listaContenedoresHermanos() {
		List<Menu> lista = new ArrayList<>();
		Menu obj = null;
		try {
			String sql = "select m.id_menu, mn.nombre_opcion_menu as contenedor_menu, m.orden_aparicion_menu,m.formulario_asociado_menu, m.nombre_opcion_menu, m.estado_registro_menu from menu m join menu mn on mn.id_menu = m.contenedor_menu where m.contenedor_menu = m.id_menu and m.formulario_asociado_menu = '#' order by m.orden_aparicion_menu asc;";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				obj = new Menu();
				obj.setId(rs.getString("id_menu"));
				obj.setContenedor(rs.getString("contenedor_menu"));
				obj.setOrdenAparicion(rs.getInt("orden_aparicion_menu"));
				obj.setNombreOpcion(rs.getString("nombre_opcion_menu"));
				obj.setFormularioAsociado(rs.getString("formulario_asociado_menu"));
				obj.setEstado(rs.getString("estado_registro_menu"));
				lista.add(obj);
			}
			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Menu> listaContenedoresHermanos(Menu menu) {
		List<Menu> lista = new ArrayList<>();
		Menu obj = null;
		try {
			String sql = "select * from menu where contenedor_menu = ? and contenedor_menu != id_menu and formulario_asociado_menu = '#' order by orden_aparicion_menu asc;";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, menu.getContenedor());
			rs = pstm.executeQuery();
			while (rs.next()) {
				obj = new Menu();
				obj.setId(rs.getString("id_menu"));
				obj.setContenedor(rs.getString("contenedor_menu"));
				obj.setOrdenAparicion(rs.getInt("orden_aparicion_menu"));
				obj.setNombreOpcion(rs.getString("nombre_opcion_menu"));
				obj.setFormularioAsociado(rs.getString("formulario_asociado_menu"));
				obj.setEstado(rs.getString("estado_registro_menu"));
				lista.add(obj);
			}
			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Menu> listaItemsHermanos(Menu menu) {
		try {
			String sql = "select id_menu from menu order by id_menu desc;";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();

			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
