package com.personal.basket.servicios;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.personal.basket.estructuras.*;
import com.personal.basket.ctes.Constantes;
import com.personal.basket.dtos.EquipoDTO;
import com.personal.basket.dtos.JugadorDTO;
import com.personal.basket.dtos.MenuDTO;

@Service("catalogoServicio")
public class CatalogoServicios implements ICatalogoServicios{

	
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								SERVICIOS DE MENU									   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //	

	public ArrayList<MenuDTO> mostrarMenu()throws Exception{
		
		ArrayList<MenuDTO> lMenu = new ArrayList<MenuDTO>();
	
		
		MenuDTO mDTO1 = new MenuDTO();
		mDTO1.setIdentificador("001");
		mDTO1.setNombre("Jugadores");
		
		MenuDTO mDTO2 = new MenuDTO();
		mDTO2.setIdentificador("002");
		mDTO2.setNombre("Equipo");
				
		MenuDTO mDTO3 = new MenuDTO();
		mDTO3.setIdentificador("003");
		mDTO3.setNombre("Liga");
		
		MenuDTO mDTO4 = new MenuDTO();
		mDTO4.setIdentificador("004");
		mDTO4.setNombre("Partidos");
		
		lMenu.add(mDTO1);
		lMenu.add(mDTO2);
		lMenu.add(mDTO3);
		lMenu.add(mDTO4);

		
		return lMenu;
	}
	
	public ArrayList<MenuDTO> mostrarMenuNoLiga()throws Exception{
		
		ArrayList<MenuDTO> lMenu = new ArrayList<MenuDTO>();

		MenuDTO mDTO5 = new MenuDTO();
		mDTO5.setIdentificador("005");
		mDTO5.setNombre("Crear Liga");
				

		MenuDTO mDTO6 = new MenuDTO();
		mDTO6.setIdentificador("006");
		mDTO6.setNombre("Apuntarse a una Liga");

		lMenu.add(mDTO5);
		lMenu.add(mDTO6);

		
		return lMenu;
	}	
	
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								OPERACIONES DE MENU									   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	public ArrayList<JugadorDTO> mostrarJugadores()throws Exception{
		
		ArrayList<JugadorDTO> lJugadores = new ArrayList<JugadorDTO>();
		
		JugadorDTO j1 = new JugadorDTO();
		j1.setCodigo("00001");
		j1.setNombre("Luis");
		j1.setApellido("Avila");
		j1.setPuesto(Constantes.BASE);
		j1.setAltura("192");
		j1.setPeso("90");
		j1.setNacionalidad(Constantes.ESP);

		JugadorDTO j2 = new JugadorDTO();
		j2.setCodigo("00002");
		j2.setNombre("Charles");
		j2.setApellido("Fish");
		j2.setPuesto(Constantes.BASE);
		j2.setAltura("184");
		j2.setPeso("82");
		j2.setNacionalidad(Constantes.USA);
		
		JugadorDTO j3 = new JugadorDTO();
		j3.setCodigo("00003");
		j3.setNombre("Javier");
		j3.setApellido("Diaz de Miguel");
		j3.setPuesto(Constantes.ALERO);
		j3.setAltura("201");
		j3.setPeso("99");
		j3.setNacionalidad(Constantes.ESP);
		

		
		lJugadores.add(j1);
		lJugadores.add(j2);
		lJugadores.add(j3);
		return lJugadores;
	}
	
	
	public EquipoDTO mostrarEquipo()throws Exception{
		
		EquipoDTO eq1 = new EquipoDTO();
		eq1.setCodigo("00001");
		eq1.setNombre("Los ventolines");
		
		return eq1;
	}
	
	
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								SERVICIOS DE LOGIN 									   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //	
	
	public boolean registrarse(String log, String pass)throws Exception{
		DatosPersonales dPers = new DatosPersonales();
		dPers.setLogin(log);
		dPers.setPassword(pass);
		// Aqui se debe llamar al metodo SQL para registrarse en BBDD.
		
		return true;
	}
	
	public boolean loggearse(String log, String pass)throws Exception{
		DatosPersonales dPers = new DatosPersonales();
		dPers.setLogin(log);
		dPers.setPassword(pass);
		// Aqui se debe llamar al metodo SQL para logearse en BBDD.
		
		return true;
	}
	
	
}
