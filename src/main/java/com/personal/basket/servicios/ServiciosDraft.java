package com.personal.basket.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.basket.ctes.Ctes;
import com.personal.basket.dao.DraftMapper;
import com.personal.basket.dao.DraftTurnoMapper;
import com.personal.basket.dao.EconomiaMapper;
import com.personal.basket.dao.EquipoMapper;
import com.personal.basket.dao.LigaJugadorJoinJugadorMapper;
import com.personal.basket.dao.LigaJugadorMapper;
import com.personal.basket.dao.LigaMapper;
import com.personal.basket.dtos.ConfiguracionDTO;
import com.personal.basket.dtos.DraftDTO;
import com.personal.basket.dtos.EconomiaDTO;
import com.personal.basket.dtos.EquipoDTO;
import com.personal.basket.dtos.LigaDTO;
import com.personal.basket.dtos.LigaJugadorDTO;
import com.personal.basket.dtos.LigaJugadorJoinJugadorDTO;
import com.personal.basket.model.ConfiguracionModelDTO;
import com.personal.basket.model.DraftModelDTO;
import com.personal.basket.model.DraftTurnoModelDTO;
import com.personal.basket.model.EconomiaModelDTO;
import com.personal.basket.model.EquipoModelDTO;
import com.personal.basket.model.LigaJugadorJoinJugadorModelDTO;
import com.personal.basket.model.LigaJugadorModelDTO;
import com.personal.basket.model.LigaModelDTO;


@Service("serviciosDraft")
public class ServiciosDraft implements IServiciosDraft{


	@Resource
	LigaMapper ligaMapper;

	@Resource
	EquipoMapper equipoMapper;

	@Resource
	EconomiaMapper economiaMapper;

	@Resource
	LigaJugadorMapper ligaJugadorMapper;

	@Resource
	LigaJugadorJoinJugadorMapper ligaJugadorJoinJugadorMapper;
	
	@Resource
	DraftMapper draftMapper;
	
	@Resource
	DraftTurnoMapper draftTurnoMapper;
	
	
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
	@Transactional
	public void setDraft()throws Exception{
		
		// Borrar todas las filas de DRAFTTURNO
		draftTurnoMapper.eliminarTurnosDraft();
		
		
		// Se obtienen todas las ligas...
		ArrayList<LigaModelDTO> lLigas= (ArrayList<LigaModelDTO>) ligaMapper.getAllLigas();
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
	
	
		
}
