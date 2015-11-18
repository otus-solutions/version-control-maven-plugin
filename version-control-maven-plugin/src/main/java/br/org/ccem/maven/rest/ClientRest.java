package br.org.ccem.maven.rest;

import org.apache.maven.plugin.MojoFailureException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientRest {
    private String urlNexus;
    private HttpURLConnection httpURLConnection;

    public ClientRest(String urlNexus) {
        this.urlNexus = urlNexus;
    }

    private void connect(String pathRest) throws IOException {
        httpURLConnection = (HttpURLConnection) new URL(urlNexus+pathRest).openConnection();
    }

    private void disconnect() {
        httpURLConnection.disconnect();
    }

    public String GET(String pathRest) throws MojoFailureException {
        try {
            connect(pathRest);

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

            String response = ConverterToJson.convert(httpURLConnection.getInputStream());

            disconnect();

            return response;
        } catch (IOException e) {
            disconnect();
            throw new MojoFailureException(e.toString());
        }
    }
}
