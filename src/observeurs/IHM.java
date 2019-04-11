package observeurs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import geometrie.*;
import circuit.*;
import enums.Terrain;
import radar.Radar;
import radar.RadarClassique;
import radar.RadarDijkstra;
import strategy.*;
import voiture.*;

public class IHM extends JFrame implements ActionListener, MouseListener {//MouseListener for the image
	//Edition (grass, points)
	private boolean ajout;
	private boolean withImage;
	private int rayon = 4;
	//Edition Point par point
	private boolean makePoints;
	private ArrayList<Vecteur> listePoints;
	//Elements
	private String namefile;
	private Simulation sim;
	private Circuit cir;
	private Voiture voiture;
	double [] faisceau = {-Math.PI/12, -Math.PI/6, -Math.PI/3, 0, Math.PI/3, Math.PI/6, Math.PI/12};;
	private Radar radar;
	private Strategy strat;
	//Les observer et image
	private CircuitObserver cObs;
	private VoitureObserver vObs;
	private RadarObserver rObs;
	private IHMSwing ihmSwing;
	private BufferedImage img;
	//onglets
	private JTabbedPane onglet;
	private JLabel labelEdit;
	private JLabel panSimulation;
	//Menu
	private JMenuBar menuBar;
	private JMenu mCircuit;
	private JMenu track1;
	private JMenu track2;
	private JMenu trackExam;
	private JMenu mStrategy;
	private ArrayList<JRadioButtonMenuItem> listTrack1;//Le track1
	private ArrayList<JRadioButtonMenuItem> listTrack2;//Le track2
	private ArrayList<JRadioButtonMenuItem> listTrackExam;//Le trackExam
	private ArrayList<String> listTrackName1;
	private ArrayList<String> listTrackName2;
	private ArrayList<String> listTrackNameExam;
	private JRadioButtonMenuItem stratRadar;
	private JRadioButtonMenuItem stratPointParPoint;
	//Boutons
	private JButton play;
	private JButton putGrass;
	private JButton putPoints;
	private JButton save;
	private JButton saveCommands;
	private JButton withOrNoImage;//Servira pour une affichage en image ou pas


	public IHM(String title, int width, int height) throws IOException{
		namefile = "1_safe.trk";
		//Liste des points
		listePoints = new ArrayList<Vecteur>();
		withImage = true;

		//Noms des circuits
		String [] list1 = {"aufeu.trk", "bond_safe.trk", "Een2.trk", "labymod.trk", "labyperso.trk", "perso.trk", "t260_safe.trk", "t2009.trk"};
		listTrackName1 = new ArrayList<String>();
		for(int i=1; i<=8; i++)
			listTrackName1.add(i+"_safe.trk");
		for(String name : list1)
			listTrackName1.add(name);

		listTrackName2 = new ArrayList<String>();
		String [] list2 = {"banly.trk", "Examarrivees.trk", "ExampointApoint.trk", "jussieu.trk", "longbeachfwy.trk", "losangeles.trk", "newyork.trk", "schtroumpf.trk"};
		for(String name : list2)
			listTrackName2.add(name);

		//TrackExam
		listTrackNameExam = new ArrayList<String>();
		String [] listExam = {"arrivee.trk", "escargot.trk", "limitationVitesse.trk", "pointApoint.trk"};
		for(String name : listExam)
			listTrackNameExam.add(name);

		//premier circuit
		String filename = "LI260_track_2012/"+listTrackName1.get(0);
		this.setCir(filename);

		//Menu
		menu();
		this.setTitle(title);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);//Le place au milieu de l'écran
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public Simulation getSim(){
		return sim;
	}
	public String getFilename(){
		return namefile;
	}
	private void setCir(Circuit circuit) {
		this.ajout = false;
		this.makePoints = false;
		ArrayList<UpdateEventListener> controleurs = new ArrayList<UpdateEventListener>();
		//On change le circuit, on change la voiture et la simulation
		this.cir = circuit;
		this.voiture = VoitureFactory.build(cir);//Pour replacer la voiture dans le nouveau circuit
		cObs = new CircuitObserver(cir);
		try {
			vObs = new VoitureObserver(voiture, "voiture.png");
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.setRadar();
		rObs = new RadarObserver(radar, voiture, cir);
		Controleur ctr = new Controleur(cir);

		//On enleve l'ihmSwing précédent
		if(ihmSwing != null)
			this.remove(this.ihmSwing);

		if(withImage){
			this.ihmSwing = new IHMSwing(new ArrayList<ObserveurSWING>());
			ihmSwing.add(cObs);
			ihmSwing.add(vObs);
			ihmSwing.add(rObs);
			controleurs.add(ihmSwing);
			ctr.add(vObs);
		}
		this.setStrat(new StrategyRadar(voiture, radar));
		this.sim = new Simulation( cir,voiture, strat, controleurs);

		if(this.withImage)
			setOnglet();
//		this.add(ihmSwing);
		manageUpdate();
	}
	private void setCir(String filename) throws FileNotFoundException{
		Circuit circuit = CircuitFactoryFromFile.build(filename);
		setCir(circuit);
	}
	public void setRadar(){
		faisceau = new double [150];
		double j = 0.25;
		for(int i=1; i<faisceau.length/2; i++){
			faisceau[i-1] = Math.PI/(i+j); //On evite de commencer par 0
			j=+0.25;
		}
		faisceau[(faisceau.length/2)] = 0;
		j=0.25;
		for(int i=1; i<faisceau.length/2; i++){
			faisceau[i-1+(faisceau.length/2)] = -Math.PI/(i+j); //On evite diviser par 0
			j=+0.25;
		}
		this.radar = new RadarClassique(voiture, cir, faisceau);
		//this.radar = new RadarDijkstra(cir, voiture, faisceau);
	}
	public void setStrat(Strategy strat) {
		this.strat = strat;
	}
	private void menu(){
		//Barre de menu
		this.menuBar = new JMenuBar();
		//Les différents menu
		mCircuit = new JMenu("mCircuit");
		track1 = new JMenu("track1");
		track2 = new JMenu("track2");
		trackExam = new JMenu("trackExam");
		mStrategy = new JMenu("mStrategy");

		//Initialisation des boutons
		play = new JButton("Play");
		putGrass = new JButton("putGrass");
		putPoints = new JButton("putPoints");
		withOrNoImage = new JButton("No Image");
		saveCommands = new JButton("Save Commands");
		//Action à faire
		play.addActionListener(this);
		putGrass.addActionListener(this);
		putPoints.addActionListener(this);
		withOrNoImage.addActionListener(this);
		saveCommands.addActionListener(this);

		//Les Items
			//Les circuits
		listTrack1 = new ArrayList<JRadioButtonMenuItem>();
		for(String trackName : listTrackName1){
			JRadioButtonMenuItem jrmi = new JRadioButtonMenuItem(trackName);
			jrmi.addActionListener(this);//Ajout dans les actions listener
			listTrack1.add(jrmi);
		}
		//Track2
		listTrack2 = new ArrayList<JRadioButtonMenuItem>();
		for(String trackName : listTrackName2){
			JRadioButtonMenuItem jrmi = new JRadioButtonMenuItem(trackName);
			jrmi.addActionListener(this);//Ajout dans les actions listener
			listTrack2.add(jrmi);
		}
		//TrackExam
		listTrackExam = new ArrayList<JRadioButtonMenuItem>();
		for(String trackName : listTrackNameExam){
			JRadioButtonMenuItem jrmi = new JRadioButtonMenuItem(trackName);
			jrmi.addActionListener(this);//Ajout dans les actions listener
			listTrackExam.add(jrmi);
		}

		stratRadar = new JRadioButtonMenuItem("Strategy Radar");
		stratPointParPoint = new JRadioButtonMenuItem("Strategy Point Par Point");
		//présélection
		listTrack1.get(0).setSelected(true);
		stratRadar.setSelected(true);

		//Action à faire
		stratRadar.addActionListener(this);
		stratPointParPoint.addActionListener(this);

		//ajouts des items dans les différents menus
		for(JRadioButtonMenuItem trackButton : listTrack1)
			track1.add(trackButton);
		for(JRadioButtonMenuItem trackButton : listTrack2)
			track2.add(trackButton);
		for(JRadioButtonMenuItem trackButton : listTrackExam)
			trackExam.add(trackButton);

		//Ajout des sous menus
		mCircuit.add(track1);
		mCircuit.add(track2);
		mCircuit.add(trackExam);

		mStrategy.add(stratRadar);
		mStrategy.add(stratPointParPoint);

		//ajouts des différents menus dans la menuBar
		menuBar.add(mCircuit);
		menuBar.add(mStrategy);
		this.setJMenuBar(menuBar);

		//Ajout boutons dans menu
		menuBar.add(play);
		menuBar.add(putGrass);
		menuBar.add(putPoints);
		menuBar.add(saveCommands);
		menuBar.add(withOrNoImage);
	}
	private void setOnglet() {
		//on enleve onglet
		if(this.onglet != null)
			this.remove(this.onglet);

		//On le remet
		this.onglet = new JTabbedPane();

		//On le remet avec les bons composants
		this.panSimulation = new JLabel();
		this.panSimulation.add(this.ihmSwing);

//		//JLabel
//		this.img = TerrainTools.imageFromCircuit(sim.getCircuit());
//		this.labelEdit = new JLabel(new ImageIcon(this.img));
//		//Controleur image
//		this.labelEdit.addMouseListener(this);

		//on remet les pages de onglet
		onglet.add("Simulation", this.panSimulation);
//		onglet.add("Edition : Point par Point", this.labelEdit);
		this.add(onglet);
	}
	public void add(JPanel jp){//Ajout d'un conteneur
		super.add(jp);
	}
	public void setSize(int width, int height){
		super.setSize(width, height);
	}

	private void play(){
		this.add(ihmSwing);//A enlever !!!!!!!!!
		Thread t = new Thread(){
		public void run() {
				sim.play();
	        }
		};
	 t.start();
	 manageUpdate();
	}
	private void putGrass(){
		/*
		 * On modifie le circuit en ajoutant de l'herbe dans certain endroit.
		 * De ce fait le nouveau circuit devra être sauvegarder dans l'ihm mais aussi dans la simulation.
		 */
		System.out.println("On ajoute de l'herbe");

		//Le bouton de sauvegarde pour bien réactualiser le circuit avec les nouvelles herbes
		save = new JButton("Sauvegarder");
		save.addActionListener(this);

		ajout = true;
		this.img = TerrainTools.imageFromCircuit(sim.getCircuit());
		this.labelEdit = new JLabel(new ImageIcon(this.img));
		//Controleur AddGrass
		this.labelEdit.addMouseListener(this);

		Box vbox = Box.createVerticalBox();
		vbox.add(save);
		JPanel panelEdit = new JPanel();
		panelEdit.add(labelEdit);
		panelEdit.add(vbox, BorderLayout.EAST);

		if(this.onglet != null)
			this.remove(this.onglet);
		onglet = new JTabbedPane();
		//On ajoute la page à l'onglet
		onglet.add("Simulaion", this.panSimulation);
		onglet.add("Edition : Ajout d'herbe", panelEdit);
		this.add(onglet);
		manageUpdate();
	}
	private void makePoints(){
		/*
		 * Meme fonctionnement que putGrass() sauf qu'on l'applique à des points.
		 */
		System.out.println("Edition point par point");
		//Le bouton de sauvegarde pour bien réactualiser le circuit avec les nouvelles herbes
		save = new JButton("Sauvegarder");
		save.addActionListener(this);

		makePoints = true;
		this.img = TerrainTools.imageFromCircuit(sim.getCircuit());
		this.labelEdit = new JLabel(new ImageIcon(this.img));
		//Controleur_Image
		this.labelEdit.addMouseListener(this);

		Box vbox = Box.createVerticalBox();
		vbox.add(save);
		JPanel panelEdit = new JPanel();
		panelEdit.add(labelEdit);
		panelEdit.add(vbox, BorderLayout.EAST);

		if(this.onglet != null)
			this.remove(this.onglet);
		onglet = new JTabbedPane();
		//On ajoute la page à l'onglet
		onglet.add("Simulaion", this.panSimulation);
		onglet.add("Edition : Point par Point", panelEdit);
		this.add(onglet);
		manageUpdate();
	}
	public void saveCommandes(){
		StrategyTools.saveListeCommande(sim.getRecord(), "Commands_saved/"+namefile+".txt");
	}
	public void manageUpdate() {
        this.repaint();
    }

	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand();

		//Si c'est un nom de circuit
		System.out.println(name);
		if(isIn(name, listTrackName1)){
			try {
				this.setCir("LI260_track_2012/"+name);
				namefile = name;
				selectRightButton(name);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(isIn(name, listTrackName2)){
			try {
				this.setCir("track2/"+name);
				namefile = name;
				selectRightButton(name);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(isIn(name, listTrackNameExam)){
			try {
				this.setCir("LI260_Circuits_Examen2011/pack1abc/"+name);
				namefile = name;
				selectRightButton(name);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(name.equals("Strategy Point Par Point")){
			this.setStrat(new StrategyPointParPoint(listePoints, voiture, radar));
			stratPointParPoint.setSelected(true);
			stratRadar.setSelected(false);
			this.setCir(cir);
		}
		else if(e.getActionCommand().equals("Play")){
			play();
		}
		else if(e.getActionCommand().equals("putGrass")){
			putGrass();
		}
		else if(e.getActionCommand().equals("putPoints")){
			makePoints();
		}
		else if(e.getActionCommand().equals("Save Commands")){
			saveCommandes();
		}
		else if(e.getActionCommand().equals("Sauvegarder")){
			System.out.println("Sauvegarde ...");
			setCir(this.cir);
			ajout = false;
			makePoints = false;
			System.out.println("Réussi !");
		}
		else if(e.getActionCommand().equals("No Image")){
			System.out.println("No Image");
			this.withImage = false;
			setCir(this.cir);
			//Changement du nom du bouton
			menuBar.remove(withOrNoImage);
			withOrNoImage = new JButton("With Image");
			menuBar.add(withOrNoImage);
		}
		else if(e.getActionCommand().equals("With Image")){
			System.out.println("With Image");
			this.withImage = true;
			setCir(this.cir);
			//Changement du nom du bouton
			this.menuBar.remove(withOrNoImage);
			this.withOrNoImage = new JButton("No Image");
			menuBar.add(withOrNoImage);
		}
		this.manageUpdate();
	}
	private boolean isIn(String text, ArrayList<String> liste){
		for(String t : liste){
			if(t.toLowerCase() == text.toLowerCase())
				return true;
		}
		return false;
	}
	public void selectRightButton(String name){
		for(JRadioButtonMenuItem jrmi : listTrack1){
			if(name.toLowerCase() != jrmi.getText().toLowerCase())
				jrmi.setSelected(false);
			else
				jrmi.setSelected(true);
		}
	}
	public void mouseClicked(MouseEvent e) {
		if(ajout){//Si on doit ajouter de l'herbe
			int x=e.getX(); int y=e.getY();

			//Si le click est dans la matrice image
			if(img!=null && (x<img.getWidth() && y<img.getHeight())){ //Vérifier que x représente la largeur ou hauteur de même que pour y
				System.out.println("{"+x+","+y+"}");
				Graphics2D g = (Graphics2D) img.getGraphics();
				g.setFont(new Font("time", 1, 18));
				g.setColor(Color.GREEN);
				//On repaint une certaine zone proche du point
				g.fillRect(x-rayon, y-rayon, 2*rayon, 2*rayon);
				//On change aussi les points dans la matrice terrain
				for(int i=x-rayon; i<=x+rayon; i++){
					for(int j=y-rayon; j<=y+rayon; j++){
						this.cir.setTerrain(j, i, Terrain.Herbe);
					}
				}
			}
		}
		if(makePoints){
			System.out.println("{"+e.getX()+","+e.getY()+"}");
			if(img != null){
				Graphics2D g = (Graphics2D) img.getGraphics();
				g.setFont(new Font("time", 1, 18));
				g.setColor(Color.BLUE);
				g.drawString("{"+e.getX()+","+e.getY()+"}", 50, 50);
				g.drawOval(e.getX()-4, e.getY()+4, 8, 8);
				e.getComponent().repaint();

				listePoints.add(new Vecteur(e.getX(), e.getY()));
			}
		}
		manageUpdate();
	}
	public void mouseEntered(MouseEvent arg0) {

	}
	public void mouseExited(MouseEvent arg0) {

	}
	public void mousePressed(MouseEvent arg0) {

	}
	public void mouseReleased(MouseEvent arg0) {

	}
}
