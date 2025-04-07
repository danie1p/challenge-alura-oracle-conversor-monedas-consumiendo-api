package modelos;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.TasasDeCambioAPI;

import java.io.IOException;
import java.util.Map;

public class TasaDeCambioMonedas implements TasaDeCambio {

    private Moneda monedaBase;
    private String monedaSecundaria;
    private final TasasDeCambioAPI tasasDeCambioAPI;
    private final String json;
    private final Gson gson;

    public TasaDeCambioMonedas(String monedaBase) throws IOException, InterruptedException {
        this.tasasDeCambioAPI = new TasasDeCambioAPI(monedaBase);
        this.json = tasasDeCambioAPI.getTasaDeCambio();

        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
        this.monedaBase = gson.fromJson(json, Moneda.class);

    }

    public TasaDeCambioMonedas(String monedaBase, TasasDeCambioAPI manejadorTasasAPI) throws IOException, InterruptedException {
        this.tasasDeCambioAPI = manejadorTasasAPI;
        this.json = tasasDeCambioAPI.getTasaDeCambio();

        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
        this.monedaBase = gson.fromJson(json, Moneda.class);
    }

    public Moneda getMonedaBase() {
        return monedaBase;
    }

    public void setMonedaBase(Moneda monedaBase) {
        this.monedaBase = monedaBase;
    }

    public String getMonedaSecundaria() {
        return monedaSecundaria;
    }

    public void setMonedaSecundaria(String monedaSecundaria) {
        this.monedaSecundaria = monedaSecundaria;
    }

    public  Map<String, Double> getTasasDeConversion() {
        return this.monedaBase.getConversionRates();
    } 

    @Override
    public double getTasaDeCambioParaMonedaSecundaria() {
        // Esta funcion retorna -1 si el usuario no ha ingresado la moneda a la que desea
        // convertir la moneday base.
        return (this.monedaSecundaria != null) ?
                monedaBase.getConversionRates().get(monedaSecundaria) :
                -1;
    }
}
