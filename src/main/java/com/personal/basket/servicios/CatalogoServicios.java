package com.personal.basket.servicios;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.personal.basket.ctes.Constantes;
import com.personal.basket.ctes.Ctes;
import com.personal.basket.dtos.DatosPersonalesDTO;
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
		mDTO1.setIdentificador(Ctes.MENU_JUGADORES);
		mDTO1.setNombre("Jugadores");
		
		MenuDTO mDTO2 = new MenuDTO();
		mDTO2.setIdentificador(Ctes.MENU_EQUIPO);
		mDTO2.setNombre("Equipo");
				
		MenuDTO mDTO3 = new MenuDTO();
		mDTO3.setIdentificador(Ctes.MENU_LIGA);
		mDTO3.setNombre("Liga");
		
		MenuDTO mDTO4 = new MenuDTO();
		mDTO4.setIdentificador(Ctes.MENU_PARTIDOS);
		mDTO4.setNombre("Partidos");
		
		lMenu.add(mDTO1);
		lMenu.add(mDTO2);
		lMenu.add(mDTO3);
		lMenu.add(mDTO4);

		
		return lMenu;
	}
	
	public ArrayList<MenuDTO> mostrarMenuNoLiga()throws Exception{
		
		ArrayList<MenuDTO> lMenu = new ArrayList<MenuDTO>();

		MenuDTO mDTO1 = new MenuDTO();
		mDTO1.setIdentificador(Ctes.MENU_NOLIGA_CREAR_LIGA);
		mDTO1.setNombre("Crear Liga");			

		MenuDTO mDTO2 = new MenuDTO();
		mDTO2.setIdentificador(Ctes.MENU_NOLIGA_INSCRIBIRSE_LIGA);
		mDTO2.setNombre("Apuntarse a una Liga");

		lMenu.add(mDTO1);
		lMenu.add(mDTO2);

		
		return lMenu;
	}	
	
	public ArrayList<MenuDTO> mostrarMenuAdministrador()throws Exception{
		
		ArrayList<MenuDTO> lMenu = new ArrayList<MenuDTO>();

		MenuDTO mDTO1 = new MenuDTO();
		mDTO1.setIdentificador(Ctes.MENU_ADMIN_EQUIPOS);
		mDTO1.setNombre("Admin. Equipo");


		MenuDTO mDTO2 = new MenuDTO();
		mDTO2.setIdentificador(Ctes.MENU_ADMIN_JUGADORES);
		mDTO2.setNombre("Admin. Jugadores");
		
		lMenu.add(mDTO1);
		lMenu.add(mDTO2);
		
		return lMenu;
	}		
	

	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								OPERACIONES DE EQUIPO								   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
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
		DatosPersonalesDTO dPers = new DatosPersonalesDTO();
		dPers.setLogin(log);
		dPers.setPassword(pass);
		
		// Aqui se debe llamar al metodo SQL para registrarse en BBDD.
		
		return true;
	}

	public DatosPersonalesDTO loggearse(String log, String pass)throws Exception{
		
		DatosPersonalesDTO dPers = new DatosPersonalesDTO();
		dPers.setLogin(log);
		dPers.setPassword(pass);
		
		// Aqui se debe llamar al metodo SQL para logearse en BBDD.
		
		// Se trae el id_liga y todo lo necesario.
		dPers.setLogado(true); // Esto es una prueba
		
		dPers.setIdLiga("0001");// Esto es una prueba (Ctes.NO_ASIGNADO_LIGA)
		//dPers.setIdLiga(Ctes.NO_ASIGNADO_LIGA);// Esto es una prueba (Ctes.NO_ASIGNADO_LIGA)
		
		dPers.setIdEquipo("00001");// Esto es una prueba (Ctes.NO_ASIGNADO_EQUIPO)
		//dPers.setIdEquipo(Ctes.NO_ASIGNADO_EQUIPO);// Esto es una prueba (Ctes.NO_ASIGNADO_EQUIPO)
		
		dPers.setAdministrador(false); // Para indicar que es un usuario de administracion.
		 
		return dPers;
	}

	
}
