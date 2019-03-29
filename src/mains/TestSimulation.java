package mains;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import circuit.*;
import strategy.Strategy;
import strategy.StrategyListeCommande;
import voiture.*;

public class TestSimulation {
	public static void main(String[] args) throws FileNotFoundException {

		Circuit track=CircuitFactoryFromFile.build("1_safe.trk" ) ;
	    Voiture v=VoitureFactory.build(track);
	
		ArrayList<Commande> coms = new ArrayList<Commande>();
		for(int i =0;i<50;i++)coms.add(new Commande(1,0));
		for(int i =0;i<50; i++) coms.add (new Commande(1,0.1));
		for (int i =0; i<50; i++) coms.add (new Commande(1,0));
		for(int i=0;i<50; i++)coms.add (new Commande(1,-0.1));
		Strategy str = new StrategyListeCommande(coms);
		if(str==null){System.out.println("str est null");}
		Simulation simu= new Simulation(track,v,str);
		if(simu==null){System.out.println("simu est null");}
		simu.play() ;
		
		//Simulation.saveListeCommande(coms,"liste_commandes.txt");

	}

}
