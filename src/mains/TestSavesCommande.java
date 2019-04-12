package mains;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Dijkstra.*;
import voiture.*;
import circuit.*;
import radar.*;
import strategy.*;

public class TestSavesCommande {
	public static void main(String[] args) throws IOException{
		String filename = "3_safe.trk";
		Circuit cir = CircuitFactoryFromFile.build(filename);
		Voiture voiture = VoitureFactory.build(cir);
		//Faisceau et radar
		double [] faisceau = new double [150];
		double j = 0.25;
		for(int i=1; i<faisceau.length/2; i++){
			faisceau[i-1] = Math.PI/(i+j); //On evite de commencer par 0
			j=+0.25;
		}
		faisceau[(faisceau.length/2)] = 0;
		j=0.25;
		for(int i=1; i<faisceau.length/2; i++){
			faisceau[i-1+(faisceau.length/2)] = -Math.PI/(i+j); //On evite diviser par 0
			j=+0.25;
		}
		Radar radar = new RadarClassique(cir, voiture, faisceau);
		Strategy strat = new StrategyRadar(voiture, radar);
		Simulation sim = new Simulation(cir, voiture, strat);

//		ArrayList<Commande> liste = new ArrayList<>();
//		liste.add(new Commande(0.1, 0));
//		liste.add(new Commande(-0.1, 0.5));

		sim.play();
		StrategyTools.saveListeCommande(sim.getRecord(), "Commands_saved"+filename);
		sim.saveIm(filename);
//		ArrayList<Commande> liste2 = new ArrayList<>();
//		liste2 = StrategyTools.loadListeCommande(filename);

//		System.out.println(liste2);
	}
}
