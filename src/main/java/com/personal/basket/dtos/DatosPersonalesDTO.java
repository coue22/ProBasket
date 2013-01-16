package com.personal.basket.dtos;

import java.io.Serializable;

public class DatosPersonalesDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Entrada
	private String			login;
	private String			password;
	
	// Salida
	private boolean			administrador;
	private boolean			logado;
	private String			idLiga;
	private String			idEquipo;
	
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public boolean isAdministrador() {
		return administrador;
	}
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
	public boolean isLogado() {
		return logado;
	}
	public void setLogado(boolean logado) {
		this.logado = logado;
	}
	public String getIdLiga() {
		return idLiga;
	}
	public void setIdLiga(String idLiga) {
		this.idLiga = idLiga;
	}
	public String getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}
	
	
	
}
