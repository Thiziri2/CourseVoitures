package radar;

import geometrie.Vecteur;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import circuit.Circuit;
import circuit.TerrainTools;
import voiture.Voiture;

public class RadarClassique implements Radar {
	private double[] faisceaux; 
	private Voiture voiture;
	private Circuit circuit;
	private final double  EPSILON=0.1;
	
	public RadarClassique( Voiture voiture, Circuit circuit,double[] faisceaux) {
		super();
		this.faisceaux = faisceaux;
		this.voiture = voiture;
		this.circuit = circuit;
	}
	private double calcScore(double angle){
		double cpt=0;
		Vecteur p=voiture.getPosition();
		Vecteur d=voiture.getDirection();
		d=d.rotation(angle);
		
		while ((TerrainTools.isRunnable(circuit.getTerrain(p)))&&(p.getX()<circuit.getMatrix().length)&&(p.getY()<circuit.getMatrix()[0].length)) {
			cpt++;
			p=p.addition(d.multiplication(EPSILON));
			//System.out.println(p);
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
		Vecteur p=null,d=null;
		BufferedImage im=TerrainTools.imageFromCircuit(circuit.getMatrix());
		
		for(int i=0;i<score.length;i++) {
			res[i]=score[i]*EPSILON;
			p=voiture.getPosition();
			d=voiture.getDirection();
			d=d.rotation(faisceaux[i]);
			
			for(int j=0;j<res[i];j++) {
				p=p.addition(d);
				im.setRGB((int)p.x,(int)p.y,Color.BLACK.getRGB());
			}
		}
		 try {
	            File outputfile = new File("saved.png");
	            ImageIO.write(im, "png", outputfile);
	         } catch (IOException e) {
	            System.out.println("Erreur lors de la sauvegarde");
	         }
		
		return res;
	}

	public int getBestIndex() {
		double[] s=scores();
		int max=0;
		int i=0;
			while(i<s.length){
				if(s[max]<s[i]) {
				max=i;}
				i++;
			}
		return max;
	}

	public double[] thetas() {
		return faisceaux ;
	}
	
	public void setFaisceaux(double[] faisceaux) {
		this.faisceaux = faisceaux;
	}
	public Voiture getVoiture() {
		return voiture;
	}
	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}
	
	public void SetVoiture(Voiture voiture) {
		this.voiture=voiture;
	}
	
	public int check() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Circuit getCir() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
