package com.personal.basket.servicios;

import java.util.ArrayList;

import com.personal.basket.dtos.OperacionDTO;
import com.personal.basket.dtos.RoleDTO;

public class ServiciosSingleton implements IServiciosSingleton{
	
	private ArrayList<OperacionDTO> lOperaciones = new ArrayList<OperacionDTO>();
	private ArrayList<RoleDTO> lRoles  = new ArrayList<RoleDTO>();
	

	
	public ArrayList<OperacionDTO> getOperaciones()throws Exception{
		
		OperacionDTO operacion1 = new OperacionDTO();
		OperacionDTO operacion2 = new OperacionDTO();
		OperacionDTO operacion3 = new OperacionDTO();
		
		operacion1.setCodigoOpera("001");
		operacion1.setDescripcion("Abono por venta de jugador");
		
		operacion1.setCodigoOpera("002");
		operacion1.setDescripcion("Abono por partido en casa");
		
		operacion1.setCodigoOpera("003");
		operacion1.setDescripcion("Cargo por compra de jugador.");
		
		lOperaciones.add(operacion1);
		lOperaciones.add(operacion2);
		lOperaciones.add(operacion3);
		
		return lOperaciones;
	}


	public ArrayList<RoleDTO> getRoles()throws Exception{
		
		// Se realiza una llamada a BBDD para recuperar 
		// todas las filas de la tabla "Role"
		
		RoleDTO role1 = new RoleDTO();
		RoleDTO role2 = new RoleDTO();
		RoleDTO role3 = new RoleDTO();
		
		role1.setCodigoRole("001");
		role1.setDescripcion("Capitan");

		role1.setCodigoRole("002");
		role1.setDescripcion("Anotador");

		role1.setCodigoRole("003");
		role1.setDescripcion("Defensor");
		
		lRoles.add(role1);
		lRoles.add(role2);
		lRoles.add(role3);
		
		return lRoles;
	}
	
}
