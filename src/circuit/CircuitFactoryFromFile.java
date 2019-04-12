package circuit;

import geometrie.Vecteur;

public class CircuitFactoryFromFile{
	
	  public final static Vecteur dirDepart = new Vecteur(0,1);
	  public final static Vecteur dirArrivee = new Vecteur(0,1);
	  
	  public CircuitFactoryFromFile(String filename){
		  super();
		  }
	  //pour la position de depart je l'est int�gr� dans build circuit
	  public static Circuit build(String filename){
		  Terrain[][] matrice=TerrainTools.lectureFichier(filename);
		  CircuitImpl C=new CircuitImpl(matrice);
		  C.setPtDepart(CircuitImpl.getStart(matrice));
		  return C;
	  }
}
