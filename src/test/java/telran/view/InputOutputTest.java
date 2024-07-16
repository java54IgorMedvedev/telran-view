package telran.view;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
record User(String username, String password,
		LocalDate dateLastLogin, String phoneNumber, int numberOfLogins) {}
class InputOutputTest {
InputOutput io = new SystemInputOutput();
	@Test
	void readObjectTest() {
		User user = io.readObject("Enter user in format <username>#<password>#<dateLastLogin>"
				+ "#<phone number>#<number of logins>", "Wrong user input format", str -> {
					String[] tokens = str.split("#");
					return new User(tokens[0], tokens[1],
							LocalDate.parse(tokens[2]), tokens[3], Integer.parseInt(tokens[4]));
				});
		io.writeLine(user);
	}
	@Test
	void readUserByFields() {
	    String username = io.readStringPredicate("Enter username: ", "Username must be at least 6 ASCII letters, first Capital, others Lower case",
	        str -> str.matches("[A-Z][a-z]{5,}"));

	    String password = io.readStringPredicate("Enter password: ", "Password must be at least 8 symbols, at least one capital letter, at least one lower case letter, at least one digit, at least one symbol from \"#$*&%\"",
	        str -> str.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[#$*&%]).{8,}"));

	    String phoneNumber = io.readStringPredicate("Enter phone number: ", "Phone number must be a valid Israel mobile phone",
	        str -> str.matches("\\+9725\\d{8}"));

	    LocalDate dateLastLogin = io.readIsoDateRange("Enter last login date (yyyy-mm-dd): ", "Date must not be after current date", LocalDate.MIN, LocalDate.now());

	    Integer numberOfLogins = io.readInt("Enter number of logins: ", "Number of logins must be a positive number");

	    User user = new User(username, password, dateLastLogin, phoneNumber, numberOfLogins);
	    io.writeLine(user);
	}


}