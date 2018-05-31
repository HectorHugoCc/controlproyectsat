package sac.millennium.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import sac.millennium.dao.IGerenciaCentralDAO;
import sac.millennium.dao.impl.GerenciaCentralSqlserverDAOImpl;
import sac.millennium.model.GerenciaCentral;
import sac.millennium.service.IGerenciaCentralService;
import sac.millennium.service.impl.GerenciaCentralServiceImpl;

@ManagedBean
@ApplicationScoped
public class GerenciaCentralBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private IGerenciaCentralDAO daoGerenciaCentral = new GerenciaCentralSqlserverDAOImpl();
	private IGerenciaCentralService servGerenciaCentral = new GerenciaCentralServiceImpl(daoGerenciaCentral);

	private List<GerenciaCentral> listaGerenciaCentral = new ArrayList<>();
	private GerenciaCentral gerenciaCentralSeleccionado;

	public GerenciaCentralBean() {
		gerenciaCentralSeleccionado = new GerenciaCentral();
	}

	public void listarTodo() {
		listaGerenciaCentral = servGerenciaCentral.findAll();
	}

	public void registra() {
		System.out.println(
				"==>" + gerenciaCentralSeleccionado.getId() + "-" + gerenciaCentralSeleccionado.getCodigoPropio());
		servGerenciaCentral.create(gerenciaCentralSeleccionado);
	}

	public void leer() {

	}

	public void modfica() {
		try {
			GerenciaCentral gerenciaCentralSeleccionado = new GerenciaCentral();
			servGerenciaCentral.update(gerenciaCentralSeleccionado);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
					"Se modificó usuario: " + gerenciaCentralSeleccionado.getDescripcion()));

			nuevo();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso",
					"Error al intentar modificar: " + e.getMessage()));
		}

	}

	private String nuevo() {
		gerenciaCentralSeleccionado = new GerenciaCentral();
		return null;
	}

	public void onRowEdit(RowEditEvent event) {
		servGerenciaCentral.update((GerenciaCentral) event.getObject());
		FacesMessage msg = new FacesMessage("Car Edited", ((GerenciaCentral) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((GerenciaCentral) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<GerenciaCentral> getListaGerenciaCentral() {
		listaGerenciaCentral = servGerenciaCentral.findAll();
		return listaGerenciaCentral;
	}

	public void setListaGerenciaCentral(List<GerenciaCentral> listaGerenciaCentral) {
		this.listaGerenciaCentral = listaGerenciaCentral;
	}

	public GerenciaCentral getGerenciaCentralSeleccionado() {
		return gerenciaCentralSeleccionado;
	}

	public void setGerenciaCentralSeleccionado(GerenciaCentral gerenciaCentralSeleccionado) {
		this.gerenciaCentralSeleccionado = gerenciaCentralSeleccionado;
	}

}
