package mains;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import voiture.Commande;
import voiture.Voiture;
import voiture.VoitureException;
import voiture.VoitureFactory;
import circuit.Circuit;
import circuit.CircuitFactoryFromFile;
import circuit.TerrainTools;

import com.sun.prism.paint.Color;

public class Test_TME3 {
 public static void main(String[] args) throws VoitureException, IOException {
    String filename = "track/1_safe.trk";

    Circuit track = CircuitFactoryFromFile.build(filename); // factory static
    Voiture v = VoitureFactory.build(track); // factory static

    BufferedImage im = TerrainTools.imageFromCircuit(track);

    ArrayList<Commande> coms = new ArrayList<Commande>();
    for(int i=0; i<50; i++) coms.add(new Commande(1,0)); // accel a fond
    for(int i=0; i<50; i++) coms.add(new Commande(1,0.05)); // accel a fond + droite
    for(int i=0; i<50; i++) coms.add(new Commande(1,0)); // accel a fond
    for(int i=0; i<50; i++) coms.add(new Commande(1,-0.05)); // accel a fond + gauche

    for(Commande c:coms){
            v.drive(c);
            System.out.println("position : "+ v.getPosition());
            im.setRGB((int) v.getPosition().getX(), (int) v.getPosition().getY(), (int) Color.BLUE.getRed());
    }

    im.setRGB(0,0, (int) Color.BLUE.getRed());
    ImageIO.write(im, "png", new File("test.png"));
}
}