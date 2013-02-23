package com.personal.basket.model;

import java.io.Serializable;

public class LigaModelDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String codLiga;
	private String nombre;
	private String password;
	

	public String getCodLiga() {
		return codLiga;
	}
	public void setCodLiga(String codLiga) {
		this.codLiga = codLiga;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	
	
	

	
}
