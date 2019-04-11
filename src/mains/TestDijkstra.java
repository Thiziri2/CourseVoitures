package mains;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

import circuit.Circuit;
import circuit.CircuitFactoryFromFile;
import circuit.TerrainTools;
import dijkstra.Dijkstra;

public class TestDijkstra {
	public static void main(String [] args) throws FileNotFoundException{
		Circuit cir = CircuitFactoryFromFile.build("labymod.trk");
		Dijkstra dij = new Dijkstra(cir);
		System.out.println("Yo");
		dij.algo();
		
		BufferedImage im = new BufferedImage(cir.getWidth(), cir.getHeight(), BufferedImage.TYPE_INT_ARGB);
		System.out.println("Image ...");
		System.out.println("Working ...");
		for(int i=0; i<cir.getHeight(); i++){
			for(int j=0; j<cir.getWidth(); j++){
				if(dij.getDist(i, j) == Double.POSITIVE_INFINITY)
					im.setRGB(j, i, TerrainTools.terrainToRGB(cir.getTerrain(i, j)).getRGB());
				else{
					im.setRGB(j, i, new Color((int) dij.getDist(i, j)%255, 0 , 0).getRGB());
				}
			}
		}
		System.out.println("Yo");
		TerrainTools.saveIm(im, "Dijk");
		System.out.println("Done !");
	}
}
