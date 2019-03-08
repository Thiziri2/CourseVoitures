package mains;

import java.util.ArrayList;

import circuit.Circuit;
import circuit.CircuitFactoryFromFile;
import observeurs.Controleur;
import observeurs.VoitureObserver;
import strategy.Strategy;
import strategy.StrategyListeCommande;
import voiture.Commande;
import voiture.FerrariFactory;
import voiture.Simulation;
import voiture.Voiture;
import voiture.VoitureFactory;

public class MVC_test {
	public static void main(String[] args) {
CircuitFactoryFromFile cfac=new CircuitFactoryFromFile("1_safe.trk");
Circuit track=cfac.build("1_safe.trk");
VoitureFactory vfac= new FerrariFactory();
Voiture v=vfac.build(track);
Controleur ihm= new Controleur(track);
ihm.add(new VoitureObserver(v));
ArrayList<Commande> coms = new ArrayList<Commande>();
for(int i =0;i<50;i++)coms.add(new Commande(1,0));
for(int i =0;i<50; i++) coms.add (new Commande(1,0.1));
for(int i =0; i<50; i++) coms.add (new Commande(1,0));
for(int i=0;i<50; i++)coms.add (new Commande(1,-0.1));
Simulation.saveListeCommande(coms,"liste_commandes.txt");
Strategy str = new StrategyListeCommande(coms);
Simulation simu= new Simulation(track,v,str);
simu.add(ihm);
simu.play();
}
}
