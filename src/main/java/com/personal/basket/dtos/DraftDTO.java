package com.personal.basket.dtos;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * 
 * @author iballesteros
 *
 */
public class DraftDTO implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;


	private String	codigoLiga;
	private String codigoEquipo;
	private String codigoJugador;
	private int orden;
	
	
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
	public String getCodigoJugador() {
		return codigoJugador;
	}
	public void setCodigoJugador(String codigoJugador) {
		this.codigoJugador = codigoJugador;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	

	
	


	

	
	
}
