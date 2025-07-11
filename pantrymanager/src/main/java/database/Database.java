package database;
import java.sql.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Database {
	private static String jdbcURL = "jdbc:h2:./src/main/java/database/testdb";
	private static String username = "sa";
	private static String password = "";
	private static Logger logger = LogManager.getLogger(dbTest.class);

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
				Statement stmt = connection.createStatement()) {
			logger.debug("Database successfully created.");
			//stmt.execute("CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(255))");
			logger.debug("Table created.");
			stmt.execute("INSERT INTO users (id, name) VALUES (14, 'Luigi Rossi')");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static String getJdbcURL() {
		return jdbcURL;
	}
	
	static String getUsername() {
		return username;
	}
	
	static String getPassword() {
		return password;
	}

}
