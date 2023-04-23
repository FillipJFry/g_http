package com.goit.fry.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class HttpStatusImageDownloader {

	private final HttpClient httpCli;

	public HttpStatusImageDownloader() {

		httpCli = HttpClient.newHttpClient();
	}

	public void downloadStatusImage(int code) throws Exception {

		HttpStatusChecker checker = new HttpStatusChecker();
		String imgUrl = checker.getStatusImage(code);

		HttpRequest req = HttpRequest.newBuilder(
						URI.create(imgUrl))
				.header("accept", "image/jpeg")
				.version(HttpClient.Version.HTTP_1_1)
				.GET()
				.build();

		String imgFileName = code + ".jpg";
		httpCli.send(req, HttpResponse.BodyHandlers.ofFile(Path.of(imgFileName)));
	}
}