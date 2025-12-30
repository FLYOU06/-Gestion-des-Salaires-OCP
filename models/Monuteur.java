package models;

public class Monuteur extends Employe implements Prime{
	private int nbHeure;
	private boolean isDanger;
	public Monuteur(int id, String nom, int age, String date_entree, int nbHeure, boolean isDanger) {
		super(id, nom, age, date_entree);
		this.nbHeure = nbHeure;
		this.isDanger = isDanger;
	}
	public Monuteur(String nom, int age, String date_entree, int nbHeure) {
		super(nom, age, date_entree);
		this.nbHeure = nbHeure;
	}
	@Override
	public double calculeSalaire() {
		if(isDanger) {
			return nbHeure*650+prime;
		}else {
			return nbHeure*650;
		}
	}
	@Override
	public String getNom() {
		return "Monuteur " +nom;
	}
}
