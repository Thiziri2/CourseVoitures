package voiture;


import java.util.ArrayList;

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
		while (index<liste.size()){
			index++;
			return liste.get(index);
		}
		return null;
	}
	

}
