package com.personal.basket.model;

import java.io.Serializable;

public class EquipoJugadorModelDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String codEquipo;
	private String codJugador;
	private java.util.Date fecalta;
	private java.util.Date fecbaja;
	private double precioventa;
	private double indemnizacion;
	private double valoraciontemp;
	private double nomina;
	// FK
	private String codRole;
	
	
	public String getCodEquipo() {
		return codEquipo;
	}
	public void setCodEquipo(String codEquipo) {
		this.codEquipo = codEquipo;
	}
	public String getCodJugador() {
		return codJugador;
	}
	public void setCodJugador(String codJugador) {
		this.codJugador = codJugador;
	}
	public java.util.Date getFecalta() {
		return fecalta;
	}
	public void setFecalta(java.util.Date fecalta) {
		this.fecalta = fecalta;
	}
	public java.util.Date getFecbaja() {
		return fecbaja;
	}
	public void setFecbaja(java.util.Date fecbaja) {
		this.fecbaja = fecbaja;
	}
	public double getPrecioventa() {
		return precioventa;
	}
	public void setPrecioventa(double precioventa) {
		this.precioventa = precioventa;
	}
	public double getIndemnizacion() {
		return indemnizacion;
	}
	public void setIndemnizacion(double indemnizacion) {
		this.indemnizacion = indemnizacion;
	}
	public double getValoraciontemp() {
		return valoraciontemp;
	}
	public void setValoraciontemp(double valoraciontemp) {
		this.valoraciontemp = valoraciontemp;
	}
	public double getNomina() {
		return nomina;
	}
	public void setNomina(double nomina) {
		this.nomina = nomina;
	}
	public String getCodRole() {
		return codRole;
	}
	public void setCodRole(String codRole) {
		this.codRole = codRole;
	}
	
	
	
	
}
