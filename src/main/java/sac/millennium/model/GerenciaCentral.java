package sac.millennium.model;

public class GerenciaCentral {

	private String id;
	private String codigoPropio;
	private String descripcion;
	private String descripcionCorta;
	private String estado;

	/*
	 * getters & setters
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigoPropio() {
		return codigoPropio;
	}

	public void setCodigoPropio(String codigoPropio) {
		this.codigoPropio = codigoPropio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
