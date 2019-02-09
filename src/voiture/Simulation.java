package voiture;

import circuit.Circuit;
import enums.Terrain;

public class Simulation {
	private Circuit circuit;
	private Voiture voiture;
	private Strategy strategy;
	public Simulation(Circuit c, Voiture v, Strategy s) {
		super();
		this.circuit = c;
		this.voiture = v;
		this.strategy = s;
	}
	public void play(){
		while(circuit.getTerrain(voiture.getPosition())==Terrain.Route){	
			voiture.drive(strategy.getCommande());
		}
		
	}
	public void playOneShot() throws VoitureException{
	Commande c = strategy.getCommande();
	// application
	voiture.drive(c);
	// MAJ Etat
	//state = updateState();
	}
}
