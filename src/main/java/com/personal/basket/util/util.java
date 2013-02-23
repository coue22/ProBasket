package com.personal.basket.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.personal.basket.dtos.ConfiguracionDTO;

public class Util {
	
	
	public static ConfiguracionDTO getValorMapaConfiguracion (Map <String, ConfiguracionDTO>mapa, String param) throws Exception
	{
		
		if (mapa.containsKey(param) == false)
			throw new Exception ("No Encontrada valor " + param + " en el mapa");
		
		return mapa.get(param);
	}
	

    //Metodo usado para obtener la fecha actual
    //@return Retorna un <b>STRING</b> con la fecha actual formato "dd/MM/yyyy"
    public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return formateador.format(ahora);
    }


}
