
package com.personal.basket.model;

import java.io.Serializable;
import java.util.Map;

public class UsuarioModelDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Entrada comun de login y registro.
	private String			login;
	private String			password;
	private String			email;
	private String			anoNac;
	private String			sexo;
	private String			activo;
	private String			administrador;
	// FK - A la economia propia.
	private String 			codEcono;	
	// FK - 
	private String			codLiga;
	// FK - 
	private String			codEquipo;
	
	
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
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public String getAdministrador() {
		return administrador;
	}
	public void setAdministrador(String administrador) {
		this.administrador = administrador;
	}
	public String getCodEcono() {
		return codEcono;
	}
	public void setCodEcono(String codEcono) {
		this.codEcono = codEcono;
	}
	public String getCodLiga() {
		return codLiga;
	}
	public void setCodLiga(String codLiga) {
		this.codLiga = codLiga;
	}
	public String getCodEquipo() {
		return codEquipo;
	}
	public void setCodEquipo(String codEquipo) {
		this.codEquipo = codEquipo;
	}


	
	
	
}
