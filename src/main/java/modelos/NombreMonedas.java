package modelos;

import com.google.gson.annotations.SerializedName;

public class NombreMonedas implements Comparable<NombreMonedas> {
    private String[][] codigosMonedas;
    private String nombreMoneda;
    @SerializedName("supported_codes")
    private String[][] codigosCompatibles;

    @Override
    public int compareTo(NombreMonedas nombreMonedas) {
        return 0;
    }
}
