package sac.millennium.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import sac.millennium.dao.IGerenciaCentralDAO;
import sac.millennium.dao.IGerenciaDAO;
import sac.millennium.dao.impl.GerenciaCentralSqlserverDAOImpl;
import sac.millennium.dao.impl.GerenciaSqlserverDAOImpl;
import sac.millennium.model.Gerencia;
import sac.millennium.model.GerenciaCentral;
import sac.millennium.service.IGerenciaCentralService;
import sac.millennium.service.IGerenciaService;
import sac.millennium.service.impl.GerenciaCentralServiceImpl;
import sac.millennium.service.impl.GerenciaServiceImpl;

@ManagedBean
@ApplicationScoped
public class GerenciaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private IGerenciaDAO daoGerencia = new GerenciaSqlserverDAOImpl();
	private IGerenciaService servGerencia = new GerenciaServiceImpl(daoGerencia);

	private IGerenciaCentralDAO daoGerCen = new GerenciaCentralSqlserverDAOImpl();
	private IGerenciaCentralService servGerCen = new GerenciaCentralServiceImpl(daoGerCen);

	private List<Gerencia> listaGerencia = new ArrayList<>();
	private List<GerenciaCentral> listaGerenciaCentral = new ArrayList<>();

	private Gerencia gerenciaSeleccionada;

	private String idGerenciaCentral;

	public GerenciaBean() {
		gerenciaSeleccionada = new Gerencia();
	}

	public void listarTodo() {

		listaGerencia = servGerencia.findAllDescripcion();
		listaGerenciaCentral = servGerCen.findAll();
	}

	public void registra() {
		GerenciaCentral gece = new GerenciaCentral();
		gece.setId(idGerenciaCentral);
		gerenciaSeleccionada.setGerenciaCentral(gece);

		servGerencia.create(gerenciaSeleccionada);
	}

	public List<Gerencia> getListaGerencia() {
		listaGerencia = servGerencia.findAllDescripcion();
		return listaGerencia;
	}

	public void setListaGerencia(List<Gerencia> listaGerencia) {
		this.listaGerencia = listaGerencia;
	}

	public Gerencia getGerenciaSeleccionada() {
		return gerenciaSeleccionada;
	}

	public void setGerenciaSeleccionada(Gerencia gerenciaSeleccionada) {
		this.gerenciaSeleccionada = gerenciaSeleccionada;
	}

	public String getIdGerenciaCentral() {
		return idGerenciaCentral;
	}

	public void setIdGerenciaCentral(String idGerenciaCentral) {
		this.idGerenciaCentral = idGerenciaCentral;
	}

	public List<GerenciaCentral> getListaGerenciaCentral() {
		listaGerenciaCentral = servGerCen.findAll();
		return listaGerenciaCentral;
	}

	public void setListaGerenciaCentral(List<GerenciaCentral> listaGerenciaCentral) {
		this.listaGerenciaCentral = listaGerenciaCentral;
	}

}
