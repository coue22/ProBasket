package com.personal.basket.dao.join;

import static org.apache.ibatis.jdbc.SelectBuilder.*;


public class LigaJugadorJoinJugador {

	/**
	 * Devuelve todos los jugadores de una liga que esten activos
	 * @param codLiga
	 * @return
	 */
	public String getLigaJugadoresActivos(String codLiga) {
		
		BEGIN(); // Clears ThreadLocal variable
		SELECT("LJ.CODJUGADOR, LJ.DINERO, LJ.CODEQUIPO, LJ.CODLIGA, J.APELLIDOS, J.NOMBRE, J.PUESTO");
		FROM("ligajugador LJ");
		FROM("jugador J");
		WHERE("LJ.CODLIGA = #{codLiga}");
		WHERE("LJ.CODJUGADOR = J.CODJUGADOR");
		WHERE("LJ.ACTIVO = 'S'");
		//WHERE("LJ.CODEQUIPO = '0000000000'");
		ORDER_BY("LJ.DINERO DESC, J.APELLIDOS ASC");
		return SQL();
		
	}
	

}
