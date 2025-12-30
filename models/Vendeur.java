package models;

public class Vendeur extends Commercial{
	public Vendeur(int id, String nom, int age, String date_entree, double chifreAffaire) {
		super(id, nom, age, date_entree, chifreAffaire);
	}
	public Vendeur(String nom, int age, String date_entree, double chifreAffaire) {
		super(nom, age, date_entree, chifreAffaire);
	}
	@Override
	public double calculeSalaire() {
		return super.calculeSalaire()+4000;
	}
	@Override
	public String getNom() {
		return "Vendeur " +nom;
	}
}
