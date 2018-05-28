package sac.millennium.service.impl;

import java.util.List;

import sac.millennium.dao.IUsuarioDAO;
import sac.millennium.model.Usuario;
import sac.millennium.service.IUsuarioService;

public class UsuarioServiceImpl implements IUsuarioService {

	IUsuarioDAO dao;

	public UsuarioServiceImpl(IUsuarioDAO dao) {
		this.dao = dao;
	}

	@Override
	public Usuario iniciarSesion(Usuario obj) {
		return dao.iniciarSesion(obj);
	}

	@Override
	public List<Usuario> findAll() {
		return dao.findAll();
	}

	@Override
	public int create(Usuario obj) {
		String id = generarId();
		obj.setId(id);
		return dao.create(obj);
	}

	@Override
	public int update(Usuario obj) {
		return dao.update(obj);
	}

	@Override
	public int delete(String key) {
		return dao.delete(key);
	}

	@Override
	public Usuario findById(String key) {
		return dao.findById(key);
	}

	@Override
	public String generarId() {
		return dao.generarId();
	}

}
