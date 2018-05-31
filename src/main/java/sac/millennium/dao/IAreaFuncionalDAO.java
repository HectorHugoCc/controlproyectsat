package sac.millennium.dao;

import java.util.List;

import sac.millennium.model.AreaFuncional;
import sac.millennium.model.Gerencia;
import sac.millennium.util.IGenericCRUD;

public interface IAreaFuncionalDAO extends IGenericCRUD<AreaFuncional, String> {

	public List<AreaFuncional> findByGerencia(Gerencia gerencia);

	public List<AreaFuncional> findAllDescripcion();
}
