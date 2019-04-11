package observeurs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import geometrie.*;

public class ControleurImage implements MouseListener {
	Image im;
	private ArrayList<Vecteur> listePoints;
	public ControleurImage(Image im){
		this.im = im;
		this.listePoints = new ArrayList<Vecteur>();
	}
	public void mouseClicked(MouseEvent e) {
		System.out.println("{"+e.getX()+","+e.getY()+"}");
		if(im != null){
			Graphics2D g = (Graphics2D) im.getGraphics();
			g.setFont(new Font("time", 1, 18));
			g.setColor(Color.BLUE);
			g.drawString("{"+e.getX()+","+e.getY()+"}", 50, 50);
			g.drawOval(e.getX()-4, e.getY()+4, 8, 8);
			e.getComponent().repaint();

			listePoints.add(new Vecteur(e.getX(), e.getY()));
		}
	}
	public ArrayList<Vecteur> getListePoints(){
		return listePoints;
	}
	public void mousePressed(MouseEvent e) {

	}
	public void mouseReleased(MouseEvent e) {

	}
	public void mouseEntered(MouseEvent e) {

	}
	public void mouseExited(MouseEvent e) {

	}

}

