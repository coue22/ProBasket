package com.personal.basket.servicios;


import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import com.personal.basket.dtos.*;

public interface ICatalogoServicios {
	
	/**
	 * Se muestra el menu que corresponda dependiendo del tipo de menu que se solicite.
	 * BBDD implicadas
	 * 				*TABLAS
	 * 					MENU: Se realiza un "select" para obtener todas las opciones de menu
	 * 							que tengan el tipo de menu de la entrada.
	 * @param idTipoMenu - Tipo de menu solicitado.
	 * @return - Devuelve un array con las opciones de menu (codigo y descripcion) para pintarlas
	 * 			 en pantalla.
	 * @throws Exception
	 */
	@Transactional(readOnly=true)
	public ArrayList<MenuDTO> mostrarMenu(String idTipoMenu)throws Exception;

	
	/**
	 * Se registra un usuario con un login, password y más datos siempre y cuando 
	 * el login este libre.
	 * BBDD implicadas
	 * 				*SECUENCUIAS
	 * 					SEQ_ECONOMIA: Para obtener un consecutivo que nos valdrá como codigo de economia.
	 * 				*TABLAS
	 * 					ECONOMIA: Se realiza un "insert" con la economia inicializada.
	 * 					USUARIO : Se realiza un "insert" con un usuario estandar.
	 * @param log - login
	 * @param pass - password
	 * @param email - email
	 * @param anoNac - año nacimiento.
	 * @param sex - sexo
	 * @param dineroInicial - Dinero con el que se comienza
	 * @return Devuelve si se ha podido o no registrar el usuario.
	 * @throws Exception
	 */
	@Transactional
	public boolean registrarse(String log, String pass, 
							   String email, String anoNac, 
							   String sex, double dineroInicial)throws Exception;
	
	/**
	 * Metodo encargado del logeo. Comprueba las credenciales del usuario y le permite o
	 * no el acceso y con un perfil determinado.
	 * BBDD implicadas
	 * 				*TABLAS
	 * 					USUARIO : Se realiza un "select" para ver si existe el usuario.
	 * @param log - login
	 * @param pass - Password
	 * @return Devuelve un usuario si se identifica de forma correcta el usuario 
	 * 			o null si no existe el usuario.
	 * @throws Exception
	 */
	@Transactional(readOnly=true)
	public UsuarioDTO loggearse(String log, String pass)throws Exception;

	

}
