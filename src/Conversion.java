import com.google.gson.annotations.SerializedName;
import java.time.LocalDateTime;

public class Conversion {
    @SerializedName("monedaBase")
    private String monedaOrigen;

    @SerializedName("monedaObjetivo")
    private String monedaObjetivo;

    @SerializedName("cantidadACambiar")
    private double cantidad;

    @SerializedName("cantidadEnMonedaObjetivo")
    private double resultado;

    @SerializedName("tiempo")
    private LocalDateTime fecha;

    public Conversion() {}

    public Conversion(String monedaOrigen, String monedaObjetivo, double cantidad, double resultado, LocalDateTime fecha) {
        this.monedaOrigen = monedaOrigen;
        this.monedaObjetivo = monedaObjetivo;
        this.cantidad = cantidad;
        this.resultado = resultado;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s = %.2f %s (Fecha: %s)", cantidad, monedaOrigen, resultado, monedaObjetivo, fecha);
    }

    // Getters y Setters
    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public void setMonedaOrigen(String monedaOrigen) {
        this.monedaOrigen = monedaOrigen;
    }

    public String getMonedaObjetivo() {
        return monedaObjetivo;
    }

    public void setMonedaObjetivo(String monedaObjetivo) {
        this.monedaObjetivo = monedaObjetivo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
