package com.personal.basket.model;

import java.io.Serializable;

public class EconomiaModelDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String codEcono;
	private double dinero;
	
	

	public String getCodEcono() {
		return codEcono;
	}
	public void setCodEcono(String codEcono) {
		this.codEcono = codEcono;
	}
	public double getDinero() {
		return dinero;
	}
	public void setDinero(double dinero) {
		this.dinero = dinero;
	}
	
	
	
	

	
}
