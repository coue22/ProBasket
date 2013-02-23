package com.personal.basket.dtos;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * 
 * @author iballesteros
 *
 */
public class DraftTurnoDTO implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;


	private String	codigoLiga;
	private String codigoEquipo;
	private int turno;
	
	public String getCodigoLiga() {
		return codigoLiga;
	}
	public void setCodigoLiga(String codigoLiga) {
		this.codigoLiga = codigoLiga;
	}
	public String getCodigoEquipo() {
		return codigoEquipo;
	}
	public void setCodigoEquipo(String codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}
	public int getTurno() {
		return turno;
	}
	public void setTurno(int turno) {
		this.turno = turno;
	}
	
	
}
