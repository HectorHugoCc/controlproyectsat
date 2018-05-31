package sac.millennium.service.impl;

import java.util.List;

import sac.millennium.dao.ITipoTemaDAO;
import sac.millennium.model.TipoTema;
import sac.millennium.service.ITipoTemaService;

public class TipoTemaServiceImpl implements ITipoTemaService {

	ITipoTemaDAO dao;

	public TipoTemaServiceImpl(ITipoTemaDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<TipoTema> findAll() {
		return dao.findAll();
	}

	@Override
	public int create(TipoTema obj) {
		String id = generarId();
		obj.setIdTipoTema(id);
		return dao.create(obj);
	}

	@Override
	public int update(TipoTema obj) {
		return dao.update(obj);
	}

	@Override
	public int delete(String key) {
		return dao.delete(key);
	}

	@Override
	public TipoTema findById(String key) {
		return dao.findById(key);
	}

	@Override
	public String generarId() {
		return dao.generarId();
	}

}
