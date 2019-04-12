package observeurs;

import geometrie.Vecteur;

import java.awt.Graphics;
import java.util.ArrayList;

import java.awt.Color;

import voiture.Voiture;
import SWING.ObserveurSWING;

public class TrajectoireObserver implements  ObserveurSWING {
	private Voiture voiture;
	private ArrayList<Vecteur> liste;
	
	public TrajectoireObserver(Voiture v) {
		voiture=v;
		liste = new ArrayList<Vecteur>();
	}

	@Override
	public void print(Graphics g) {
		g.setColor(Color.BLACK);
		liste.add(voiture.getPosition());
		for(Vecteur v:liste){
			g.fillRect((int)v.y,(int) v.x, 1, 1);
		}
	}


}
