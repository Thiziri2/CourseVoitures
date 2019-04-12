package strategy;

import java.nio.channels.Selector;
import java.util.ArrayList;

import voiture.Commande;

public class StrategySelector implements Strategy {
	ArrayList<Strategy> listStrat;
	ArrayList<Selector> listSelect;

	public StrategySelector(){
		listStrat = new ArrayList<Strategy>();
		listSelect = new ArrayList<Selector>();
	}
	public void add(Strategy strat, Selector selector){
		listStrat.add(strat); listSelect.add(selector);
	}
	public Commande getCommande() {
		for(int i=0; i<listStrat.size(); i++){
			if(listSelect.get(i).isOpen())
				return listStrat.get(i).getCommande();
		}
		return null;
	}

}
