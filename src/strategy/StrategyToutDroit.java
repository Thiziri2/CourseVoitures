package strategy;

import voiture.Commande;

public class StrategyToutDroit implements Strategy {

	public Commande getCommande() {
		return new Commande(1,0) ;
	}

}
