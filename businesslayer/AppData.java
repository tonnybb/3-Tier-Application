package businesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import datalayer.DatabaseConnection;
import datalayer.DatabaseConnection;

// This is an example of using the Singleton pattern to make the application's data available throughout the 
// application while guaranteeing that there is only one copy of it.

public class AppData {
	private static int i = 0; // used as iterator for the arrayList

	private List<Person> people = new ArrayList<Person>();

	// this is the reference to the single instance of the AppData class
	private static AppData appData = null;

	// A private constructor that is only called one time
	private AppData() {
	}

	// A public method to make the app Data available throughout the
	// application. The first time the method is called, the Single instance of
	// AppData is
	// created, each subsequent time, the one data object created is returned.
	public static AppData getAppData() {
		if (appData == null) {
			appData = new AppData();
		}

		return appData;

	}

	// example of a method to change the appData from throughout the project
	// there might be lots of there to add / remove data.

	public void addPerson(Person person) {
		Statement stmt = null;

		people.add(person); // this adds the object to the datastructures in RAM

		try {
			// Create database connection
			Connection con = DatabaseConnection.getConnection();

			// Create Statement object
			stmt = con.createStatement();

			// Create DB insert command
			String insertStatement = "INSERT INTO CONTACTS VALUES(" + "'"
					+ people.get(i).getFirstName() + "', '"
					+ people.get(i).getLastName() + "', '"
					+ people.get(i).getPhoneNumber() + "', '"
					+ people.get(i).getEmail() + "')";

			// Insert the 'insertStatement' into DB
			stmt.executeUpdate(insertStatement);

			System.out.println(people.get(i).toString()
					+ "...was saved to the database.\n");

			// i is used as iterator in the people arraylist
			i++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Person getPerson(String firstNameOfPersonToFind,
			String lastNameOfPersonToFind) {
		Statement stmt = null;

		String firstName = null;
		String lastName = null;
		String email = null;
		String phoneNumber = null;

		try {
			// Create database connection
			Connection con = DatabaseConnection.getConnection();

			// Create Statement object
			stmt = con.createStatement();

			String getPersonQuery = "SELECT FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL_ADDRESS FROM CONTACTS where FIRST_NAME = '"
					+ firstNameOfPersonToFind
					+ "' AND LAST_NAME = '"
					+ lastNameOfPersonToFind + "'";

			ResultSet rs = stmt.executeQuery(getPersonQuery);
			firstName = rs.getString("FIRST_NAME");
			lastName = rs.getString("LAST_NAME");
			email = rs.getString("EMAIL_ADDRESS");
			phoneNumber = rs.getString("PHONE_NUMBER");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new Person(firstName, lastName, email, phoneNumber);
	}

	public static ArrayList<Object> findAllPeople() {
		Statement stmt = null;
		ArrayList<Object> allPeople = new ArrayList<Object>();
		String firstName = null;
		String lastName = null;
		String email = null;
		String phoneNumber = null;

		try {
			// Create database connection
			Connection con = DatabaseConnection.getConnection();

			// Create Statement object
			stmt = con.createStatement();

			String getAllPeopleQuery = "SELECT FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL_ADDRESS FROM CONTACTS";
			ResultSet rs = stmt.executeQuery(getAllPeopleQuery);

			while (rs.next()) {
				firstName = rs.getString("FIRST_NAME");
				lastName = rs.getString("LAST_NAME");
				email = rs.getString("EMAIL_ADDRESS");
				phoneNumber = rs.getString("PHONE_NUMBER");

				allPeople.add(new Person(firstName, lastName, phoneNumber,
						email));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allPeople;
	}

	public static void deletePerson(String firstNameOfPersonToDelete,
			String lastNameofPersonToDelete) {
		Statement stmt = null;

		try {
			// Create database connection
			Connection con = DatabaseConnection.getConnection();

			// Create Statement object
			stmt = con.createStatement();

			// Get info about the person we're about to delete
			String getPersonQuery = "SELECT FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL_ADDRESS FROM CONTACTS WHERE FIRST_NAME = '"
					+ firstNameOfPersonToDelete
					+ "' AND LAST_NAME = '"
					+ lastNameofPersonToDelete + "'";
			ResultSet rs = stmt.executeQuery(getPersonQuery);

			String firstName = rs.getString("FIRST_NAME");
			String lastName = rs.getString("LAST_NAME");
			String email = rs.getString("EMAIL_ADDRESS");
			String phoneNumber = rs.getString("PHONE_NUMBER");

			String deletePersonStatement = "DELETE FROM CONTACTS WHERE FIRST_NAME = '"
					+ firstNameOfPersonToDelete
					+ "' AND LAST_NAME = '"
					+ lastNameofPersonToDelete + "'";
			stmt.executeUpdate(deletePersonStatement);

			System.out.println("The following record was deleted:\n"
					+ firstName + " " + lastName + "\n" + phoneNumber + "\n"
					+ email);
			System.out
					.println("\nThe database contains the following records: ");

			ArrayList<Object> myPeople = AppData.findAllPeople();

			for (Object element : myPeople) {
				System.out.println(element.toString());
			}

		} catch (SQLException e) {
			System.out.println("Error: The person: \""
					+ firstNameOfPersonToDelete + " "
					+ lastNameofPersonToDelete
					+ "\" was not found. No records were deleted.");
			System.out
					.println("\nThe database contains the following records: ");
			ArrayList<Object> myPeople = AppData.findAllPeople();

			for (Object element : myPeople) {
				System.out.println(element.toString());
			}
		}
	}

}