package com.goit.fry.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class HttpStatusChecker {

	private final HttpClient httpCli;

	public HttpStatusChecker() {

		httpCli = HttpClient.newHttpClient();
	}

	public String getStatusImage(int code) throws Exception {

		if (code < 100 || code >= 600)
			throw new Exception("no such code: " + code);

		HttpRequest req = HttpRequest.newBuilder(
				URI.create("https://http.cat/" + code + ".jpg"))
				.header("accept", "image/jpeg")
				.version(HttpClient.Version.HTTP_1_1)
				.GET()
				.build();

		HttpResponse<Void> response = httpCli.send(req, HttpResponse.BodyHandlers.discarding());
		String path = response.uri().toString();
		if (code != 404 && Path.of(path).endsWith("404.jpg"))
			throw new Exception("no such code: " + code);

		return path;
	}
}