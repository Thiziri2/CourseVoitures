package radar;

import voiture.Voiture;

public interface Radar {
	public double[] scores(); // s c o r e de ch aque b r a n c h e
	public double[] distancesInPixels(); // p o u r l � o b s e r v e r
	public int getBestIndex(); // m e i l l e u r i n d i c e
	public double[] thetas(); // a n g l e s de ch aque f a i s c e a u
	public void SetVoiture(Voiture voiture);
	 
}
