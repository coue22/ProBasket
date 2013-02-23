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
import com.personal.basket.dao.DetEconomiaMapper;
import com.personal.basket.dao.EconomiaMapper;
import com.personal.basket.dao.MenuMapper;
import com.personal.basket.dao.UsuarioMapper;

import com.personal.basket.model.ConfiguracionModelDTO;
import com.personal.basket.model.DetEconomiaModelDTO;
import com.personal.basket.model.EconomiaModelDTO;
import com.personal.basket.model.MenuModelDTO;
import com.personal.basket.model.UsuarioModelDTO;

@Service("catalogoServicio")
public class CatalogoServicios implements ICatalogoServicios{


	@Resource
	MenuMapper menuMapper;
	@Resource
	UsuarioMapper usuarioMapper;
	@Resource
	EconomiaMapper economiaMapper;
	@Resource
	DetEconomiaMapper detEconomiaMapper;	
	

	@Transactional(readOnly=true)
	public ArrayList<MenuDTO> mostrarMenu(String idTipoMenu)throws Exception{
		
		ArrayList<MenuDTO> lMenu = new ArrayList<MenuDTO>();
		
		// Se realiza una llamada al modelo de datos para devolver el
		// contenido del menu a pintar
		List<MenuModelDTO> lModelMenuDTO = menuMapper.getMenus(idTipoMenu);
		

		// Se recorre la lista para ir rellenando el DTO para la vista.
		for( MenuModelDTO s : lModelMenuDTO ){
			
			MenuDTO menu = new MenuDTO();
			
			menu.setIdentificador(s.getCodigo());
			menu.setNombre(s.getDescripcion());
			
			lMenu.add(menu);
			
		}
				
		return lMenu;
	}
	
	
	@Transactional
	public boolean registrarse(String login, String pass, 
							   String email, String anoNac, 
							   String sex, double dineroInicial)throws Exception{
		

		
		// Comprueba si existe el usuario en BBDD, si no hay un usuario con ese 
		// login entonces se pueden realizar la insercion de usuario.
		int nUsuarios = usuarioMapper.existeUsuario(login);
		if (nUsuarios == 0){
			
			// Se obtiene el codigo de economia.
			String codigoEconomia = economiaMapper.getSecuenciaEconomiaHSQL();
			
			// Se genera una Economia con el dinero establecido.
			EconomiaModelDTO ecDTO = new EconomiaModelDTO();
			ecDTO.setCodEcono(codigoEconomia);
			ecDTO.setDinero(dineroInicial);
			economiaMapper.InicializarEconomia(ecDTO);
			
			// Se inserta en el Detalle de la Economia el movimiento.
			String codigoDetEconomia = detEconomiaMapper.getSecuenciaDetEconomiaHSQL();
			DetEconomiaModelDTO decDTO = new DetEconomiaModelDTO();
			decDTO.setCodDetEconomia(codigoDetEconomia);;
			decDTO.setCodigoEcono(codigoEconomia);
			decDTO.setCodigoOpera("1");
			decDTO.setDetalle("Se registra el usuario " + login);
			//decDTO.setFecha(fecha)
			detEconomiaMapper.setDetEconomia(decDTO);
			
			// Se registra un usuario.
			UsuarioModelDTO usrModelDTO = new UsuarioModelDTO();
			usrModelDTO.setLogin(login);
			usrModelDTO.setPassword(pass);
			usrModelDTO.setEmail(email);
			usrModelDTO.setAnoNac(anoNac);
			usrModelDTO.setSexo(sex);
			usrModelDTO.setActivo(Ctes.SI);
			usrModelDTO.setAdministrador(Ctes.NO);
			usrModelDTO.setCodEcono(codigoEconomia);
			usrModelDTO.setCodLiga(Ctes.NO_ASIGNADO_LIGA);
			usrModelDTO.setCodEquipo(Ctes.NO_ASIGNADO_EQUIPO);
			
			usuarioMapper.registrar(usrModelDTO);
			
			return true;
		}
			
		
		return false;

	}
	
	@Transactional(readOnly=true)
	public UsuarioDTO loggearse(String log, String pass)throws Exception{
	

		// Se realiza un select para ver si se puede logar o no.
		UsuarioModelDTO umDTO = new UsuarioModelDTO();
		umDTO.setLogin(log);
		umDTO.setPassword(pass);
		UsuarioModelDTO umSalDTO = usuarioMapper.logarse(umDTO);
		
		if (umSalDTO == null)
			return null;
	
		// Se devuelve el usuario 
		UsuarioDTO dPers = new UsuarioDTO();
		dPers.setLogin(log);
		dPers.setPassword(pass);		
		dPers.setLogado(true);
		dPers.setCodigoLiga(umSalDTO.getCodLiga());
		dPers.setCodigoEquipo(umSalDTO.getCodEquipo());
		dPers.setCodigoEcono(umSalDTO.getCodEcono());
		if (umSalDTO.getAdministrador().equalsIgnoreCase(Ctes.SI))
			dPers.setAdministrador(true);
		else
			dPers.setAdministrador(false);
		
		
		return dPers;
		
		
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
/*
		dPers.setLogado(true);
		dPers.setCodigoLiga(Ctes.NO_ASIGNADO_LIGA);
		dPers.setCodigoEquipo(Ctes.NO_ASIGNADO_EQUIPO);
		dPers.setAdministrador(false);
*/			
		
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
		



		
	}


	
}
