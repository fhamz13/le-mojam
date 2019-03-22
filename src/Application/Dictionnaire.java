package Application;

import java.util.ArrayList;

public class Dictionnaire {
	ArrayList<String> word = new ArrayList<String>();
	ArrayList<String> explination = new ArrayList<String>();
	ArrayList<String> meaning = new ArrayList<String>();
	
	public Dictionnaire(String mot){
		mot = enleverHarakat(mot);
		Requete recherche = new Requete(mot);
		this.word = recherche.getWord();
		this.explination = recherche.getExplination();
		this.meaning = recherche.getMeaning();
	}
	
	public String enleverHarakat(String mot){
		String[] harakat = {"\u064B","\u064C","\u064D","\u064E","\u064F","\u0650","\u0651",
				"\u0652"};
		for (int i=0; i<harakat.length; i++)
			mot = mot.replaceAll(harakat[i], "");
		return mot;
	}
	
	public String effacerHarakatFin(String mot){
		String hf = mot.substring(0, mot.length()-1);
		String s = new String();
		if (hf!="\u064E" || hf!="\u0651")
			s = mot.substring(0, mot.length()-2);
		return s;
	}
	
	public ArrayList<String> getWord(){
		/*String [] mot = new String[this.word.size()];
		for (int i=0; i<mot.length; i++)
			mot[i] = this.word.get(i);
		return mot;*/
		return this.word;
	}
	public ArrayList<String> getExplination(){
		/*String [] explication = new String[this.explination.size()];
		for (int i=0; i<explication.length; i++)
			explication[i] = this.explination.get(i);
		return explication;*/
		return this.explination;
	}
	public ArrayList<String> getMeaning(){
		/*String [] signification = new String[this.meaning.size()];
		for (int i=0; i<signification.length; i++)
			signification[i] = this.meaning.get(i);
		return signification;*/
		return this.meaning;
	}
}
