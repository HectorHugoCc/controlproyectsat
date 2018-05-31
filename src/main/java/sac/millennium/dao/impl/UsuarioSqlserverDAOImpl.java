package sac.millennium.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import sac.millennium.dao.IUsuarioDAO;
import sac.millennium.model.AreaFuncional;
import sac.millennium.model.Gerencia;
import sac.millennium.model.GerenciaCentral;
import sac.millennium.model.Perfil;
import sac.millennium.model.Puesto;
import sac.millennium.model.Usuario;
import sac.millennium.util.Conexion;

public class UsuarioSqlserverDAOImpl implements IUsuarioDAO {

	private Connection cx;
	private ResultSet rs;
	private PreparedStatement pstm;

	public UsuarioSqlserverDAOImpl() {
		cx = Conexion.conectar();
	}

	@Override
	public Usuario iniciarSesion(Usuario obj) {
		Usuario usuario = null;
		try {
			String sql = "select * from usuario where codigo_usuario=? and palabra_clave_usuario=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, obj.getCodigo());
			pstm.setString(2, obj.getClave());
			rs = pstm.executeQuery();
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getString("id_usuario"));
				usuario.setCodigo(rs.getString("codigo_usuario"));
				usuario.setNombre(rs.getString("nombre_usuario"));
				usuario.setClave(rs.getString("palabra_clave_usuario"));
				usuario.setCorreo(rs.getString("cuenta_correo_usuario"));

				AreaFuncional af = new AreaFuncional();
				Gerencia g = new Gerencia();
				GerenciaCentral gc = new GerenciaCentral();
				gc.setId(rs.getString("gerencia_c_pertenece_usuario"));

				g.setId(rs.getString("gerencia_pertenece_usuario"));

				g.setGerenciaCentral(gc);
				af.setId(rs.getString("area_func_pertenece_usuario"));
				af.setGerencia(g);

				usuario.setAreaFuncional(af);
				Perfil p = new Perfil();
				p.setId(rs.getString("id_perfil_usuario"));
				usuario.setPerfil(p);
				Puesto pu = new Puesto();
				pu.setId(rs.getString("id_puesto_usuario"));
				usuario.setPuesto(pu);
				usuario.setEstado(rs.getString("estado_usuario"));
			}

			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	@Override
	public List<Usuario> findAll() {
		List<Usuario> lista = new ArrayList<>();
		try {
			Usuario obj;
			String sql = "select u.id_usuario,u.codigo_usuario,u.nombre_usuario,u.palabra_clave_usuario,u.cuenta_correo_usuario, \r\n"
					+ "  gc.descripcion_gerencia_c,ge.descripcion_gerencia,arf.descripcion_area_func  , perf.descripcion_perfil,\r\n"
					+ "   pu.descripcion_puesto, u.estado_usuario\r\n" + "  from usuario u \r\n"
					+ "  inner join gerencia_central gc on u.gerencia_c_pertenece_usuario=gc.id_gerencia_c\r\n"
					+ "  inner join gerencia ge on u.gerencia_pertenece_usuario=ge.id_gerencia\r\n"
					+ "  inner join area_funcional arf on u.area_func_pertenece_usuario=arf.id_area_funcional\r\n"
					+ "  inner  join perfil perf on u.id_perfil_usuario=perf.id_perfil \r\n"
					+ " inner  join puesto pu on u.id_puesto_usuario=pu.id_puesto";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				obj = new Usuario();
				obj.setId(rs.getString("id_usuario"));
				obj.setCodigo(rs.getString("codigo_usuario"));
				obj.setNombre(rs.getString("nombre_usuario"));
				obj.setClave(rs.getString("palabra_clave_usuario"));
				obj.setCorreo(rs.getString("cuenta_correo_usuario"));
				AreaFuncional af = new AreaFuncional();
				Gerencia g = new Gerencia();
				GerenciaCentral gc = new GerenciaCentral();
				gc.setDescripcion(rs.getString("descripcion_gerencia_c"));
				g.setDescripcion(rs.getString("descripcion_gerencia"));
				g.setGerenciaCentral(gc);
				af.setDescripcion(rs.getString("descripcion_area_func"));
				af.setGerencia(g);
				obj.setAreaFuncional(af);
				Perfil p = new Perfil();
				p.setDescripcion(rs.getString("descripcion_perfil"));
				obj.setPerfil(p);
				Puesto pu = new Puesto();
				pu.setDescripcion(rs.getString("descripcion_puesto"));
				obj.setPuesto(pu);
				obj.setEstado(rs.getString("estado_usuario"));

				lista.add(obj);
			}
			cerrarRecursos();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public int create(Usuario obj) {
		int estado = -1;
		try {
			String sql = "insert into usuario(id_usuario, codigo_usuario, nombre_usuario, palabra_clave_usuario, cuenta_correo_usuario, gerencia_c_pertenece_usuario, gerencia_pertenece_usuario, area_func_pertenece_usuario, id_perfil_usuario, id_puesto_usuario, estado_usuario) values(?,?,?,?,?,?,?,?,?,?,?)";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, obj.getId());
			pstm.setString(2, obj.getCodigo());
			pstm.setString(3, obj.getNombre());
			pstm.setString(4, obj.getClave());
			pstm.setString(5, obj.getCorreo());
			pstm.setString(6, obj.getAreaFuncional().getGerencia().getGerenciaCentral().getId());
			pstm.setString(7, obj.getAreaFuncional().getGerencia().getId());
			pstm.setString(8, obj.getAreaFuncional().getId());
			pstm.setString(9, obj.getPerfil().getId());
			pstm.setString(10, obj.getPuesto().getId());
			pstm.setString(11, obj.getEstado());

			estado = pstm.executeUpdate();
			cerrarRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return estado;
	}

	@Override
	public int update(Usuario obj) {
		int estado = -1;
		try {
			String sql = "update usuario set codigo_usuario=?, nombre_usuario=?, palabra_clave_usuario=?, cuenta_correo_usuario=?, gerencia_c_pertenece_usuario=?, gerencia_pertenece_usuario=?, area_func_pertenece_usuario=?, id_perfil_usuario=?, id_puesto_usuario=?, estado_usuario=? where id_usuario = ?\r\n"
					+ "";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, obj.getCodigo());
			pstm.setString(2, obj.getNombre());
			pstm.setString(3, obj.getClave());
			pstm.setString(4, obj.getCorreo());
			pstm.setString(5, obj.getAreaFuncional().getGerencia().getGerenciaCentral().getId());
			pstm.setString(6, obj.getAreaFuncional().getGerencia().getId());
			pstm.setString(7, obj.getAreaFuncional().getId());
			pstm.setString(8, obj.getPerfil().getId());
			pstm.setString(9, obj.getPuesto().getId());

			pstm.setString(10, obj.getEstado());

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
			String sql = "delete from usuario where id_usuario=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, key);
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public Usuario findById(String key) {
		Usuario obj = null;
		try {
			String sql = "select * from usuario where id_usuario=?";
			pstm = cx.prepareStatement(sql);
			pstm.setString(1, key);
			rs = pstm.executeQuery();

			if (rs.next()) {
				obj = new Usuario();
				obj.setId(rs.getString("id_usuario"));
				obj.setCodigo(rs.getString("codigo_usuario"));
				obj.setNombre(rs.getString("nombre_usuario"));
				obj.setClave(rs.getString("palabra_clave_usuario"));
				obj.setCorreo(rs.getString("cuenta_correo_usuario"));
				AreaFuncional af = new AreaFuncional();
				Gerencia g = new Gerencia();
				GerenciaCentral gc = new GerenciaCentral();
				gc.setId(rs.getString("gerencia_c_pertenece_usuario"));
				g.setId(rs.getString("gerencia_pertenece_usuario"));
				g.setGerenciaCentral(gc);
				af.setId(rs.getString("area_func_pertenece_usuario"));
				af.setGerencia(g);
				obj.setAreaFuncional(af);
				Perfil p = new Perfil();
				p.setId(rs.getString("id_perfil_usuario"));
				obj.setPerfil(p);
				Puesto pu = new Puesto();
				pu.setId(rs.getString("id_puesto_usuario"));
				obj.setEstado(rs.getString("estado_usuario"));
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
			String sql = "select id_usuario from usuario order by id_usuario desc;";
			pstm = cx.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next()) {
				id = String.valueOf(fmt.format("%05d", Integer.parseInt(rs.getString("id_usuario")) + 1));
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

	/*
	 * public List<Usuario> findAllG(String cod) { List<Usuario> lista = new
	 * ArrayList<>(); Usuario obj = null; try { String sql =
	 * "select * from usuario u\r\n" + " where u.gerencia_pertenece_usuario=?"; pstm
	 * = cx.prepareStatement(sql); pstm.setString(1, cod); rs = pstm.executeQuery();
	 * 
	 * if (rs.next()) { obj = new Usuario(); obj.setId(rs.getString("id_usuario"));
	 * obj.setCodigo(rs.getString("codigo_usuario"));
	 * obj.setNombre(rs.getString("nombre_usuario"));
	 * obj.setClave(rs.getString("palabra_clave_usuario"));
	 * obj.setCorreo(rs.getString("cuenta_correo_usuario")); AreaFuncional af = new
	 * AreaFuncional(); Gerencia g = new Gerencia(); GerenciaCentral gc = new
	 * GerenciaCentral(); gc.setId(rs.getString("gerencia_c_pertenece_usuario"));
	 * g.setId(rs.getString("gerencia_pertenece_usuario"));
	 * g.setGerenciaCentral(gc);
	 * af.setId(rs.getString("area_func_pertenece_usuario")); af.setGerencia(g);
	 * obj.setAreaFuncional(af); Perfil p = new Perfil();
	 * p.setId(rs.getString("id_perfil_usuario")); obj.setPerfil(p); Puesto pu = new
	 * Puesto(); pu.setId(rs.getString("id_puesto_usuario")); obj.setPuesto(pu);
	 * obj.setEstado(rs.getString("estado_usuario"));
	 * 
	 * lista.add(obj); } cerrarRecursos(); } catch (Exception e) {
	 * e.printStackTrace(); } return lista; }
	 */
}
