package sac.millennium.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import sac.millennium.dao.IGerenciaCentralDAO;
import sac.millennium.dao.ITemaDAO;
import sac.millennium.dao.ITipoTemaDAO;
import sac.millennium.dao.impl.GerenciaCentralSqlserverDAOImpl;
import sac.millennium.dao.impl.TemaSqlserverDAOImpl;
import sac.millennium.dao.impl.TipoTemaSqlserverDAOImpl;
import sac.millennium.model.GerenciaCentral;
import sac.millennium.model.Tema;
import sac.millennium.model.TipoTema;
import sac.millennium.service.IGerenciaCentralService;
import sac.millennium.service.ITemaService;
import sac.millennium.service.ITipoTemaService;
import sac.millennium.service.impl.GerenciaCentralServiceImpl;
import sac.millennium.service.impl.TemaServiceImpl;
import sac.millennium.service.impl.TipoTemaServiceImpl;

@ManagedBean
@ApplicationScoped
public class TemaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ITemaDAO daoTema = new TemaSqlserverDAOImpl();
	private ITemaService servTema = new TemaServiceImpl(daoTema);

	private IGerenciaCentralDAO daoGerCen = new GerenciaCentralSqlserverDAOImpl();
	private IGerenciaCentralService servGerCen = new GerenciaCentralServiceImpl(daoGerCen);

	private ITipoTemaDAO daoTipo = new TipoTemaSqlserverDAOImpl();
	private ITipoTemaService servTipo = new TipoTemaServiceImpl(daoTipo);

	private Tema tema;

	List<Tema> listaTema;
	List<GerenciaCentral> listaGerenciaCentral;
	List<TipoTema> listaTipo;

	private String idGerenciaCentral;
	private String idTipo;

	public TemaBean() {
		tema = new Tema();
	}

	public void registrar() {
		GerenciaCentral gc = new GerenciaCentral();
		gc.setId(idGerenciaCentral);
		tema.setGerenciaCentral(gc);

		TipoTema tt = servTipo.findById(idTipo);
		tema.setTipoTema(tt);

		servTema.create(tema);

	}

	public void listarTodo() {
		listaTema = servTema.findAll();
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public List<Tema> getListaTema() {
		listaTema = servTema.findAll();
		return listaTema;
	}

	public void setListaTema(List<Tema> listaTema) {
		this.listaTema = listaTema;
	}

	public List<GerenciaCentral> getListaGerenciaCentral() {
		listaGerenciaCentral = servGerCen.findAll();
		return listaGerenciaCentral;
	}

	public void setListaGerenciaCentral(List<GerenciaCentral> listaGerenciaCentral) {
		this.listaGerenciaCentral = listaGerenciaCentral;
	}

	public String getIdGerenciaCentral() {
		return idGerenciaCentral;
	}

	public void setIdGerenciaCentral(String idGerenciaCentral) {
		this.idGerenciaCentral = idGerenciaCentral;
	}

	public List<TipoTema> getListaTipo() {
		listaTipo = servTipo.findAll();
		return listaTipo;
	}

	public void setListaTipo(List<TipoTema> listaTipo) {
		this.listaTipo = listaTipo;
	}

	public String getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(String idTipo) {
		this.idTipo = idTipo;
	}

}
