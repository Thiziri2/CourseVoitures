package circuit;

import java.io.FileNotFoundException;

import enums.Terrain;
import geometrie.Vecteur;

public class CircuitFactoryFromFile{
	
	  public final static Vecteur dirDepart = new Vecteur(0,1);
	  public final static Vecteur dirArrivee = new Vecteur(0,1);
	  
	  public CircuitFactoryFromFile(String filename){
		  super();
		  }
	  public static Circuit build(String filename) throws FileNotFoundException{
		  Terrain[][] matrice=TerrainTools.lectureFichier(filename);
		  CircuitImpl C=new CircuitImpl(matrice);
		  C.setPtDepart(CircuitImpl.getStart(matrice));
		  return C;
	  }
}
