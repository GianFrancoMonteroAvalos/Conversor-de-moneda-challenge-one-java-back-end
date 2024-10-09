import java.util.List;
import java.util.Scanner;

public class Menu {
    private final List<String> monedasDisponibles;
    private final Scanner scanner;

    public Menu(List<String> monedasDisponibles) {
        this.monedasDisponibles = monedasDisponibles;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {
        System.out.println("**********************************************************************************");
        System.out.println("A continuación digíte la opción que desea realizar:");
        System.out.println("1) Realizar una conversión");
        System.out.println("2) Consultar historial de conversiones");
        System.out.println("3) Salir");
        System.out.println("**********************************************************************************");
    }

    public int leerOpcionPrincipal() {
        System.out.print("Elija una opción válida: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // O cualquier otro valor que indique una opción inválida
        }
    }

    public void mostrarMenuMonedas(String tipo) {
        System.out.println("Seleccione la denominación para " + tipo + ":");
        for (String moneda : monedasDisponibles) {
            System.out.println(moneda + " --- " + obtenerNombreMoneda(moneda));
        }
        System.out.println("**********************************************************************************");
    }

    public String leerOpcionMoneda(String tipo) {
        System.out.print("Ingrese la opción de moneda: ");
        String moneda = scanner.nextLine().toUpperCase();
        if (!monedasDisponibles.contains(moneda)) {
            System.out.println("Moneda no válida. Intente de nuevo.");
            return leerOpcionMoneda(tipo);
        }
        return moneda;
    }

    public double leerCantidadACambiar() {
        System.out.print("Seleccione la cantidad que desea cambiar: ");
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Cantidad inválida. Intente de nuevo.");
            return leerCantidadACambiar();
        }
    }

    public void imprimirConversiones(List<Conversion> historialDeConversiones) {
        if (historialDeConversiones.isEmpty()) {
            System.out.println("No hay conversiones en el historial.");
            return;
        }
        System.out.println("Historial de conversiones:");
        for (Conversion conversion : historialDeConversiones) {
            System.out.println(conversion);
        }
    }

    private String obtenerNombreMoneda(String codigo) {
        switch (codigo) {
            case "USD":
                return "Dólar Estadounidense";
            case "ARS":
                return "Peso Argentino";
            case "BRL":
                return "Real Brasileño";
            case "EUR":
                return "Euro";
            case "GBP":
                return "Libra Esterlina";
            case "AUD":
                return "Dólar Australiano";
            case "CAD":
                return "Dólar Canadiense";
            case "CHF":
                return "Franco Suizo";
            case "JPY":
                return "Yen Japonés";
            case "CNY":
                return "Yuan Chino";
            default:
                return "Moneda desconocida";
        }
    }
}
