package database;
import java.sql.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class DBaddRow {
	private static Logger logger = LogManager.getLogger(dbTest.class);
	public static void main(String[] args) {
		addRow();
	}
	
	public static void addRow() {
		/*
		String jdbcURL = "jdbc:h2:./src/main/java/database/testdb";
		String username = "sa";
		String password = "";
		*/
		
		try (Connection connection = DriverManager.getConnection(Database.getJdbcURL(), Database.getUsername(), Database.getPassword());
	             Statement stmt = connection.createStatement()) {
				stmt.execute("INSERT INTO users (id, name) VALUES (3, 'Mario Rossi')");
				logger.debug("Row added.");

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

}
