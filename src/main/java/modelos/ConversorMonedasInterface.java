package modelos;

public interface ConversorMonedasInterface {
    boolean sonMoneSoportadas();
    double getTasaDeCambio();
    double convertirDivisas();
}
