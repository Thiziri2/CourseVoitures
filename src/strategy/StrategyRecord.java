package strategy;


import java.util.ArrayList;

import voiture.Commande;

public class StrategyRecord implements Strategy{
	private Strategy strat;
	private ArrayList<Commande> listecommande = new ArrayList<>();
	
	public StrategyRecord(Strategy s){
		strat = s;
	}
	public Commande getCommande() {
		Commande com = strat.getCommande();
		listecommande.add(com);
		return com;
	}
}
/*public class StrategyRecord implements Strategy{
	Strategy strat;
    
    public static void saveListeCommande(ArrayList<Commande> liste, String filename){
        try {
                DataOutputStream os = new DataOutputStream(new FileOutputStream(filename));
                for(Commande c:liste){
                        os.writeDouble(c.getAcc());
                        os.writeDouble(c.getTurn());
                }
                os.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    public static ArrayList<Commande> loadListeCommande(  String filename) throws IOException{
        ArrayList<Commande> liste = null;

        try {
                @SuppressWarnings("resource")
				DataInputStream os = new DataInputStream(new FileInputStream(filename));

                liste = new ArrayList<Commande>();
                double a,t;
                
                while(true){ // on attend la fin de fichier
                        a = os.readDouble();
                        t = os.readDouble();
                        liste.add(new Commande(a,t));
                }

        } catch (EOFException e){
                return liste;
        } 

    }

	public Commande getCommande() {
		// TODO Auto-generated method stub
		return null;
	}


}*/
