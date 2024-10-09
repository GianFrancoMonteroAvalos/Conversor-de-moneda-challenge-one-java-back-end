import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class HistorialConversiones {
    private static final String CARPETA_DATOS = "data";
    private static final String ARCHIVO_HISTORIAL = "historial_conversiones.json";
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setPrettyPrinting()
            .create();

    public static void guardarHistorial(ArrayList<Conversion> listaDeConversiones) {
        try {
            String rutaCompleta = Paths.get(CARPETA_DATOS, ARCHIVO_HISTORIAL).toAbsolutePath().toString();
            Files.createDirectories(Paths.get(CARPETA_DATOS));

            String json = gson.toJson(listaDeConversiones);

            FileWriter writer = new FileWriter(rutaCompleta);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el historial: " + e.getMessage());
        }
    }

    public static ArrayList<Conversion> cargarHistorial() {
        try {
            String rutaCompleta = Paths.get(CARPETA_DATOS, ARCHIVO_HISTORIAL).toAbsolutePath().toString();
            File archivo = new File(rutaCompleta);
            if (!archivo.exists()) {
                return new ArrayList<>();
            }

            String json = new String(Files.readAllBytes(Paths.get(rutaCompleta)));

            Type tipoLista = new TypeToken<ArrayList<Conversion>>() {}.getType();
            ArrayList<Conversion> listaDeConversiones = gson.fromJson(json, tipoLista);

            if (listaDeConversiones == null) {
                return new ArrayList<>();
            }

            return listaDeConversiones;
        } catch (IOException e) {
            System.out.println("Error al cargar el historial: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
