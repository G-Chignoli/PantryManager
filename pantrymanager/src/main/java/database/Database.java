package database;
import java.sql.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Database {
	private static String jdbcURL = "jdbc:h2:./src/main/java/database/PRODUCT";
	private static String username = "sa";
	private static String password = "";
	private static Logger logger = LogManager.getLogger(Database.class);

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
				Statement stmt = connection.createStatement()) {
			logger.debug("Connected Successfully.");
			stmt.execute("CREATE TABLE PRODUCT (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255) NOT NULL, weight INT, qty INT, expiration_date DATE, calories FLOAT)");
			logger.debug("Table created.");
			
		} catch (SQLException e) {
			logger.error("Something went wrong!");
		} 
	}
}
