package SWING;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPanel;

import circuit.*;

public class Panneau extends JPanel { 
	Circuit cir;
	private BufferedImage img;
	public Panneau() throws FileNotFoundException{
		super();
		cir = CircuitFactoryFromFile.build("1_safe.trk");
		img = TerrainTools.imageFromCircuit(cir.getMatrix());
	}
	public void paintComponent(Graphics g){
		g.drawImage(img, 0, 0, this);
		  //Pour une image de fond
		  //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
	}               
}
