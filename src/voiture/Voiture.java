package voiture;

import geometrie.Vecteur;

public interface Voiture {
	 // pour le pilotage
    public void drive(Commande c);
    public double getMaxTurn(); // cf juste après

    // pour l'observation
    public double getVitesse();
    // retourne la position
	public Vecteur getPosition();
    public Vecteur getDirection();
    public double getBraquage();
}
