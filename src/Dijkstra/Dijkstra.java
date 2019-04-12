package Dijkstra;

import geometrie.Vecteur;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

import circuit.*;

public class Dijkstra {
	Circuit cir;
	double [][] distance;
	Comparator<Vecteur> comp;
	PriorityBlockingQueue <Vecteur> Q;
	//ArrayList<Vecteur> Q = new ArrayList<>();

	public Dijkstra(Circuit cir){
		this.cir = cir;
		distance = new double [cir.getHeight()] [cir.getWidth()]; //création tableau des distances
		//initialisation du tableau de distance
		for(int i=0; i<distance.length; i++){
			for(int j=0; j<distance[0].length; j++){
				if(cir.getTerrain(i, j) == Terrain.EndLine)//ligne d'arrivee
					distance[i][j] = 0;
				else{
					distance[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
		//PriorityBlockingQueue
		comp = new ComparatorDijk(distance);
		Q = new PriorityBlockingQueue<>(cir.getHeight()*cir.getWidth(), comp);
		for(int i=0; i<cir.getHeight(); i++){
			for(int j=0; j<cir.getWidth(); j++){
				if(cir.getTerrain(i, j) == Terrain.EndLine){//On rajoute la ligne d'arrivee
					Q.offer(new Vecteur(i, j));
				}
			}
		}
		
	}
	public void algo(){
		while(!(Q.isEmpty())){//tant qu'il reste des points à explorer
			//Extraction du point le plus proche de la ligne d'arrivée
			Vecteur tete = Q.poll();//la méthode poll(supprime le vecteur récupéré dans tet
			//System.out.println(tete.toString());

			if(tete != null){
//				System.out.println("La tete est : "+tete);
				neighborUpdate(tete);
			}
		}
	}
	private void neighborUpdate(Vecteur v){
		int x = (int) v.getX();
		int y = (int) v.getY();
		// Va regarder le terrain sur les 4 points cardinaux en gros
		for(int i =-1; i<= 1;i++){
			for(int j = -1; j<= 1;j++){
				Vecteur v2= new Vecteur(x+i,y+j);
				if(!TerrainTools.isRunnable(cir.getTerrain(v2))){//Si ce n'est pas roulable
					continue;
				}
				//if((distance[x][y]==0) && ((new Vecteur(i,j)).prodScal(cir.getDirectionArrivee()) > 0)){
					//continue;
				//} traite le cas de la ligne d'arrivée mais ne fonctionne pas ...S
				int weight = poids(i, j);
				if (distance[x + i][y + j] > distance[x][y] + weight) {
					Q.remove(v2);
					distance[x + i][y + j] = distance[x][y] + weight;
					Q.add(v2);
				}

			}
		}
	}
	private int poids(int i, int j) {
		// Si on est sur une case diagonale alors on mets 14
		if (((i == -1) && (j == -1)) || ((i == 1) && (j == -1))
				|| ((i == -1) && (j == 1)) || ((i == 1) && (j == 1))) {
			return 14;
		}

		return 10;
	}

	public double getDist(int i, int j){
		return distance[i][j];
	}
}

