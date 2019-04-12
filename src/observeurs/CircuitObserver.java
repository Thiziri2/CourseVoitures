package observeurs;

import java.awt.Graphics;
import java.awt.Image;

import circuit.*;
import SWING.ObserveurSWING;

public class CircuitObserver implements  ObserveurSWING{
	private Image trackIm ;
	private Circuit circuit;
	
	public CircuitObserver(Circuit cir){
		this.circuit  = cir;
		trackIm = TerrainTools.imageFromCircuit(cir.getMatrix());
	}

	public void print(Graphics g) {
		g.drawImage(trackIm, 0, 0, null);
	}




}
