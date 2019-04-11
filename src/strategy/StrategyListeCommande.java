package strategy;


import java.util.ArrayList;

import circuit.Circuit;
import voiture.Commande;
import voiture.Voiture;

public class StrategyListeCommande implements Strategy {

	private ArrayList<Commande>liste=new ArrayList<Commande>();
	private int index;
	
	public StrategyListeCommande(ArrayList<Commande>liste) {
		this.liste=liste ;
		index=0;
	}
	public Commande getCommande ( ) {
		if(index>=liste.size())
			return null;
		Commande c = liste.get(index);
		index++;
		return c;
		
	}
	public void init(Voiture v, Circuit cir) {
		v.drive(getCommande());
	}
	

}
