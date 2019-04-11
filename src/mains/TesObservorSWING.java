package mains;

import java.io.IOException;

import observeurs.IHM;
import strategy.StrategyTools;


public class TesObservorSWING {

	public static void main(String[] args) throws IOException {
		IHM ihm = new IHM(" Test_Swing", 1000, 800);

		String filename = ihm.getFilename();
		StrategyTools.saveListeCommande(ihm.getSim().getRecord(), filename+".txt");

	}

}