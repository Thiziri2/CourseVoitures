package radar;

import geometrie.Vecteur;
import circuit.Circuit;
import enums.Terrain;
import voiture.Voiture;

public class RadarClassique implements Radar {
	private Vecteur[] faisceaux; 
	private Voiture voiture;
	private Circuit circuit;
	private final double  EPSILON=0.2;
	
	public RadarClassique(Vecteur[] faisceaux, Voiture voiture, Circuit circuit) {
		super();
		this.faisceaux = faisceaux;
		this.voiture = voiture;
		this.circuit = circuit;
	}
	private double calcScore(double angle){
		double cpt=0;
		Vecteur p=voiture.getPosition();
		Vecteur d=voiture.getDirection();
		d.rotation(angle);
		while ((circuit.getTerrain(p)!= Terrain.Herbe)&&(p.getX()<circuit.getMatrix().length)&&(p.getY()<circuit.getMatrix()[0].length)) {
			cpt++;
			p.addition(d.multiplication(EPSILON));
			}
		return cpt;
	}

	public double[] scores() {
		Vecteur d=voiture.getDirection();
		double[] scores=new double[faisceaux.length];
		for(int i=0;i<faisceaux.length;i++){
			scores[i]=calcScore(d.angle(faisceaux[i]));
		}
		return scores;
	}

	public double[] distancesInPixels() {
		//je ne comprends pas ce que peut faire cette fonction
		return null;
	}

	public int getBestIndex() {
		double[] s=scores();
		int max=0;
		int i=0;
			while(s[i]<s[max]){
				max=i;
				i++;
			}
		return max;
	}

	public double[] thetas() {
		double[] angles=new double[faisceaux.length];
			Vecteur d=voiture.getDirection();
			for(int i=0;i<faisceaux.length;i++){
				angles[i]=d.angle(faisceaux[i]);
			}
		return angles ;
	}

}
