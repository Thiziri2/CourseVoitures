package strategy;
import geometrie.*;

import java.util.ArrayList;

import dijkstra.DijkstraPointParPoint;
import radar.Radar;
import radar.RadarDijkstra;
import strategy.Strategy;
import voiture.*;

public class StrategyPointParPoint implements Strategy {

	private ArrayList<Vecteur> listePoints;
	private ArrayList<Commande> listeCommandes;
	private double rayon=1;
	private Voiture voiture;
	private Radar radar;

	public StrategyPointParPoint(ArrayList<Vecteur> listePoints, Voiture voiture, Radar radar) {
		this.listePoints = listePoints;
		this.voiture = voiture;
		this.radar = radar;
	}

	public Commande getCommande() {
		Vecteur cible = listePoints.get(0);//On prend le plus proche
		if(pointAtteint(cible)){
			listePoints.remove(cible);
			cible = listePoints.get(0);//On prend le suivant
		}
		radar = new RadarDijkstra(radar.getCir(), voiture, radar.thetas(), new DijkstraPointParPoint(radar.getCir(), cible));
		int index = radar.getBestIndex();// 1 est le pas
		double [] angles = radar.thetas();
		double angle = angles[index] * voiture.getBraquage();

		Commande result;
		if(voiture.getVitesse() < 0.1)
			result = new Commande(0.15, angle);
		else
			result = new Commande(-1, angle);
		return result;
	}
	public boolean pointAtteint(Vecteur cible){
		if(voiture.getPosition().estAutour(cible, rayon)){
			return true;
		}
		return false;
	}
	public ArrayList<Commande> getListeCommande() {
		return this.listeCommandes;
	}
}

