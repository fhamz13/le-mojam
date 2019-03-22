package Application;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Requete {
	private static Connection c = Connexion.Konnexion();
	private static ArrayList<String> word = new ArrayList<String>();
	private static ArrayList<String> explination = new ArrayList<String>();
	private static ArrayList<String> meaning = new ArrayList<String>();
	public Requete(String mot){
		try {
			Statement state = c.createStatement();
			mot = mot.replaceAll("'", "''");
			//String requete1 = "SELECT DISTINCT wordkey FROM Keys where searchwordkey='"+mot+"'";
			String requete1 = "SELECT DISTINCT wordkey FROM Keys where searchwordkey like '"+mot+"%'";
			if (mot.length() ==1)
				requete1 = "SELECT DISTINCT wordkey FROM Keys where searchwordkey = '"+mot+"'";
			ResultSet motVocalise = state.executeQuery(requete1);
			ArrayList<String> tabMotVocalise = new ArrayList<String>();
			while(motVocalise.next()){
				tabMotVocalise.add(motVocalise.getString(1).replaceAll("'", "''"));
			}
			if (tabMotVocalise.isEmpty()){
				requete1 = "SELECT DISTINCT word FROM wordstable where word = '"+mot+"'";
				motVocalise = state.executeQuery(requete1);
				tabMotVocalise = new ArrayList<String>();
				while(motVocalise.next()){
					tabMotVocalise.add(motVocalise.getString(1).replaceAll("'", "''"));
				}
			}

			for (int i=0; i<tabMotVocalise.size();i++){
				String requete2 = "select word,explination,meaning from wordstable where word='"
						+tabMotVocalise.get(i)+"'";
				ResultSet resultSet = state.executeQuery(requete2);
				while(resultSet.next()){
						word.add(resultSet.getString(1));
						explination.add(resultSet.getString(2));
						meaning.add(resultSet.getString(3));
				}
			}
			state.close();
		}
		catch (SQLException e){
			JOptionPane.showMessageDialog(null, "Problème rencontré :" + e.getMessage(),
			"Résultat",JOptionPane.ERROR_MESSAGE);
			/*JOptionPane.showMessageDialog(null, "Mot non trouvé dans le dictionnaire !", 
					"Mot introuvable", JOptionPane.INFORMATION_MESSAGE );*/
		}
	}
	public ArrayList<String> getWord() {
		return word;
	}
	public ArrayList<String> getExplination() {
		return explination;
	}
	public ArrayList<String> getMeaning() {
		return meaning;
	}
}
