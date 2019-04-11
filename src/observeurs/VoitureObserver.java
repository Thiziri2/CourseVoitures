package observeurs;
import geometrie.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


import javax.imageio.ImageIO;
import voiture.*;

public class VoitureObserver implements ObserveurSWING, ObserveurImage{
    private Voiture voiture;
    private BufferedImage imCar;
    private Color color = Color.yellow;

    public VoitureObserver(Voiture voiture) {
            this.voiture = voiture;
    }
    public VoitureObserver(Voiture v, String filename) throws IOException{
    	voiture = v;
    	InputStream file = new FileInputStream(filename);//cr�ation de l'objet fichier
    	this.imCar=ImageIO.read(file); // lecture fichier original
    }

    public int getX(){ // a inverser pour l'affichage en horizontal
            return (int) voiture.getPosition().getX();
    }
    public int getY(){
            return (int) voiture.getPosition().getY();
    }

    public Color getColor() {
        if(voiture.getVitesse()<0.3) // vitesse faible -> cyan
           return new Color(0, (int)(voiture.getVitesse()*255*2), (int) (voiture.getVitesse()*255*2));

        if(voiture.getVitesse() == 0.9)
          return new Color((int)(voiture.getVitesse()*255),  (int) (voiture.getVitesse()*255), 0);

        return new Color((int)(voiture.getVitesse()*255), 0, (int) (voiture.getVitesse()*255));
    }

    public void print(BufferedImage im) {
            im.setRGB(getY(), getX(), getColor().getRGB());
    }
    public void print(Graphics g){
        // Attention a l'inversion eventuelle des coordonnees
        g.setColor(color);
        //g.drawRect((int) voiture.getPosition().getY(), (int) voiture.getPosition().getX(), 2, 2);
//        int delta=25; // bords � laisser vierges
//        g.drawImage(imCar, delta, delta, imCar.getHeight()-2*delta, imCar.getWidth()-2*delta, null);
        affichage(imCar, g);
        g.setColor(Color.red);
        g.drawLine((int) voiture.getPosition().getY(),
                                (int) voiture.getPosition().getX(),
                                (int) (voiture.getPosition().getY()+voiture.getDirection().getY()*10),
                                (int) (voiture.getPosition().getX()+voiture.getDirection().getX()*10));

        g.setColor(Color.black);
        g.drawString(String.format("v: %.2f d: (%6.2f, %6.2f) derap: ", voiture.getVitesse(),
                        voiture.getDirection().getY(), voiture.getDirection().getX()),
                        10, 10);
    }
    public void affichage(BufferedImage car, Graphics g){
    	// calcul de l'angle � appliquer sur l'image de la voiture pour la rendre
    	// coh�rente avec la simulation � chaque instant
    	// -> d�pend de l'image et de l'affichage de l'interface (horizontale ou vertical)
    	double angle = Math.PI/2 + voiture.getDirection().angle(new Vecteur(0, 1));

    	// construction d'une transformation
    	AffineTransform transform = new AffineTransform();
    	// rotation par rapport � un centre � d�finir (cf javadoc)
    	transform.rotate(angle, (car.getWidth() / 2), (car.getHeight() / 2));
    	// transformation => transformation d'image
    	AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);

    	// image finale
    	BufferedImage carMod = op.filter(car, null);
    	g.drawImage(carMod, getY() - 25, getX() - 25, null);
    }
}

