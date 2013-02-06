/**
 * Representa la salida de una inscripcion. Es para guardar el 
 * 
 */


package com.personal.basket.dtos;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * 
 * @author iballesteros
 *
 */
public class SalidaInscripcionDTO implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String codigoLiga;
	private String codigoEquipo;

	private String nombreLiga;
	private String nombreEquipo;
	
	private String codigoEcono;
	
	public String getCodigoLiga() {
		return codigoLiga;
	}
	public void setCodigoLiga(String codigoLiga) {
		this.codigoLiga = codigoLiga;
	}
	public String getCodigoEquipo() {
		return codigoEquipo;
	}
	public void setCodigoEquipo(String codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}
	public String getNombreLiga() {
		return nombreLiga;
	}
	public void setNombreLiga(String nombreLiga) {
		this.nombreLiga = nombreLiga;
	}
	public String getNombreEquipo() {
		return nombreEquipo;
	}
	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}
	public String getCodigoEcono() {
		return codigoEcono;
	}
	public void setCodigoEcono(String codigoEcono) {
		this.codigoEcono = codigoEcono;
	}

	
	
	
	
}
