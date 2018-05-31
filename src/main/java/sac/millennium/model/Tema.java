package sac.millennium.model;

import java.util.Date;

public class Tema {
	private String id;
	private String nombre;
	private GerenciaCentral gerenciaCentral;
	private String naturaleza;
	private Date fechaInicio;
	private Date fechaFin;
	private String diasUtiles;
	private double avanceCompletado;
	private double avancePlaneado;
	private String estado;
	private TipoTema tipoTema;

	public Tema() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public GerenciaCentral getGerenciaCentral() {
		return gerenciaCentral;
	}

	public void setGerenciaCentral(GerenciaCentral gerenciaCentral) {
		this.gerenciaCentral = gerenciaCentral;
	}

	public String getNaturaleza() {
		return naturaleza;
	}

	public void setNaturaleza(String naturaleza) {
		this.naturaleza = naturaleza;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getDiasUtiles() {
		return diasUtiles;
	}

	public void setDiasUtiles(String diasUtiles) {
		this.diasUtiles = diasUtiles;
	}

	public double getAvanceCompletado() {
		return avanceCompletado;
	}

	public void setAvanceCompletado(double avanceCompletado) {
		this.avanceCompletado = avanceCompletado;
	}

	public double getAvancePlaneado() {
		return avancePlaneado;
	}

	public void setAvancePlaneado(double avancePlaneado) {
		this.avancePlaneado = avancePlaneado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public TipoTema getTipoTema() {
		return tipoTema;
	}

	public void setTipoTema(TipoTema tipoTema) {
		this.tipoTema = tipoTema;
	}
}
