package com.personal.basket.servicios;


import java.util.ArrayList;


import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.personal.basket.ctes.Ctes;

import com.personal.basket.dao.ConfiguracionMapper;
import com.personal.basket.dao.EquipoRealMapper;
import com.personal.basket.dao.JugadorRealMapper;
import com.personal.basket.dao.LigaJugadorMapper;
import com.personal.basket.dao.LigaMapper;
import com.personal.basket.dao.NacionalidadMapper;
import com.personal.basket.dao.RoleMapper;

import com.personal.basket.dtos.ConfiguracionDTO;
import com.personal.basket.dtos.EquipoRealDTO;
import com.personal.basket.dtos.JugadorRealDTO;
import com.personal.basket.dtos.LigaDTO;
import com.personal.basket.dtos.NacionalidadDTO;
import com.personal.basket.dtos.OperacionDTO;
import com.personal.basket.dtos.RoleDTO;

import com.personal.basket.model.ConfiguracionModelDTO;
import com.personal.basket.model.JugadorRealModelDTO;
import com.personal.basket.model.LigaJugadorModelDTO;
import com.personal.basket.model.LigaModelDTO;
import com.personal.basket.model.NacionalidadModelDTO;
import com.personal.basket.model.RoleModelDTO;

import com.personal.basket.util.LecturaFicheroJugadores;


@Service("servicioAdministracion")
public class ServiciosAdministracion implements IServiciosAdministracion{

	@Resource
	ConfiguracionMapper configuracionMapper;
	
	@Resource
	NacionalidadMapper nacionalidadMapper;
	
	
	@Resource
	RoleMapper roleMapper;
	
	@Resource
	EquipoRealMapper equipoRealMapper;
	
	@Resource
	JugadorRealMapper jugadorRealMapper;
	
	@Resource
	LigaJugadorMapper ligaJugadorMapper;	
	
	@Resource
	LigaMapper ligaMapper;	
	
	// **************************************************************************
	// **************************************************************************
	// Configuracion
	// **************************************************************************
	// **************************************************************************
	@Transactional
	public boolean modificarConfiguracion(ConfiguracionDTO cDTO)throws Exception{
		
		//System.out.println("Parametro: " + cDTO.getParametro());
		//System.out.println("valor: " + cDTO.getValor());
		
		ConfiguracionModelDTO cmDTO = new ConfiguracionModelDTO();
		cmDTO.setParametro(cDTO.getParametro());
		cmDTO.setValor(cDTO.getValor());
		configuracionMapper.modificarConfiguracion(cmDTO);
		
		return true;
	}
	
	@Transactional
	public boolean insertarConfiguracion(ConfiguracionDTO cDTO)throws Exception{
		
		//System.out.println("Parametro: " + cDTO.getParametro());
		//System.out.println("valor: " + cDTO.getValor());
		
		ConfiguracionModelDTO cmDTO = new ConfiguracionModelDTO();
		cmDTO.setParametro(cDTO.getParametro());
		cmDTO.setValor(cDTO.getValor());
		configuracionMapper.insertarConfiguracion(cmDTO);
		
		return true;
		
	}
	
	
	// **************************************************************************
	// **************************************************************************
	// Nacionalidad
	// **************************************************************************
	// **************************************************************************	
	@Transactional
	public boolean eliminarNacionalidad(NacionalidadDTO nDTO)throws Exception{
		System.out.println("Codigo nacionalidad a eliminar: " + nDTO.getCodigoNacionalidad());
		
		NacionalidadModelDTO nmDTO = new NacionalidadModelDTO();
		nmDTO.setCodNacionalidad(nDTO.getCodigoNacionalidad());
		
		nacionalidadMapper.eliminarNacionalidad(nmDTO);
		
		return true;
	}
	@Transactional
	public boolean insertarNacionalidad(NacionalidadDTO nDTO)throws Exception{
		System.out.println("Codigo nacionalidad a eliminar: " + nDTO.getCodigoNacionalidad());
		System.out.println("Descripcion a insertar: " + nDTO.getDescripcion());
		
		NacionalidadModelDTO nmDTO = new NacionalidadModelDTO();
		nmDTO.setCodNacionalidad(nDTO.getCodigoNacionalidad());
		nmDTO.setDescripcion(nDTO.getDescripcion());
		
		nacionalidadMapper.insertarNacionalidad(nmDTO);
		
		return true;
	}
	
	// **************************************************************************
	// **************************************************************************
	// Operacion
	// **************************************************************************
	// **************************************************************************
	@Transactional
	public boolean modificarOperacion(OperacionDTO oDTO)throws Exception{
		System.out.println("Codigo operacion a modificar: " + oDTO.getCodigoOpera());
		System.out.println("Descripcion a modificar: " + oDTO.getDescripcion());
		return true;
	}
	@Transactional
	public boolean insertarOperacion(OperacionDTO oDTO)throws Exception{
		// El codigo es autogenerado.
		System.out.println("Descripcion a insertar: " + oDTO.getDescripcion());
		return true;
	}
	
	// **************************************************************************
	// **************************************************************************
	// Role
	// **************************************************************************
	// **************************************************************************
	@Transactional
	public void modificarRole(RoleDTO rDTO)throws Exception{
		
		System.out.println("Codigo role a modificar: " + rDTO.getCodigoRole());
		System.out.println("Descripcion a modificar: " + rDTO.getDescripcion());
		
		RoleModelDTO rmDTO = new RoleModelDTO();
		rmDTO.setCodRole(rDTO.getCodigoRole());
		rmDTO.setDescripcion(rDTO.getDescripcion());
		roleMapper.modificarRole(rmDTO);

	}
	
	@Transactional
	public void insertarRole(RoleDTO rDTO)throws Exception{

		System.out.println("Codigo de role a insertar: " + rDTO.getCodigoRole());
		System.out.println("Descripcion role a insertar: " + rDTO.getDescripcion());
		
		RoleModelDTO rmDTO = new RoleModelDTO();
		rmDTO.setCodRole(rDTO.getCodigoRole());
		rmDTO.setDescripcion(rDTO.getDescripcion());
		roleMapper.insertarRole(rmDTO);

	}
	
	// **************************************************************************
	// **************************************************************************
	// Equipo
	// **************************************************************************
	// **************************************************************************
	@Transactional
	public boolean modificarEquipoCatalogo(EquipoRealDTO eDTO)throws Exception{
		
		// Se llama para modificar en base de datos
		System.out.println("Codigo: " + eDTO.getCodigoEquipoReal());
		System.out.println("Nombre: " + eDTO.getNombre());
		System.out.println("Siglas: " + eDTO.getSiglas());
		
		// AQUI SE DEBE REALIZAR LA LLAMADA A MYBATIS PARA UN UPDATE.
		
		// SE DEBE EVALUAR LA SALIDA DE LA MODIFICACION.
		
		// Se debe llamar a la recarga del servicio Singleton para Equipos Reales.
		
		return true;
	}
	

	@Transactional
	public boolean insertarEquipoCatalogo(EquipoRealDTO eDTO)throws Exception{
		
		// Inserta en base de datos un equipo real.
		// El codigo debe ser autogenerado en bbdd.
		System.out.println("Nombre: " + eDTO.getNombre());
		System.out.println("Siglas: " + eDTO.getSiglas());
		
		// AQUI SE DEBE REALIZAR LA LLAMADA A MYBATIS PARA UN INSERT.
		
		// SE DEBE EVALUAR LA SALIDA DE LA INSERCION.
		
		// Se debe llamar a la recarga del servicio Singleton para Equipos Reales.		
		
		return true;
	}
	
	@Transactional(readOnly=true)
	public boolean existeEquipoReal(String codigoEquipoReal)throws Exception{
		
		//System.out.println("codigoEquipoReallllllllll: " + codigoEquipoReal);
		
		// Si no encuentra un equipo real con ese codigo entonces devuelve false.
		if (equipoRealMapper.existeEquipoReal(codigoEquipoReal)==0)
			return false;
		
		return true;
	}
	

	// **************************************************************************
	// **************************************************************************
	// Jugador
	// **************************************************************************
	// **************************************************************************

    
	@Transactional
	public ArrayList<JugadorRealDTO> insertarJugadoresPorFichero(String nbFichero, String codEquipoReal, boolean bInsertar)throws Exception{
		
		//System.out.println("ficherooooo: " + nbFichero);		
		
		// Se lee el fichero de jugadores para su posterior insercion en BBDD.
		LecturaFicheroJugadores lfj = new LecturaFicheroJugadores();
		ArrayList<JugadorRealDTO> lFicheroJugadores = (ArrayList<JugadorRealDTO>) lfj.cargarJugadores(nbFichero, codEquipoReal);
		
		// Si se dice que hay que insertar entonces se hacen los insert en BBDD.
		if (bInsertar){
			
			// Se recorre la lista para insertar los jugadores tanto en la
			// tabla JUGADOR como en LIGAJUGADOR.
			for( JugadorRealDTO s : lFicheroJugadores ){
				
				//System.out.println(s.getCodigoJugador() + "--" + s.getNombre());
				
				JugadorRealModelDTO jrmDTO = new JugadorRealModelDTO();
				
				jrmDTO.setCodJugador(s.getCodigoJugador());
				jrmDTO.setActivo("S");
				jrmDTO.setNombre(s.getNombre());
				jrmDTO.setApellidos(s.getApellidos());
				jrmDTO.setPuesto(s.getPuesto());
				jrmDTO.setAltura(s.getAltura());
				jrmDTO.setPeso("");
				//System.out.println(Util.getFechaActual());
				//jrmDTO.setFecalta(Util.getFechaActual());
				jrmDTO.setPrecio(s.getPrecio());
				jrmDTO.setCodEquipoReal(codEquipoReal);
				jrmDTO.setCodNacionalidad(s.getCodigoNacionalidad());
				
				// Se inserta un jugador en la tabla JUGADOR
				jugadorRealMapper.insertJugador(jrmDTO);
				
				
				// Se inserta un jugador en la tabla LIGAJUGADOR
				// Esto hace que se realice una insercion por liga y para ello recupero las 
				// Ligas y luego inserto en LIGAJUGADOR.
				ArrayList<LigaModelDTO> lLigas= (ArrayList<LigaModelDTO>) ligaMapper.getAllLigas();
				for( LigaModelDTO s1 : lLigas ){
					LigaJugadorModelDTO ljmDTO = new LigaJugadorModelDTO();
					
					ljmDTO.setCodLiga(s1.getCodLiga());
					ljmDTO.setCodJugador(s.getCodigoJugador());
					ljmDTO.setDinero(s.getPrecio() );
					ljmDTO.setActivo(jrmDTO.getActivo());
					ljmDTO.setCodEquipo(Ctes.NO_ASIGNADO_EQUIPO);
					
					ligaJugadorMapper.asignarJugador(ljmDTO);

				}
				
				
			}
		}

		return lFicheroJugadores;
	}
	
	
	@Transactional
	public void cambiarEstadoJugador(String codigoJugador, String estado)throws Exception{
		
		JugadorRealModelDTO jrmDTO = new JugadorRealModelDTO();
		
		// Se cambia el estado para ....
		String activo = "";  
		if (estado.equalsIgnoreCase("S"))
			activo="N";
		else
			activo="S";
		
		// ... realizar el update con el codigo de estado correcto en la tabla Jugador
		// Esto provoca 1 update.
		jrmDTO.setCodJugador(codigoJugador);
		jrmDTO.setActivo(activo);
		jugadorRealMapper.cambiarEstadoJugador(jrmDTO);
		
		
		// ... y realizar el update con el codigo de estado correcto en la tabla LigaJugador
		// Esto provoca n updates, uno por cada liga en la que se encuentre el jugador.
		LigaJugadorModelDTO ljmDTO = new LigaJugadorModelDTO();
		ljmDTO.setCodJugador(codigoJugador);
		ljmDTO.setActivo(activo);
		ligaJugadorMapper.cambiarEstadoJugador(ljmDTO);

	}
	
	
	// Inserta un jugador desde la administracion.
	@Transactional
	public void insertarJugador(JugadorRealDTO jDTO)throws Exception{

		
		JugadorRealModelDTO jrmDTO = new JugadorRealModelDTO();
		
		jrmDTO.setCodJugador(jDTO.getCodigoJugador());
		jrmDTO.setActivo(jDTO.getActivo());
		jrmDTO.setNombre(jDTO.getNombre());
		jrmDTO.setApellidos(jDTO.getApellidos());
		jrmDTO.setAltura(jDTO.getAltura());
		jrmDTO.setPeso(jDTO.getPeso());
		jrmDTO.setPrecio(jDTO.getPrecio());
		jrmDTO.setPuesto(jDTO.getPuesto());
		jrmDTO.setCodEquipoReal(jDTO.getCodigoEquipoReal());
		jrmDTO.setCodNacionalidad(jDTO.getCodigoNacionalidad());
		
		// Se inserta un jugador en la tabla JUGADOR
		jugadorRealMapper.insertJugador(jrmDTO);

		
		// Se inserta un jugador en la tabla LIGAJUGADOR
		// Esto hace que se realice una insercion por liga y para ello recupero las 
		// Ligas y luego inserto en LIGAJUGADOR.
		ArrayList<LigaModelDTO> lLigas= (ArrayList<LigaModelDTO>) ligaMapper.getAllLigas();
		for( LigaModelDTO s1 : lLigas ){
			LigaJugadorModelDTO ljmDTO = new LigaJugadorModelDTO();
			
			ljmDTO.setCodLiga(s1.getCodLiga());
			ljmDTO.setCodJugador(jDTO.getCodigoJugador());
			ljmDTO.setDinero(jDTO.getPrecio() );
			ljmDTO.setActivo(jDTO.getActivo());
			ljmDTO.setCodEquipo(Ctes.NO_ASIGNADO_EQUIPO);
			
			ligaJugadorMapper.asignarJugador(ljmDTO);

		}
		
		
		return;
	}

	
	
}
