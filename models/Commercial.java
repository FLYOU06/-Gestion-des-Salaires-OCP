package models;

public class Commercial extends Employe {
	protected double chifreAffaire;
	
	public Commercial(int id, String nom, int age, String date_entree, double chifreAffaire) {
		super(id, nom, age, date_entree);
		this.chifreAffaire = chifreAffaire;
	}
	public Commercial(String nom, int age, String date_entree, double chifreAffaire) {
		super(nom, age, date_entree);
		this.chifreAffaire = chifreAffaire;
	}
	@Override
	public double calculeSalaire() {
		return chifreAffaire*0.2;
	}
}
