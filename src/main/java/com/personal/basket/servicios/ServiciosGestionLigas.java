package com.personal.basket.servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.basket.ctes.Ctes;
import com.personal.basket.dao.EquipoMapper;
import com.personal.basket.dao.LigaJugadorMapper;
import com.personal.basket.dao.LigaMapper;
import com.personal.basket.dao.MenuMapper;
import com.personal.basket.dao.UsuarioMapper;
import com.personal.basket.dtos.JugadorRealDTO;
import com.personal.basket.dtos.LigaDTO;
import com.personal.basket.dtos.MenuDTO;

import com.personal.basket.dtos.UsuarioDTO;
import com.personal.basket.model.EquipoModelDTO;
import com.personal.basket.model.LigaJugadorModelDTO;
import com.personal.basket.model.LigaModelDTO;
import com.personal.basket.model.MenuModelDTO;
import com.personal.basket.model.UsuarioModelDTO;



@Service("servicioGestionLigas")
public class ServiciosGestionLigas implements IServiciosGestionLigas{

	@Resource
	LigaMapper ligaMapper;
	
	@Resource
	UsuarioMapper usuarioMapper;
	
	@Resource
	EquipoMapper equipoMapper;
	
	@Resource
	LigaJugadorMapper ligaJugadorMapper;	


	@Transactional
	public boolean crearLiga (LigaDTO lDTO, String loginUsuario, HashMap<String, JugadorRealDTO> hmJugadoresReales)throws Exception{

		//UsuarioDTO sUserDTO = null;
		
		// Comprobar que NO existe una liga con ese nombre.
		int nLigas = ligaMapper.existeLiga(lDTO.getNombre());
		if (nLigas == 0){
			
			// Se obtiene el secuencial de la liga.
			String codigoLiga = ligaMapper.getSecuenciaLigaHSQL();
			
			// Se crea la liga.
			LigaModelDTO lmDTO = new LigaModelDTO();
			lmDTO.setCodLiga(codigoLiga);
			lmDTO.setNombre(lDTO.getNombre());
			lmDTO.setPassword(lDTO.getPassword());
			ligaMapper.crearLiga(lmDTO);
			
			// EXISTEN 4 LUGARES DONDE SE ASOCIAN LOS JUGADORES A LAS LIGAS
			// 1.- UN SITIO ES AQUI PORQUE CUANDO SE CREA UNA LIGA SE DEBEN
			// ASIGNAR TODOS LOS JUGADORES ACTIVOS EXISTENTES A ESA LIGA.
			//
			// 2.- CUANDO SE INSERTAN JUGADORES POR FICHERO EN LA ADMINISTRACION
			// ENTONCES SE ASIGNAN LOS JUGADORES A TODAS LAS LIGAS EXISTENTES.
			//
			// 3.- CUANDO SE DA DE ALTA 1 JUGADOR (OPERACION NO SOPORTADA POR EL MOMENTO)
			//			
			// 4.- CUANDO SE CAMBIA LA INACTIVIDAD DE UN JUGADOR, CUANDO SE DA DE BAJA O DE ALTA.
			//		cambiarEstadoJugador en ServiciosAdministracion.
			//
			// SON OPERACIONES DISTINTAS PERO EN TODAS SE ASIGNAN JUGADORES A LA LIGA.
			
			// ESTE CASO "crearLiga" SE CORRESPONDE CON EL SUPUESTO 1, ES DECIR, CUANDO
			// SE GENERA UNA LIGA SE DEBEN ASIGNAR LOS JUGADORES A DICHA LIGA 
			// TANTO ACTIVOS COMO NO ACTIVOS.
			//HashMap<String, JugadorRealDTO> hmJugadoresReales
			
			// Se recorre los jugadores existentes y los va insertando en la liga. (Tabla: LigaJugador)
			Iterator it = hmJugadoresReales.entrySet().iterator();
			while (it.hasNext()) {
				
				Map.Entry e = (Map.Entry)it.next();
				
				LigaJugadorModelDTO ljmDTO = new LigaJugadorModelDTO();
				
				ljmDTO.setCodLiga(codigoLiga);
				ljmDTO.setCodJugador(e.getKey().toString());
				ljmDTO.setDinero(((JugadorRealDTO)e.getValue()).getPrecio() );
				ljmDTO.setActivo(((JugadorRealDTO)e.getValue()).getActivo());
				ljmDTO.setCodEquipo(Ctes.NO_ASIGNADO_EQUIPO);
				
				ligaJugadorMapper.asignarJugador(ljmDTO);
				
				//System.out.println(e.getKey() + "--" + ((JugadorRealDTO)e.getValue()).getCodigoJugador() + "--" + ((JugadorRealDTO)e.getValue()).getPrecio() );
			}
			
			
			return true;

			
		}else{
			// Ya existe una liga con este nombre.
			return false;
		}

		

	}

	@Transactional(readOnly=true)
	public  ArrayList<LigaDTO> obtenerLigas (String nbLigaABuscar)throws Exception{
		
		
		ArrayList<LigaDTO> lLiga = new ArrayList<LigaDTO>();
		
		
		LigaModelDTO lmDTO = new LigaModelDTO();
		lmDTO.setNombre(nbLigaABuscar);
		List<LigaModelDTO> lModelLiga = ligaMapper.buscarLigas(lmDTO);
		for( LigaModelDTO s : lModelLiga ){
			
			LigaDTO ligaDTO = new LigaDTO();
			ligaDTO.setCodigoLiga(s.getCodLiga());
			ligaDTO.setNombre(s.getNombre());
			
			if (s.getPassword().equals(""))
				ligaDTO.setLigaPublica(true);
			else
				ligaDTO.setLigaPublica(false);
			
			lLiga.add(ligaDTO);

		}
		
		
		return lLiga;
		

	}
	
	@Transactional
	public UsuarioDTO inscribirLiga (LigaDTO lDTO, String loginUsuario, String nbEquipo)throws Exception{
	
		// Se debe actualizar la tabla del usuario para indicar la liga a la que pertence.
		String codLiga = lDTO.getCodigoLiga();
		String nbLiga = lDTO.getNombre();
		String passLiga = lDTO.getPassword();
		
		UsuarioDTO sUserDTO = new UsuarioDTO();
		
		// 1.- Se comprueba que se puede añadir a la liga
		//		Si es publica --> no hay mayor problema
		//		Si es privada --> debe comprobar la contraseña.
		if (lDTO.isLigaPublica()== false){
			
			LigaModelDTO lmDTO = new LigaModelDTO();
			lmDTO.setNombre(nbLiga);
			lmDTO.setPassword(passLiga);
			if (ligaMapper.acreditacionEnLiga(lmDTO) == 0){
				sUserDTO.setError(Ctes.ERROR_INSCRIPCION_ACREDITACION);
				return sUserDTO;
			}
				
		}	

		// 2.- Se comprueba que no exista un equipo con ese nombre
		int nEquipo = equipoMapper.existeEquipo(nbEquipo);
		if (nEquipo == 0){
			
			// 3.1- Se obtiene el codigo de equipo.
			String codEquipo = equipoMapper.getSecuenciaEquipoHSQL();
			
			// 3.2- Se crea un equipo.		
			EquipoModelDTO eqmDTO = new EquipoModelDTO();
			eqmDTO.setCodEquipo(codEquipo);
			eqmDTO.setCodLiga(codLiga);
			eqmDTO.setNombre(nbEquipo);
			eqmDTO.setPosicion(0);
			eqmDTO.setPuntuacionTotal(0);
			equipoMapper.crearEquipo(eqmDTO);
			
			// TODO - PUEDEN FALTAR INSERCCIONES EN TABLAS DERIVADAS
			
			// 4.- Se actualiza el usuario con el codigo de la liga y el codigo de equipo 
			UsuarioModelDTO usrModelDTO = new UsuarioModelDTO();
			usrModelDTO.setLogin(loginUsuario);
			usrModelDTO.setCodLiga(codLiga);
			usrModelDTO.setCodEquipo(codEquipo);
			usuarioMapper.actualizarUsuarioLiga(usrModelDTO);
			
			//5.- Se obtienen datos de usuario para devolver el codigo de liga, de economia y de equipo. 
			UsuarioModelDTO sUsrModelDTO = usuarioMapper.getDatosUsuario(loginUsuario);
			
			
			sUserDTO.setCodigoLiga(sUsrModelDTO.getCodLiga());
			sUserDTO.setCodigoEcono(sUsrModelDTO.getCodEcono());
			sUserDTO.setCodigoEquipo(sUsrModelDTO.getCodEquipo());
			sUserDTO.setError(Ctes.OK); // No existe error

			
		}else{
			sUserDTO.setError(Ctes.ERROR_INSCRIPCION_NB_EQUIPO); // Ya existe un equipo con ese nombre
			return sUserDTO;
		}
			
		return sUserDTO;

	}
	
	
	
	
	
	
	
}
