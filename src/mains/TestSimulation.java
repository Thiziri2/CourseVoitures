package mains;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import circuit.*;
import voiture.*;

public class TestSimulation {
//j'ai juste recopi� le code du prof 
	public static void main(String[] args) {

		Circuit track=CircuitFactoryFromFile.build("1_safe.trk" ) ;
	    Voiture v=FerrariFactory.build(track);
		BufferedImage im =TerrainTools.imageFromCircuit(track.getMatrix());
		ArrayList<Commande> coms = new ArrayList<Commande>();
		for(int i =0;i<50;i++)coms.add(new Commande(1,0));
		for(int i =0;i<50; i++) coms.add (new Commande(1,0.1));
		for (int i =0; i<50; i++) coms.add (new Commande(1,0));
		for(int i=0;i<50; i++)coms.add (new Commande(1,-0.1));
		Strategy str = new StrategyListeCommande(coms);
		Simulation simu= new Simulation(track,v,str);
		simu.play(im) ;

	}

}
