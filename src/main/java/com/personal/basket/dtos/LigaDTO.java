package com.personal.basket.dtos;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * 
 * @author iballesteros
 *
 */
public class LigaDTO implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String nombre;
	private String password;// Puede ser vacia.
	
	private String error;
	private boolean ligaPublica;
	
	//private ArrayList <EquipoDTO>		lEquiposTotales;
	//private	ArrayList <JugadorDTO> 		lJugadoresTotales;
	
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public boolean isLigaPublica() {
		return ligaPublica;
	}
	public void setLigaPublica(boolean ligaPublica) {
		this.ligaPublica = ligaPublica;
	}
	
	/*
	public ArrayList<EquipoDTO> getlEquiposTotales() {
		return lEquiposTotales;
	}
	public void setlEquiposTotales(ArrayList<EquipoDTO> lEquiposTotales) {
		this.lEquiposTotales = lEquiposTotales;
	}
	public ArrayList<JugadorDTO> getlJugadoresTotales() {
		return lJugadoresTotales;
	}
	public void setlJugadoresTotales(ArrayList<JugadorDTO> lJugadoresTotales) {
		this.lJugadoresTotales = lJugadoresTotales;
	}
	*/
	
	
}
