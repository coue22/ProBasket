/**
 * Mapeará una tabla que estará rellena desde el inicio con los distintos
 * roles que puede haber. 
 * Se podrán leer los datos cuando sea preciso.
 * 
 * 	Ejemplos de role:
 * 		public static final String ROLE_CAPITAN	 			= "001";
		public static final String ROLE_ANOTADOR 			= "002";
		public static final String ROLE_DEFENSOR 			= "003";
 */
package com.personal.basket.dtos;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * 
 * @author iballesteros
 *
 */
public class RoleDTO implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String codigoRole; 
	private String descripcion;
	
	
	public String getCodigoRole() {
		return codigoRole;
	}
	public void setCodigoRole(String codigoRole) {
		this.codigoRole = codigoRole;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
