/**
 * Representa la relacion entre una liga y un jugador.
 *	Al crear una liga se deben crear todos los jugadores que son posibles para una liga.
 * 		 
 */

package com.personal.basket.dtos;

import java.io.Serializable;

public class LigaJugadorJoinJugadorDTO implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	


	private String codigoLiga;
	private String codigoJugador;
	private String nombre;
	private String apellidos;
	private double dinero;
	private String codigoEquipo;
	private String puesto;
	
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	


	
	
	
}
