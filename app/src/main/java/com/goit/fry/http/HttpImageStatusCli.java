package com.goit.fry.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HttpImageStatusCli {

	private static final Logger logger = LogManager.getRootLogger();

	public void askStatus() {

		System.out.println("Enter HTTP status code");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int code = -1;
		try {
			String codeStr = reader.readLine();
			code = Integer.parseInt(codeStr);
			HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
			downloader.downloadStatusImage(code);
		}
		catch(NumberFormatException e) {

			System.err.println("Please enter a valid number");
		}
		catch (Exception e) {

			logger.error(e);
			System.err.println("There is no image for this code: " + code);
		}
	}
}