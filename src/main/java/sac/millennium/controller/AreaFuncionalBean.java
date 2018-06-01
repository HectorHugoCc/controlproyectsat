package sac.millennium.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import sac.millennium.dao.IAreaFuncionalDAO;
import sac.millennium.dao.IGerenciaCentralDAO;
import sac.millennium.dao.IGerenciaDAO;
import sac.millennium.dao.impl.AreaFuncionalSqlserverDAOImpl;
import sac.millennium.dao.impl.GerenciaCentralSqlserverDAOImpl;
import sac.millennium.dao.impl.GerenciaSqlserverDAOImpl;
import sac.millennium.model.AreaFuncional;
import sac.millennium.model.Gerencia;
import sac.millennium.model.GerenciaCentral;
import sac.millennium.service.IAreaFuncionalService;
import sac.millennium.service.IGerenciaCentralService;
import sac.millennium.service.IGerenciaService;
import sac.millennium.service.impl.AreaFuncionalServiceImpl;
import sac.millennium.service.impl.GerenciaCentralServiceImpl;
import sac.millennium.service.impl.GerenciaServiceImpl;

@ManagedBean
@SessionScoped
public class AreaFuncionalBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private IAreaFuncionalDAO daoAreaFunc = new AreaFuncionalSqlserverDAOImpl();
	private IAreaFuncionalService servAreaFunc = new AreaFuncionalServiceImpl(daoAreaFunc);

	private IGerenciaCentralDAO daoGerCen = new GerenciaCentralSqlserverDAOImpl();
	private IGerenciaCentralService servGerCen = new GerenciaCentralServiceImpl(daoGerCen);

	private IGerenciaDAO daoGer = new GerenciaSqlserverDAOImpl();
	private IGerenciaService servGere = new GerenciaServiceImpl(daoGer);

	private AreaFuncional areaFuncionalSeleccionado = new AreaFuncional();

	private String idGerenciaCentral;
	private String idGerencia;

	private List<GerenciaCentral> listaGerenciaCentral = new ArrayList<>();
	private List<Gerencia> listaGerencia = new ArrayList<>();
	private List<AreaFuncional> listaAreaFuncional = new ArrayList<>();

	public void listarTodo() {
		listaAreaFuncional = servAreaFunc.findAllDescripcion();
		listaGerenciaCentral = servGerCen.findAll();
	}

	public void listarGerencia() {
		GerenciaCentral gc = new GerenciaCentral();
		gc.setId(idGerenciaCentral);

		listaGerencia = servGere.findByGerenciaCentral(gc);
	}

	public void registrar() {
		GerenciaCentral gc = new GerenciaCentral();
		gc.setId(idGerenciaCentral);

		Gerencia ge = new Gerencia();
		ge.setId(idGerencia);
		ge.setGerenciaCentral(gc);
		areaFuncionalSeleccionado.setGerencia(ge);

		servAreaFunc.create(areaFuncionalSeleccionado);

	}

	public String getIdGerenciaCentral() {
		return idGerenciaCentral;
	}

	public void setIdGerenciaCentral(String idGerenciaCentral) {
		this.idGerenciaCentral = idGerenciaCentral;
	}

	public String getIdGerencia() {
		return idGerencia;
	}

	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}

	public List<GerenciaCentral> getListaGerenciaCentral() {
		listaGerenciaCentral = servGerCen.findAll();
		return listaGerenciaCentral;
	}

	public void setListaGerenciaCentral(List<GerenciaCentral> listaGerenciaCentral) {
		this.listaGerenciaCentral = listaGerenciaCentral;
	}

	public List<Gerencia> getListaGerencia() {

		return listaGerencia;
	}

	public void setListaGerencia(List<Gerencia> listaGerencia) {
		this.listaGerencia = listaGerencia;
	}

	public List<AreaFuncional> getListaAreaFuncional() {
		listaAreaFuncional = servAreaFunc.findAllDescripcion();
		return listaAreaFuncional;
	}

	public void setListaAreaFuncional(List<AreaFuncional> listaAreaFuncional) {
		this.listaAreaFuncional = listaAreaFuncional;
	}

	public AreaFuncional getAreaFuncionalSeleccionado() {
		return areaFuncionalSeleccionado;
	}

	public void setAreaFuncionalSeleccionado(AreaFuncional areaFuncionalSeleccionado) {
		this.areaFuncionalSeleccionado = areaFuncionalSeleccionado;
	}

}
