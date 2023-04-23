package com.goit.fry.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class HttpStatusImageDownloaderTest {

	private static final Logger logger = LogManager.getRootLogger();

	@ParameterizedTest
	@ValueSource(ints = {202, 411, 506})
	void downloadStatusImageDoesNotThrowOnCorrectCode(int code) {

		logger.info("downloading a correct image for the code " + code);
		HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
		try {
			downloader.downloadStatusImage(code);
			assertTrue(Files.exists(Path.of(code + ".jpg")));
		}
		catch (Exception e) {

			assertNull(e);
		}
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 99, 600})
	void downloadStatusImageThrowsOnIncorrectCode(int code) {

		logger.info("trying to download an incorrect image for the code " + code);
		HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
		assertThrows(Exception.class, () -> downloader.downloadStatusImage(code));
	}
}