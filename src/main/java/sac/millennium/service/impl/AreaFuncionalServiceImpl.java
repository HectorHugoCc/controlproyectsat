package sac.millennium.service.impl;

import java.util.List;

import sac.millennium.dao.IAreaFuncionalDAO;
import sac.millennium.model.AreaFuncional;
import sac.millennium.model.Gerencia;
import sac.millennium.service.IAreaFuncionalService;

public class AreaFuncionalServiceImpl implements IAreaFuncionalService {

	IAreaFuncionalDAO dao;

	public AreaFuncionalServiceImpl(IAreaFuncionalDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<AreaFuncional> findAll() {
		return dao.findAll();
	}

	@Override
	public int create(AreaFuncional obj) {
		return dao.create(obj);
	}

	@Override
	public int update(AreaFuncional obj) {
		return dao.update(obj);
	}

	@Override
	public int delete(String key) {
		return dao.delete(key);
	}

	@Override
	public AreaFuncional findById(String key) {
		return dao.findById(key);
	}

	@Override
	public String generarId() {

		return null;
	}

	@Override
	public List<AreaFuncional> findByGerencia(Gerencia gerencia) {

		return dao.findByGerencia(gerencia);
	}

}
