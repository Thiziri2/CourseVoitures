package strategy;

import voiture.Commande;

public class StrategyToutDroit implements Strategy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Commande getCommande() {
		return new Commande(1,0) ;
	}

}
