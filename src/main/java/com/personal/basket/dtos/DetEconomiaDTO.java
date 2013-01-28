/**
 * Mapeará una tabla "DetEconomia"
 */
package com.personal.basket.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


/**
 * 
 * @author iballesteros
 *
 */
public class DetEconomiaDTO implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	// PK
	private String codigoDetEconomia;
	private Date fecha;
	private String detalle;
	
	// FK
	private String codigoOpera;
	// FK
	private String codigoEcono;
	
	
	public String getCodigoDetEconomia() {
		return codigoDetEconomia;
	}
	public void setCodigoDetEconomia(String codigoDetEconomia) {
		this.codigoDetEconomia = codigoDetEconomia;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getCodigoOpera() {
		return codigoOpera;
	}
	public void setCodigoOpera(String codigoOpera) {
		this.codigoOpera = codigoOpera;
	}
	public String getCodigoEcono() {
		return codigoEcono;
	}
	public void setCodigoEcono(String codigoEcono) {
		this.codigoEcono = codigoEcono;
	} 

	
	
}
