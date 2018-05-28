package sac.millennium.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import sac.millennium.dao.IMenuDAO;
import sac.millennium.dao.IPerfilDAO;
import sac.millennium.dao.IUsuarioDAO;
import sac.millennium.dao.impl.MenuSqlserverDAOImpl;
import sac.millennium.dao.impl.PerfilSqlserverDAOImpl;
import sac.millennium.dao.impl.UsuarioSqlserverDAOImpl;
import sac.millennium.model.Menu;
import sac.millennium.model.Perfil;
import sac.millennium.model.Usuario;
import sac.millennium.service.IMenuService;
import sac.millennium.service.IPerfilService;
import sac.millennium.service.IUsuarioService;
import sac.millennium.service.impl.MenuServiceImpl;
import sac.millennium.service.impl.PerfilServiceImpl;
import sac.millennium.service.impl.UsuarioServiceImpl;
import sac.millennium.util.Conexion;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 5844963453614414477L;

	// DAO
	private IUsuarioDAO daoUsuario = new UsuarioSqlserverDAOImpl();
	private IPerfilDAO daoPerfil = new PerfilSqlserverDAOImpl();
	private IMenuDAO daoMenu = new MenuSqlserverDAOImpl();

	// services
	private IUsuarioService service = new UsuarioServiceImpl(daoUsuario);
	private IPerfilService servPerfil = new PerfilServiceImpl(daoPerfil);
	private IMenuService servMenu = new MenuServiceImpl(daoMenu);

	// globals
	private boolean loggedIn;
	private Usuario usuario;
	private Perfil perfil;
	private List<Menu> listaMenu;
	private MenuModel model;
	String ruta = "";

	@PostConstruct
	public void init() {
		usuario = new Usuario();
		perfil = new Perfil();
		listaMenu = new ArrayList<>();
		model = new DefaultMenuModel();
	}

	public void login(ActionEvent event) {
		FacesMessage message = null;

		this.usuario = service.iniciarSesion(this.usuario);

		if (this.usuario != null) {
			perfil = servPerfil.findById(this.usuario.getPerfil().getId());
			listaMenu = servMenu.findMenuByPerfil(perfil);
			listaMenu.forEach(x -> System.out.println(x));
			construirMenu();
			loggedIn = true;
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
			loggedIn = true;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido(a)", usuario.getNombre());
			ruta = "/satcontrolproyect/view/common/principal.xhtml";
		} else {
			System.out.println("Error de logeo");
			loggedIn = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de acceso",
					"Usuario o password es incorrecto");
			this.usuario = new Usuario();
			ruta = "/satcontrolproyect/";
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
		PrimeFaces.current().ajax().addCallbackParam("ruta", ruta);
	}

	public void logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		loggedIn = false;
		Conexion.desconectar();
	}

	public void construirMenu() {
		DefaultSubMenu firstSubmenu;
		// DefaultSubMenu secondSubmenu;
		DefaultMenuItem firstItem;
		DefaultMenuItem secondItem;
		// DefaultMenuItem thirdItem;

		for (Menu m : listaMenu) {
			if (m.getFormularioAsociado().equals("#") && m.getId().equals(m.getContenedor())) {
				firstSubmenu = new DefaultSubMenu(m.getNombreOpcion());
				for (Menu sm : listaMenu) {
					// if (sm.getFormularioAsociado().equals("#") && !sm.getId().equals(m.getId()))
					// {
					// secondSubmenu = new DefaultSubMenu(sm.getNombreOpcion());
					// // for (Menu ssm : listaMenu) {
					// // if (!ssm.getFormularioAsociado().equals("#") &&
					// // ssm.getContenedor().equals(sm.getId())) {
					// // thirdItem = new DefaultMenuItem(ssm.getNombreOpcion());
					// // thirdItem.setUrl(ssm.getFormularioAsociado());
					// // secondSubmenu.addElement(thirdItem);
					// // }
					// // }
					// firstSubmenu.addElement(secondSubmenu);
					// } else {
					if (!sm.getFormularioAsociado().equals("#") && sm.getContenedor().equals(m.getId())) {
						if (!sm.getId().equals(m.getId())) {
							secondItem = new DefaultMenuItem(sm.getNombreOpcion());
							secondItem.setUrl(sm.getFormularioAsociado());
							firstSubmenu.addElement(secondItem);
						}
					}
					// }
				}
				model.addElement(firstSubmenu);
			} else {
				if (!m.getFormularioAsociado().equals("#") && m.getId().equals(m.getContenedor())) {
					firstItem = new DefaultMenuItem(m.getNombreOpcion());
					firstItem.setUrl(m.getFormularioAsociado());
					model.addElement(firstItem);
				}
			}
		}
	}

	/*
	 * getters & setters
	 */

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Menu> getListaMenu() {
		return listaMenu;
	}

	public void setListaMenu(List<Menu> listaMenu) {
		this.listaMenu = listaMenu;
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

}
