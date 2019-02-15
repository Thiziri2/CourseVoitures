package mains;

import circuit.Circuit;
import circuit.CircuitFactoryFromFile;
import radar.Radar;
import radar.RadarClassique;
import voiture.Voiture;
import voiture.VoitureFactory;

public class TestRadar {

	public static void main(String[] args) {
		
		double  pi=Math.PI;
		double [] faisceaux= {pi/3,pi/6,0,-pi/6,-pi/3,pi/4};
		String filename = "2_safe.trk";
		Circuit circuit=CircuitFactoryFromFile.build(filename);
		Voiture voiture= VoitureFactory.build(circuit);
		Radar radar=new RadarClassique(faisceaux,voiture,circuit);
		double [] score=radar.scores();
		for(int i=0;i<score.length;i++) {
			System.out.print(score[i]+" ~ ");//afficher sur une ligne les scores des faisceaux
		}
		System.out.println("\n");
		radar.distancesInPixels();
		System.out.println(radar.getBestIndex());
	
		
	}
}
