package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	// connexion partagée
	private static Connection con;
	// Méthode pour obtenir la connexion
	public static Connection getConnection() {
		if (con == null) {
			try {
				String url = "jdbc:mysql://localhost:3306/gestion_ocp";
				con = DriverManager.getConnection(url, "root", "");
				System.out.println("Connexion bien rétablie !!");
			} catch (SQLException ex) {
				System.out.println("Erreur : " + ex.getMessage());
			}
		}
		return con;
	}
}
