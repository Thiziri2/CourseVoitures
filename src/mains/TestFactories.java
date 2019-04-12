package mains;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import voiture.Commande;
import voiture.Voiture;
import voiture.VoitureFactory;
import circuit.*;

public class TestFactories{
	public static void main(String[] args) throws FileNotFoundException{
	
		Circuit cir = CircuitFactoryFromFile.build("1_safe.trk");
		Voiture v = VoitureFactory.build(cir);
		
		BufferedImage imCir = TerrainTools.imageFromCircuit(cir.getMatrix());
		
		
		ArrayList<Commande> l1, l2, l3, l4, l5;
		l1 = new ArrayList<Commande>();
		l2 = new ArrayList<Commande>();
		l3 = new ArrayList<Commande>();
		l4 = new ArrayList<Commande>();
		l5 = new ArrayList<Commande>();
		for(int i=0; i<101; i++){//Les accélérations
			l1.add(new Commande(1, 0));
			l2.add(new Commande(1, 0));
			l3.add(new Commande(1, 0));
			l4.add(new Commande(1, 0.1));
			l5.add(new Commande(1, -0.1));
			//System.out.println(i);
		}
		//Concatener
		l2.addAll(l4);
		l3.addAll(l5);
		
		int a = 0;
		//drive
		for(int i=0; i<l1.size(); i++){
			v.drive(l1.get(i));
			for(int x = (int) v.getPosition().getX()-a; x<= (int) v.getPosition().getX()+a; x++){//cases autour
				for(int y = (int) v.getPosition().getY()-a; y<= (int) v.getPosition().getY()+a; y++){
					imCir.setRGB( y, x, Color.YELLOW.getRGB());//Je change la couleur de la voiture.
				}
			}
		}
		//System.out.println("Second");
		for(int i=0; i<l2.size(); i++){
			//System.out.println(i);
			//System.out.println("turn max voiture:" + v.getMaxTurn());
			//System.out.println("turn commande:" + l2.get(i).getTurn());
			v.drive(l2.get(i));
			for(int x = (int) v.getPosition().getX()-a; x<= (int) v.getPosition().getX()+a; x++){//cases autour
				for(int y = (int) v.getPosition().getY()-a; y<= (int) v.getPosition().getY()+a; y++){
					imCir.setRGB( y, x, Color.YELLOW.getRGB());//Je change la couleur de la voiture.
				}
			}
		}
		for(int i=0; i<l3.size(); i++){
			//System.out.println("turn max voiture:" + v.getMaxTurn());
			//System.out.println("turn commande:" + l2.get(i).getTurn());
			v.drive(l3.get(i));
			for(int x = (int) v.getPosition().getX()-a; x<= (int) v.getPosition().getX()+a; x++){//cases autour
				for(int y = (int) v.getPosition().getY()-a; y<= (int) v.getPosition().getY()+a; y++){
					imCir.setRGB( y, x, Color.YELLOW.getRGB());//Je change la couleur de la voiture.
				}
			}
		}
		TerrainTools.saveIm(imCir, "test");
		
	}	
}
