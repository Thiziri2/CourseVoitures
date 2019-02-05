package circuit;

import java.util.ArrayList;

import enums.Terrain;
import geometrie.Vecteur;

public interface Circuit {    
        public Terrain getTerrain(int i, int j);
        public Terrain getTerrain(Vecteur v);
        public Vecteur getPointDepart();
        public Vecteur getDirectionDepart();
        public Vecteur getDirectionArrivee();
        public int getWidth();
        public int getHeight();
        public ArrayList<Vecteur> getArrivees();

        public double getDist(int i, int j);
}
