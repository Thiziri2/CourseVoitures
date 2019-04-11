package observeurs;

import java.awt.Graphics;
import java.awt.Image;

import circuit.*;

public class CircuitObserver implements ObserveurSWING {
	private Image trackIm ;
	private Circuit cir;
	
	public CircuitObserver(Circuit cir){
		this.cir  = cir;
		trackIm = TerrainTools.imageFromCircuit(cir);
	}

	public void print(Graphics g) {
		g.drawImage(trackIm, 0, 0, null);
	}
}

