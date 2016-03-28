package datalayer;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

	private static Connection con;

	public static void createDatabase() {
		try {
			// Establish/create database connection
			con = DriverManager.getConnection("jdbc:sqlite:CONTACTLIST.db");
			
			Statement stmt = con.createStatement();
			
			// Create a table
			String setUpTable = "CREATE TABLE CONTACTS"
					+ "(FIRST_NAME TEXT NOT NULL, "
					+ " LAST_NAME TEXT NOT NULL, "
					+ " PHONE_NUMBER TEXT NOT NULL, " 
					+ " EMAIL_ADDRESS TEXT)";

			// Execute query. Returns an integer representing the number of rows
			// affected by the SQL statement.
			stmt.executeUpdate(setUpTable);

			// Close open Statement resources
			stmt.close();

			// Releases this Connection object's database and JDBC resources
			// immediately instead of waiting for them to be automatically
			// released.
			con.close();

			System.out.println("No database file was found. Created new empty database.\n");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public static Connection getConnection() throws SQLException {

		try {
			// Load JDBC driver
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		File file = new File("CONTACTLIST.db");

		// Determine whether the database file exists.
		if (!file.exists()) {
			createDatabase();
		}

		if (con == null || con.isClosed()) {
			con = magicallyCreateNewConnection();
		}
		return con;
	}

	private static Connection magicallyCreateNewConnection() {
		Connection con = null;

		try {
			// Create database connection
			con = DriverManager.getConnection("jdbc:sqlite:CONTACTLIST.db");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;

	}
}