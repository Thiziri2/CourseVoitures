package mains;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import observeurs.IHM;
import strategy.*;
import voiture.*;
import circuit.*;

public class TesObservorSWING {

	public static void main(String[] args) throws IOException {
		IHM ihm = new IHM(" Test_Swing", 1000, 800);

//		String filename = ihm.getFilename();
//		FileSave.saveListeCommande(ihm.getSim().getRecord(), filename+".txt");

	}

}