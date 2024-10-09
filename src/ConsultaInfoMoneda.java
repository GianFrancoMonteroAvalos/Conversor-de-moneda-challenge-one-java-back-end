import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConsultaInfoMoneda {
    private static final String API_KEY = "ead8ad5283df21623f9382a7";

    public double buscaMoneda(String base, String target, double amount) throws DataNotAvailableException {
        String urlString = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%s",
                API_KEY, base, target, amount);
        HttpURLConnection conn = null;

        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
                if ("success".equals(jsonResponse.get("result").getAsString())) {
                    return jsonResponse.get("conversion_result").getAsDouble();
                } else {
                    throw new DataNotAvailableException("Error en la respuesta: " + jsonResponse.get("error-type").getAsString());
                }
            } else {
                throw new DataNotAvailableException("Error: " + responseCode);
            }
        } catch (Exception e) {
            throw new DataNotAvailableException("Error al realizar la consulta: " + e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public static class DataNotAvailableException extends Exception {
        public DataNotAvailableException(String message) {
            super(message);
        }
    }
}
