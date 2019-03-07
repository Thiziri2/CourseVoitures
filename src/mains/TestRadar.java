package mains;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import circuit.Circuit;
import circuit.CircuitFactoryFromFile;
import circuit.TerrainTools;
import geometrie.Vecteur;
import radar.Radar;
import radar.RadarClassique;
import radar.StrategyRadar;
import voiture.Voiture;
import voiture.VoitureFactory;

public class TestRadar {

	public static void main(String[] args) throws IOException {
		
		double  pi=Math.PI;
		double [] faisceaux= {pi/3,pi/6,0,-pi/6,-pi/3,pi/4};
		String filename = "1_safe.trk";
		Circuit circuit=CircuitFactoryFromFile.build(filename);
		Voiture voiture= VoitureFactory.build(circuit);
		Radar radar=new RadarClassique(faisceaux,voiture,circuit);
		StrategyRadar rad=new StrategyRadar(radar, faisceaux, circuit, voiture);
		BufferedImage im=TerrainTools.imageFromCircuit(circuit.getMatrix());
		Vecteur v=voiture.getPosition();
		//double [] score=radar.scores();
		/*for(int i=0;i<score.length;i++) {
			System.out.print(score[i]+" ~ ");//afficher sur une ligne les scores des faisceaux
		}
		System.out.println("\n");*/
		//radar.distancesInPixels();
		//System.out.println(radar.getBestIndex());
		System.out.println(voiture.getPosition());
		
		for(int i=0;i<10000;i++) { 
			
			im.setRGB((int)v.x,(int)v.y,Color.black.getRGB() );
			radar.SetVoiture(voiture);
			rad.SetRadar(radar);
			voiture.drive(rad.getCommande());
			v=voiture.getPosition();
			
			}
		ImageIO.write(im, "png", new File("test.png"));
		
		System.out.println(voiture.getPosition());
		
		for(int i=0;i<10000;i++) { 
			//System.out.println("vu");
			im.setRGB((int)v.x,(int)v.y,Color.black.getRGB() );
			radar.SetVoiture(voiture);
			rad.SetRadar(radar);
			voiture.drive(rad.getCommande());//System.out.println(rad.getCommande().getAcc());
			v=voiture.getPosition();
			
			}
		
		System.out.println(voiture.getPosition());
		radar.distancesInPixels();
	}
}
