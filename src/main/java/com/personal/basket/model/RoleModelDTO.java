package com.personal.basket.model;

import java.io.Serializable;

public class RoleModelDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String codRole;
	private String descripcion;
	
	
	public String getCodRole() {
		return codRole;
	}
	public void setCodRole(String codRole) {
		this.codRole = codRole;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

	
	
}
