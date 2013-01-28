/**
 * Mapeará una tabla que estará rellena desde el inicio con las distintas
 * operaciones posibles
 * Se podrán leer los datos cuando sea preciso.
 * 
 * 	Ejemplos de operacion:
 * 		Abono por venta de jugador
 * 		Abono por partido en casa
 * 		....
 * 		....
 * 		Cargo por compra de jugador.
 * 		Cargo por ....
 * 		....
 */
package com.personal.basket.dtos;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * 
 * @author iballesteros
 *
 */
public class OperacionDTO implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String codigoOpera; 
	private String descripcion;
	
	
	public String getCodigoOpera() {
		return codigoOpera;
	}
	public void setCodigoOpera(String codigoOpera) {
		this.codigoOpera = codigoOpera;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

	
	
}
