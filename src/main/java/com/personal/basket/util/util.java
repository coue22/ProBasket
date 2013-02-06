package com.personal.basket.util;

import java.util.Map;

import com.personal.basket.dtos.ConfiguracionDTO;

public class util {
	
	
	public static ConfiguracionDTO getValorMapaConfiguracion (Map <String, ConfiguracionDTO>mapa, String param) throws Exception
	{
		
		if (mapa.containsKey(param) == false)
			throw new Exception ("No Encontrada valor " + param + " en el mapa");
		
		return mapa.get(param);
	}
	

	
}
