package com.personal.basket.servicios;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.personal.basket.dtos.DraftDTO;
import com.personal.basket.dtos.EconomiaDTO;
import com.personal.basket.dtos.EquipoDTO;
import com.personal.basket.dtos.LigaDTO;
import com.personal.basket.dtos.LigaJugadorDTO;
import com.personal.basket.dtos.LigaJugadorJoinJugadorDTO;



public interface IServiciosDraft {
	
	// **************************************************************************
	// **************************************************************************
	// Liga
	// **************************************************************************
	// **************************************************************************
	@Transactional(readOnly=true)
	public LigaDTO getLiga(String codLiga)throws Exception;
	
	// **************************************************************************
	// **************************************************************************
	// Equipo
	// **************************************************************************
	// **************************************************************************	
	@Transactional(readOnly=true)
	public EquipoDTO getEquipo(String codEquipo)throws Exception;

	// **************************************************************************
	// **************************************************************************
	// Usuario
	// **************************************************************************
	// **************************************************************************	
	@Transactional(readOnly=true)
	public EconomiaDTO getEconomia(String codEcono)throws Exception;	
	
	// **************************************************************************
	// **************************************************************************
	// LigaJugador
	// **************************************************************************
	// **************************************************************************
	@Transactional(readOnly=true)
	public List<LigaJugadorJoinJugadorDTO> getLigaJugadoresActivos(String codLiga)throws Exception;
	
	// **************************************************************************
	// **************************************************************************
	// Draft
	// **************************************************************************
	// **************************************************************************
	@Transactional
	public void guardarEleccionDraft(String codLiga, String codEquipo, List<DraftDTO> lDraftJugadores)throws Exception;
	@Transactional
	public List<DraftDTO> getDraftEquipo(String codLiga, String codEquipo)throws Exception;
	
	
	// **************************************************************************
	// **************************************************************************
	// VARIAS TABLAS
	// **************************************************************************
	// **************************************************************************
	// Establece los turnos del draft para una liga y todos los equipos.
	@Transactional
	public void setDraft()throws Exception;
	
	// Establece el jugador que le toca a un equipo.
	@Transactional
	public void setDraftJugadorAEquipo()throws Exception;
	
}
