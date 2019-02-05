package circuit;

import enums.Terrain;
import geometrie.Vecteur;

public class CircuitFactoryFromFile {
	  public final Vecteur dirDepart = new Vecteur(0,1);
	  public final Vecteur dirArrivee = new Vecteur(0,1);
	  
	  public static Circuit build(String filename){
		  Terrain[][] matrice=TerrainTools.lectureFichier(filename);
		  Circuit C=new CircuitImpl(matrice);
		  return C;
	  }
}
