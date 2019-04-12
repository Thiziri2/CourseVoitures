package mains;

import java.util.ArrayList;

import circuit.*;
import strategy.Strategy;
import strategy.StrategyListeCommande;
import voiture.*;

public class TestSimulation {
//j'ai juste recopi� le code du prof 
	public static void main(String[] args) {
		String filename="1_safe.trk";
		Circuit track=CircuitFactoryFromFile.build(filename) ;
	    Voiture v=FerrariFactory.build(track);
	    
		//BufferedImage im =TerrainTools.imageFromCircuit(track.getMatrix());
		ArrayList<Commande> coms = new ArrayList<Commande>();
		for(int i =0;i<50;i++)coms.add(new Commande(1,0));
		for(int i =0;i<50; i++) coms.add (new Commande(1,0.1));
		for (int i =0; i<50; i++) coms.add (new Commande(1,0));
		for(int i=0;i<50; i++)coms.add (new Commande(1,-0.1));
		Strategy str = new StrategyListeCommande(coms);
		Simulation simu= new Simulation(track,v,str);
		
		simu.play() ;
		simu.saveIm("simulation");
		Simulation.saveListeCommande(coms,"liste_commandes.txt");

	}

}
