package com.personal.basket.dtos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author iballesteros
 *
 */
public class LigaDTO implements Serializable{

	// Una liga tiene un nombre y una password 
	// y debe estar rellena con todos los equipos y jugadores del juego.
	// Para poder añadir o eliminar jugadores o equipos se hará en todas las ligas.
	
	// No puede haber jugadores en una personaDTO sino se encuentra en la ligaDTO
	// Nos sirve para comprobar que no tienes un jugador/equipo que no este dado de alta en la liga.

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String password;
	
	private ArrayList <EquipoDTO>		lEquiposTotales;
	private	ArrayList <JugadorDTO> 		lJugadoresTotales;
	
	
	
	
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
	
	
	
}
