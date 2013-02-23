/**
 * Son jugadores reales.
 */
package com.personal.basket.dtos;

import java.io.Serializable;

public class JugadorRealDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoJugador;
	private String activo;
	private String nombre;
	private String apellidos;
	private String puesto; // En constantes: puede tener 3 valores Base, Alero, Pivot
	private String altura;
	private String peso;
	private double precio;
	//private java.util.Date fecalta;
	
	private String fecaltaMostrar;
	
	
	// FK
	private String codigoEquipoReal;
	private String codigoNacionalidad;
	
	public String getCodigoJugador() {
		return codigoJugador;
	}
	public void setCodigoJugador(String codigoJugador) {
		this.codigoJugador = codigoJugador;
	}	

	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
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

	public String getCodigoEquipoReal() {
		return codigoEquipoReal;
	}
	public void setCodigoEquipoReal(String codigoEquipoReal) {
		this.codigoEquipoReal = codigoEquipoReal;
	}
	public String getCodigoNacionalidad() {
		return codigoNacionalidad;
	}
	public void setCodigoNacionalidad(String codigoNacionalidad) {
		this.codigoNacionalidad = codigoNacionalidad;
	}
	/*
	public java.util.Date getFecalta() {
		return fecalta;
	}
	public void setFecalta(java.util.Date fecalta) {
		this.fecalta = fecalta;
	}
	*/
	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getFecaltaMostrar() {
		return fecaltaMostrar;
	}
	public void setFecaltaMostrar(String fecaltaMostrar) {
		this.fecaltaMostrar = fecaltaMostrar;
	}
	

	
	
	
	
}

