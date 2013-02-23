package com.personal.basket.model;

import java.io.Serializable;

public class EquipoModelDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String codEquipo;
	private String nombre;

	private int posicion; //Posicion en la tabla
	private double puntuacionTotal; // Puntos acumulados
	
	private String	codLiga;

	
	

	public String getCodEquipo() {
		return codEquipo;
	}

	public void setCodEquipo(String codEquipo) {
		this.codEquipo = codEquipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public double getPuntuacionTotal() {
		return puntuacionTotal;
	}

	public void setPuntuacionTotal(double puntuacionTotal) {
		this.puntuacionTotal = puntuacionTotal;
	}

	public String getCodLiga() {
		return codLiga;
	}

	public void setCodLiga(String codLiga) {
		this.codLiga = codLiga;
	}


	
	
	

	
}
