
package com.personal.basket.dtos;

import java.io.Serializable;
import java.util.Map;

public class UsuarioDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Entrada comun de login y registro.
	private String			login;
	private String			password;
	
	// Entrada del registro (NO login)
	private String			email;
	private String			anoNac;
	private String			sexo;
	
	
	// Salida comun
	private boolean			administrador;
	private boolean			logado;
	// FK - 
	private String			codigoLiga;
	// FK - 
	private String			codigoEquipo;
	// FK - A la economia propia.
	private String codigoEcono;
	
	// Salida de registro
	//private boolean			registrado;
	//private String			sErrorRegistrado;
	
	// Salida de login
	private Map<String, String> mapConfiguracion;
	
	

	
	
	
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
	
	
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAnoNac() {
		return anoNac;
	}
	public void setAnoNac(String anoNac) {
		this.anoNac = anoNac;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
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
	
	/*
	public boolean isRegistrado() {
		return registrado;
	}
	public void setRegistrado(boolean registrado) {
		this.registrado = registrado;
	}
	
	
	
	public String getsErrorRegistrado() {
		return sErrorRegistrado;
	}
	public void setsErrorRegistrado(String sErrorRegistrado) {
		this.sErrorRegistrado = sErrorRegistrado;
	}
	*/
	
	public Map<String, String> getMapConfiguracion() {
		return mapConfiguracion;
	}
	public void setMapConfiguracion(Map<String, String> mapConfiguracion) {
		this.mapConfiguracion = mapConfiguracion;
	}
	
	public String getCodigoEcono() {
		return codigoEcono;
	}
	public void setCodigoEcono(String codigoEcono) {
		this.codigoEcono = codigoEcono;
	}
	
	
	
	
	
	
	
}
