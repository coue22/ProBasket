package com.personal.basket.servicios;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.basket.ctes.Ctes;
import com.personal.basket.dao.ConfiguracionMapper;
import com.personal.basket.dtos.EconomiaDTO;
import com.personal.basket.dtos.OperacionDTO;
import com.personal.basket.dtos.UsuarioDTO;
import com.personal.basket.dtos.EquipoRealDTO;
import com.personal.basket.dtos.MenuDTO;
import com.personal.basket.dtos.RoleDTO;
import com.personal.basket.model.ConfiguracionDTO;


@Service("catalogoServicio")
public class CatalogoServicios implements ICatalogoServicios{

	/*
	@Autowired
	ConfiguracionMapper configuracionMapper;
	*/
	
	
	
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								SERVICIOS DE MENU									   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //	

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
	
	public ArrayList<MenuDTO> mostrarMenuAdministrador()throws Exception{
		
		ArrayList<MenuDTO> lMenu = new ArrayList<MenuDTO>();

		MenuDTO mDTO1 = new MenuDTO();
		mDTO1.setIdentificador(Ctes.MENU_ADMIN_EQUIPOS);
		mDTO1.setNombre("Equipo");


		MenuDTO mDTO2 = new MenuDTO();
		mDTO2.setIdentificador(Ctes.MENU_ADMIN_JUGADORES);
		mDTO2.setNombre("Jugadores");
		
		
		MenuDTO mDTO3 = new MenuDTO();
		mDTO3.setIdentificador(Ctes.MENU_ADMIN_EQUIPOS+"1");
		mDTO3.setNombre("Equipo");


		MenuDTO mDTO4 = new MenuDTO();
		mDTO4.setIdentificador(Ctes.MENU_ADMIN_JUGADORES+"1");
		mDTO4.setNombre("Jugadores");
		
		
		
		MenuDTO mDTO5 = new MenuDTO();
		mDTO5.setIdentificador(Ctes.MENU_ADMIN_EQUIPOS+"2");
		mDTO5.setNombre("Equipo");


		MenuDTO mDTO6 = new MenuDTO();
		mDTO6.setIdentificador(Ctes.MENU_ADMIN_JUGADORES+"2");
		mDTO6.setNombre("Jugadores");
		
		
		lMenu.add(mDTO1);
		lMenu.add(mDTO2);
		lMenu.add(mDTO3);
		lMenu.add(mDTO4);		
		lMenu.add(mDTO5);
		lMenu.add(mDTO6);
		
		return lMenu;
	}		
	

	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								OPERACIONES DE EQUIPO								   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	public EquipoRealDTO mostrarEquipo()throws Exception{
		
		// No se refiere a este equipo.
		/*
		EquipoRealDTO eq1 = new EquipoRealDTO();
		eq1.setCodigo("00001");
		eq1.setNombre("Los ventolines");
		
		return eq1;
		*/
		
		
		return null;
	}
	
	
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
		
		// Se trae el id_liga y todo lo necesario.
		dPers.setLogado(true); // Esto es una prueba
		
		dPers.setCodigoLiga("0001");// Esto es una prueba
		//dPers.setCodigoLiga(Ctes.NO_ASIGNADO_LIGA);// Esto es una prueba (Ctes.NO_ASIGNADO_LIGA)
		
		dPers.setCodigoEquipo("00001");// Esto es una prueba 
		//dPers.setCodigoEquipo(Ctes.NO_ASIGNADO_EQUIPO);// Esto es una prueba (Ctes.NO_ASIGNADO_EQUIPO)
		
		dPers.setAdministrador(false); // Para indicar que es un usuario de administracion.
		
		
	
/*			
		Map <String, String> mAux = new HashMap<String, String>();
		// Se llama a base de datos para recuperar los valores de la tabla configuracion
		List<Configuracion> lConfig = configuracionMapper.getAll();
		for (int i = 0; i<lConfig.size(); i++){
			ConfiguracionDTO conf = lConfig.get(i);
			//System.out.println(conf.getParametro() + "--" + conf.getValor());
			mAux.put(conf.getParametro(), conf.getValor());
		}
		dPers.setMapConfiguracion(mAux);
*/		
		
		// PARA CUANDO NO TENGO CONECTIVIDAD
		Map <String, String> mAux = new HashMap<String, String>();
		mAux.put("jornada", "1");
		mAux.put("temporada", "2012");
		dPers.setMapConfiguracion(mAux);

		
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
