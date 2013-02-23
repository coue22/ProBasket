package com.personal.basket.model;

import java.io.Serializable;

public class LigaJugadorModelDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String codLiga;
	private String codJugador;
	private java.util.Date fecalta;
	private String activo;
	private double dinero;
	private String codEquipo;
	
	
	public String getCodLiga() {
		return codLiga;
	}
	public void setCodLiga(String codLiga) {
		this.codLiga = codLiga;
	}
	public String getCodJugador() {
		return codJugador;
	}
	public void setCodJugador(String codJugador) {
		this.codJugador = codJugador;
	}
	public java.util.Date getFecalta() {
		return fecalta;
	}
	public void setFecalta(java.util.Date fecalta) {
		this.fecalta = fecalta;
	}
	
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public double getDinero() {
		return dinero;
	}
	public void setDinero(double dinero) {
		this.dinero = dinero;
	}
	public String getCodEquipo() {
		return codEquipo;
	}
	public void setCodEquipo(String codEquipo) {
		this.codEquipo = codEquipo;
	}
	

	
	

	
}
