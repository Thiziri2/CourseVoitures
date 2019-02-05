package voiture;

import geometrie.Vecteur;

public class VoitureImpl implements Voiture{
	// outils pour la gestion des limites de rotation en fonction de la vitesse
	private double[] tabVitesse;    
	private double[] tabTurn;   

	// capacités    
	private final double vmax, braquage;
	private final double alpha_c, alpha_f, beta_f;

	// état à l'instant t
	private double vitesse;
	private Vecteur position;
	private Vecteur direction;

	public void drive(Commande c){
        // VERIFICATIONS !!!
        // 1) Est ce que la rotation et l'accélération sont entre -1 et 1, sinon, throw new RuntimeException
        // 2) Est ce que la rotation demandée est compatible avec la vitesse actuelle, sinon, throw new RuntimeException
      
        // approche normale
        // 1.1) gestion du volant
        direction = direction.rotation(c.getTurn() * braquage); // modif de direction
        // Note: on remarque bien que l'on tourne d'un pourcentage des capacités de la voiture

        // 1.2) garanties, bornes...
        direction = direction.normalisation(); // renormalisation pour éviter les approximations

        // 2.1) gestion des frottements

        vitesse -= alpha_f;
        vitesse -= beta_f*vitesse;

        // 2.2) gestion de l'acceleration/freinage

        vitesse += c.getAcc() * alpha_c;

        vitesse = Math.max(0., vitesse); // pas de vitesse négative (= pas de marche arriere)
        vitesse = Math.min(vmax, vitesse); // pas de dépassement des capacités

        // 3) mise à jour de la position

        position = position.add(direction.fact(vitesse));

	public double getMaxTurn() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getVitesse() {
		return vitesse;
	}

	public Vecteur getPosition() {
		return position;
	}

	public Vecteur getDirection() {
		return direction;
	}

	public double getBraquage() {
		return braquage;
	}

}
