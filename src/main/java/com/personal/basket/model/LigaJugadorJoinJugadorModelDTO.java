package com.personal.basket.model;


import java.io.Serializable;

public class LigaJugadorJoinJugadorModelDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String codLiga;
	private String codJugador;
	private String nombre;
	private String apellidos;
	private double dinero;
	private String codEquipo;
	private String puesto;
	
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
	public String getCodEquipo() {
		return codEquipo;
	}
	public void setCodEquipo(String codEquipo) {
		this.codEquipo = codEquipo;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	

	

	
	

	
}
