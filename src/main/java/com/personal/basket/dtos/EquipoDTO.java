/**
 * Equipos Reales. Es el catalogo de equipos reales. 
 */

package com.personal.basket.dtos;

import java.io.Serializable;

public class EquipoDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String nombre;
	private String siglas;
	private int temporadaInicio;
	private int temporadaFin;
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSiglas() {
		return siglas;
	}
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	public int getTemporadaInicio() {
		return temporadaInicio;
	}
	public void setTemporadaInicio(int temporadaInicio) {
		this.temporadaInicio = temporadaInicio;
	}
	public int getTemporadaFin() {
		return temporadaFin;
	}
	public void setTemporadaFin(int temporadaFin) {
		this.temporadaFin = temporadaFin;
	}
	
	
}
