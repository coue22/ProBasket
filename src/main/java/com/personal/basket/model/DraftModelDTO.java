package com.personal.basket.model;

import java.io.Serializable;

public class DraftModelDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String codLiga;
	private String codEquipo;
	private String codJugador;
	private int orden;
	
	
	public String getCodLiga() {
		return codLiga;
	}
	public void setCodLiga(String codLiga) {
		this.codLiga = codLiga;
	}
	public String getCodEquipo() {
		return codEquipo;
	}
	public void setCodEquipo(String codEquipo) {
		this.codEquipo = codEquipo;
	}
	public String getCodJugador() {
		return codJugador;
	}
	public void setCodJugador(String codJugador) {
		this.codJugador = codJugador;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}

	
	

	
}
