/**
 * Representa la relacion entre un Equipo y un jugador.
 *	Segun se realizan compras/ventas de jugadores se debe reflejar aquí.
 * 		 
 */

package com.personal.basket.dtos;

import java.io.Serializable;

public class EquipoJugadorDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// PK esta formado por codigoEquipo y codigoJugador
	private String codigoEquipo;
	private String codigoJugador;
	
	private String fechaIni;
	
	private double precioVenta;
	private double indemnizacion;
	private double nomina;
	
	// Propiedad temporadl para calculos en una jornada..
	private double valoracionTemp;
	
	
	// FK - Es una clave de role.
	// Ctes.NO_ASIGNADO_ROLE
	// Valor de role.
	private String codigoRole;


	public String getCodigoEquipo() {
		return codigoEquipo;
	}


	public void setCodigoEquipo(String codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
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


	public double getPrecioVenta() {
		return precioVenta;
	}


	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}


	public double getIndemnizacion() {
		return indemnizacion;
	}


	public void setIndemnizacion(double indemnizacion) {
		this.indemnizacion = indemnizacion;
	}


	public double getNomina() {
		return nomina;
	}


	public void setNomina(double nomina) {
		this.nomina = nomina;
	}


	public double getValoracionTemp() {
		return valoracionTemp;
	}


	public void setValoracionTemp(double valoracionTemp) {
		this.valoracionTemp = valoracionTemp;
	}


	public String getCodigoRole() {
		return codigoRole;
	}


	public void setCodigoRole(String codigoRole) {
		this.codigoRole = codigoRole;
	} 
	
	
	
	
}
