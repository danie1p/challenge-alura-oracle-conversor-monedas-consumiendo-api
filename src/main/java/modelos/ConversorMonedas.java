package modelos;

import utils.Monedas;

import java.io.IOException;

public class ConversorMonedas implements ConversorMonedasInterface {

    private TasaDeCambioMonedas administradorMonedas;
    private Monedas[] monedas;
    private double cantidadAConvertir;
    private String monedaBase;
    private String monedaSecundaria;

    public ConversorMonedas(String monedaBase, String monedaSecundaria, Double cantidadAConvertir) throws IOException, InterruptedException {
        this.cantidadAConvertir = cantidadAConvertir;
        this.monedas = Monedas.values();
        this.monedaBase = monedaBase;
        this.monedaSecundaria = monedaSecundaria;
        this.administradorMonedas = new TasaDeCambioMonedas(this.monedaBase);
    }

    // Retorna false, si la moneda no esta en la lista de monedas soportadas.
    @Override
    public boolean sonMoneSoportadas() {

        short numeroDeMonedasEncontradas = 0;
        this.administradorMonedas.setMonedaSecundaria(this.monedaSecundaria);
        for (Monedas monedaDeAmerica : this.monedas) {
            if (monedaDeAmerica.toString().equals(this.monedaBase) ||
                monedaDeAmerica.toString().equals(this.monedaSecundaria))
                numeroDeMonedasEncontradas++;
        }
        // Retorna false, si la moneda no esta en la lista de monedas soportadas.
        return numeroDeMonedasEncontradas == 2;
    }

    @Override
    public double getTasaDeCambio() {
        // Esta funcion retorna -1 en casa que la moneda ingresada no sea soportada.
        return  (sonMoneSoportadas()) ?
                this.administradorMonedas.getTasaDeCambioParaMonedaSecundaria() :
                -1.0;
    }

    @Override
    public double convertirDivisas() {
        return this.getTasaDeCambio() *
                this.cantidadAConvertir;
    }
}
