package geometrie;

public class VecteurTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vecteur v1 = new Vecteur(-3, 4);
		Vecteur v2 = new Vecteur(7, -2);
		Vecteur v3 = new Vecteur(0, 6);
		
		Vecteur v4 = v1.addition(v2);
		System.out.println(v4);
		v2=v2.soustraction(v3);
		System.out.println(v2);
		
		v1=v1.rotation(Math.PI);
		System.out.println(v1);
		
		Vecteur v5 = new Vecteur(1, 0);
		System.out.println(v5);
		
		v5=v5.rotation(Math.PI/2);
		System.out.println(v5);
		
		System.out.println("Le produit scalaire de "+v2+" et "+v3+" est "+v2.prodScal(v3));
	}

}
