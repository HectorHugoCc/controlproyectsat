package sac.millennium.service;

import java.util.List;

import sac.millennium.model.Gerencia;
import sac.millennium.model.GerenciaCentral;
import sac.millennium.util.IGenericCRUD;

public interface IGerenciaService extends IGenericCRUD<Gerencia, String> {

	public List<Gerencia> findByGerenciaCentral(GerenciaCentral gerenciaCentral);

	public List<Gerencia> findAllDescripcion();
}
