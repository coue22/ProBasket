package com.personal.basket.model;

import java.io.Serializable;

public class MenuModelDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String codigo;
	private String descripcion;
	private String codigoTipoMenu;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigoTipoMenu() {
		return codigoTipoMenu;
	}
	public void setCodigoTipoMenu(String codigoTipoMenu) {
		this.codigoTipoMenu = codigoTipoMenu;
	}
	
	
	

	
}
