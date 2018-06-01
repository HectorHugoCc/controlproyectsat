package sac.millennium.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import sac.millennium.dao.IPuestoDAO;
import sac.millennium.dao.impl.PuestoSqlserverDAOImpl;
import sac.millennium.model.Puesto;
import sac.millennium.service.IPuestoService;
import sac.millennium.service.impl.PuestoServiceImpl;

@SuppressWarnings("unused")
@ManagedBean
@ApplicationScoped
public class PuestoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private IPuestoDAO daoPuesto = new PuestoSqlserverDAOImpl();
	private IPuestoService servPuesto = new PuestoServiceImpl(daoPuesto);

	private Puesto puestoSeleccionado;

	private List<Puesto> listaPuesto = new ArrayList<>();

	@PostConstruct
	public void init() {
		puestoSeleccionado = new Puesto();
		// listarTodo();
	}

	public PuestoBean() {
		puestoSeleccionado = new Puesto();

	}

	public void listarTodo() {
		listaPuesto = servPuesto.findAll();
	}

	public void registrar() {
		System.out.println("Guardando......" + puestoSeleccionado.getId() + "--" + puestoSeleccionado.getDescripcion()
				+ "--" + puestoSeleccionado.getDescripcionCorta() + "--" + puestoSeleccionado.getEstado());

		servPuesto.create(puestoSeleccionado);

		puestoSeleccionado = new Puesto();
	}

	public Puesto getPuestoSeleccionado() {
		return puestoSeleccionado;
	}

	public void setPuestoSeleccionado(Puesto puestoSeleccionado) {
		this.puestoSeleccionado = puestoSeleccionado;
	}

	public List<Puesto> getListaPuesto() {
		listaPuesto = servPuesto.findAll();
		return listaPuesto;
	}

	public void setListaPuesto(List<Puesto> listaPuesto) {
		this.listaPuesto = listaPuesto;
	}

	public void onRowEdit(RowEditEvent event) {
		servPuesto.update((Puesto) event.getObject());
		FacesMessage msg = new FacesMessage("Puesto editado", ((Puesto) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelado", ((Puesto) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

}
