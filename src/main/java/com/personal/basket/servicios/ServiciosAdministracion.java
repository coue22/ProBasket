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
import com.personal.basket.dtos.EquipoRealDTO;
import com.personal.basket.dtos.JugadorDTO;
import com.personal.basket.model.Calendario;


@Service("servicioAdministracion")
public class ServiciosAdministracion implements IServiciosAdministracion{




	public void InicializarConfiguracion()throws Exception{
		
		// AQUI SE DEBEN REALIZAR LOS INSERT. YA SE ESTA haciendo la llamada en el controller.
		// SE HACEN A TRAVES DE MYBATIS
		// Tabla Operacion, 
		// Tabla Role, 
		// EquipoReal, 
		// Jugador
		
		// Configuracion (ConfiguracionDTO y Configuracion)
			// Lectura de un fichero de datos para insertarlo en la tabla.
		

		
			//ListaFicheros = File.filter ("lengua_*.txt")
						

		  
		  
		//InputStream is = this.getClass().getClassLoader().getResourceAsStream("./resources/archivo.txt");
		//System.out.println(is.read());
				
		//URL inputStream  = Thread.currentThread().getContextClassLoader().getResource("WEB-INF//archivo.txt");
		//inputStream.
		
		//String rutaRelativaApp = getServletContext().getRealPath("/") + "fichero.txt";
		
	      //File archivo = null;
	      //FileReader fr = null;
	      //BufferedReader br = null;
	      
	      
	      //String archivo="/WEB-INF/archivo.txt"; 
	      
	      //InputStream inputStream  = getServletContext().getResourceAsStream(archivo);
	      
	      //ServletContext.getRealPath("WEB-INF");
	      
	      /*
	      //Thread.currentThread().getContextClassLoader().getResource("NOMBRE");
	      Thread.currentThread().getContextClassLoader().getResource("WEB-INF//archivo.txt");
	      

	      archivo = new File ("//WEB-INF//archivo.txt");
	      fr = new FileReader (archivo);
	      br = new BufferedReader(fr);

	      // Lectura del fichero
	      String linea;
	      while((linea=br.readLine())!=null)
	    	  System.out.println(linea);		
		*/

	}
	
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								OPERACIONES DE EQUIPO								   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// OK
	public ArrayList<EquipoRealDTO> mostrarCatalogoEquipos()throws Exception{
		
		// Debe ir a BBDD a recuperar todos los equipos cuya temporada
		// sea "temporadaInicio" y "temporadaFin".
		
		ArrayList<EquipoRealDTO> lEquipo = new ArrayList<EquipoRealDTO>();
		
		EquipoRealDTO e1 = new EquipoRealDTO();
		e1.setCodigoEquipoReal("00001");
		e1.setNombre("Baloncesto Fuenlabrada");
		e1.setSiglas("MCF");
		//e1.setTemporadaInicio(temporadaInicio);
		//e1.setTemporadaFin(temporadaFin);
		
		EquipoRealDTO e2 = new EquipoRealDTO();
		e2.setCodigoEquipoReal("00002");
		e2.setNombre("Real Madrid");
		e2.setSiglas("RM");
		//e2.setTemporadaInicio(temporadaInicio);
		//e2.setTemporadaFin(temporadaFin);		
		
		EquipoRealDTO e3 = new EquipoRealDTO();
		e3.setCodigoEquipoReal("00003");
		e3.setNombre("Barcelona");
		e3.setSiglas("FCB");
		//e3.setTemporadaInicio(temporadaInicio);
		//e3.setTemporadaFin(temporadaFin);		
		
		EquipoRealDTO e4 = new EquipoRealDTO();
		e4.setCodigoEquipoReal("00004");
		e4.setNombre("Cajasol");
		e4.setSiglas("CAJ");
		//e4.setTemporadaInicio(temporadaInicio);
		//e4.setTemporadaFin(temporadaFin);			
		
		lEquipo.add(e1);
		lEquipo.add(e2);
		lEquipo.add(e3);
		lEquipo.add(e4);
		
		return lEquipo;
	}
	
	// OK	
	public boolean modificarEquipoCatalogo(EquipoRealDTO eDTO)throws Exception{
		
		// Se llama para modificar en base de datos
		System.out.println("Codigo: " + eDTO.getCodigoEquipoReal());
		System.out.println("Nombre: " + eDTO.getNombre());
		System.out.println("Siglas: " + eDTO.getSiglas());
		
		// AQUI SE DEBE REALIZAR LA LLAMADA A MYBATIS PARA UN UPDATE.
		
		// SE DEBE EVALUAR LA SALIDA DE LA MODIFICACION.
		
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
		
		return true;
	}
	
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								OPERACIONES DE JUGADOR								   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	
	public ArrayList<JugadorDTO> mostrarCatalogoJugadores()throws Exception{
		
		ArrayList<JugadorDTO> lJugadores = new ArrayList<JugadorDTO>();
		
		JugadorDTO j1 = new JugadorDTO();
		j1.setCodigoJugador("00001");
		j1.setActivo(true);
		j1.setNombre("Luis");
		j1.setApellido("Avila");
		j1.setPuesto(Constantes.BASE);
		j1.setAltura("192");
		j1.setPeso("90");
		j1.setNacionalidad(Constantes.ESP);
		//j1.setTemporadaInicio(temporadaInicio);
		//j1.setTemporadaFin(temporadaFin);
		

		JugadorDTO j2 = new JugadorDTO();
		j2.setCodigoJugador("00002");
		j2.setActivo(true);
		j2.setNombre("Charles");
		j2.setApellido("Fish");
		j2.setPuesto(Constantes.BASE);
		j2.setAltura("184");
		j2.setPeso("82");
		j2.setNacionalidad(Constantes.USA);
		//j2.setTemporadaInicio(temporadaInicio);
		//j2.setTemporadaFin(temporadaFin);
		
		
		JugadorDTO j3 = new JugadorDTO();
		j3.setCodigoJugador("00003");
		j3.setActivo(true);
		j3.setNombre("Javier");
		j3.setApellido("Diaz de Miguel");
		j3.setPuesto(Constantes.ALERO);
		j3.setAltura("201");
		j3.setPeso("99");
		j3.setNacionalidad(Constantes.ESP);
		//j3.setTemporadaInicio(temporadaInicio);
		//j3.setTemporadaFin(temporadaFin);
		
		lJugadores.add(j1);
		lJugadores.add(j2);
		lJugadores.add(j3);
		return lJugadores;		
	}
	
	public boolean eliminarJugadorCatalogo(JugadorDTO jDTO)throws Exception{
		//jDTO.getCodigo()
		return true;
	}
	
	public boolean insertarJugadorCatalogo(JugadorDTO jDTO)throws Exception{
		//jDTO.getCodigo()
		return true;
	}
	
	
	

	
	
}
