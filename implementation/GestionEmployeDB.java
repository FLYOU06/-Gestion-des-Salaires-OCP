package implementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import models.Employe;
import models.Monuteur;
import models.Producteur;
import models.Reprisentateur;
import models.Vendeur;

public class GestionEmployeDB {

//------ methode addEmployee() ------
public static void addEmployee(Employe e, String type, double value, boolean risk) {
String sql = "INSERT INTO employe (nom, age, date_entree, type_employe, valeur, salaire, a_risque) VALUES (?, ?, ?, ?, ?, ?, ?)";

//------ 1. Ouverture de connexion
try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
//------ 2. Préparation de la requête SQL
stmt.setString(1, e.getNom());
stmt.setInt(2, e.getAge());
stmt.setString(3, e.getDateEntree());
stmt.setString(4, type);
stmt.setDouble(5, value);
stmt.setDouble(6, e.calculeSalaire());
stmt.setBoolean(7, risk);
//------ 3. Exécution de la requête
stmt.executeUpdate();

System.out.println("Employee added successfully: " + e.getNom());
} catch (SQLException ex) {
ex.printStackTrace();
}
}
//------ methode getAllEmployees() ------
public static List<Employe> getAllEmployees() {
List<Employe> employees = new ArrayList<>();
String sql = "SELECT * FROM employe";
//------ 1. Ouverture de connexion
try (Statement stmt = DBConnection.getConnection().createStatement();

//------ 2. Préparation de la requête SQL


//------ 3. Exécution de la requête
ResultSet rs = stmt.executeQuery(sql)) {
//------ 4. Traitement des résultats
while (rs.next()) {
int id = rs.getInt("id");
String nom = rs.getString("nom");
int age = rs.getInt("age");
String dateEntree = rs.getString("date_entree");
String type = rs.getString("type_employe");
double value = rs.getDouble("valeur");
boolean risk = rs.getBoolean("a_risque");
Employe e = createEmployeFromType(id, nom, age, dateEntree, type,value, risk);
employees.add(e);
}
} catch (SQLException ex) {
ex.printStackTrace();
}
return employees;
}
//------ methode updateEmployee() ------
public static void updateEmployee(int id, String name, int age, String date,
double value, boolean risk) {String sql = "UPDATE Employe SET nom=?, age=?, date_entree=?, valeur=?,a_risque=? WHERE id=?";
//------ 1. Ouverture de connexion
try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {

//------ 2. Préparation de la requête SQL
stmt.setString(1, name);
stmt.setInt(2, age);
stmt.setString(3, date);
stmt.setDouble(4, value);
stmt.setBoolean(5, risk);
stmt.setInt(6, id);

//------ 3. Exécution de la requête
int rows = stmt.executeUpdate();
if (rows > 0)
System.out.println("Employee updated successfully (ID: " + id + ")");
else
System.out.println("No employee found with ID: " + id);
} catch (SQLException ex) {
ex.printStackTrace();
}
}
//------ methode deleteEmployee() ------

public static void deleteEmployee(int id) {
String sql = "DELETE FROM Employe WHERE id=?";
//------ 1. Ouverture de connexion
try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {

//------ 2. Préparation de la requête SQL
stmt.setInt(1, id);

//------ 3. Exécution de la requête
int rows = stmt.executeUpdate();
if (rows > 0)
System.out.println("Employee deleted successfully (ID: " + id + ")");
else
System.out.println("No employee found with ID: " + id);
} catch (SQLException ex) {
ex.printStackTrace();
}
}
//------ methode getAverageSalary() -----
public static double getAverageSalary() {
	String sql = "SELECT AVG(salaire) AS moyenne FROM Employe";
//------ 1. Ouverture de connexion
try (Statement stmt = DBConnection.getConnection().createStatement();

//------ 2. Préparation de la requête SQL
		
//------ 3. Exécution de la requête
		ResultSet rs = stmt.executeQuery(sql)) {

//------ 4. Traitement des résultats
if (rs.next())
return rs.getDouble("moyenne");
} catch (SQLException ex) {
ex.printStackTrace();
}
return 0.0;
}

private static Employe createEmployeFromType(int id, String nom, int age,String dateEntree, String type, double value,boolean isDanger) {
return switch (type.toUpperCase()) {
case "VENDEUR" -> new Vendeur(id, nom, age, dateEntree, value);
case "REPRESENTANT" -> new Reprisentateur(id,nom, age, dateEntree, value);
case "PRODUCTEUR" -> new Producteur(id,nom, age, dateEntree, (int) value, isDanger);
case "MANUTENTIONNAIRE" -> new Monuteur(id,nom, age, dateEntree, (int) value, isDanger);
default -> throw new IllegalArgumentException("Unknown employee type: " +type);
};
}
}
