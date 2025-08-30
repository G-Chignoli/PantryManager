package utils;

import java.nio.file.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class DatabaseInit{

	private static final String RESOURCES_PATH = "/db/PANTRY_EMPTY.mv.db";
	private static final Path TARGET_PATH = Paths.get("db/PANTRY.mv.db");
	private static final Logger logger = LogManager.getLogger(DatabaseInit.class);

	public static void initialize() {
		if (Files.exists(TARGET_PATH)) {
			logger.warn("Database già esistente.");
			return;
		}

		try {
			Files.createDirectories(TARGET_PATH.getParent());

			try (InputStream input_stream = DatabaseInit.class.getResourceAsStream(RESOURCES_PATH)) {
				if (input_stream == null) {
					logger.error("File del database non trovato in: " + RESOURCES_PATH);
					return;
				}

				Files.copy(input_stream, TARGET_PATH, StandardCopyOption.REPLACE_EXISTING);
				logger.warn("Database correttamente copiato da /resources.");

			}

		} catch (IOException e) {
			logger.error("Errore durante la copia del database");
		}
	}
}
