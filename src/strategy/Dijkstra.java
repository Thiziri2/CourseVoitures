package strategy;

import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;

import circuit.Circuit;
import circuit.TerrainTools;
import enums.Terrain;
import geometrie.Vecteur;

public class Dijkstra{
	private double[][] distance;
	private PriorityBlockingQueue<Vecteur> Q;
	Circuit circuit;
	ArrayList<Vecteur> arrivee;

	public Dijkstra(Circuit c) {
		circuit =c;
		distance = new double[c.getWidth()][c.getHeight()];
		Q = new PriorityBlockingQueue<Vecteur>(1000, new ComparatorDijk(distance));
		arrivee=c.getArrivees();
		for (int i = 0; i < distance.length; i++)
			for (int j = 0; j < distance[0].length; j++) {
				if(arrivee.contains(new Vecteur(i,j)))
					distance[i][j] =0.0;
				else
					distance[i][j] = Double.POSITIVE_INFINITY;
			}
	}

	public void init() {
		while(!Q.isEmpty())
			mAJ(Q.poll());
			
	}
	
	public void mAJ(Vecteur s) {
		int i,j;
		Terrain[][] T= circuit.getMatrix();
		for(i=-1;i<=1;i++) {
			for(j=-1;j<=1;j++) {
				if(TerrainTools.isRunnable(T[(int) (s.x+i)][(int) (s.y+j)])) {
					
				}
			}
		}
	}
	
	public double[][] getDistance(){
		return distance;
	}


	public PriorityBlockingQueue<Vecteur> getQ() {
		return Q;
	}

	public Circuit getCircuit() {
		return circuit;
	}


	public ArrayList<Vecteur> getArrivee() {
		return arrivee;
	}


}
