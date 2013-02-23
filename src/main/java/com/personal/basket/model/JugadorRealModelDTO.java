package com.personal.basket.model;

import java.io.Serializable;
import java.util.Date;

public class JugadorRealModelDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String codJugador;
	private String activo;
	private String nombre;
	private String apellidos;
	private String puesto;
	private String altura;
	private String peso;
	private java.util.Date fecalta;
	private double precio;
	private String codEquipoReal;
	private String codNacionalidad;
	
	
	
	public String getCodJugador() {
		return codJugador;
	}
	public void setCodJugador(String codJugador) {
		this.codJugador = codJugador;
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
	public java.util.Date getFecalta() {
		return fecalta;
	}
	public void setFecalta(java.util.Date fecalta) {
		this.fecalta = fecalta;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}	
	public String getCodEquipoReal() {
		return codEquipoReal;
	}
	public void setCodEquipoReal(String codEquipoReal) {
		this.codEquipoReal = codEquipoReal;
	}
	public String getCodNacionalidad() {
		return codNacionalidad;
	}
	public void setCodNacionalidad(String codNacionalidad) {
		this.codNacionalidad = codNacionalidad;
	}
	
}
