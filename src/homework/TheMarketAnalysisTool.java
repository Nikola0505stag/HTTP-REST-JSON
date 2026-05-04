package homework;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

record MarketData(int userId, int id, String title, String body){}

class EmptyDataException2 extends RuntimeException {
    public EmptyDataException2(String message) {
        super(message);
    }

    public EmptyDataException2(String message, Throwable throwable) {
        super(message, throwable);
    }
}

public class TheMarketAnalysisTool {
    private static final String URL = "https://jsonplaceholder.typicode.com/posts";
    private static final Gson GSON = new Gson();

    static void main() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .build();

            String json = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

            Type type = new TypeToken<ArrayList<MarketData>>(){ }.getType();

            List<MarketData> data = GSON.fromJson(json, type);

            List<MarketData> filteredDataByID = data.stream()
                    .filter(m -> m.id() == 3)
                    .toList();

            MarketData largestTitle = null;
            int maxLength = Integer.MIN_VALUE;
            for (MarketData filtered : filteredDataByID) {
                if (filtered.title().length() > maxLength) {
                    maxLength = filtered.title().length();
                    largestTitle = filtered;
                }
            }

            if (largestTitle == null) {
                throw new EmptyDataException2("No data for player with id == 3 !");
            }

            System.out.println(largestTitle);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
