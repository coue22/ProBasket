package com.personal.basket.bbdd;

public class MySQLConnection extends DBConnection {

	 public MySQLConnection(String usuario, String password, String driver, String urlconection) {
		 
		 super(	usuario, 
				password, 
				driver, 
				urlconection);
		 
		 //super(	"tu_Usuario", "tu_password", "org.gjt.mm.mysql.Driver", "jdbc:mysql://ip_de_Equipo/nombre_base_de_datos");
				 
	 }

	 
/*
 public static void main (String args[]){ // probamos realizar una consulta.
  MySQLConnection  ms = new MySQLConnection ();
  Integer id = null;
  ms.open();
  ResultSet rs = ms.ejecutaQuery(” TU CONSULTA “);
  try {
   if (rs.next()){
    id = rs.getInt(”id”);
   }
  } catch (SQLException e) {
   e.printStackTrace();
  }
  ms.close();
 }
}	 
 */
	 
}