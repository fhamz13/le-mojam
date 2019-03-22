package Application;

import java.sql.*;

import javax.swing.JOptionPane;

public class Connexion {
	public static Connection Konnexion(){
	   Connection c = null;
	   try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite::resource:Application/dict");
	   }
	   catch (ClassNotFoundException ex){
			JOptionPane.showMessageDialog(null, "Classe introuvable !" /*+ ex.getMessage()*/);
	   }
	   catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Connection impossible !" /*+ ex.getMessage()*/);
	   }
	   return c;
	}
}
