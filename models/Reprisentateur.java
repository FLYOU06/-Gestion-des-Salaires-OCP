package models;

public class Reprisentateur extends Commercial{
	public Reprisentateur(int id, String nom, int age, String date_entree, double chifreAffaire) {
		super(id, nom, age, date_entree, chifreAffaire);
	}
	public Reprisentateur(String nom, int age, String date_entree, double chifreAffaire) {
		super(nom, age, date_entree, chifreAffaire);
	}
	@Override
	public double calculeSalaire() {
		return super.calculeSalaire()+8000;
	}
	@Override
	public String getNom() {
		return "Reprisentateur " +nom;
	}
}
