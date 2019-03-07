package voiture;

import geometrie.Vecteur;
import java.lang.Math;


public class VoitureImpl implements Voiture {
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

	public VoitureImpl(double[] tabVitesse,double[] tabTurn,double vmax,double braquage,double alpha_c,double alpha_f,double beta_f,double vitesse,Vecteur position,Vecteur direction) {
		this.tabVitesse=tabVitesse;
		this.tabTurn=tabTurn;
		this.vmax=vmax;
		this.braquage=braquage;
		this.alpha_c=alpha_c;
		this.alpha_f=alpha_f;
		this.beta_f=beta_f;
		this.position=position;
		this.vitesse=vitesse;
		this.direction=direction;
	}
	
	
	public void drive(Commande c) throws RuntimeException{
        // VERIFICATIONS !!!
        // 1) Est ce que la rotation et l'accélération sont entre -1 et 1, sinon, throw new RuntimeException
      if(c.getAcc()<(-1) || c.getAcc()>1 || (c.getTurn()<(-1)||c.getTurn()>1)) {
    	  throw new RuntimeException();
      }
      
      // 2) Est ce que la rotation demandée est compatible avec la vitesse actuelle, sinon, throw new RuntimeException
      /*if((c.getTurn()<-getMaxTurn())||(c.getTurn()>getMaxTurn())) {
      	  System.out.println(c.getTurn());
    	  throw new RuntimeException();
      }*/
      
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
        position = position.addition(direction.multiplication(vitesse));
	}
	
	
	/*private  void driveSansDerapage (Commande c)  {
	//  approche normale
	//  1)gestion du  volant
	direction=direction.rotation(braquage * c.getTurn());
	//  2.1) gestion des frottements(négatif)
	vitesse-= alpha_f;
	vitesse-=vitesse * beta_f;
	vitesse+= alpha_c*c.getAcc();
	//  2.3) garanties , bornes... approximations numériques
	// ramener  le vecteur  de direction à un vecteur unitaire
	direction.normalisation();
	vitesse = Math.max(0. ,vitesse);  // pas de vitesse négative
	vitesse = Math.min(vmax,vitesse);
	//  3)  mise  à  jour  de  la  position
		position=position.addition(direction.multiplication(vitesse));
	}*/
	
	
	public double getMaxTurn() {
		int i=0;
		while(vitesse>= tabVitesse[i]*vmax) {
			i++;
		}
		return tabTurn[i];
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
	
	public String getDerapage() {
		return null;  //~~~~~~~~
	}

}
