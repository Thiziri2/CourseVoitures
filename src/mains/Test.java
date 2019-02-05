package mains;
import java.io.IOException;

import circuit.TerrainTools;
import enums.Terrain;

public class Test {

	public static void main(String[] args) throws IOException {

		System.out.println(TerrainTools.terrainToRGB(Terrain.Eau));
		System.out.println(TerrainTools.charFromTerrain(Terrain.Eau));
		System.out.println(TerrainTools.isRunnable(Terrain.Eau));
		//char x;
		Terrain[][] circuit1 = TerrainTools.lectureFichier("1_safe.trk");
		if(circuit1==null) {System.out.println("null");}
                  // test pull
 		/*for(int i=0;i<768;i++) {
			for(int j=0;j<1024;j++) {
				x=TerrainTools.charFromTerrain(circuit1[i][j]);
				System.out.print(x);
			}
			System.out.print('\n');
		}*/
 		TerrainTools.imageFromCircuit(circuit1);
	
	}

}

