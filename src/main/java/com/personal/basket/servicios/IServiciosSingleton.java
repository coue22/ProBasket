package com.personal.basket.servicios;

import java.util.ArrayList;

import com.personal.basket.dtos.MenuDTO;
import com.personal.basket.dtos.OperacionDTO;
import com.personal.basket.dtos.RoleDTO;

public interface IServiciosSingleton {
	
	//private ArrayList<OperacionDTO> operacionesd;
	//private static String dd;
	
	public ArrayList<OperacionDTO> getOperaciones()throws Exception;
	public ArrayList<RoleDTO> getRoles()throws Exception;

}
