package circuit;

import java.awt.Color;
import java.awt.image.BufferedImage;

import enums.Terrain;
import exceptions.TerrainException;

import java.io.*;

import javax.imageio.ImageIO;


public class TerrainTools {
	public TerrainTools() {
		super();
		// TODO Auto-generated constructor stub
	}
public static Terrain terrainFromChar(char c) throws TerrainException{
			switch (c){
			case '.':  return Terrain.Route;
			case 'g':  return Terrain.Herbe;
			case 'b':  return Terrain.Eau; 
			case 'o':  return Terrain.Obstacle; 
			case 'r':  return Terrain.BandeRouge;
			case 'w':  return Terrain.BandeBlanche;
			case '*':  return Terrain.StartPoint;
			case '!':  return Terrain.EndLine;
			case 'm':  return Terrain.Boue;
			default : throw new TerrainException("valeur non trouve");
			}
		}
	public static char charFromTerrain(Terrain t){
		int indexT= 0;
		Terrain tab[]=Terrain.values();
		for(int i=0;i<tab.length;i++){
			if(t.equals(tab[i]))  indexT=i;
		}		
		return Terrain.conversion[indexT];
	}
	
	public static Color terrainToRGB(Terrain t){
		int indexT= 0;
		Terrain tab[]=Terrain.values();
		for(int i=0;i<tab.length;i++){
			if(t.equals(tab[i]))  indexT=i;
		}		
		return Terrain.convColor[indexT];
	}
	
	public static boolean isRunnable(Terrain t){
	if(t.equals(Terrain.Route)||t.equals(Terrain.Boue)||t.equals(Terrain.Route)||t.equals(Terrain.BandeBlanche)||t.equals(Terrain.BandeRouge)||t.equals(Terrain.EndLine)||t.equals(Terrain.StartPoint))
		return true;
	else return false;
	}
	
	public static Terrain[][] lectureFichier(String name) throws FileNotFoundException{
		InputStream file = new FileInputStream(name);
		try {
		   // ouverture en mode lecture
		   InputStreamReader fr = new InputStreamReader(file);
		   // fonction suppl�mentaire
		   BufferedReader in = new BufferedReader(fr);
		   String buf = in.readLine();//1ere ligne
		   int nbc = Integer.parseInt(buf);//convesion en int
		   buf = in.readLine();//2e ligne
		   int nbl = Integer.parseInt(buf);
		   Terrain[][] mat = new Terrain[nbl][nbc];//Le bufint1 c'est le nombre de colonnes l'autre le nombre de lignes.
		   
		   buf = in.readLine();
		   for(int i=0; i<nbl; i++){
			   if(buf!=""){
				   for(int j=0; j<buf.length(); j++){
					   char[] chaine = buf.toCharArray();//Pour l'avoir comme tableau de char
					   mat[i][j] = terrainFromChar(chaine[j]);
				   }
			   }
			   buf = in.readLine();
		   }
		   in.close();
		   return mat;

		 // dans l'idéal, on sépare la gestion des exceptions
		 } catch (Exception e) {
		   e.printStackTrace();
		   System.err.println("Invalid Format : " + file
		            + "... Loading aborted");
		   return null;
		 }
	}
	
	public static BufferedImage imageFromCircuit(Terrain[][] track) {
		BufferedImage img = new BufferedImage(track.length, track[0].length, BufferedImage.TYPE_INT_ARGB);
		//la matrice est horizontale et l'image est verticale il faut juste garder en tete 
		//que quand on avance a droite dans la matrice on descend dans l'image  
		for(int i=0;i<track.length;i++) {
 			for(int j=0;j<track[0].length;j++) {

 				img.setRGB(i,j,terrainToRGB(track[i][j]).getRGB() );
 			}
 		}
 	    try {
 	        File outputfile = new File("saved.png");
 	        ImageIO.write(img, "png", outputfile);
 	     } catch (IOException e) {
 	        System.out.println("Erreur lors de la sauvegarde");
 	     }
 	    return img;
    	}
	
	public static BufferedImage imageFromCircuit(Circuit cir){
		BufferedImage im = new BufferedImage(cir.getWidth(), cir.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		//remplissage de l'image
		for(int i=0; i<im.getHeight(); i++){
			for(int j=0; j<im.getWidth(); j++){
				im.setRGB(j, i, terrainToRGB(cir.getTerrain(i, j)).getRGB());//bien inverser j et i car j abscisse et i ordonnée.
			}
		}
		return im;
	}
	
	
	public static void saveCir(Circuit cir, String name){
		BufferedImage im = TerrainTools.imageFromCircuit(cir.getMatrix());
		try{
			File outputfile = new File(name+".png");
            ImageIO.write(im, "png", outputfile);
		} catch(IOException e){
			System.out.println("Erreur lors de la sauvegarde");
		}
	}
	
	
	public static void saveIm(BufferedImage im, String name){
		try{
			File outputfile = new File(name+".png");
            ImageIO.write(im, "png", outputfile);
		} catch(IOException e){
			System.out.println("Erreur lors de la sauvegarde");
		}
	}
	
}