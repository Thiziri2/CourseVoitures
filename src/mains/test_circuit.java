package mains;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import circuit.*;


public class test_circuit {

	public static void main(String[] args) throws FileNotFoundException {
		Circuit circuit= CircuitFactoryFromFile.build("3_safe.trk");
		BufferedImage im=TerrainTools.imageFromCircuit(circuit.getMatrix());
		try {
            File outputfile = new File("saved.png");
            ImageIO.write(im, "png", outputfile);
         } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde");
         }
	}
}
