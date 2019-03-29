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
import circuit.Circuit;
import circuit.TerrainTools;
import enums.Terrain;
//import enums.Terrain;
import geometrie.Vecteur;
import observeurs.UpdateEventListener;
import observeurs.UpdateEventSender;

public class Simulation implements UpdateEventSender {
	private Circuit circuit;
	private Voiture voiture;
	private Strategy strategy;
	private ArrayList<Commande> record;
	private ArrayList<UpdateEventListener> listeners;
	
	public Simulation(Circuit c, Voiture v, Strategy s) {
		super();
		this.circuit = c;
		this.voiture = v;
		this.strategy = s;
		this.record=new ArrayList<Commande>();
	}
	/*
	public void play(){
			BufferedImage imCir = TerrainTools.imageFromCircuit(circuit);
			//On ne manipule plus d'image dans la simulation
			Commande com;
			boolean endgame = false;
			int pas=0;

			System.out.println("Je simule");
			do{
				com = strategy.getCommande();
				voiture.drive(com);
				record.add(com);
				//On donne à l'observer voiture les données pour qu'il change imprime la couleur de la position de la voiture
				update();

				//En mode image
				imCir.setRGB((int) voiture.getPosition().y, (int) voiture.getPosition().x, Color.YELLOW.getRGB());

				if(!TerrainTools.isRunnable(circuit.getTerrain(voiture.getPosition()))){//Si le terrain n'est pas Runnable
					endgame = true;
					System.out.println("perdu !!! Nombre de pas : "+(pas++));
				}
				if(circuit.getTerrain(voiture.getPosition()) == Terrain.EndLine){
					endgame = true;
					System.out.println("Gagné ! Game over ! Congrats! Nombre de pas : "+(pas++));
				}
				pas++;
			}while(com != null && !endgame);
	}*/
	public void play(){
		BufferedImage im=TerrainTools.imageFromCircuit(circuit.getMatrix());
		Vecteur p =voiture.getPosition();
		while((TerrainTools.isRunnable(circuit.getTerrain(p)))&&(p.x<circuit.getMatrix()[0].length)&&(p.y<circuit.getMatrix().length)) {
			
			voiture.drive(strategy.getCommande());
			 p =voiture.getPosition();

			 int vx=(int) p.x;
			 int vy=(int) p.y;

			 im.setRGB(vx,vy,Color.yellow.getRGB());
			
		}
		
		TerrainTools.saveIm(im, "simulation");
		
	}
	public void playOneShot() throws VoitureException{
		Commande c = strategy.getCommande();
		// application
		voiture.drive(c);
		//MAJ Etat
		//state = updateState();
	}
	
	public void addCommande(Commande e) {
		record.add(e);
	}
	
	public ArrayList<Commande> getRecord(){
		return record;
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

	
	public void add(UpdateEventListener listener) {
		listeners.add(listener);
	}

	public void update() {
		for(UpdateEventListener listener:listeners)
			listener.manageUpdate();		
	}
}
