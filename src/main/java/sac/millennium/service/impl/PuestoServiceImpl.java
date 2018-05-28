package sac.millennium.service.impl;

import java.util.List;

import sac.millennium.dao.IPuestoDAO;
import sac.millennium.model.Puesto;
import sac.millennium.service.IPuestoService;

public class PuestoServiceImpl implements IPuestoService {

	IPuestoDAO dao;

	public PuestoServiceImpl(IPuestoDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<Puesto> findAll() {

		return dao.findAll();
	}

	@Override
	public int create(Puesto obj) {
		String id = generarId();
		obj.setId(id);
		return dao.create(obj);
	}

	@Override
	public int update(Puesto obj) {
		return dao.update(obj);
	}

	@Override
	public int delete(String key) {
		return dao.delete(key);
	}

	@Override
	public Puesto findById(String key) {
		return dao.findById(key);
	}

	@Override
	public String generarId() {
		// TODO Auto-generated method stub
		return dao.generarId();
	}

}
