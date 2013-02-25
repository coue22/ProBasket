package com.personal.basket.servicios;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.basket.ctes.Ctes;
import com.personal.basket.ctes.CtesOperacion;
import com.personal.basket.dao.DetEconomiaMapper;
import com.personal.basket.dao.DraftMapper;
import com.personal.basket.dao.DraftTurnoMapper;
import com.personal.basket.dao.EconomiaMapper;
import com.personal.basket.dao.EquipoJugadorMapper;
import com.personal.basket.dao.EquipoMapper;
import com.personal.basket.dao.LigaJugadorJoinJugadorMapper;
import com.personal.basket.dao.LigaJugadorMapper;
import com.personal.basket.dao.LigaMapper;
import com.personal.basket.dao.UsuarioMapper;
import com.personal.basket.dtos.ConfiguracionDTO;
import com.personal.basket.dtos.DraftDTO;
import com.personal.basket.dtos.EconomiaDTO;
import com.personal.basket.dtos.EquipoDTO;
import com.personal.basket.dtos.LigaDTO;
import com.personal.basket.dtos.LigaJugadorDTO;
import com.personal.basket.dtos.LigaJugadorJoinJugadorDTO;
import com.personal.basket.model.ConfiguracionModelDTO;
import com.personal.basket.model.DetEconomiaModelDTO;
import com.personal.basket.model.DraftModelDTO;
import com.personal.basket.model.DraftTurnoModelDTO;
import com.personal.basket.model.EconomiaModelDTO;
import com.personal.basket.model.EquipoJugadorModelDTO;
import com.personal.basket.model.EquipoModelDTO;
import com.personal.basket.model.LigaJugadorJoinJugadorModelDTO;
import com.personal.basket.model.LigaJugadorModelDTO;
import com.personal.basket.model.LigaModelDTO;
import com.personal.basket.model.UsuarioModelDTO;
import com.personal.basket.util.Util;


@Service("serviciosDraft")
public class ServiciosDraft implements IServiciosDraft{
	
	@Autowired
	ServiciosSingleton servicioSingleton;
	@Autowired
	ServiciosAdministracion servicioAdministracion;
	
	public ServiciosSingleton getServicioSingleton() {
		return servicioSingleton;
	}
	public void setServicioSingleton(ServiciosSingleton servicioSingleton) {
		this.servicioSingleton = servicioSingleton;
	}

	public ServiciosAdministracion getServicioAdministracion() {
		return servicioAdministracion;
	}
	public void setServicioAdministracion(
			ServiciosAdministracion servicioAdministracion) {
		this.servicioAdministracion = servicioAdministracion;
	}




	@Resource
	LigaMapper ligaMapper;

	@Resource
	EquipoMapper equipoMapper;

	@Resource
	EconomiaMapper economiaMapper;
	
	@Resource
	DetEconomiaMapper detEconomiaMapper;
	
	@Resource
	LigaJugadorMapper ligaJugadorMapper;

	@Resource
	LigaJugadorJoinJugadorMapper ligaJugadorJoinJugadorMapper;
	
	@Resource
	DraftMapper draftMapper;
	
	@Resource
	DraftTurnoMapper draftTurnoMapper;
	
	@Resource
	UsuarioMapper usuarioMapper;
	
	@Resource
	EquipoJugadorMapper equipoJugadorMapper;
	
	// **************************************************************************
	// **************************************************************************
	// Liga
	// **************************************************************************
	// **************************************************************************
	@Transactional(readOnly=true)
	public LigaDTO getLiga(String codLiga)throws Exception{
		
		LigaModelDTO lmDTO = ligaMapper.getLiga(codLiga);
		if (lmDTO == null)
			return null;
		
		LigaDTO lDTO = new LigaDTO();
		lDTO.setCodigoLiga(codLiga);
		lDTO.setNombre(lmDTO.getNombre());
		
		return lDTO;
		
	}
	
	// **************************************************************************
	// **************************************************************************
	// Equipo
	// **************************************************************************
	// **************************************************************************	
	@Transactional(readOnly=true)
	public EquipoDTO getEquipo(String codEquipo)throws Exception{
		
		EquipoModelDTO eqmDTO = equipoMapper.getEquipo(codEquipo);
		if (eqmDTO == null)
			return null;
		
		EquipoDTO eqDTO = new EquipoDTO();
		eqDTO.setCodigoEquipo(codEquipo);
		eqDTO.setNombre(eqmDTO.getNombre());
		eqDTO.setPosicion(eqmDTO.getPosicion());
		eqDTO.setPuntuacionTotal(eqmDTO.getPuntuacionTotal());
		eqDTO.setCodigoLiga(eqmDTO.getCodLiga());
		
		
		return eqDTO;
	}
	
	// **************************************************************************
	// **************************************************************************
	// Usuario
	// **************************************************************************
	// **************************************************************************	
	@Transactional(readOnly=true)
	public EconomiaDTO getEconomia(String codEcono)throws Exception{
		 
		EconomiaModelDTO ecmDTO = economiaMapper.getEconomia(codEcono);
		if (ecmDTO == null)
			return null;
		
		EconomiaDTO ecDTO = new EconomiaDTO();
		ecDTO.setCodigoEcono(codEcono);
		ecDTO.setDinero(ecmDTO.getDinero());
		
		return ecDTO;
	}
	
	// **************************************************************************
	// **************************************************************************
	// LigaJugador
	// **************************************************************************
	// **************************************************************************
	@Transactional(readOnly=true)
	public List<LigaJugadorJoinJugadorDTO> getLigaJugadoresActivos(String codLiga)throws Exception{
		
		
		
		ArrayList<LigaJugadorJoinJugadorDTO> lljjjJugadoresDTO = new  ArrayList<LigaJugadorJoinJugadorDTO>();
		lljjjJugadoresDTO.clear();
		
		
		//ArrayList<LigaJugadorModelDTO> lljmJugadoresDTO = (ArrayList<LigaJugadorModelDTO>) ligaJugadorMapper.getJugadoresActivos(codLiga);
		
		ArrayList<LigaJugadorJoinJugadorModelDTO> lljjjmJugadoresDTO = 
				(ArrayList<LigaJugadorJoinJugadorModelDTO>) ligaJugadorJoinJugadorMapper.getLigaJugadoresActivos(codLiga);		
		
		
		for( LigaJugadorJoinJugadorModelDTO s : lljjjmJugadoresDTO ){
			
			LigaJugadorJoinJugadorDTO ljjjDTO = new LigaJugadorJoinJugadorDTO();
			
			ljjjDTO.setCodigoLiga(codLiga);				// Codigo de liga.
			ljjjDTO.setCodigoJugador(s.getCodJugador());	// Codigo de jugador
			ljjjDTO.setDinero(s.getDinero());				// Dinero que cuesta en el draft
			ljjjDTO.setCodigoEquipo(s.getCodEquipo()); 	// Equipo al que pertenece el jugador. (Si es 0000000000 es que este libre)
			ljjjDTO.setNombre(s.getNombre());
			ljjjDTO.setApellidos(s.getApellidos());
			ljjjDTO.setPuesto(s.getPuesto());
			
			lljjjJugadoresDTO.add(ljjjDTO);

		}
		
		
		
		return lljjjJugadoresDTO;
		
	}
	
	@Transactional
	public void guardarEleccionDraft(String codLiga, String codEquipo, List<DraftDTO> lDraftJugadores)throws Exception{
		
		// Se elimina la anterior eleccion de jugadores para una liga y un equipo.
		DraftModelDTO dmDeleteDTO = new DraftModelDTO();
		dmDeleteDTO.setCodLiga(codLiga);
		dmDeleteDTO.setCodEquipo(codEquipo);
		draftMapper.eliminarEleccionDraft(dmDeleteDTO);
		
		
		// Se insertan los jugadores
		int orden = 1;
		for( DraftDTO s : lDraftJugadores ){
			
			DraftModelDTO dmDTO = new DraftModelDTO();
			dmDTO.setCodLiga(s.getCodigoLiga());
			dmDTO.setCodEquipo(s.getCodigoEquipo());
			dmDTO.setCodJugador(s.getCodigoJugador());
			
			System.out.println("jug:" + s.getCodigoJugador());
			if (!s.getCodigoJugador().equalsIgnoreCase("-1")){
				dmDTO.setOrden(orden);
				System.out.println("orden:" + orden);
				orden++;
				
				draftMapper.guardarEleccionDraft(dmDTO);
				
			}

		}
		
		
		
	}
	
	@Transactional
	public List<DraftDTO> getDraftEquipo(String codLiga, String codEquipo)throws Exception{
		
		
		// Se establecen los criterios de busqueda para ir a BBDD
		DraftModelDTO dmDTO = new DraftModelDTO();
		dmDTO.setCodLiga(codLiga);
		dmDTO.setCodEquipo(codEquipo);
		
		// Se buscan los datos en BBDD tabla Draft.
		List<DraftModelDTO> lmDraft = draftMapper.getDraftEquipo(dmDTO);
		
		
		// Se rellena la lista de DraftDTO para pasarla al controlador.
		List<DraftDTO> lDraft = new ArrayList<DraftDTO>();
		for( DraftModelDTO s : lmDraft ){
			
			DraftDTO dSalidaDTO = new DraftDTO();
			dSalidaDTO.setCodigoJugador(s.getCodJugador());
			dSalidaDTO.setOrden(s.getOrden());
			
			System.out.println("Pintar en combos: " + s.getCodJugador() + "--" + s.getOrden());
			
			lDraft.add(dSalidaDTO);
		}
		
		return lDraft;
		
	}
	
	
	// **************************************************************************
	// **************************************************************************
	// VARIAS TABLAS
	// **************************************************************************
	// **************************************************************************
	// Establece los turnos del draft para una liga y todos los equipos.
	@Transactional
	public void setDraft()throws Exception{
		
		// Borrar todas las filas de DRAFTTURNO
		draftTurnoMapper.eliminarTurnosDraft();
		
		
		// Se obtienen todas las ligas...
		ArrayList<LigaModelDTO> lLigas= (ArrayList<LigaModelDTO>) ligaMapper.getAllLigas();
//System.out.println("NUMERO DE LIGAS: " + lLigas.size());
		for( LigaModelDTO liga : lLigas ){
		
			// Si es distinto a la liga por defecto (0000000000)hacer....
			if (!liga.getCodLiga().equalsIgnoreCase(Ctes.NO_ASIGNADO_LIGA)){
				
				System.out.println("Name league: " + liga.getCodLiga() + "-->" + liga.getNombre());
				
				// Se obtiene el numero de equipos de la liga.
				List<EquipoModelDTO> lEquipos =  equipoMapper.getEquiposLiga(liga.getCodLiga());
				//System.out.println("num Equipos:" + lEquipos.size());
				int turnoAGrabar=0;
				int turno=1;
				for (int i = 1 ; i<=Ctes.NUM_RONDAS_DRAFT; i++){
					
					System.out.println("Ronda: " + i);
					
					int ncont=1;
					for (int j = 0 ; j<lEquipos.size(); j++){
						
						if (i%2==1){
							turnoAGrabar = turno;
							//System.out.println("Equipo:" + lEquipos.get(j).getCodEquipo() + "turno:" + turno);
							
						}else{
							turnoAGrabar = turno+lEquipos.size()-ncont-j;
							//System.out.println("Equipo:" + lEquipos.get(j).getCodEquipo() + "turno:" + (turno+lEquipos.size()-ncont-j));
							ncont++;
						}
						
						System.out.println("Equipo:" + lEquipos.get(j).getCodEquipo() + " turno:" + turnoAGrabar);
						
						// Se inserta en la tabla de insercción de turnos del draft (DRAFTTURNO)
						DraftTurnoModelDTO dtmDTO = new DraftTurnoModelDTO();
						dtmDTO.setCodLiga(liga.getCodLiga() );
						dtmDTO.setCodEquipo(lEquipos.get(j).getCodEquipo());
						dtmDTO.setTurno(turnoAGrabar);
						draftTurnoMapper.guardarTurnoDraft(dtmDTO);
						
						// Se aumenta un turno.
						turno++;
						
						
						
						
					}	// for j

				} // for i
	
			} // if la liga no es la 0000000000


		} // for de la liga
		

	}
	
	/**
// Inicializa de nuevo un DRAFT. (OJO!!! antes se deberia hacer la seleccion si se desea.)
update economia set dinero=4000000;
select * from economia;

update configuracion set valor='1' where parametro='turnodraft';
select * from configuracion;

update ligajugador set codequipo='0000000000';
select * from ligajugador where codequipo<>'0000000000';

delete from equipojugador;
select * from equipojugador;

delete from deteconomia;
select * from deteconomia;
	 */
	
	// Establece el jugador que le toca a un equipo PARA TODAS LAS LIGAS.
	@Transactional
	public void setDraftJugadorAEquipo()throws Exception{
		
		HashMap<String, ConfiguracionDTO> hmConfiguracion = null;
		
		
		// Se obtiene el mapa de configuracion. La intencion es obtener el parametro "turnodraft" y "draft"
		hmConfiguracion = servicioSingleton.getConfiguracion(Ctes.NO);
		
		
		// Se recupera la variable de  draft y solo si tiene una "S" se continua
		ConfiguracionDTO cDTODraft = null;
		cDTODraft = Util.getValorMapaConfiguracion(hmConfiguracion, Ctes.MAPA_CONFIGURACION_DRAFT);
		if (cDTODraft.getValor().equalsIgnoreCase(Ctes.SI)){
			
			// Se recupera la variable de turnodraft.
			ConfiguracionDTO cDTOTurnoDraft = null;
			cDTOTurnoDraft = Util.getValorMapaConfiguracion(hmConfiguracion, Ctes.MAPA_CONFIGURACION_TURNO_DRAFT);
		
			// Se recupera el turno actual del draft en el que estamos.
			int turnoDraft = Integer.valueOf(cDTOTurnoDraft.getValor()).intValue();
			

			
			// Se obtienen todas las ligas...
			ArrayList<LigaModelDTO> lLigas= (ArrayList<LigaModelDTO>) ligaMapper.getAllLigas();
			for( LigaModelDTO liga : lLigas ){
			
				// Si es distinto a la liga por defecto (0000000000)hacer....
				if (!liga.getCodLiga().equalsIgnoreCase(Ctes.NO_ASIGNADO_LIGA)){
					
					
					List<EquipoModelDTO> lEquiposEnLiga =  equipoMapper.getEquiposLiga(liga.getCodLiga());
					
					if (turnoDraft > Ctes.NUM_RONDAS_DRAFT * lEquiposEnLiga.size()){
						System.out.println("Ya termino el draft para la liga: " + liga.getCodLiga() + " con el turno: " + turnoDraft);
						
					}else{
					
						System.out.println("Seleccion de la liga " + liga.getNombre() + " en el turno " + turnoDraft);
						
						//  Ir a "draftturno" a recuperar el codigo de equipo que tiene que
						//		 elegir para la liga y el turno actual
						// 			Salida: Tengo el codigo de equipo al cual le toca elegir.
						DraftTurnoModelDTO dtmDTO = new DraftTurnoModelDTO();
						dtmDTO.setCodLiga(liga.getCodLiga());
						dtmDTO.setTurno(turnoDraft);
						DraftTurnoModelDTO dtmSalidaDTO = draftTurnoMapper.getEquipoTurnoDraft(dtmDTO);
						System.out.println("Equipo a elegir: " + dtmSalidaDTO.getCodEquipo());
					
					
					
						//  HACER LA LOGICA REFERENTE A SELECCIONAR EL JUGADOR QUE SE ESCOGE. (tabla DRAFT) O ELEGIR AL AZAR.
						// 		 En este punto ya tengo el equipo que selecciona.
						//		 Ir a la tabla "draft" para ver si tiene seleccionado jugador.
						//			Si tiene: Se escoge y se recupera el coste.
						//			No tiene: Al azar con un algoritmo para seleccionar
						List<DraftDTO> lDraft = this.getDraftEquipo(liga.getCodLiga(), dtmSalidaDTO.getCodEquipo());
						String codJugadorASeleccionar="";
						double dineroCoste = 0;
						if (lDraft.size()>0){// Si es mayor que 0, entonces se escoge al primer jugador.
							
							//System.out.println("Se escoge el primero de la lista: " + lDraft.get(0).getCodigoJugador());
							codJugadorASeleccionar = lDraft.get(0).getCodigoJugador();
							
							LigaJugadorModelDTO ljmDTO = new LigaJugadorModelDTO();
							ljmDTO.setCodLiga(liga.getCodLiga());
							ljmDTO.setCodJugador(codJugadorASeleccionar);
							LigaJugadorModelDTO ljmSalidaDTO = ligaJugadorMapper.getLigaJugador(ljmDTO);
							
							dineroCoste=ljmSalidaDTO.getDinero();
							
							System.out.println("LISTA-->Se escoge el primero de la lista: " + lDraft.get(0).getCodigoJugador() + " cuyo coste es: " + dineroCoste);
							
						}else{ // Hay que escoger uno al azar. De momento el primero ya que sera el de coste inferior.
	
							List<LigaJugadorModelDTO> lLigaJugador = ligaJugadorMapper.getJugadoresActivosSinEquipo(liga.getCodLiga());
							
	
							codJugadorASeleccionar = lLigaJugador.get(0).getCodJugador();
							dineroCoste=lLigaJugador.get(0).getDinero();
							
							System.out.println("AZAR-->Se escoge al AZAR al jugador. De momento sera el del precio mas bajo:"  + codJugadorASeleccionar + " cuyo coste es: " + dineroCoste);
						}
	
						// RECUPERAR EL CODIGO DE ECONOMIA DEL USUARIO por CODIGO DE LIGA Y CODIGO DE EQUIPO.
						UsuarioModelDTO umDTO = new UsuarioModelDTO();
						umDTO.setCodLiga(liga.getCodLiga());
						umDTO.setCodEquipo(dtmSalidaDTO.getCodEquipo());
						UsuarioModelDTO umSalidaDTO = usuarioMapper.getDatosUsuarioPorLigayEquipo(umDTO);
						System.out.println(" El codigo de economia es :" + umSalidaDTO.getCodEcono());
							
						
						// Se comprueba el dinero del que dispongo y el coste del jugador.
						// Aqui pueden pasar 2 cosas:
						// 	1: SI Tenga dinero --> Entonces se hace el proceso normal.
						//	2: NO Tenga dinero --> Entonces no elige jugador.
						EconomiaModelDTO emSalidaDTO = economiaMapper.getEconomia(umSalidaDTO.getCodEcono());
						if (Double.valueOf(emSalidaDTO.getDinero()).doubleValue() < dineroCoste){
							// TODO: Solucionar esto con una insercion en un tabla de draft
							// y NO levantando una excepcion.
							System.out.println("ESTO PUEDE SER MUY COMUN PORQUE PUEDES TENER SELECCIONADO UN JUGADOR DE IMPORTE MAYOR QUE EL DINERO DISPONIBLE");
							System.out.println("ESTE PROBLEMA SE PODRIA SOLUCIONAR SI SOLO SE LE MOSTRASEN LOS JUGADORES CUYO IMPORTE SEA MENOR QUE EL QUE SE TIENE");
							System.out.println("EN ESE CASO SERIA IMPOSIBLE VENIR POR AQUI, A NO SER QUE SE ESCOGIERA AL AZAR");
							
						}else{
							
							System.out.println("COMENZAMOS A REALIZAR UPDATES E INSERT");
							
							// UPDATE Restar el dinero que vale el jugador en "Economia" e  INSERT en "deteconomia" dejar registro de la resta.
							EconomiaModelDTO ecomDTO = new EconomiaModelDTO();
							ecomDTO.setCodEcono(umSalidaDTO.getCodEcono());
							ecomDTO.setDinero(dineroCoste);
							economiaMapper.restarEconomia(ecomDTO);
							
							// Se INSERTA en el Detalle de la Economia el movimiento.
							String codigoDetEconomia = detEconomiaMapper.getSecuenciaDetEconomiaHSQL();
							DetEconomiaModelDTO decDTO = new DetEconomiaModelDTO();
							decDTO.setCodDetEconomia(codigoDetEconomia);
							decDTO.setCodigoEcono(umSalidaDTO.getCodEcono());
							decDTO.setCodigoOpera(CtesOperacion.CARGO_SELECCION_DRAFT);
							decDTO.setDetalle(CtesOperacion.TXT_CARGO_SELECCION_DRAFT + turnoDraft + "/" + liga.getCodLiga() + "/" + dtmSalidaDTO.getCodEquipo() + "/" + codJugadorASeleccionar);
							detEconomiaMapper.setDetEconomia(decDTO);
	
							// Se realiza el UPDATE sobre "LIGAJUGADOR"
							LigaJugadorModelDTO jrmDTO = new LigaJugadorModelDTO();
							jrmDTO.setCodLiga(liga.getCodLiga());
							jrmDTO.setCodEquipo(dtmSalidaDTO.getCodEquipo());
							jrmDTO.setCodJugador(codJugadorASeleccionar);
							ligaJugadorMapper.asignarEquipoAJugador(jrmDTO);
							
							
							// INSERT EQUIPOJUGADOR 
							EquipoJugadorModelDTO ejmDTO= new EquipoJugadorModelDTO();
							ejmDTO.setCodEquipo(dtmSalidaDTO.getCodEquipo());
							ejmDTO.setCodJugador(codJugadorASeleccionar);
							ejmDTO.setCodRole("0");
							// Hoy
							java.util.Date dmFecAlta = new java.util.Date();
							ejmDTO.setFecalta(dmFecAlta); 
							// 31-12-2999
							java.util.Date dmFecBaja = new java.util.Date(1099,11,31);
							ejmDTO.setFecbaja(dmFecBaja);
							ejmDTO.setIndemnizacion(0);
							ejmDTO.setNomina(0);
							ejmDTO.setPrecioventa(0);
							ejmDTO.setValoraciontemp(0);
							equipoJugadorMapper.asignarJugador(ejmDTO);
	
	
							// ELIMINAR DE LAS ELECCIONES DEL DRAFT AL JUGADOR ESCOGIDO DE TODOS LOS 
							// EQUIPOS DE LA LIGA PUESTO QUE YA LO TIENE ESTE EQUIPO.
							DraftModelDTO dmEliminarDTO = new DraftModelDTO();
							dmEliminarDTO.setCodLiga(liga.getCodLiga());
							dmEliminarDTO.setCodJugador(codJugadorASeleccionar);
							draftMapper.eliminarJugadorDelDraft(dmEliminarDTO);
							
							// ELIMINAR DE LAS ELECCIONES DEL DRAFT DEL EQUIPO QUE ESTA EN EL TURNO (el que elige) 
							// A TODOS LOS JUGADORES QUE SUPEREN EL DINERO DISPONIBLE EN CAJA.
							EconomiaModelDTO emDineroDisponibleDTO = economiaMapper.getEconomia(umSalidaDTO.getCodEcono());
							double dinero_disponible = emDineroDisponibleDTO.getDinero();
	
							// Recupera todos los jugadores que tiene seleccionados para el draft.
							DraftModelDTO dmJugDTO = new DraftModelDTO();
							dmJugDTO.setCodLiga(liga.getCodLiga());
							dmJugDTO.setCodEquipo(dtmSalidaDTO.getCodEquipo());
							List<DraftModelDTO> lJugDraft = draftMapper.getDraftEquipo(dmJugDTO);
							for( DraftModelDTO s1 : lJugDraft ){
								
								// Obtiene el dinero de cada jugador.
								LigaJugadorModelDTO ljmDTO = new LigaJugadorModelDTO();
								ljmDTO.setCodLiga(liga.getCodLiga());
								ljmDTO.setCodJugador(s1.getCodJugador());
								LigaJugadorModelDTO ljmSalidaDTO = ligaJugadorMapper.getLigaJugador(ljmDTO);
								dineroCoste=ljmSalidaDTO.getDinero();
								
								// Si el dinero disponible es menor que el dinero
								// que cuesta el jugador se elimina de la tabla de seleccionables
								// o tabla "draft"
								if (dinero_disponible < dineroCoste){
									DraftModelDTO dmEliminarDTO2 = new DraftModelDTO();
									dmEliminarDTO2.setCodLiga(liga.getCodLiga());
									dmEliminarDTO2.setCodEquipo(dtmSalidaDTO.getCodEquipo());
									dmEliminarDTO2.setCodJugador(s1.getCodJugador());
									draftMapper.eliminarJugadorEquipoDelDraft(dmEliminarDTO2);
								}
								
								
							}
							
							
						}
					}

					
				} // No se hace nada para la liga '0000000000'
				
			}// Para todas las ligas
			

			// Se actualiza la variable de turno draft y se refresca el mapa.
			ConfiguracionDTO cDTO = new ConfiguracionDTO();
			cDTO.setParametro(cDTOTurnoDraft.getParametro());
			turnoDraft = turnoDraft + 1;
			cDTO.setValor(String.valueOf(turnoDraft));
			servicioAdministracion.modificarConfiguracion(cDTO);
			
			// Se refresca el mapa de configuracion.
			hmConfiguracion = servicioSingleton.getConfiguracion(Ctes.SI);
			
		}// 'S' la variable draft de la tabla configuracion
		

	}
	
	
	
	
	
	
}
