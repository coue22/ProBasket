/**
 * Representa la relacion entre una liga y un jugador.
 *	Al crear una liga se deben crear todos los jugadores que son posibles para una liga.
 * 		 
 */

package com.personal.basket.dtos;

import java.io.Serializable;

public class LigaJugadorDTO implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// PK esta formado por codigoLiga y codigoJugador
	private String codigoLiga;
	private String codigoJugador;
	
	private String fechaIni;
	
	// Si el codigoEquipo es Ctes.NO_ASIGNADO_EQUIPO entonces es su precio de puja.
	// Si el codigoEquipo es un valor real entonces es el precio por el que fue comprado.
	private double dinero;
	
	// FK - Puede ser nulo si no tiene equipo asignado el jugador en la liga.
	// Al crear una liga se deben generar todos los jugadores  con este codigo con
	// Ctes.NO_ASIGNADO_EQUIPO
	private String codigoEquipo;

	
	
	
	public String getCodigoLiga() {
		return codigoLiga;
	}

	public void setCodigoLiga(String codigoLiga) {
		this.codigoLiga = codigoLiga;
	}

	public String getCodigoJugador() {
		return codigoJugador;
	}

	public void setCodigoJugador(String codigoJugador) {
		this.codigoJugador = codigoJugador;
	}

	public String getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}

	public double getDinero() {
		return dinero;
	}

	public void setDinero(double dinero) {
		this.dinero = dinero;
	}

	public String getCodigoEquipo() {
		return codigoEquipo;
	}

	public void setCodigoEquipo(String codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}
	
	

	
}
