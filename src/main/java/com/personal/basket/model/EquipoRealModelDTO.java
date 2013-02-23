package com.personal.basket.model;

import java.io.Serializable;

public class EquipoRealModelDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String codEquipoReal;
	private String nombre;
	private String siglas;
	private String activo;
	private String patrocinio;
	
	
	public String getCodEquipoReal() {
		return codEquipoReal;
	}
	public void setCodEquipoReal(String codEquipoReal) {
		this.codEquipoReal = codEquipoReal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSiglas() {
		return siglas;
	}
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public String getPatrocinio() {
		return patrocinio;
	}
	public void setPatrocinio(String patrocinio) {
		this.patrocinio = patrocinio;
	}
	

	
	
	
}
