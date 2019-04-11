package radar;
import geometrie.Vecteur;
import voiture.Voiture;
import circuit.*;
import dijkstra.Dijkstra;
import enums.Terrain;

public class RadarDijkstra implements Radar{
	private double [] faisceau;
	private double [] scores;
	private Voiture v;
	private Circuit cir;
	private Dijkstra dijk;
	private double epsilon = 0.1;

	public RadarDijkstra(Circuit cir, Voiture v, double [] f){
		this(cir, v,f, new Dijkstra(cir));
	}
	public RadarDijkstra(Circuit cir, Voiture v, double [] f, Dijkstra dijk){
		this.v = v;
		this.cir = cir;
		faisceau = f;
		this.dijk = dijk;
		dijk.algo();//Mise à jour des distances
	}

	private double check(double angle){
		Vecteur p = v.getPosition();
		Vecteur d = v.getDirection().rotation(angle);
		double min = dijk.getDist((int) p.getX(), (int) p.getY());

		while(TerrainTools.isRunnable(cir.getTerrain((int) p.getX(), (int) p.getY()))){
			if(cir.getTerrain(p) == Terrain.EndLine &&
					d.prodScal(cir.getDirectionArrivee())<0){
				return Double.POSITIVE_INFINITY; // INFINI donne le pire score possible.
			}
			p = p.addition(d.multiplication(epsilon));//MAJ position
			if(min > dijk.getDist((int)p.getX(), (int)p.getY())){
				min= dijk.getDist((int)p.getX(), (int)p.getY());
			}
		}
		return min;
	}
	public int check() {

		return 0;
	}
	public double[] scores(){
		scores = new double[faisceau.length];
		for(int i = 0; i<faisceau.length; i++){
			scores[i] = check(faisceau[i]);//Calcule les min des faisceaux
		}
		return scores;
	}

	public double[] distancesInPixels() {
		return null;
	}

	public int getBestIndex(){
		scores();//MAJ des scores;
//		ArrayList<Object> tabBestIndex = new ArrayList<Object>();
		double minValue=Double.POSITIVE_INFINITY;
		// Le plus petit des min
		int bestIndex = 0;
			for(int j=0; j<scores.length; j++){
				if(scores[j]<minValue){
					minValue = scores[j];
					bestIndex = j;
				}
//				else if(scores[j]==minValue){
//					tabBestIndex.add(j);
//				}
			}
//		return (int) tabBestIndex.get(tabBestIndex.size()/2);
		return bestIndex;
	}

	public double[] thetas() {
		return faisceau;
	}

	public Circuit getCir() {
		return cir;
	}
}
