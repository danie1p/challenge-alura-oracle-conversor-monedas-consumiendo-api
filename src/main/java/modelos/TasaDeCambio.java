package modelos;

import java.util.Map;

public interface TasaDeCambio {

    double getTasaDeCambioParaMonedaSecundaria();
    Map<String, Double> getTasasDeConversion();

}
