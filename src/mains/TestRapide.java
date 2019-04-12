package mains;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

import voiture.Simulation;
import strategy.*;
import voiture.*;
import circuit.*;
import radar.*;
import Dijkstra.*;

public class TestRapide {

	public static void main(String [] args) throws FileNotFoundException{
		String filename="2_safe.trk";
		Circuit cir = CircuitFactoryFromFile.build(filename);
//		Circuit cir = CircuitFactory.build("track2/1_safe.trk");
		double [] faisceau = new double[200];
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
		Voiture v = VoitureFactory.build(cir);
		Radar r = new RadarDijk(cir, v, faisceau);
		Strategy str = new StrategyRadar(v, r);
		Simulation sim = new Simulation(cir, v, str);

		System.out.println("Working ...");
		sim.play();
		sim.saveIm(filename);
		System.out.println("Done !");
	}
}
