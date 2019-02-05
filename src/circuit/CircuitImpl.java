package circuit;

import java.util.ArrayList;

import enums.Terrain;
import geometrie.Vecteur;

public class CircuitImpl implements Circuit{
	private Terrain[][] matrice;
	private Vecteur ptDepart;
	private Vecteur sensDepart;
	private Vecteur sensArrivee;
	
	public CircuitImpl(Terrain[][] T, Vecteur ptDepart,Vecteur sensDepart, Vecteur sensArrivee){
		matrice=T;
		this.ptDepart=ptDepart;
		this.sensDepart=sensDepart;
		this.sensArrivee=sensArrivee;
	}
	public CircuitImpl(Terrain[][] T){
		matrice=T;
	}
	public Terrain getTerrain(int i, int j) {
		return matrice[i][j];
	}
	public Terrain getTerrain(Vecteur v) {
		return matrice[(int)v.getX()][(int)v.getY()];
		//pas trop compris 
	}
	public Vecteur getPointDepart() {
		return ptDepart;
	}
	public Vecteur getDirectionDepart() {
		return sensDepart;
	}
	public Vecteur getDirectionArrivee() {
		return sensArrivee;
	}
	public int getWidth() {//largeur=nbcolonne
		return matrice[0].length;
	}
	public int getHeight() {
		return matrice.length;
	}
	public ArrayList<Vecteur> getArrivees() {
		//je ne sais pas que doit faire cette fonction
		ArrayList<Vecteur> lv=new ArrayList<Vecteur>();
		//lv.add(ptDepart);
		//lv.add(sensDepart);
		lv.add(sensArrivee);
		return lv;
	}
	public double getDist(int i, int j) {
		Vecteur v=new Vecteur(i,j);
		return v.norme();
	}

}
