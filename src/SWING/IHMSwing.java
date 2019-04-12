package SWING;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import circuit.Circuit;
import circuit.TerrainTools;
import observeurs.UpdateEventListener;

public class IHMSwing extends JPanel implements UpdateEventListener {
	private ArrayList<ObserveurSWING> list;
	private BufferedImage im;
	private Circuit c;
	
	public IHMSwing(Circuit c){
		this.c=c;
		list=new ArrayList<ObserveurSWING>();
		im=TerrainTools.imageFromCircuit(c.getMatrix());
	}
	public void add(ObserveurSWING o){
		if(o==null)		return;
		
		list.add(o);
	}
	public void manageUpdate() {
		repaint(); // ordre de mise à jour  
        try {      // temporisation (sinon, on ne voit rien)
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	public void paint(Graphics g){
        super.paint(g);

        for(ObserveurSWING o : list)
            o.print(g);
    }
	public BufferedImage getImage() {
		return im;
	}
}
