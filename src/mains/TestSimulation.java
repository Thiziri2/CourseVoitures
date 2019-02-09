package mains;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import circuit.Circuit;
import circuit.CircuitFactoryFromFile;
import circuit.TerrainTools;
import voiture.Commande;
import voiture.Simulation;
import voiture.Strategy;
import voiture.StrategyListeCommande;
import voiture.Voiture;
import voiture.VoitureFactory;

public class TestSimulation {
//j'ai juste recopié le code du prof 
	public static void main(String[] args) {
		CircuitFactory cfac= new CircuitFactoryFromFile("safe_1");
		Circuit track=cfac.build( ) ;
		VoitureFactory vFac = new FerrariFactory(track);
	    Voiture v=vFac.build(track);
		BufferedImage im =TerrainTools.imageFromCircuit(track.getMatrix());
		ArrayList<Commande> coms = new ArrayList<Commande>();
		for(int i =0;i<50;i++)coms.add(new Commande(1,0));
		for(int i =0;i<50; i++) coms.add (new Commande(1,0.1));
		for (int i =0; i<50; i++) coms.add (new Commande(1,0));
		for(int i=0;i<50; i++)coms.add (new Commande(1,-0.1));
		Strategy str = new StrategyListeCommande(coms);
		Simulation simu= new Simulation(track,v,str);
		simu.play() ;

	}

}
