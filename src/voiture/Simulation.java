package voiture;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import circuit.Circuit;
import circuit.TerrainTools;
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
	public void play(BufferedImage im){
		Vecteur p =voiture.getPosition();
		while((TerrainTools.isRunnable(circuit.getTerrain(p)))&&(p.x<circuit.getMatrix()[0].length)&&(p.y<circuit.getMatrix().length)) {
			
			voiture.drive(strategy.getCommande());
			 p =voiture.getPosition();

			 int vx=(int) p.x;
			 int vy=(int) p.y;

			 im.setRGB(vx,vy,Color.yellow.getRGB());
			
		}
		
		try {
            File outputfile = new File("saved.png");
            ImageIO.write(im, "png", outputfile);
         } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde");
         }
		
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

	@Override
	public void add(UpdateEventListener listener) {
		listeners.add(listener);
	}
	@Override
	public void update() {
		for(UpdateEventListener listener:listeners)
			listener.manageUpdate();		
	}
}
