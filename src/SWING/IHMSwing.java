package SWING;

import javax.swing.JPanel;

import com.sun.prism.Graphics;

import observeurs.UpdateEventListener;

public class IHMSwing extends JPanel implements UpdateEventListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void manageUpdate() {
        repaint(); // ordre de mise à jour  
        try {      // temporisation (sinon, on ne voit rien)
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
    public void paint(Graphics g){
        super.paint((java.awt.Graphics) g);

        for(ObserveurSWING o:list)
            o.print(g);
    }
}
