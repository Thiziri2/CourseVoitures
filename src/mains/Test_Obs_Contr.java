package mains;

import java.io.FileNotFoundException;

import observeurs.*;
import radar.*;
import voiture.Simulation;
import strategy.*;
import voiture.*;
import circuit.*;

public class Test_Obs_Contr {

	public static void main(String[] args) throws FileNotFoundException {
		String filename="1_safe";
		Circuit cir = CircuitFactoryFromFile.build(filename+".trk");
		Voiture v = VoitureFactory.build(cir);
		VoitureObserver vObs = new VoitureObserver(v);
		Controleur ctr = new Controleur(cir);
		ctr.add(vObs);
		double faisceau[] = {-Math.PI/12, -Math.PI/6, -Math.PI/3, 0, Math.PI/3, Math.PI/6, Math.PI/12};
		Radar radar = new RadarClassique(cir, v, faisceau);
		Strategy strat = new StrategyRadar(v, radar);
		Simulation sim = new Simulation(cir, v, strat);
		sim.add(ctr);
		
		sim.play();
		sim.saveIm(filename);
	}

}