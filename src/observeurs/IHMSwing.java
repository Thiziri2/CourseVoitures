package observeurs;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class IHMSwing extends JPanel implements UpdateEventListener {
	private ArrayList<ObserveurSWING> list;
	
	public IHMSwing(ArrayList<ObserveurSWING> l){
		list = l;
	}
	public void add(ObserveurSWING o){
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
}
