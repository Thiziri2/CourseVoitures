package mains;

import java.io.FileNotFoundException;

import circuit.*;
import radar.*;
import voiture.Simulation;
import strategy.*;
import voiture.*;
import Dijkstra.*;

public class TestRadar {
	public static void main(String[] args) throws FileNotFoundException{
		String filename = "1_safe";
		Circuit cir = CircuitFactoryFromFile.build(filename+".trk");
		Voiture voiture = VoitureFactory.build(cir);
		double faisceau [] = {-Math.PI/12, -Math.PI/3, -Math.PI/6, 0, Math.PI/6, Math.PI/3, Math.PI/12};
		Radar radar = new RadarDijk(cir, voiture, faisceau);
		double [] tab=radar.scores();
		for(int i=0;i<tab.length;i++) {
			System.out.println(tab[i]);
		}
		
		Strategy strat = new StrategyRadar(voiture, radar);

		System.out.println("YEP");
		Simulation sim = new Simulation(cir, voiture, strat);
		System.out.println("Doing the job");
		sim.play();
		sim.saveIm(filename);
		System.out.println("DONE");
	}
}
