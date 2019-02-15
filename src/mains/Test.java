package mains;
import java.awt.Color;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import circuit.TerrainTools;
import enums.Terrain;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import circuit.*;
import voiture.*;

public class Test {
	public static void main(String[] args) throws IOException {
		Circuit circuit=CircuitFactoryFromFile.build("1_safe.trk");
		//pour la creation d'un circuit pas besoin d'un constructeur vu qu'on a la methode static build qui s'applique directement a sa class
		//c'est pour ca que le constructeur de curcuitFactoryFromFile est vide il sert juste a ne pas faire une erreure
		//idem pour voiture
		Voiture v1=VoitureFactory.build(circuit);
		Terrain[][] track=circuit.getMatrix();
		ArrayList<Commande> liste1 = new ArrayList<Commande>();
		ArrayList<Commande> liste2 = new ArrayList<Commande>();
		ArrayList<Commande> liste3 = new ArrayList<Commande>();
		//double x;
		//  ** implementation des listes de commandes **
		for(int i=0;i<1000;i++) {
			//x=(Math.random()*2)-1;vitesse aléatoire entre -1 et 1(les 2exclues)

			liste1.add(i,new Commande((Math.random()*2)-1, 0));//liste qui va tout droit
			liste2.add(i,new Commande((Math.random()*2)-1, 0));//liste qui va à droite(les 100 premières commande vont tout droit)
			liste3.add(i,new Commande((Math.random()*2)-1, 0));//liste qui va à gauche(les 100 premières commande vont tout droit)
		}
		for(int i=1000;i<2000;i++) {
			//x=(Math.random()*2)-1;vitesse aléatoire
			liste2.add(i,new Commande((Math.random()*2)-1, -v1.getMaxTurn()));//turn max vers la gauche
			liste3.add(i,new Commande((Math.random()*2)-1, v1.getMaxTurn()));//turn max vers la droite
		}
		
		//System.out.println(circuit.getPointDepart());
		
		BufferedImage im=TerrainTools.imageFromCircuit(track);//on récupère l'image du circuit
		
		 for(int i=0;i<liste1.size();i++) {//on parcour la liste de commande liste1 et on applique le deplacement de v1
			 
			 v1.drive(liste1.get(i));//déplacement v1
			 
			 int vx=(int) v1.getPosition().x;//on récupère la position de v1
			 int vy=(int) v1.getPosition().y;
			 im.setRGB(vx,vy,Color.BLACK.getRGB());//on modifie la couleur du pixel associé à la position (V1.x,v1.y)
		 }
		 
		for(int i=0;i<liste2.size();i++) {
			
			 v1.drive(liste2.get(i));//déplacement v1
			 
			 int vx=(int) v1.getPosition().x;
			 int vy=(int) v1.getPosition() .y;
			 im.setRGB(vx,vy,Color.BLACK.getRGB() );//on modifie la couleur du pixel associé à la position (V1.x,v1.y)
		 }
		
		 for(int i=0;i<liste3.size();i++) {
			 
			 v1.drive(liste3.get(i));

			 int vx=(int) v1.getPosition().x;
			 int vy=(int) v1.getPosition() .y;
			 im.setRGB(vx,vy,Color.BLACK.getRGB() );//on modifie la couleur du pixel associé à la position (V1.x,v1.y)
		 }
		 
		 try {
	            File outputfile = new File("saved.png");
	            ImageIO.write(im, "png", outputfile);
	         } catch (IOException e) {
	            System.out.println("Erreur lors de la sauvegarde");
	         }
		System.out.println("fait");
		//System.out.println(liste1.get(2).getAcc());
		
		//System.out.println(TerrainTools.terrainToRGB(Terrain.Eau));
		//System.out.println(TerrainTools.charFromTerrain(Terrain.Eau));
		//System.out.println(TerrainTools.isRunnable(Terrain.Eau));
		//char x;
		/*Terrain[][] circuit1 = TerrainTools.lectureFichier("1_safe.trk");
		if(circuit1==null) {System.out.println("null");}
                  // test pull
 		for(int i=0;i<768;i++) {
			for(int j=0;j<1024;j++) {
				x=TerrainTools.charFromTerrain(circuit1[i][j]);
				System.out.print(x);
			}
			System.out.print('\n');
		}
		//
 		TerrainTools.imageFromCircuit(circuit1);*/

	}
}
