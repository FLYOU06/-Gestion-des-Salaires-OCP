package models;

public class Producteur extends Employe implements Prime{
	private int nbUnite;
	private boolean isDanger;
	public Producteur(int id, String nom, int age, String date_entree, int nbUnite, boolean isDanger) {
		super(id, nom, age, date_entree);
		this.nbUnite = nbUnite;
		this.isDanger = isDanger;
	}
	public Producteur(String nom, int age, String date_entree, int nbUnite) {
		super(nom, age, date_entree);
		this.nbUnite = nbUnite;
	}
	
	@Override
	public double calculeSalaire() {
		if(isDanger) {
			return nbUnite*5+prime;
		}else {
			return nbUnite*5;
		}
	}
	@Override
	public String getNom() {
		return "Producteur " +nom;
	}
}
