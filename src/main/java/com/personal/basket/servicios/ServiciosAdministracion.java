package com.personal.basket.servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.basket.ctes.Constantes;
import com.personal.basket.dao.CalendarioMapper;
import com.personal.basket.dao.PersonaMapper;
import com.personal.basket.dtos.ConfiguracionDTO;
import com.personal.basket.dtos.EquipoRealDTO;
import com.personal.basket.dtos.JugadorRealDTO;
import com.personal.basket.dtos.NacionalidadDTO;
import com.personal.basket.dtos.OperacionDTO;
import com.personal.basket.dtos.RoleDTO;
import com.personal.basket.model.Calendario;


@Service("servicioAdministracion")
public class ServiciosAdministracion implements IServiciosAdministracion{

	// **************************************************************************
	// **************************************************************************
	// Configuracion
	// **************************************************************************
	// **************************************************************************

	public boolean modificarConfiguracion(ConfiguracionDTO cDTO)throws Exception{
		System.out.println("Parametro: " + cDTO.getParametro());
		System.out.println("valor: " + cDTO.getValor());		
		return true;
	}
	
	public boolean insertarConfiguracion(ConfiguracionDTO cDTO)throws Exception{
		System.out.println("Parametro: " + cDTO.getParametro());
		System.out.println("valor: " + cDTO.getValor());		
		return true;
		
	}
	
	
	// **************************************************************************
	// **************************************************************************
	// Nacionalidad
	// **************************************************************************
	// **************************************************************************	
	public boolean eliminarNacionalidad(NacionalidadDTO nDTO)throws Exception{
		System.out.println("Codigo nacionalidad a eliminar: " + nDTO.getCodigoNacionalidad());
				
		return true;
	}
	
	public boolean insertarNacionalidad(NacionalidadDTO nDTO)throws Exception{
		// El codigo de nacionalidad es un autoincremental.
		System.out.println("Descripcion a insertar: " + nDTO.getDescripcion());		
		return true;
	}
	
	// **************************************************************************
	// **************************************************************************
	// Operacion
	// **************************************************************************
	// **************************************************************************
	public boolean modificarOperacion(OperacionDTO oDTO)throws Exception{
		System.out.println("Codigo operacion a modificar: " + oDTO.getCodigoOpera());
		System.out.println("Descripcion a modificar: " + oDTO.getDescripcion());
		return true;
	}
	
	public boolean insertarOperacion(OperacionDTO oDTO)throws Exception{
		// El codigo es autogenerado.
		System.out.println("Descripcion a insertar: " + oDTO.getDescripcion());
		return true;
	}
	
	// **************************************************************************
	// **************************************************************************
	// Role
	// **************************************************************************
	// **************************************************************************
	public boolean modificarRole(RoleDTO rDTO)throws Exception{
		System.out.println("Codigo role a modificar: " + rDTO.getCodigoRole());
		System.out.println("Descripcion a modificar: " + rDTO.getDescripcion());
		return true;
	}
	public boolean insertarRole(RoleDTO rDTO)throws Exception{
		// El codigo es autogenerado.
		System.out.println("Descripcion role a insertar: " + rDTO.getDescripcion());
		return true;
	}
	
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								OPERACIONES DE EQUIPO								   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //

	// OK	
	public boolean modificarEquipoCatalogo(EquipoRealDTO eDTO)throws Exception{
		
		// Se llama para modificar en base de datos
		System.out.println("Codigo: " + eDTO.getCodigoEquipoReal());
		System.out.println("Nombre: " + eDTO.getNombre());
		System.out.println("Siglas: " + eDTO.getSiglas());
		
		// AQUI SE DEBE REALIZAR LA LLAMADA A MYBATIS PARA UN UPDATE.
		
		// SE DEBE EVALUAR LA SALIDA DE LA MODIFICACION.
		
		// Se debe llamar a la recarga del servicio Singleton para Equipos Reales.
		
		return true;
	}
	
	// OK	
	public boolean insertarEquipoCatalogo(EquipoRealDTO eDTO)throws Exception{
		
		// Inserta en base de datos un equipo real.
		// El codigo debe ser autogenerado en bbdd.
		System.out.println("Nombre: " + eDTO.getNombre());
		System.out.println("Siglas: " + eDTO.getSiglas());
		
		// AQUI SE DEBE REALIZAR LA LLAMADA A MYBATIS PARA UN INSERT.
		
		// SE DEBE EVALUAR LA SALIDA DE LA INSERCION.
		
		// Se debe llamar a la recarga del servicio Singleton para Equipos Reales.		
		
		return true;
	}
	
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								OPERACIONES DE JUGADOR								   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	

	/*
	public boolean eliminarJugadorCatalogo(JugadorRealDTO jDTO)throws Exception{
		//jDTO.getCodigo()
		return true;
	}
	
	public boolean insertarJugadorCatalogo(JugadorRealDTO jDTO)throws Exception{
		//jDTO.getCodigo()
		return true;
	}
	
	*/
	

	
	
}
