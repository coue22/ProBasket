package com.personal.basket.model;

import java.io.Serializable;

public class DraftTurnoModelDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String codLiga;
	private String codEquipo;
	private int turno;
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
	public int getTurno() {
		return turno;
	}
	public void setTurno(int turno) {
		this.turno = turno;
	}
	
	
	

	
}
