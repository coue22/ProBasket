package com.personal.basket.model;

import java.io.Serializable;

public class ConfiguracionModelDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String parametro;
	private String valor;
	
	
	public String getParametro() {
		return parametro.trim();
	}
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	public String getValor() {
		return valor.trim();
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

	
	
}
