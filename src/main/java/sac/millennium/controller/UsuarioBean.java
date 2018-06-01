package sac.millennium.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.api.UIData;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import sac.millennium.dao.IAreaFuncionalDAO;
import sac.millennium.dao.IGerenciaCentralDAO;
import sac.millennium.dao.IGerenciaDAO;
import sac.millennium.dao.IPerfilDAO;
import sac.millennium.dao.IPuestoDAO;
import sac.millennium.dao.IUsuarioDAO;
import sac.millennium.dao.impl.AreaFuncionalSqlserverDAOImpl;
import sac.millennium.dao.impl.GerenciaCentralSqlserverDAOImpl;
import sac.millennium.dao.impl.GerenciaSqlserverDAOImpl;
import sac.millennium.dao.impl.PerfilSqlserverDAOImpl;
import sac.millennium.dao.impl.PuestoSqlserverDAOImpl;
import sac.millennium.dao.impl.UsuarioSqlserverDAOImpl;
import sac.millennium.model.AreaFuncional;
import sac.millennium.model.Gerencia;
import sac.millennium.model.GerenciaCentral;
import sac.millennium.model.Perfil;
import sac.millennium.model.Puesto;
import sac.millennium.model.Usuario;
import sac.millennium.service.IAreaFuncionalService;
import sac.millennium.service.IGerenciaCentralService;
import sac.millennium.service.IGerenciaService;
import sac.millennium.service.IPerfilService;
import sac.millennium.service.IPuestoService;
import sac.millennium.service.IUsuarioService;
import sac.millennium.service.impl.AreaFuncionalServiceImpl;
import sac.millennium.service.impl.GerenciaCentralServiceImpl;
import sac.millennium.service.impl.GerenciaServiceImpl;
import sac.millennium.service.impl.PerfilServiceImpl;
import sac.millennium.service.impl.PuestoServiceImpl;
import sac.millennium.service.impl.UsuarioServiceImpl;

@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private IUsuarioDAO daoUsuario = new UsuarioSqlserverDAOImpl();
	private IUsuarioService servUsuario = new UsuarioServiceImpl(daoUsuario);

	private IAreaFuncionalDAO daoAreaFunc = new AreaFuncionalSqlserverDAOImpl();
	private IAreaFuncionalService servAreaFunc = new AreaFuncionalServiceImpl(daoAreaFunc);

	private IGerenciaCentralDAO daoGerCen = new GerenciaCentralSqlserverDAOImpl();
	private IGerenciaCentralService servGerCen = new GerenciaCentralServiceImpl(daoGerCen);

	private IGerenciaDAO daoGer = new GerenciaSqlserverDAOImpl();
	private IGerenciaService servGere = new GerenciaServiceImpl(daoGer);

	private IPerfilDAO daoPerfil = new PerfilSqlserverDAOImpl();
	private IPerfilService servPerfil = new PerfilServiceImpl(daoPerfil);

	private IPuestoDAO daoPuesto = new PuestoSqlserverDAOImpl();
	private IPuestoService servPuesto = new PuestoServiceImpl(daoPuesto);

	private Usuario usuarioSeleccionado;
	// private GerenciaCentral gerencia_ce;
	// private Gerencia gerencia;
	private UIData dtFila;

	private String idGerenciaCentral;
	private String idGerencia;
	private String idAreaFunc;
	private String idPerfil;
	private String idPuesto;
	private String idUsuario;

	private List<Usuario> listaUsuario;
	private List<GerenciaCentral> listaGerenciaCentral;
	private List<Gerencia> listaGerencia;
	private List<AreaFuncional> listaAreaFuncional;
	private List<Perfil> listaPerfiles;
	private List<Puesto> listaPuestos;

	@PostConstruct
	public void init() {
		usuarioSeleccionado = new Usuario();
		listaUsuario = new ArrayList<>();
		listaGerenciaCentral = new ArrayList<>();
		listaGerencia = new ArrayList<>();
		listaAreaFuncional = new ArrayList<>();
		listaPerfiles = new ArrayList<>();
		listaPuestos = new ArrayList<>();
		listarTodo();

		// gerencia_ce = new GerenciaCentral();

	}

	public UsuarioBean() throws Exception {
		usuarioSeleccionado = new Usuario();
		listarTodo();
	}

	public void listarTodo() {
		listaUsuario = servUsuario.findAll();
		listaGerenciaCentral = servGerCen.findAll();
		listaPerfiles = servPerfil.findAll();
		listaPuestos = servPuesto.findAll();

	}

	public void listarGerencia() {
		System.out.println(idGerenciaCentral);
		GerenciaCentral ge = new GerenciaCentral();
		ge.setId(idGerenciaCentral);

		listaGerencia = servGere.findByGerenciaCentral(ge);
	}

	public void listarAreaFunc() {
		System.out.println("--" + idGerencia);
		Gerencia g = new Gerencia();
		g.setId(idGerencia);

		listaAreaFuncional = servAreaFunc.findByGerencia(g);
	}

	public void buscar() {

		// usuarioSeleccionado = new Usuario();

		usuarioSeleccionado = (Usuario) dtFila.getRowData();
		// listarGerencia();
		// listarAreaFunc();

		System.out.println("==" + usuarioSeleccionado.getAreaFuncional().getGerencia().getDescripcion());

	}

	public void registrar() {
		GerenciaCentral gc = new GerenciaCentral();
		gc.setId(idGerenciaCentral);

		Gerencia ge = new Gerencia();
		ge.setId(idGerencia);
		ge.setGerenciaCentral(gc);

		AreaFuncional are = new AreaFuncional();
		are.setId(idAreaFunc);
		are.setGerencia(ge);

		usuarioSeleccionado.setAreaFuncional(are);

		Perfil per = new Perfil();
		per.setId(idPerfil);
		usuarioSeleccionado.setPerfil(per);

		Puesto pu = new Puesto();
		pu.setId(idPuesto);
		usuarioSeleccionado.setPuesto(pu);

		servUsuario.create(usuarioSeleccionado);

		System.out.println("usuarioBean " + usuarioSeleccionado.getId() + "-" + usuarioSeleccionado.getCodigo());

		usuarioSeleccionado = new Usuario();
	}

	public void nuevo() {
		usuarioSeleccionado = new Usuario();
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public List<Usuario> getListaUsuario() {
		listaUsuario = servUsuario.findAll();
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public void modificar() {
		GerenciaCentral gc = new GerenciaCentral();
		gc.setId(idGerenciaCentral);

		Gerencia ge = new Gerencia();
		ge.setId(idGerencia);
		ge.setGerenciaCentral(gc);

		AreaFuncional are = new AreaFuncional();
		are.setId(idAreaFunc);
		are.setGerencia(ge);
		usuarioSeleccionado.setAreaFuncional(are);

		Perfil per = new Perfil();
		per.setId(idPerfil);
		usuarioSeleccionado.setPerfil(per);

		Puesto pu = new Puesto();
		pu.setId(idPuesto);
		usuarioSeleccionado.setPuesto(pu);

		servUsuario.update(usuarioSeleccionado);

	}

	public void onRowEdit(RowEditEvent event) {

		// modificar();

		System.out.println("----" + usuarioSeleccionado.getCodigo() + usuarioSeleccionado.getAreaFuncional());
		// Usuario usuarioSeleccionado = new Usuario();
		/*
		 * GerenciaCentral gc = new GerenciaCentral(); gc.setId(idGerenciaCentral);
		 * 
		 * Gerencia ge = new Gerencia(); ge.setId(idGerencia);
		 * ge.setGerenciaCentral(gc);
		 * 
		 * AreaFuncional are = new AreaFuncional(); are.setId(idAreaFunc);
		 * are.setGerencia(ge);
		 * 
		 * Perfil per = new Perfil(); per.setId(idPerfil);
		 * usuarioSeleccionado.setPerfil(per);
		 * 
		 * Puesto pu = new Puesto(); pu.setId(idPuesto);
		 * usuarioSeleccionado.setPuesto(pu);
		 * 
		 * usuarioSeleccionado.setAreaFuncional(are);
		 * 
		 * servUsuario.update(usuarioSeleccionado);
		 */
		// servUsuario.update(((RowEditEvent) this.usuarioSeleccionado)
		// event.getObject());

		this.usuarioSeleccionado = (Usuario) event.getObject();

		servUsuario.update(usuarioSeleccionado);
		/*
		 * usuarioSeleccionado.setCodigo(usuarioSeleccionado.getCodigo());
		 * usuarioSeleccionado.setNombre(usuarioSeleccionado.getNombre());
		 * usuarioSeleccionado.setClave(usuarioSeleccionado.getClave());
		 * usuarioSeleccionado.setCorreo(usuarioSeleccionado.getCorreo());
		 * usuarioSeleccionado.getAreaFuncional().getGerencia().getGerenciaCentral().
		 * setId(idGerenciaCentral);
		 * usuarioSeleccionado.getAreaFuncional().getGerencia().setId(idGerencia);
		 * usuarioSeleccionado.getAreaFuncional().setId(idAreaFunc);
		 * usuarioSeleccionado.getPerfil().setId(idPerfil);
		 * usuarioSeleccionado.getPuesto().setId(idPuesto);
		 * usuarioSeleccionado.setEstado(usuarioSeleccionado.getEstado());
		 * 
		 * servUsuario.update(this.usuarioSeleccionado); event.getPhaseId(); //
		 * dataEdit.setTotalVenta(dataEdit.getPrecioProducto().multiply(dataEdit.
		 * getKilosVendidos(), // MathContext.UNLIMITED));
		 * 
		 * // Usuario cc = (Usuario) event.getObject();
		 * 
		 * FacesMessage msg = new FacesMessage( "Usuario " + usuarioSeleccionado.getId()
		 * + ": " + usuarioSeleccionado.getNombre() + " Editado");
		 * FacesContext.getCurrentInstance().addMessage(null, msg);
		 */

	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelado", ((Usuario) event.getObject()).getId());
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

	public List<GerenciaCentral> getListaGerenciaCentral() {
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

		return listaAreaFuncional;
	}

	public void setListaAreaFuncional(List<AreaFuncional> listaAreaFuncional) {
		this.listaAreaFuncional = listaAreaFuncional;
	}

	/*
	 * public GerenciaCentral getGerencia_ce() { return gerencia_ce; }
	 * 
	 * public void setGerencia_ce(GerenciaCentral gerencia_ce) { this.gerencia_ce =
	 * gerencia_ce; }
	 * 
	 * public Gerencia getGerencia() { return gerencia; }
	 * 
	 * public void setGerencia(Gerencia gerencia) { this.gerencia = gerencia; }
	 */

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

	public String getIdAreaFunc() {
		return idAreaFunc;
	}

	public void setIdAreaFunc(String idAreaFunc) {
		this.idAreaFunc = idAreaFunc;
	}

	public List<Perfil> getListaPerfiles() {
		return listaPerfiles;
	}

	public void setListaPerfiles(List<Perfil> listaPerfiles) {
		this.listaPerfiles = listaPerfiles;
	}

	public List<Puesto> getListaPuestos() {
		return listaPuestos;
	}

	public void setListaPuestos(List<Puesto> listaPuestos) {
		this.listaPuestos = listaPuestos;
	}

	public String getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(String idPuesto) {
		this.idPuesto = idPuesto;
	}

	public UIData getDtFila() {
		return dtFila;
	}

	public void setDtFila(UIData dtFila) {
		this.dtFila = dtFila;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

}
