package com.personal.basket.dtos;

import java.io.Serializable;
import java.util.Map;

public class NacionalidadDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String codigoNacionalidad;
	private String descripcion;
	
	
	public String getCodigoNacionalidad() {
		return codigoNacionalidad;
	}
	public void setCodigoNacionalidad(String codigoNacionalidad) {
		this.codigoNacionalidad = codigoNacionalidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

	
	
	
	
	
}
