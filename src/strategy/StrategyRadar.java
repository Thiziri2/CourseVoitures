package strategy;

import circuit.Circuit;
import radar.Radar;
import voiture.Commande;
import voiture.Voiture;

public class StrategyRadar implements Strategy{
	private Radar radar;
	private Voiture v;

	public StrategyRadar(Voiture v, Radar r){
		this.radar = r; this.v = v;
	}

	public Commande getCommande() {
		//Il faut savoir s'il faut tourner à gauche ou à droite, acc ou freiner
		//L'angle doit être entre -1 et 1 donc il faut diviser par le braquege
		int index = radar.getBestIndex();// 1 est le pas
		double [] angles = radar.thetas();
		double angle = angles[index] * v.getBraquage();

		Commande result;
//			if(Math.abs(angle)>0.5 && v.getVitesse()>0.5)
//				result = new Commande(-0.9, angle);
		if(v.getVitesse() < 0.2)
			result = new Commande(0.05, angle);
		else
			result = new Commande(-1, angle);

		return result;
	}
	public Radar getRadar(){
		return radar;
	}
}


