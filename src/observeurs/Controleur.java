package observeurs;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import circuit.Circuit;
import circuit.TerrainTools;

public class Controleur implements UpdateEventListener{
	ArrayList<Observeur> liste;
	BufferedImage image;

	public Controleur(Circuit c) {
		liste = new ArrayList<Observeur>();
		image=TerrainTools.imageFromCircuit(c.getMatrix());
	}

	public void add(Observeur vobs) {
		liste.add(vobs);
	}

	@Override
	public void manageUpdate() {
		for(Observeur o:liste)
		o.print(image);		
	}
}
