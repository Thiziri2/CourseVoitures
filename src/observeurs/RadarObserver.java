package observeurs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import radar.Radar;
import circuit.*;
import voiture.*;
import geometrie.*;

public class RadarObserver implements ObserveurImage, ObserveurSWING {
	private Radar radar;
	private Voiture voiture;
	private Color color = Color.orange;
	private Circuit cir;
	
	public RadarObserver(Radar r, Voiture voiture, Circuit cir){
		this.radar = r;
		this.voiture = voiture;
		this.cir = cir;
	}
	
	public void print(BufferedImage im) {
		System.out.println("A remplir !");
	}
	public void print(Graphics g) {
		g.setColor(color);
		double angles [] = radar.thetas();
		for(double angle : angles){
			angle *= voiture.getBraquage();
			Vecteur v = voiture.getDirection().rotation(angle);//On fait la rotation
			Vecteur fin;
			//On determine la fin
			Vecteur p = voiture.getPosition();
			Vecteur d = voiture.getDirection().rotation(angle);
			while(TerrainTools.isRunnable(cir.getTerrain((int) p.getX(), (int) p.getY()))){
				p = p.addition(d.multiplication(1));//MAJ position
			}
			g.drawLine((int) voiture.getPosition().getY(),
                (int) voiture.getPosition().getX(),
                (int) (p.getY()),
                (int) (p.getX()));
		
		}
	}
	
}
