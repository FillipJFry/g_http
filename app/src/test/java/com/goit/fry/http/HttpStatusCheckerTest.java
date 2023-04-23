package com.goit.fry.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HttpStatusCheckerTest {

	private static final Logger logger = LogManager.getRootLogger();

	@ParameterizedTest
	@ValueSource(ints = {200, 401, 305})
	void getStatusImageDoesNotThrowOnCorrectCode(int code) {

		logger.info("testing the correct code " + code);
		HttpStatusChecker checker = new HttpStatusChecker();
		try {
			String imgUrl = checker.getStatusImage(code);
			logger.info(code + ": " + imgUrl);
			assertEquals("https://http.cat/" + code + ".jpg", imgUrl);
		}
		catch (Exception e) {

			assertNull(e);
		}
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 99, 600})
	void getStatusImageThrowsOnIncorrectCode(int code) {

		logger.info("testing the incorrect code " + code);
		HttpStatusChecker checker = new HttpStatusChecker();
		assertThrows(Exception.class, () -> checker.getStatusImage(code));
	}
}