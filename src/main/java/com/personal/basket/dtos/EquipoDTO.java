package com.personal.basket.dtos;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * 
 * @author iballesteros
 *
 */
public class EquipoDTO implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	
	private String codigoEquipo;
	private String nombre;

	private int posicion; //Posicion en la tabla
	private double puntuacionTotal; // Puntos acumulados
	
	// PK
	// Liga a la que pertence el equipo
	//	Ctes.NO_ASIGNADO_LIGA --> Si no tiene asignada liga por el momento
	//	Un codigo con el codigo de la liga si pertenece a una liga.
	private String	codigoLiga; 
	

	
	
	public String getCodigoEquipo() {
		return codigoEquipo;
	}
	public void setCodigoEquipo(String codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
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
	public String getCodigoLiga() {
		return codigoLiga;
	}
	public void setCodigoLiga(String codigoLiga) {
		this.codigoLiga = codigoLiga;
	}


	

	
	
}
