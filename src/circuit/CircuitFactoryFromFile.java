package circuit;

import enums.Terrain;
import geometrie.Vecteur;

public class CircuitFactoryFromFile extends CircuitImpl{
	
	  public final static Vecteur dirDepart = new Vecteur(0,1);
	  public final static Vecteur dirArrivee = new Vecteur(0,1);
	  
	  public CircuitFactoryFromFile(String filename) {
		  super(TerrainTools.lectureFichier(filename),CircuitImpl.getStart(TerrainTools.lectureFichier(filename)),dirDepart,dirArrivee);
		  
		  }
		
	  
	  public static Circuit build(String filename){
		  Terrain[][] matrice=TerrainTools.lectureFichier(filename);
		  Circuit C=new CircuitImpl(matrice);
		  return C;
	  }
}
