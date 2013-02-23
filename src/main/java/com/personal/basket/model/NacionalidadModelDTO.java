package com.personal.basket.model;

import java.io.Serializable;

public class NacionalidadModelDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String codNacionalidad;
	private String descripcion;
	
	public String getCodNacionalidad() {
		return codNacionalidad;
	}
	public void setCodNacionalidad(String codNacionalidad) {
		this.codNacionalidad = codNacionalidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

	
	
}
