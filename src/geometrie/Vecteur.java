package geometrie;
import java.lang.Math;
public class Vecteur {
	
	public final double x,y;

	public Vecteur(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Vecteur(Vecteur a, Vecteur b){
		this.x=b.getX()-a.getX();
		this.y=b.getY()-a.getY();
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vecteur other = (Vecteur) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vecteur [x=" + x + ", y=" + y+ "]"; 
	}
	
	public Vecteur addition(Vecteur v1) {
		return new Vecteur(this.x+v1.getX(),this.y + v1.getY());
	}
	public Vecteur soustraction(Vecteur v1) {
		return new Vecteur(this.x - v1.getX(),this.y - v1.getY());
	}
	public double prodScal(Vecteur v1) {
		return (x*v1.getX() + y*v1.getY());
	}
	public double prodVect(Vecteur v2) {
		return this.x*v2.getY()-this.y*v2.getX();
	}
	
	@Override
	public Vecteur clone() {
		return new Vecteur(this.x,this.y);
	}

	public double norme() {
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
	}
	public Vecteur normalisation(){
		return new Vecteur(this.x/this.norme(), this.y/this.norme());
	}
	
	public double angle(Vecteur b){
		if(this.prodScal(b)/(this.norme()*b.norme())<-1 || this.prodScal(b)/(this.norme()*b.norme())>1){
			System.out.println("L'angle n'est pas conforme");
			return  500;//Code pour dire que le résultat obtenu est erroné
		}
		return Math.signum(this.prodVect(b))*Math.acos(this.prodScal(b)/(this.norme()*b.norme()));
	}

	public Vecteur multiplication(double k) {
		return new Vecteur(this.x*k,this.y*k);
	}
	public Vecteur rotation(double angle){
		return new Vecteur(this.x*Math.cos(angle) - this.y*Math.sin(angle), this.x*Math.sin(angle) + this.y*Math.cos(angle));
	}
	
	public double distance(Vecteur v){
		return Math.sqrt(Math.pow((this.x-v.x), 2) + Math.pow((this.y-v.y), 2));
	}
	public boolean estAutour(Vecteur cible, double rayon){
		//Si le Vecteur courant (this) est à une distance <= au rayon
		if(this.distance(cible)<=rayon)
			return true;
		return false;
	}

}
