package com.personal.basket.util;

import java.util.Map;

public class util {

	public static String getValorMapa (Map <String, String>mapa, String param) throws Exception
	{
		
		if (mapa.containsKey(param) == false)
			throw new Exception ("No Encontrada valor " + param + " en el mapa");
		
		return mapa.get(param);
	}
	
	
}
