/**
 * Representa a la tabla Economia.
 * Al registrarse un usuario se debe crear una economia.
 */


package com.personal.basket.dtos;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * 
 * @author iballesteros
 *
 */
public class EconomiaDTO implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String codigoEcono;
	private double dinero;
	
	
	public String getCodigoEcono() {
		return codigoEcono;
	}
	public void setCodigoEcono(String codigoEcono) {
		this.codigoEcono = codigoEcono;
	}
	public double getDinero() {
		return dinero;
	}
	public void setDinero(double dinero) {
		this.dinero = dinero;
	}
	
	


	
	
}
