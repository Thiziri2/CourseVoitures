package radar;

import geometrie.Vecteur;
import circuit.Circuit;
import enums.Terrain;
import voiture.Voiture;

public class RadarClassique implements Radar {
	private double[] faisceaux; 
	private Voiture voiture;
	private Circuit circuit;
	private final double  EPSILON=0.1;
	
	public RadarClassique(double[] faisceaux, Voiture voiture, Circuit circuit) {
		super();
		this.faisceaux = faisceaux;
		this.voiture = voiture;
		this.circuit = circuit;
	}
	private double calcScore(double angle){
		double cpt=0;
		Vecteur p=voiture.getPosition();
		Vecteur d=voiture.getDirection();
		Vecteur v=new Vecteur(0, 1);
		d=d.rotation(angle);
		//(circuit.getTerrain(p)!=Terrain.Herbe)
		while ((TerrainTools.isRunnable(circuit.getTerrain(p)))&&(p.getX()<circuit.getMatrix().length)&&(p.getY()<circuit.getMatrix()[0].length)) {
			cpt++;
			v=d.multiplication(EPSILON);
			p=p.addition(v);
			}
		return cpt;
	}
	public double[] scores() {
		double[] scores=new double[faisceaux.length];
		for(int i=0;i<faisceaux.length;i++){
			scores[i]=calcScore(faisceaux[i]);
		}
		return scores;
	}

	public double[] distancesInPixels() {
		double[] score=this.scores();
		double[] res=new double[score.length];
		for(int i=0;i<score.length;i++) {
			res[i]=score[i]/EPSILON;
		}
		return res;
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
		return faisceaux ;
	}

}
