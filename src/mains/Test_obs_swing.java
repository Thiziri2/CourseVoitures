package mains;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import SWING.*;
import observeurs.*;
import radar.Radar;
import radar.RadarClassique;
import strategy.*;
import voiture.*;
import circuit.*;

public class Test_obs_swing {

	public static void main(String[] args) throws IOException {
		JFrame  fen =new JFrame();
		String filename="2_safe";
		Circuit cir = CircuitFactoryFromFile.build(filename+".trk");
		Voiture v = VoitureFactory.build(cir);
		VoitureObserver vObs = new VoitureObserver(v);
		IHMSwing ihm =new IHMSwing(cir);
		Controleur ctr = new Controleur(cir);
		ctr.add(vObs);
		double faisceau[] = {-Math.PI/12, -Math.PI/6, -Math.PI/3, 0, Math.PI/3, Math.PI/6, Math.PI/12};
		Radar radar = new RadarClassique(cir, v, faisceau);
		StrategyRadar strat = new StrategyRadar(v, radar);
		Simulation sim = new Simulation(cir, v, strat);
		sim.add(ctr);
		ihm.add(new CircuitObserver(cir));
		ihm.add(new VoitureObserver(v));
		ihm.add(new TrajectoireObserver(v));
		ihm.add(new RadarObserver (strat.getRadar(),v, cir));
		sim.add(ihm);  //possibilité de mettre plusieurs obs
		ihm.setPreferredSize(new Dimension(1024,768));
		fen.setContentPane(ihm);
		fen.setVisible(true);
		fen.pack();
		sim.play();
	}

}