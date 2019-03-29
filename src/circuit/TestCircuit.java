package circuit;

import java.io.FileNotFoundException;
import enums.Terrain;

public class TestCircuit {
	public static void main(String[] args) throws FileNotFoundException {
        Terrain[][] circuit1 = TerrainTools.lectureFichier("1_safe.trk");
		if(circuit1==null) {System.out.println("null");}
		//juste pour voir notre circuit
 		TerrainTools.imageFromCircuit(circuit1);
 		TerrainTools.saveIm(TerrainTools.imageFromCircuit(circuit1), "premier_circuit");
}
}
