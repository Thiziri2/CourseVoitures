package voiture;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import strategy.Strategy;
import circuit.*;
import geometrie.*;
import observeurs.*;

public class Simulation implements UpdateEventSender {
	private Circuit circuit;
	private Voiture voiture;
	private Strategy strategy;
	private BufferedImage img;
	private ArrayList<Commande> record =new ArrayList<Commande>();
	private ArrayList<UpdateEventListener> listeners;
	
	public Simulation(Circuit c, Voiture v, Strategy s, ArrayList<UpdateEventListener> l) {
		super();
		this.circuit = c;
		this.voiture = v;
		this.strategy = s;
		listeners=l;
		img=TerrainTools.imageFromCircuit(circuit.getMatrix());
	}


	public Simulation(Circuit cir, Voiture v, Strategy strat) {
		this( cir,v, strat, new ArrayList<UpdateEventListener>());
	}
	public void play(){
		Commande com;
		boolean endgame = false;
		int pas=0;
		System.out.println("je simule ...");
		com = strategy.getCommande();
		
		do{
			voiture.drive(com);
			record.add(com);
			//On donne à l'observer voiture les données pour qu'il change imprime la couleur de la position de la voiture
			update();

			//En mode image
			img.setRGB((int) voiture.getPosition().y, (int) voiture.getPosition().x, Color.yellow.getRGB());

			if(!TerrainTools.isRunnable(circuit.getTerrain(voiture.getPosition()))){//Si le terrain n'est pas Runnable
				endgame = true;
				System.out.println("perdu !!! Nombre de pas : "+(pas++));
			}
			if(circuit.getTerrain(voiture.getPosition()) == Terrain.EndLine){
				endgame = true;
				System.out.println("Gagné ! Game over ! Congrats! Nombre de pas : "+(pas++));
			}
			pas++;
			com = strategy.getCommande();
		}while(com != null && !endgame);
	}
	
	public BufferedImage simuleIm(){
		play();
		return img;
	}
	public void playOneShot() throws VoitureException{
		Commande c = strategy.getCommande();
		// application
		voiture.drive(c);
		//MAJ Etat
		update();
	}
	
	public void addCommande(Commande e) {
		record.add(e);
	}
	
	public ArrayList<Commande> getRecord(){
		return record;
	}
	
	public Circuit getCircuit() {
		return circuit;
	}
	public static  void saveListeCommande( ArrayList<Commande> liste,String filename){
		try{
				DataOutputStream  os =new DataOutputStream(new FileOutputStream(filename));
				for(Commande c : liste){
					os.writeDouble(c.getAcc());
					os.writeDouble(c.getTurn());
				}os.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void add(UpdateEventListener listener) {
		listeners.add(listener);
	}
	@Override
	public void update() {
		for(UpdateEventListener listener:listeners)
			listener.manageUpdate();		
	}


	public void saveIm(String name) {
		TerrainTools.saveIm(img, name);
	}
}
