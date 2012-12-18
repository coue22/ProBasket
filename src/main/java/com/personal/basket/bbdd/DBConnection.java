package com.personal.basket.bbdd;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;



public abstract class DBConnection {

	 private String usuario = null;
	 private String password = null;
	 private String driver = null;
	 private String urlconection = null;
	 private Connection conection = null;
	 private PreparedStatement preparar = null;
	 private ResultSet resultado = null;
	 

	 public DBConnection(String usuario, String password, String driver, String urlconection) {
		 super();
		 this.usuario = usuario;
		 this.password = password;
		 this.driver = driver;
		 this.urlconection = urlconection;
	  
	 }
	 
	 public Connection open() throws DBConneccionException{
		 try {
			 Class.forName(driver);
			 this.conection = DriverManager.getConnection(this.urlconection,this.usuario,this.password);
		 } catch (SQLException e) {
			 throw new DBConneccionException(e.getMessage());
		 } catch (ClassNotFoundException e) {
			 throw new DBConneccionException("Driver not Found: "+e.getMessage());
		 }
		 return this.conection;
	 }

	 /**
	  * Se utiliza para realizar los insert, update o delete a los regisros
	  */
	 public boolean ejecutaUpdate (String cmd)throws DBConneccionException{
		 if (cmd != null)
			 try {
				 this.preparar = this.conection.prepareStatement(cmd);
				 this.preparar.executeUpdate();
			 } catch (SQLException e) {
				 throw new DBConneccionException(e.getMessage());
			 }
		 else
			 throw new DBConneccionException("String SQL statement value is null");
		 
		 return true;
	 }
	 
	 
	 /**
	  * obtiene los registros de una consulta mediante un result set para que puedan
	  * ser manejados a discrecion del usuario.
	  */
	 public ResultSet ejecutaQuery (String cmd)throws DBConneccionException{
	  //System.out.println("——"+cmd);
		 if (cmd != null){
			 try {
				 this.preparar = this.conection.prepareStatement(cmd);
				 this.resultado = this.preparar.executeQuery(); 
			 } catch (SQLException e) {
				 throw new DBConneccionException(e.getMessage());
			 }  
		 }
		 return this.resultado;
	 }
	 
	 
	 /**
	  * Permite cerrar la conection SQL.
	  */
	 
	 public void close() throws DBConneccionException{
		 try {
			 if (this.conection != null){
				 this.conection.clearWarnings();
				 this.conection.close();
			 }
		 } catch (SQLException e) {
			 this.conection = null;
			 throw new DBConneccionException(e.getMessage());
		 }
	 }
	 
	 public String getPassword() {
		 return password;
	 }

	 public void setPassword(String password) {
		 this.password = password;
	 }

	 public String getUsuario() {
		 return usuario;
	 }

	 public void setUsuario(String usuario) {
		 this.usuario = usuario;
	 }

	 public String getDriver() {
		 return driver;
	 }

	 public void setDriver(String driver) {
		 this.driver = driver;
	 }

	 public Connection getconection() {
		 return conection;
	 }
	 public void setUrlconection(String urlconection) {
		 this.urlconection = urlconection;
	 }
	 protected PreparedStatement getPreparar() {
		 return preparar;
	 }
	 protected void setPreparar(PreparedStatement preparar) {
		 this.preparar = preparar;
	 }
	 

	 protected ResultSet getResultado() {
		 return resultado;
	 }
	 protected void setResultado(ResultSet resultado) {
		 this.resultado = resultado;
	 }

	}