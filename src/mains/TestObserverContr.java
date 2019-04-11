package mains;

import java.io.FileNotFoundException;

import observeurs.Controleur;
import observeurs.VoitureObserver;
import radar.Radar;
import radar.RadarDijkstra;
import strategy.*;
import voiture.*;
import circuit.*;

public class TestObserverContr {

	public static void main(String[] args) throws FileNotFoundException {
		Circuit cir = CircuitFactoryFromFile.build("1_safe.trk");
		Voiture v = VoitureFactory.build(cir);
		VoitureObserver vObs = new VoitureObserver(v);
		Controleur ctr = new Controleur(cir);
		ctr.add(vObs);
		double faisceau[] = {-Math.PI/12, -Math.PI/6, -Math.PI/3, 0, Math.PI/3, Math.PI/6, Math.PI/12};
		Radar radar = new RadarDijkstra(cir, v, faisceau);
		Strategy strat = new StrategyRadar(v, radar);
		Simulation sim = new Simulation(cir,v, strat);
		sim.add(ctr);
		
		sim.play();
		TerrainTools.saveIm(ctr.getImage(), "test_Obs_ctr");
	}

}