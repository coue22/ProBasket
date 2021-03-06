/**
 * Equipos Reales. Es el catalogo de equipos reales. 
 */

package com.personal.basket.dtos;

import java.io.Serializable;

public class EquipoRealDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoEquipoReal;
	private String nombre;
	private String siglas;
	private String activo;
	private String patrocinio;


	public String getCodigoEquipoReal() {
		return codigoEquipoReal;
	}
	public void setCodigoEquipoReal(String codigoEquipoReal) {
		this.codigoEquipoReal = codigoEquipoReal;
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
