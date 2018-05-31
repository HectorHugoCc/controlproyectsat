package sac.millennium.service.impl;

import java.util.List;

import sac.millennium.dao.IGerenciaDAO;
import sac.millennium.model.Gerencia;
import sac.millennium.model.GerenciaCentral;
import sac.millennium.service.IGerenciaService;

public class GerenciaServiceImpl implements IGerenciaService {

	IGerenciaDAO dao;

	public GerenciaServiceImpl(IGerenciaDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<Gerencia> findAll() {
		return dao.findAll();
	}

	@Override
	public int create(Gerencia obj) {
		String id = generarId();
		obj.setId(id);
		return dao.create(obj);
	}

	@Override
	public int update(Gerencia obj) {
		return dao.update(obj);
	}

	@Override
	public int delete(String key) {
		return dao.delete(key);
	}

	@Override
	public Gerencia findById(String key) {
		return dao.findById(key);
	}

	@Override
	public String generarId() {
		return dao.generarId();
	}

	@Override
	public List<Gerencia> findByGerenciaCentral(GerenciaCentral gerenciaCentral) {
		return dao.findByGerenciaCentral(gerenciaCentral);
	}

	@Override
	public List<Gerencia> findAllDescripcion() {
		return dao.findAllDescripcion();
	}

}
