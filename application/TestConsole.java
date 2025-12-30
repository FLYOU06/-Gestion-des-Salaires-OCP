package application;

import java.util.List;

import connection.DBConnection;
import implementation.GestionEmployeDB;
import models.Employe;
import models.Vendeur;

public class TestConsole {
	public static void main(String[] args) {
		 //------ 1. Ouverture de la connexion à la base de données
		DBConnection.getConnection();
		
		//------ 2. Création d un objet Employé (type Vendeur)
		Employe e = new Vendeur(1,"Aicha", 30, "2020-05-10", 50000);
		
		 //------ 3. Insertion de l employ é dans la base de données
		 GestionEmployeDB.addEmployee(e, "VENDEUR", 50000, false);
		
		
		 //------ 4. Récupération de la liste des employés depuis la base de données
		 List<Employe> list = GestionEmployeDB.getAllEmployees();
		
		 //------ 5. Parcours et affichage des informations de chaque employé
		 for (Employe emp : list) {
		 System.out.println(
		 "ID: " + emp.getId() +
		 " | Nom: " + emp.getNom() +
		 " | Age: " + emp.getAge() +
		 " | Date entrée: " + emp.getDateEntree() +
		 " | Salaire: " + emp.calculeSalaire()
		 );
		}

	}
}
