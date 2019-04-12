package mains;

import java.awt.image.BufferedImage;

import circuit.*;
import observeurs.Controleur;
import observeurs.Observeur;
import observeurs.VoitureObserver;
import radar.Radar;
import radar.RadarClassique;
import strategy.Strategy;
import strategy.StrategyRadar;
import voiture.*;

public class test_5 {
	public static void main() {
		String filename = "1_safe";
		Circuit cir = CircuitFactoryFromFile.build(filename+".trk");
		Voiture voiture = VoitureFactory.build(cir);
		double faisceau [] = {-Math.PI/12, -Math.PI/3, -Math.PI/6, 0, Math.PI/6, Math.PI/3, Math.PI/12};
		Radar radar = new RadarClassique(cir, voiture, faisceau);
		double [] tab=radar.scores();
		
		Strategy strat = new StrategyRadar(voiture, radar);

		Simulation simu = new Simulation(cir, voiture, strat);

		BufferedImage im=TerrainTools.imageFromCircuit(cir.getMatrix());
		Observeur vobs=new VoitureObserver(voiture);
		Controleur contr=new Controleur(cir);
		contr.add(vobs);
		vobs.print(im);
		simu.add(contr);
		simu.play();
		
		System.out.println("fait");
	}
	
}
