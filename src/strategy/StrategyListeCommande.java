package strategy;


import java.util.ArrayList;

import voiture.Commande;

public class StrategyListeCommande implements Strategy {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Commande>liste;
	private int index ;
	
	public StrategyListeCommande(ArrayList<Commande>liste) {
	this.liste=new ArrayList<Commande>();
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
	

}
