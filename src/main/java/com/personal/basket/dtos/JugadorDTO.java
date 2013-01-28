/**
 * Son jugadores reales.
 */
package com.personal.basket.dtos;

import java.io.Serializable;

public class JugadorDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoJugador;
	private boolean activo;
	private String nombre;
	private String apellido;
	private String puesto; // En constantes: puede tener 3 valores Base, Alero, Pivot
	private String altura;
	private String peso;
	private String nacionalidad;
	
	// PK
	private String codigoEquipoReal;
	
/*
	private int temporadaInicio;
	private int temporadaFin;	
*/
	

	
	public String getCodigoJugador() {
		return codigoJugador;
	}
	public void setCodigoJugador(String codigoJugador) {
		this.codigoJugador = codigoJugador;
	}	
	
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getCodigoEquipoReal() {
		return codigoEquipoReal;
	}
	public void setCodigoEquipoReal(String codigoEquipoReal) {
		this.codigoEquipoReal = codigoEquipoReal;
	}
	
/*
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
*/
	
	
	
	
	
}

