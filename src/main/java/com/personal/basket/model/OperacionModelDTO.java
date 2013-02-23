package com.personal.basket.model;

import java.io.Serializable;

public class OperacionModelDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String codOpera;
	private String descripcion;
	
	
	public String getCodOpera() {
		return codOpera;
	}
	public void setCodOpera(String codOpera) {
		this.codOpera = codOpera;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}
