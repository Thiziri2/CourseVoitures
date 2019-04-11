package mains;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

import circuit.*;
import observeurs.Controleur;
import observeurs.Observeur;
import observeurs.VoitureObserver;
import voiture.*;

public class test_5 {
	public static void main() throws FileNotFoundException {
		String filename = "1_safe.trk"; 
		Circuit c=CircuitFactoryFromFile.build(filename); //si méthode build  static
		BufferedImage im=TerrainTools.imageFromCircuit(c.getMatrix());
		Voiture v = VoitureFactory.build(c);//si méthode build static
		//Strategy str=new Strategy();
		Observeur vobs=new VoitureObserver(v);
		Controleur contr=new Controleur(c);
		//contr.add(vobs);
		vobs.print(im);
		System.out.println("fait");
	}
	
}
