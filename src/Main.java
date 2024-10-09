import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final List<String> monedasDisponibles = List.of("USD", "ARS", "BRL", "EUR", "GBP", "AUD", "CAD", "CHF", "JPY", "CNY");
    private static final Menu menu = new Menu(monedasDisponibles);
    private static final ConsultaInfoMoneda consultaInfoMoneda = new ConsultaInfoMoneda();
    private static final ArrayList<Conversion> historialDeConversiones = HistorialConversiones.cargarHistorial();

    public static void main(String[] args) {
        while (true) {
            menu.mostrarMenuPrincipal();
            int opcion = menu.leerOpcionPrincipal();
            switch (opcion) {
                case 1:
                    realizarConversion();
                    break;
                case 2:
                    menu.imprimirConversiones(historialDeConversiones);
                    break;
                case 3:
                    System.out.println("Saliendo del programa.");
                    HistorialConversiones.guardarHistorial(historialDeConversiones);
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    public static void realizarConversion() {
        try {

            menu.mostrarMenuMonedas("origen");
            String monedaOrigen = menu.leerOpcionMoneda("origen");

            menu.mostrarMenuMonedas("objetivo");
            String monedaObjetivo = menu.leerOpcionMoneda("objetivo");

            double cantidad = menu.leerCantidadACambiar();

            double conversionResult = consultaInfoMoneda.buscaMoneda(monedaOrigen, monedaObjetivo, cantidad);

            Conversion conversion = new Conversion(monedaOrigen, monedaObjetivo, cantidad, conversionResult, LocalDateTime.now());
            historialDeConversiones.add(conversion);
            System.out.println(conversion);
        } catch (NumberFormatException e) {
            System.out.println("Error: La cantidad ingresada no es válida.");
        } catch (ConsultaInfoMoneda.DataNotAvailableException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Se produjo un error inesperado: " + e.getMessage());
        }
    }

}
