package mains;

import java.util.ArrayList;

import circuit.*;
import strategy.Strategy;
import strategy.StrategyListeCommande;
import voiture.*;

public class TestSimulation {
	public static void main(String[] args) {

		Circuit track=CircuitFactoryFromFile.build("1_safe.trk" ) ;
	    Voiture v=FerrariFactory.build(track);
	    
		ArrayList<Commande> coms = new ArrayList<Commande>();
		for(int i =0;i<50;i++)coms.add(new Commande(1,0));
		for(int i =0;i<50; i++) coms.add (new Commande(1,0.1));
		for (int i =0; i<50; i++) coms.add (new Commande(1,0));
		for(int i=0;i<50; i++)coms.add (new Commande(1,-0.1));
		Strategy str = new StrategyListeCommande(coms);
		Simulation simu= new Simulation(track,v,str);
		simu.play() ;
		
		Simulation.saveListeCommande(coms,"liste_commandes.txt");

	}

}
