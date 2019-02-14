package voiture;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import circuit.Circuit;
import enums.Terrain;

public class Simulation {
	private Circuit circuit;
	private Voiture voiture;
	private Strategy strategy;
	public Simulation(Circuit c, Voiture v, Strategy s) {
		super();
		this.circuit = c;
		this.voiture = v;
		this.strategy = s;
	}
	public void play(BufferedImage im){

		while(circuit.getTerrain(voiture.getPosition())==Terrain.Route){	
			
			voiture.drive(strategy.getCommande());
			
			System.out.println(voiture.getPosition());
			 int vx=(int) voiture.getPosition().x;
			 int vy=(int) voiture.getPosition().y;
			 im.setRGB(vx,vy,Color.BLACK.getRGB());
		}
		try {
            File outputfile = new File("saved.png");
            ImageIO.write(im, "png", outputfile);
         } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde");
         }
		
	}
	public void playOneShot() throws VoitureException{
	Commande c = strategy.getCommande();
	// application
	voiture.drive(c);
	// MAJ Etat
	//state = updateState();
	}
	
	/*public void Affichage(BufferedImage im){
		Graphics g = (Graphics) im.getGraphics();
		g.setColor(Color.BLACK);
	}*/
}
