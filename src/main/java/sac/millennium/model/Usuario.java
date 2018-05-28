package sac.millennium.model;

public class Usuario {

	private String id;
	private String codigo;
	private String nombre;
	private String clave;
	private String correo;
	private AreaFuncional areaFuncional;
	private Perfil perfil;
	private Puesto puesto;
	private String estado;

	/*
	 * getters & setters
	 */
	public Usuario() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public AreaFuncional getAreaFuncional() {
		return areaFuncional;
	}

	public void setAreaFuncional(AreaFuncional areaFuncional) {
		this.areaFuncional = areaFuncional;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
