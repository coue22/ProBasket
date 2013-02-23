package com.personal.basket.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import com.personal.basket.dao.join.LigaJugadorJoinJugador;
import com.personal.basket.model.LigaJugadorJoinJugadorModelDTO;





// NO TIENE XML ASOCIADO YA QUE SE USA SelectBuilder DE MYBATIS
public interface LigaJugadorJoinJugadorMapper {
	
	//Devuelve todos los jugadores activos y sin equipo.
    @SelectProvider(method="getLigaJugadoresActivos", type=LigaJugadorJoinJugador.class)  
	public List<LigaJugadorJoinJugadorModelDTO> getLigaJugadoresActivos(String codLiga);  

} 