package com.goit.fry.http;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class HttpImageStatusCliTest {

	@Test
	void askStatusCorrect() {

		ByteArrayOutputStream out = setStreams("506");

		HttpImageStatusCli cli = new HttpImageStatusCli();
		assertDoesNotThrow(cli::askStatus);

		assertTrue(Files.exists(Path.of("506.jpg")));
		assertEquals(0, out.size());
	}

	@Test
	void askStatusNotANumber() {

		ByteArrayOutputStream out = setStreams("test");

		HttpImageStatusCli cli = new HttpImageStatusCli();
		assertDoesNotThrow(cli::askStatus);

		assertEquals("Please enter a valid number\n", out.toString());
	}

	@Test
	void askStatusIncorrectCode() {

		ByteArrayOutputStream out = setStreams("987");

		HttpImageStatusCli cli = new HttpImageStatusCli();
		assertDoesNotThrow(cli::askStatus);

		assertEquals("There is no image for this code: 987\n", out.toString());
	}

	@Test
	void askStatus404() {

		ByteArrayOutputStream out = setStreams("404");

		HttpImageStatusCli cli = new HttpImageStatusCli();
		assertDoesNotThrow(cli::askStatus);

		assertTrue(Files.exists(Path.of("404.jpg")));
		assertEquals(0, out.size());
	}

	private ByteArrayOutputStream setStreams(String input) {

		System.setIn(new ByteArrayInputStream(input.getBytes()));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setErr(new PrintStream(out));

		return out;
	}
}