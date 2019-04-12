package Dijkstra;

import java.util.ArrayList;

import geometrie.Vecteur;
import voiture.Voiture;
import circuit.*;
import radar.*;

public class RadarDijk implements Radar{
	private double [] faisceau;
	private double [] scores;
	private Voiture voiture;
	private Circuit circuit;
	private Dijkstra dijk;
	private double epsilon = 0.1;

	public RadarDijk(Circuit cir, Voiture v, double [] f){
		this(cir, v, f, new Dijkstra(cir));
	}
	public RadarDijk(Circuit cir, Voiture v, double [] f, Dijkstra dijk){
		this.voiture = v;
		this.circuit = cir;
		faisceau = f;
		this.dijk = dijk;
		dijk.algo();//Mise à jour des distances
		scores=scores(); //mise à jour des scores
	}

		
	private double check(double angle){
		Vecteur p = voiture.getPosition();
		Vecteur d = voiture.getDirection().rotation(angle);
		double min = dijk.getDist((int) p.getX(), (int) p.getY());

		while((TerrainTools.isRunnable(circuit.getTerrain(p)))&&(p.getX()<circuit.getMatrix().length)&&(p.getY()<circuit.getMatrix()[0].length)){
			if(circuit.getTerrain(p) == Terrain.EndLine &&
					d.prodScal(circuit.getDirectionArrivee())<0){
				return Double.POSITIVE_INFINITY; // INFINI donne le pire score possible.
			}
			p = p.addition(d.multiplication(epsilon));//MAJ position
			if(min > dijk.getDist((int)p.getX(), (int)p.getY())){
				min= dijk.getDist((int)p.getX(), (int)p.getY());
				
			}
		}
		return min;
	}
	
public double[] scores() {
		double[] scores=new double[faisceau.length];
		for(int i=0;i<faisceau.length;i++){
			scores[i]=check(faisceau[i]);
			System.out.println(scores[i]);
		}
		return scores;
	}

	public double[] distancesInPixels() {
		return null;
	}

	public int getBestIndex(){
//		ArrayList<Object> tabBestIndex = new ArrayList<Object>();
		//double minValue=Double.POSITIVE_INFINITY;
		double max=0;
		// Le plus petit des min
		int bestIndex = 0;
		for(int j=0; j<scores.length; j++){
			if(scores[j]>max){
				max = scores[j];
				bestIndex = j;
			}
		}
		return bestIndex;
	}

	public double[] thetas() {
		return faisceau;
	}

	public Circuit getCir() {
		return circuit;
	}

}
