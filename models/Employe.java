package models;

public abstract class Employe {
	protected int id;
	protected String nom;
	protected int age;
	protected String date_entree;
	public Employe(String nom, int age, String date_entree) {
		this.nom = nom;
		this.age = age;
		this.date_entree = date_entree;
	}
	public Employe(int id, String nom, int age, String date_entree) {
		this.id = id;
		this.nom = nom;
		this.age = age;
		this.date_entree = date_entree;
	}
	
	public abstract double calculeSalaire();
	public String getNom() {
		return "L'employen " +nom;
	}
	public int getAge() {return age;}
	public int getId() {return id;}
	public String getDateEntree() {return date_entree;}
}
