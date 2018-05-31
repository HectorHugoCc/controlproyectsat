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

import sac.millennium.dao.IMenuDAO;
import sac.millennium.dao.IPerfilDAO;
import sac.millennium.dao.impl.MenuSqlserverDAOImpl;
import sac.millennium.dao.impl.PerfilSqlserverDAOImpl;
import sac.millennium.model.Menu;
import sac.millennium.model.Perfil;
import sac.millennium.service.IMenuService;
import sac.millennium.service.IPerfilService;
import sac.millennium.service.impl.MenuServiceImpl;
import sac.millennium.service.impl.PerfilServiceImpl;

@ManagedBean
@ApplicationScoped
public class MenuBean implements Serializable {

	private static final long serialVersionUID = -4656629421354393217L;

	// DAO
	private IMenuDAO daoMenu = new MenuSqlserverDAOImpl();
	private IPerfilDAO daoPerfil = new PerfilSqlserverDAOImpl();

	// service
	private IMenuService servMenu = new MenuServiceImpl(daoMenu);
	private IPerfilService servPerfil = new PerfilServiceImpl(daoPerfil);

	// globales
	private List<Menu> listamenu;
	private List<Menu> listaPadres;
	private List<Menu> listaHijos;
	private List<Menu> listaHermanos;
	private List<Perfil> listaPefil;
	private List<Menu> listaSubmenuEdit;
	private List<Menu> listaItemEdit;
	private Menu menu;
	private Menu submenuSeleccionado;
	private String tipoMenu;// submenu | item
	private String perfil;
	private String padre;// contenedor padre
	private String hijo;// contenedor hijo

	@PostConstruct
	public void init() {
		tipoMenu = Menu.SUBMENU;
		padre = "0";
		hijo = "0";
		this.menu = new Menu();
		this.submenuSeleccionado = new Menu();
		this.menu.setFormularioAsociado("#");
		this.menu.setContenedor("0");
		listamenu = new ArrayList<>();
		listaPadres = new ArrayList<>();
		listaHijos = new ArrayList<>();
		listaPefil = new ArrayList<>();
		listaHermanos = new ArrayList<>();
		listaHermanos = servMenu.listaContenedoresHermanos();
		listaSubmenuEdit = new ArrayList<>();
		listaSubmenuEdit = servMenu.listaContenedoresHermanos();
		listaItemEdit = new ArrayList<>();
		listarTodo();
		listarPerfiles();
		listarPadres();
	}

	public void guardar() {
		System.out.println("MENU_BEAN>>Guardando..." + this.menu);
		servMenu.create(menu);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Registrado"));
	}

	public void editar() {
		servMenu.update(menu);
	}

	public void eliminar() {

	}

	public void listarTodo() {
		listamenu = servMenu.findAll();
	}

	public void listarPadres() {// first submenu
		listaPadres = servMenu.listaContenedoresHermanos();
	}

	public void listarHijos() {// second submenu
		menu.setContenedor(padre);
		listaHijos = servMenu.listaContenedoresHermanos(menu);
	}

	public void listarHermanos() {// items y submenu del mismo nivel (para listar el combo de opciones)
		if (tipoMenu.equalsIgnoreCase(Menu.SUBMENU)) {
			listaHermanos = servMenu.listaContenedoresHermanos();
		} else {
			if (padre.equals("0")) {
				listaHermanos = servMenu.listaContenedoresHermanos();
			} else {
				this.menu.setContenedor(padre);
				listaHermanos = servMenu.listaHermanos(menu);
			}
		}
	}

	public void listarItemsEdit(Menu men) {
		System.out.println("MENUBEAN==>> " + men);
		System.out.println("Lista de hermanos");
		men.setContenedor(men.getId());
		listaItemEdit = servMenu.listaHermanos(men);
		listaItemEdit.forEach(x -> System.out.println(x));
	}

	private void listarPerfiles() {
		listaPefil = servPerfil.findAll();
	}

	public void setURL() {
		if (tipoMenu.equalsIgnoreCase("S")) {
			this.menu.setFormularioAsociado("#");
		} else {
			this.menu.setFormularioAsociado("");
		}
	}

	public void onRowEdit(RowEditEvent event) {
		System.out.println(event.getObject());
		servMenu.update((Menu) event.getObject());
		FacesMessage msg = new FacesMessage("Menu Editado", ((Menu) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edición Cancelada", ((Menu) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Celda Cambiada",
					"Antiguo: " + oldValue + ", Nuevo:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/*
	 * getters & setters
	 */
	public List<Menu> getListamenu() {
		return listamenu;
	}

	public void setListamenu(List<Menu> listamenu) {
		this.listamenu = listamenu;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getTipoMenu() {
		return tipoMenu;
	}

	public void setTipoMenu(String tipoMenu) {
		this.tipoMenu = tipoMenu;
	}

	public String getPadre() {
		return padre;
	}

	public void setPadre(String padre) {
		this.padre = padre;
	}

	public String getHijo() {
		return hijo;
	}

	public void setHijo(String hijo) {
		this.hijo = hijo;
	}

	public List<Menu> getListaPadres() {
		return listaPadres;
	}

	public void setListaPadres(List<Menu> listaPadres) {
		this.listaPadres = listaPadres;
	}

	public List<Menu> getListaHijos() {
		return listaHijos;
	}

	public void setListaHijos(List<Menu> listaHijos) {
		this.listaHijos = listaHijos;
	}

	public List<Menu> getListaHermanos() {
		return listaHermanos;
	}

	public void setListaHermanos(List<Menu> listaHermanos) {
		this.listaHermanos = listaHermanos;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getListaPefil() {
		return listaPefil;
	}

	public void setListaPefil(List<Perfil> listaPefil) {
		this.listaPefil = listaPefil;
	}

	public List<Menu> getListaSubmenuEdit() {
		return listaSubmenuEdit;
	}

	public void setListaSubmenuEdit(List<Menu> listaSubmenuEdit) {
		this.listaSubmenuEdit = listaSubmenuEdit;
	}

	public List<Menu> getListaItemEdit() {
		return listaItemEdit;
	}

	public void setListaItemEdit(List<Menu> listaItemEdit) {
		this.listaItemEdit = listaItemEdit;
	}

	public Menu getSubmenuSeleccionado() {
		return submenuSeleccionado;
	}

	public void setSubmenuSeleccionado(Menu submenuSeleccionado) {
		this.submenuSeleccionado = submenuSeleccionado;
	}

}
