package com.personal.basket.servicios;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.personal.basket.ctes.Ctes;

import com.personal.basket.dtos.EconomiaDTO;
import com.personal.basket.dtos.UsuarioDTO;
import com.personal.basket.dtos.MenuDTO;

import com.personal.basket.dao.ConfiguracionMapper;
import com.personal.basket.dao.MenuMapper;

import com.personal.basket.model.ConfiguracionDTO;
import com.personal.basket.model.MenuModelDTO;

@Service("catalogoServicio")
public class CatalogoServicios implements ICatalogoServicios{


	//@Autowired
	@Resource
	ConfiguracionMapper configuracionMapper;
	
	@Resource
	MenuMapper menuMapper;
/*
	public ConfiguracionMapper getConfiguracionMapper() {
		return configuracionMapper;
	}

	public void setConfiguracionMapper(ConfiguracionMapper configuracionMapper) {
		this.configuracionMapper = configuracionMapper;
	}	
*/	
	
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								SERVICIOS DE MENU									   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //	

	public ArrayList<MenuDTO> mostrarMenu(String idTipoMenu)throws Exception{
		
		ArrayList<MenuDTO> lMenu = new ArrayList<MenuDTO>();
		
		List<MenuModelDTO> lModelMenuDTO = menuMapper.getMenus(idTipoMenu);
		

		for( MenuModelDTO s : lModelMenuDTO ){
			MenuDTO menu = new MenuDTO();
			
			menu.setIdentificador(s.getCodigo());
			menu.setNombre(s.getDescripcion());
			
			lMenu.add(menu);
			
		}
				
		return lMenu;
	}
	
	
/*
	public ArrayList<MenuDTO> mostrarMenu()throws Exception{
		
		ArrayList<MenuDTO> lMenu = new ArrayList<MenuDTO>();
		
		
		MenuDTO mDTO1 = new MenuDTO();
		mDTO1.setIdentificador(Ctes.MENU_EQUIPO);
		mDTO1.setNombre("Equipo");
				
		MenuDTO mDTO2 = new MenuDTO();
		mDTO2.setIdentificador(Ctes.MENU_LIGA);
		mDTO2.setNombre("Liga");
		
		
		lMenu.add(mDTO1);
		lMenu.add(mDTO2);
		
		return lMenu;
	}
*/
	
/*	
	public ArrayList<MenuDTO> mostrarMenuDraft()throws Exception{
		
		ArrayList<MenuDTO> lMenu = new ArrayList<MenuDTO>();
		
		
		MenuDTO mDTO1 = new MenuDTO();
		mDTO1.setIdentificador(Ctes.MENU_DRAFT);
		mDTO1.setNombre("Draft");

		
		
		lMenu.add(mDTO1);

		
		return lMenu;
	}	
*/
	
/*	
	@Transactional
	public ArrayList<MenuDTO> mostrarMenuNoLiga()throws Exception{
		

		
		
		
		
		ArrayList<MenuDTO> lMenu = new ArrayList<MenuDTO>();

		MenuDTO mDTO1 = new MenuDTO();
		mDTO1.setIdentificador(Ctes.MENU_NOLIGA_CREAR_LIGA);
		mDTO1.setNombre("Crear Liga");			

		MenuDTO mDTO2 = new MenuDTO();
		mDTO2.setIdentificador(Ctes.MENU_NOLIGA_INSCRIBIRSE_LIGA);
		mDTO2.setNombre("Inscribirse");

		lMenu.add(mDTO1);
		lMenu.add(mDTO2);

		
		return lMenu;
	}	
*/
/*
	public ArrayList<MenuDTO> mostrarMenuAdministrador()throws Exception{
		
		ArrayList<MenuDTO> lMenu = new ArrayList<MenuDTO>();

	
		
		
		MenuDTO mDTO1 = new MenuDTO();
		mDTO1.setIdentificador(Ctes.MENU_ADMIN_CONFIG);
		mDTO1.setNombre("Configuracion");

		MenuDTO mDTO2 = new MenuDTO();
		mDTO2.setIdentificador(Ctes.MENU_ADMIN_NACION);
		mDTO2.setNombre("Nacionalidades");
		
		MenuDTO mDTO3 = new MenuDTO();
		mDTO3.setIdentificador(Ctes.MENU_ADMIN_OPERAC);
		mDTO3.setNombre("Operaciones");		
		
		MenuDTO mDTO4 = new MenuDTO();
		mDTO4.setIdentificador(Ctes.MENU_ADMIN_ROLES);
		mDTO4.setNombre("Roles");		
		
		MenuDTO mDTO5 = new MenuDTO();
		mDTO5.setIdentificador(Ctes.MENU_ADMIN_EQUIPOS);
		mDTO5.setNombre("Equipo");

		MenuDTO mDTO6 = new MenuDTO();
		mDTO6.setIdentificador(Ctes.MENU_ADMIN_JUGADORES);
		mDTO6.setNombre("Jugadores");
		
		lMenu.add(mDTO1);
		lMenu.add(mDTO2);
		lMenu.add(mDTO3);
		lMenu.add(mDTO4);
		lMenu.add(mDTO5);
		lMenu.add(mDTO6);

		
		return lMenu;
	}		
*/	

	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								OPERACIONES DE EQUIPO								   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
/*	
	public EquipoRealDTO mostrarEquipo()throws Exception{
		
		// No se refiere a este equipo.
		
		EquipoRealDTO eq1 = new EquipoRealDTO();
		eq1.setCodigo("00001");
		eq1.setNombre("Los ventolines");
		
		return eq1;
	
		
		
		return null;
	}
*/	
	
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								SERVICIOS DE LOGIN 									   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //	
	public boolean existeUsuario(String login)throws Exception{
		
		// Debe ir a BBDD a comprobar si existe el login
		
		//return true; // Existe ya ese login y por lo tanto no se puede continuar con el alta.
		return false;
	}
	
	public UsuarioDTO registrarse(String login, String pass, 
							   String email, String anoNac, 
							   String sex, String codigoEcono)throws Exception{
		

		
		UsuarioDTO dPers = new UsuarioDTO();
		
		// Datos de entrada para el registro.
		dPers.setLogin(login);
		dPers.setPassword(pass);
		dPers.setEmail(email);
		dPers.setEmail(anoNac);
		dPers.setEmail(sex);
		dPers.setCodigoEcono(codigoEcono);
		
		// Aqui se debe llamar al metodo SQL para registrarse en BBDD.
		// Se llamda al servicio para registrarse.
		//boolean bRegistrado = true;
		
		//Datos de salida fijos si el servicio funciona.
		//if (bRegistrado == true){
		//	dPers.setRegistrado(bRegistrado);
		//	dPers.setsErrorRegistrado("");			
		//}else{
		//	dPers.setRegistrado(false);
		//	dPers.setsErrorRegistrado("No se puede registrar porque ya existe un usuario con ese login.");	
		//}
		
		
		
		// VAlores que siempre son asi pero le dare una vuelta porque luego ni se usan 
		// para meterlos en sesion, ya que se meten fijos.
		dPers.setLogado(false);
		dPers.setCodigoLiga(Ctes.NO_ASIGNADO_LIGA);
		dPers.setCodigoEquipo(Ctes.NO_ASIGNADO_EQUIPO);
		dPers.setAdministrador(false);
		
		return dPers;
	}

	public UsuarioDTO loggearse(String log, String pass)throws Exception{
		
		UsuarioDTO dPers = new UsuarioDTO();
		dPers.setLogin(log);
		dPers.setPassword(pass);
		
		// Aqui se debe llamar al metodo SQL para logearse en BBDD.
		
		//*****************************************************************************
		//*****************************************************************************		
		// FORMA 1 - Modo administrador
		//*****************************************************************************
		//*****************************************************************************		
		// Datos para administrador
/*
		dPers.setLogado(true);
		dPers.setCodigoLiga(Ctes.NO_ASIGNADO_LIGA);
		dPers.setCodigoEquipo(Ctes.NO_ASIGNADO_EQUIPO);
		dPers.setAdministrador(true);
*/
		
		//*****************************************************************************
		//*****************************************************************************		
		// FORMA 2 - Forma crear ligas e inscribirse.
		//*****************************************************************************
		//*****************************************************************************		
		// Datos para crear o inscribirse a una liga
		// CREAR LIGA		: Cuando se crea la liga simplemente se crea la liga y no
		//					  se asocia a ningún usuario, ni equipo, ni nada. Nos tendremos
		//					  que inscribir para pertenecer a una liga.
		// INSCRIBIRSE LIGA	: Funciona igual que crear liga pero te adhieres a una liga existente
		//					  con un equipo. Se establecen 4 variables de session.
		//		sesion.setAttribute(ConstantesSesion.PERTENECE_LIGA, Ctes.SI);
		//		sesion.setAttribute(ConstantesSesion.TIENE_EQUIPO, Ctes.SI);
		//		
		//		sesion.setAttribute(ConstantesSesion.MI_LIGA, "Codigo de la liga.");
		//		sesion.setAttribute(ConstantesSesion.MI_EQUIPO, "Codigo de tu equipo.");

		dPers.setLogado(true);
		dPers.setCodigoLiga(Ctes.NO_ASIGNADO_LIGA);
		dPers.setCodigoEquipo(Ctes.NO_ASIGNADO_EQUIPO);
		dPers.setAdministrador(false);
			
		
		//*****************************************************************************
		//*****************************************************************************		
		// FORMA 3 - Forma liga comenzada o en draft (Todo depende de una consulta a la tabla configuracion en BBDD)
		//			 Se buscar el parametro "draft". Si tiene un "S" entonces estamos aun en tiempo de draft.
		//*****************************************************************************
		//*****************************************************************************
		// Perteneces a una liga y tienes un equipo.
/*		
		dPers.setLogado(true);
		dPers.setCodigoLiga("0000000001");
		dPers.setCodigoEquipo("0000000021");
		dPers.setAdministrador(false);
*/	
		

	
		return dPers;
	}
	
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								SERVICIOS DE ROLE 									   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //


	
	
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								SERVICIOS DE ECONOMIA								   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	public EconomiaDTO setEconomia()throws Exception{
		
		// Se accede a base de datos a insertar un registro con el codigo de economia.
		// Un secuencia para insertar sería lo suyo.
		
		EconomiaDTO eDTO = new EconomiaDTO();

		// Devuelve el codigo de la economia.
		eDTO.setCodigoEcono("0000000024");
		eDTO.setDinero(Ctes.ECONOMIA_INICIAL);
		
		//eDTO.setCodigoEcono(Ctes.NO_ASIGNADO_ECONOMIA);
		//eDTO.setDinero(Ctes.ECONOMIA_INICIAL); // Este valor cuando va mal no tiene sentido.
		
		return eDTO;

	}
	
	
	
	
}
