package utils;

import modelos.NombreMoneda;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TasasDeCambioAPI implements NombreMoneda {

    private String clave;
    private String monedaBase;
    private String direction;
    private String uri;

    public TasasDeCambioAPI(String monedaBase) {
        this.clave = "14571b1462664c0e131a16d2";
        this.direction = "https://v6.exchangerate-api.com/v6/";
        this.monedaBase = monedaBase;
        this.uri = this.direction +
                this.clave + "/latest/" + monedaBase;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public void setMonedaBase(String monedaBase) {
        this.monedaBase = monedaBase;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTasaDeCambio() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(this.uri))
                    .build();
        HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

        client.close();

        return response.body();

    }

    public String nombreMonedas() {
        return "";
    }
}





