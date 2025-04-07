package modelos;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class Moneda implements Comparable<Moneda> {

    @SerializedName("base_code")
    private String baseCode;
    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;
    @SerializedName("supported_codes")
    private String[][] supportedCodes;

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }

    @Override
    public int compareTo(Moneda moneda) {
        return this.baseCode.compareTo(moneda.baseCode);
    }

    @Override
    public String toString() {
        return "Moneda{" +
                "baseCode='" + baseCode + '\'' +
                 "conversionRates" + this.conversionRates +
                '}';
    }
}
