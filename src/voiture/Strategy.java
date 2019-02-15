package voiture;

import java.io.Serializable;

public interface Strategy extends Serializable {
	public Commande getCommande(); 
}
