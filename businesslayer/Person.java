package businesslayer;
public class Person implements Comparable<Person> {

	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;

	public Person(String firstName, String lastName, String phoneNumber, String email) {
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPhoneNumber(phoneNumber);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public int compareTo(Person obj) {
		return firstName.concat(lastName).compareTo(obj.getFirstName().concat(((Person) obj).getLastName()));
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj instanceof Person)) {
			if (firstName.concat(lastName).equals(((Person) obj).getFirstName().concat(((Person) obj).getLastName()))) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		return "Name: " + firstName + " " + lastName + "\nPhone: " + phoneNumber + "\nEmail: "
				+ email + "\n";
	}
}