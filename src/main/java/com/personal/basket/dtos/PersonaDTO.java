package com.personal.basket.dtos;

import java.io.Serializable;
import java.util.ArrayList;

import com.personal.basket.estructuras.DatosPersonales;

public class PersonaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Propiedades personales.
	private DatosPersonales				datosPersonales;
	
	// Propiedades para el juego.
	private EquipoDTO					equipo;		// Contiene el equipo propio.
	private	ArrayList <JugadorDTO> 		lJugador;	// Contiene los jugadores propio.
	private LigaDTO						liga;		// Contiene todos los equipos y liga válidos.
	
		
	
	public DatosPersonales getDatosPersonales() {
		return datosPersonales;
	}
	public void setDatosPersonales(DatosPersonales datosPersonales) {
		this.datosPersonales = datosPersonales;
	}
	
	public EquipoDTO getEquipo() {
		return equipo;
	}
	public void setEquipo(EquipoDTO equipo) {
		this.equipo = equipo;
	}
	
	public ArrayList<JugadorDTO> getlJugador() {
		return lJugador;
	}
	public void setlJugador(ArrayList<JugadorDTO> lJugador) {
		this.lJugador = lJugador;
	}
	public LigaDTO getLiga() {
		return liga;
	}
	public void setLiga(LigaDTO liga) {
		this.liga = liga;
	}
	
	
	
}
