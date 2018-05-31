package sac.millennium.service.impl;

import java.time.LocalDate;
import java.util.List;

import sac.millennium.dao.ITemaDAO;
import sac.millennium.model.Tema;
import sac.millennium.service.ITemaService;

public class TemaServiceImpl implements ITemaService {

	ITemaDAO dao;

	public TemaServiceImpl(ITemaDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<Tema> findAll() {
		return dao.findAll();
	}

	@Override
	public int create(Tema obj) {
		String id = generarId();

		System.out.println("sigla: " + obj.getTipoTema().getSiglasTipoTema());

		LocalDate date = LocalDate.now();

		String tipo = obj.getTipoTema().getSiglasTipoTema().concat(String.valueOf(date.getYear()));

		obj.setId(tipo.concat(id));

		System.out.println(obj.getId());

		return dao.create(obj);
	}

	@Override
	public int update(Tema obj) {
		return dao.update(obj);
	}

	@Override
	public int delete(String key) {
		return dao.delete(key);
	}

	@Override
	public Tema findById(String key) {
		return dao.findById(key);
	}

	@Override
	public String generarId() {
		return dao.generarId();
	}

}
