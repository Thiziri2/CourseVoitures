package radar;

import circuit.Circuit;
import voiture.Commande;
import voiture.Strategy;
import voiture.Voiture;

public class StrategyRadar implements Strategy{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Radar radar;
	Circuit circuit;
	Voiture voiture;
	double[] faisceaux;
	
	public  StrategyRadar(Radar radar,double[] faisceaux,Circuit circuit,Voiture voiture) {
		super();
		this.radar=radar;
		this.faisceaux=faisceaux;
		this.circuit=circuit;
		this.voiture=voiture;
		
	}

	@Override
	public Commande getCommande() {
		/*for(int i=0;i<faisceaux.length;i++) {
			if(i==radar.getBestIndex()) {
				return new Commande(,faisceaux[i]);
			}
		}*/
		int index=radar.getBestIndex();
		if(Math.abs(faisceaux[index])<voiture.getMaxTurn()) {
			return new Commande(1,faisceaux[index]);
		}
		else {
			return new Commande(-1,0);
		}
	}
			

	}


