package sac.millennium.service.impl;

import java.util.List;

import sac.millennium.dao.IGerenciaCentralDAO;
import sac.millennium.model.GerenciaCentral;
import sac.millennium.service.IGerenciaCentralService;

public class GerenciaCentralServiceImpl implements IGerenciaCentralService {

	IGerenciaCentralDAO dao;

	public GerenciaCentralServiceImpl(IGerenciaCentralDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<GerenciaCentral> findAll() {
		return dao.findAll();
	}

	@Override
	public int create(GerenciaCentral obj) {
		String id = generarId();
		obj.setId(id);
		return dao.create(obj);
	}

	@Override
	public int update(GerenciaCentral obj) {
		return dao.update(obj);
	}

	@Override
	public int delete(String key) {
		return dao.delete(key);
	}

	@Override
	public GerenciaCentral findById(String key) {
		return dao.findById(key);
	}

	@Override
	public String generarId() {
		return dao.generarId();
	}

}
