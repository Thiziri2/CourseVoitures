package observeurs;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import circuit.*;

public class Controleur implements UpdateEventListener{
	private ArrayList<ObserveurImage> observeurs;
	private BufferedImage imCir;
	
	public Controleur(Circuit cir){
		observeurs = new ArrayList<ObserveurImage>();
		//On construit l'image associee au circuit
		imCir = TerrainTools.imageFromCircuit(cir);
	}
	public void manageUpdate() {
		for(ObserveurImage o: observeurs)
			o.print(imCir);
	} 
	public void add(ObserveurImage o){
		observeurs.add(o);
	}
	public BufferedImage getImage(){
		return imCir;
	}
}
