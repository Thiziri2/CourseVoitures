package mains;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

import Dijkstra.*;
import circuit.*;

public class Test_dijk {
	public static void main(String [] args) throws FileNotFoundException{
		Circuit cir = CircuitFactoryFromFile.build("bond_safe.trk");
		Dijkstra dij = new Dijkstra(cir);
		
		dij.algo();
		
		BufferedImage im = new BufferedImage(cir.getWidth(), cir.getHeight(), BufferedImage.TYPE_INT_ARGB);
		System.out.println("Image ...");
		System.out.println("Working ...");
		
		for(int i=0; i<cir.getHeight(); i++){
			for(int j=0; j<cir.getWidth(); j++){
				if(dij.getDist(i, j) == Double.POSITIVE_INFINITY)
					im.setRGB(j, i, TerrainTools.terrainToRGB(cir.getTerrain(i, j)).getRGB());
				else{
					//System.out.println(dij.getDist(i, j)%255);
					im.setRGB(j, i, new Color((int) dij.getDist(i, j)%255, 0 , 0).getRGB());
				}
			}
		}
		
		/*for(int i=0;i<cir.getHeight();i++) {
			for(int j=0;j<cir.getWidth();j++) {
				System.out.print(dij.getDist(i, j));
			}
			System.out.println("");
		}*/
		System.out.println("Yo");
		TerrainTools.saveIm(im, "Dijk");
		System.out.println("Done !");
	}
}
