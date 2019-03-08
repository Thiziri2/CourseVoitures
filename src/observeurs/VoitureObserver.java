package observeurs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import SWING.ObserveurSWING;
import voiture.Voiture;

public class VoitureObserver implements Observeur, ObserveurSWING{
    private Voiture voiture;
    private Color color = Color.yellow;

    public VoitureObserver(Voiture voiture) {
            super();
            this.voiture = voiture;
    }

    public int getX(){// a inverser pour l'affichage horizontal
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
    
    public void print(Graphics g){
            // Attention a l'inversion eventuelle des coordonnees
            g.setColor(color);
            g.drawRect((int) voiture.getPosition().getX(), (int) voiture.getPosition().getY(), 2, 2);
            g.setColor(Color.red);
            g.drawLine((int) voiture.getPosition().getX(),
                                    (int) voiture.getPosition().getY(),
                                    (int) (voiture.getPosition().getX()+voiture.getDirection().getX()*10),
                                    (int) (voiture.getPosition().getY()+voiture.getDirection().getY()*10));

            g.setColor(Color.black);
            g.drawString(String.format("v: %.2f d: (%6.2f, %6.2f) derap: ", voiture.getVitesse(),
                            voiture.getDirection().getX(), voiture.getDirection().getY()) + voiture.getDerapage(),
                            10, 10);


    }

	@Override
	public void print(BufferedImage im) {
        im.setRGB(getX(), getY(), getColor().getRGB());		
	}

	@Override
	public void print(com.sun.prism.Graphics g) {
		// TODO Auto-generated method stub
		
	}


}

