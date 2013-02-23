package com.personal.basket.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.personal.basket.HomeController;
import com.personal.basket.ctes.Ctes;
import com.personal.basket.dtos.JugadorRealDTO;

public class LecturaFicheroJugadores {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// Devuelve codigo#apellido#nombre
	private String getCodigoNombreApellidos(String linea){
		
		// De una entrada parecida a la que esta en la linea de abajo.
		//<td class="beige"><a href="jugador.php?id=B3A">Mainoldi, Leo</a></td>
		
		//System.out.println ("Linea    :"+linea);
		String lineas[] = linea.split("id=");
		//System.out.println ("Linea    :"+lineas[1]);
		
		// A partir de aqui tendría lo que se puede ver abajo.
		// B3A">Mainoldi, Leo</a></td>
		String sublineas[] = lineas[1].split("\">");
		
		// Recupero el codigo de jugador...
		//System.out.println("codigo: " + sublineas[0]);		
		String codigoJugador = sublineas[0];

		
		// A partir de aqui tendría lo que se puede ver abajo. sublineas[1]
		// Mainoldi, Leo</a></td>		
		//System.out.println("resto: " + sublineas[1]);
		String sublineas1[] = sublineas[1].split("</a>");
		//System.out.println("Apellido, nombre: " + sublineas1[0]);
		
		// A partir de aqui tendría lo que se puede ver abajo. sublineas1[0]
		// Mainoldi, Leo	
		String apellido = sublineas1[0].substring(0, sublineas1[0].indexOf(","));
		//System.out.println("ape:" + apellido);
		
		String nombre = sublineas1[0].substring(sublineas1[0].indexOf(",")+2);
		//System.out.println("nombre:" + nombre);
		
		return codigoJugador+"#"+apellido+"#"+nombre;
	}		
	
	// Puede devolver B, E, A ,F ,P
	private String getPosicion(String linea){

		// De una entrada parecida a la que esta en la linea de abajo.
		//<td class="blanco">F</td>
		// Se desea obtener en este ejemplo la letra "F".
		
		//System.out.println ("Linea    :"+linea);
		
		String lineas[] = linea.split("\">");
		//System.out.println ("Linea    :"+lineas[0]);
		//System.out.println ("Linea    :"+lineas[1]);
		//System.out.println ("Posicion:"+lineas[1].substring(0, 1));
		return lineas[1].substring(0, 1);
		
	}
	
	// Devuelve la nacionalidad.
	private String getNacionalidad(String linea){

		// De una entrada parecida a la que esta en la linea de abajo.
		//<td class="blanco">ITA</td>
		// Se desea obtener en este ejemplo la letra "ITA".
		
		//System.out.println ("Linea    :"+linea);
		
		String lineas[] = linea.split("\">");
		//System.out.println ("Linea    :"+lineas[0]);
		//System.out.println ("Linea    :"+lineas[1]);
		
		
		String sublineas[] = lineas[1].split("</");

		return sublineas[0];
		
	}	
	
	// Devuelve la licencia.
	private String getLicencia(String linea){
		// De una entrada parecida a la que esta en la linea de abajo.
		//<td class="gris">JFL</td>
		// Se desea obtener en este ejemplo la letra "JFL".
		
		//System.out.println ("Linea    :"+linea);
		
		String lineas[] = linea.split("\">");
		//System.out.println ("Linea    :"+lineas[0]);
		//System.out.println ("Linea    :"+lineas[1]);
		
		
		String sublineas[] = lineas[1].split("</");
//System.out.println("lic:" + sublineas[0]);
		return sublineas[0];
	}
	
	// Devuelve la altura.
	private String getAltura(String linea){
		// De una entrada parecida a la que esta en la linea de abajo.
		//<td class="gris">2.02</td>
		// Se desea obtener en este ejemplo la letra "2.02".
		
		//System.out.println ("Linea    :"+linea);
		
		String lineas[] = linea.split("\">");
		//System.out.println ("Linea    :"+lineas[0]);
		//System.out.println ("Linea    :"+lineas[1]);
		
		
		String sublineas[] = lineas[1].split("</");
//System.out.println("alt:" +sublineas[0]);
		return sublineas[0];
	}	
	
	// Devuelve la nacionalidad.
	private double getPrecio(String linea){

		// De una entrada parecida a la que esta en la linea de abajo.
		//<td class="blanco">325000</td>
		// Se desea obtener en este ejemplo la letra "325000".
		
		//System.out.println ("Linea    :"+linea);
		
		String lineas[] = linea.split("\">");
		//System.out.println ("Linea    :"+lineas[0]);
		//System.out.println ("Linea    :"+lineas[1]);
		
		
		String sublineas[] = lineas[1].split("</");

		
		return Double.parseDouble(sublineas[0]);
		
	}		
	
	public List<JugadorRealDTO> cargarJugadores (String nbFichero, String codEquipoReal) throws Exception{
			
		
		List<JugadorRealDTO> lJugador = new ArrayList<JugadorRealDTO>();
		

		java.net.URL url = this.getClass().getClassLoader().getResource("jugadores/" + nbFichero);
		InputStream is =  null;
		try {
			is = url.openStream();
			
			//Read the file and do stuff
			BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
            String strLinea;
            int numeroLinea=0;
            
        	String datosCodigoNombreApellidos = "";
        	String posicion = "";
        	String nacionalidad = "";
        	String licencia ="";
        	String altura = "";
        	double precioCompra = 0.0;
        	
            // Leer el archivo linea por linea
            while ((strLinea = buffer.readLine()) != null)   {
            	

            			
            	numeroLinea++;
            	int moduloLinea = numeroLinea%Ctes.FILAS_JUGADOR_FICHERO;
            	
            	switch(moduloLinea){
            	
            		// Si ocurre esto es el tr de inicio y no tiene interes
            		case 1: //System.out.println ("ivan111:"+strLinea);
            				break;
            				
            		// Si ocurre esto es el dorsal y no nos interesa
            		case 2: //System.out.println ("ivan222:"+strLinea);
            				break;            	
            				
            		// Nos interesa porque esta el codigo, nombre y apellido del jugador 
            		case 3: datosCodigoNombreApellidos = getCodigoNombreApellidos(strLinea);
            					//System.out.println("datosCodigoNombreApellidos:" + datosCodigoNombreApellidos);
            				break;    
            				
	        		// Nos interesa porque esta la posicion
	        		case 4: posicion = getPosicion(strLinea);
	        				break;              				
                    				
	        		// Nos interesa porque esta la nacionalidad
	        		case 5: nacionalidad = getNacionalidad(strLinea);
	        				break;       	        				
	
	        		// NO interesa por el momento.
	        		case 6: licencia = getLicencia(strLinea);
	        				break;       	        	
	        				
	        		case 7: altura = getAltura(strLinea);
    					break;       
    					
	        		case 8: 
    					break;
    					
	        		case 9:  precioCompra = getPrecio(strLinea);
    					break;      					
	        				
	    	        				
            		// Si ocurre esto es el tr de final
            		case 0: //System.out.println ("ivan000:"+strLinea);
            				JugadorRealDTO jDTO = new JugadorRealDTO();
            				
            				String cadena[]= datosCodigoNombreApellidos.split("#");
            				jDTO.setCodigoJugador(cadena[0]);
            				jDTO.setApellidos(cadena[1]);
            				jDTO.setNombre(cadena[2]);
            				jDTO.setPeso("");
            				if (posicion.equals("F"))
            					jDTO.setPuesto("P");
            				else if (posicion.equals("A"))
            					jDTO.setPuesto("E");
            				else
            					jDTO.setPuesto(posicion);
            				
            				jDTO.setActivo(Ctes.SI);
            				jDTO.setCodigoNacionalidad(nacionalidad);
            				jDTO.setAltura(altura);
            				jDTO.setCodigoEquipoReal(codEquipoReal);
            				jDTO.setPrecio(precioCompra);
            				
            				lJugador.add(jDTO);
            				
                    	 	datosCodigoNombreApellidos = "";
                    	 	posicion = "";
                    	 	nacionalidad = "";
                    	 	licencia ="";
                    		altura = "";
                    		precioCompra = 0.0;
            				break;
            				
            		default://System.out.println (strLinea);
            				break;
            	}
            	
            	
            }
            
            
			
		} catch(Exception e){
			//System.out.println("Error: " + e.getCause() + "-->" + e.getMessage() + "-->" + e.getStackTrace() + ". Es posible que el fichero no exista.");
			logger.error(e + ". Es posible que el fichero no exista.");
			throw new Exception(e + ". Es posible que el fichero no exista.");
			
		}finally{
			try {
				is.close();
			} catch (Exception e) {
				//System.out.println("Error1: " + e.getCause() + "-->" + e.getMessage() + "-->" + e.getStackTrace());
				logger.error(e + ". Es posible que el fichero no exista.");
				throw new Exception(e + ". Es posible que el fichero no exista."); 
			}
			
		}
	
		return lJugador;
	}
		
	
}
