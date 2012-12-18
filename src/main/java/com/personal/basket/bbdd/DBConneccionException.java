package com.personal.basket.bbdd;

public class DBConneccionException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String mensaje;
	
	public DBConneccionException(String msg){
		mensaje = msg;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
