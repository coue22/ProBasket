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
		return codigo.trim();
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion.trim();
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigoTipoMenu() {
		return codigoTipoMenu.trim();
	}
	public void setCodigoTipoMenu(String codigoTipoMenu) {
		this.codigoTipoMenu = codigoTipoMenu;
	}
	
	
	

	
}
