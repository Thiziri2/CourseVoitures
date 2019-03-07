package strategy;

import java.io.Serializable;

import voiture.Commande;

public interface Strategy extends Serializable {
	public Commande getCommande(); 
}
