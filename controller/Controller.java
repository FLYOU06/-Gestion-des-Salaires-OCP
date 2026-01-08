package controller;

import java.util.List;
import implementation.GestionEmployeDB;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Employe;
import models.Monuteur;
import models.Producteur;
import models.Reprisentateur;
import models.Vendeur;

public class Controller{
	@FXML private TextField txtName;
	@FXML private TextField txtAge;
	@FXML private TextField txtDate;
	@FXML private TextField txtValue;
	@FXML private ComboBox<String> comboProducteur;
	@FXML private TableView<Employe> table;
	@FXML private TableColumn<Employe, Integer> colId;
	@FXML private TableColumn<Employe, String> colName;
	@FXML private TableColumn<Employe, Integer> colAge;
	@FXML private TableColumn<Employe, String> colDate;
	@FXML private TableColumn<Employe, Double> colSalary;
	private ObservableList<Employe> employeeList = FXCollections.
	observableArrayList();
	@FXML
	public void initialize() {
		comboProducteur.getItems().addAll("VENDEUR", "REPRESENTANT", "PRODUCTEUR", "MANUTENTIONNAIRE");
		// Liaison de la colonne ID avec l attribut "id" de la classe Employe
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		//Liaison de la colonne Nom avec l attribut "nom" de la classe Employe
		colName.setCellValueFactory(new PropertyValueFactory<>("nom"));
		//Liaison de la colonne ID avec l attribut "id" de la classe Employe
		colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
		// Liaison de la colonne Date d entr ée avec l attribut "dateEntree" dela classe Employe 
		colDate.setCellValueFactory(new PropertyValueFactory<>("date_entree"));
		// Calcul et affichage dynamique du salaire pour chaque employé
		colSalary.setCellValueFactory(param ->new SimpleDoubleProperty(param.getValue().calculeSalaire()).asObject());
		// Chargement de la liste des employés dans le TableView
		table.setItems(employeeList);
		afficherAction();
	}
	
@FXML
private void ajouterAction() {
	try {
		String name = txtName.getText();
		int age = Integer.parseInt(txtAge.getText());
		String date = txtDate.getText();
		String type = comboProducteur.getValue();
		double value = Double.parseDouble(txtValue.getText());
		Employe emp = switch (type) {
		case "VENDEUR" -> new Vendeur(name, age, date, value);
		case "REPRESENTANT" -> new Reprisentateur(name, age, date, value);
		case "PRODUCTEUR" -> new Producteur(name, age, date, (int)value);
		case "MANUTENTIONNAIRE" -> new Monuteur(name, age, date, (int) value);
		default -> throw new IllegalArgumentException("Type invalide");
	};
	 GestionEmployeDB.addEmployee(emp, type, value, false);
	 afficherAction();
	 clearFields();
	 } catch (Exception e) {
		 showAlert("Erreur", e.getMessage());
	 }
 }
 
 @FXML
 private void modifierAction() {
	 TextInputDialog dialog = new TextInputDialog();
	 dialog.setTitle("Modifier un employé");
	 dialog.setHeaderText("Entrez l'ID de l'employé à modifier :");
	 dialog.setContentText("ID :");
	 
	 dialog.showAndWait().ifPresent(idStr -> {
		 try {
			 int id = Integer.parseInt(idStr);
			 GestionEmployeDB.updateEmployee(
					 id, 
					 txtName.getText(),
					 Integer.parseInt(txtAge.getText()),
					 txtDate.getText(),
					 Double.parseDouble(txtValue.getText()),
					 false
			 );
			 afficherAction();
			 clearFields();
		 } catch (Exception e) {
			 showAlert("Erreur", e.getMessage());
		 }
	 });
 }
 
 @FXML
 private void supprimerAction() {
	 TextInputDialog dialog = new TextInputDialog();
	 dialog.setTitle("Supprimer un employé");
	 dialog.setHeaderText("Entrez l'ID de l'employé à supprimer :");
	 dialog.setContentText("ID :");
	 
	 dialog.showAndWait().ifPresent(idStr -> {
		 try {
			 GestionEmployeDB.deleteEmployee(Integer.parseInt(idStr));
			 afficherAction();
		 } catch (Exception e) {
			 showAlert("Erreur", e.getMessage());
		 }
	 });
 }
 
 @FXML
 private void afficherAction() {
	 List<Employe> list = GestionEmployeDB.getAllEmployees();
	 employeeList.setAll(list);
 }
 
 @FXML
 private void salaireMoyenAction() {
	 double avg = GestionEmployeDB.getAverageSalary();
	 showAlert("Salaire Moyen", String.format("Salaire moyen : %.2f Dhs",avg));
 }
 
 private void clearFields() {
	 txtName.clear();
	 txtAge.clear();
	 txtDate.clear();
	 txtValue.clear();
	 comboProducteur.setValue(null);
 }
 private void showAlert(String title, String msg) {
	 Alert alert = new Alert(Alert.AlertType.INFORMATION);
	 alert.setHeaderText(null);
	 alert.setTitle(title);
	 alert.setContentText(msg);
	 alert.showAndWait();
}
 
}
