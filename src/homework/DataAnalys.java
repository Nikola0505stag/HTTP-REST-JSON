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

record Match(int userID, int id, String title, String body) { }

public class DataAnalys {
    private static final String URL = "https://jsonplaceholder.typicode.com/posts";
    private static final Gson GSON = new Gson();

    static void main() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .build();

        try {
            String jsonResponse = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            Type listType = new TypeToken<ArrayList<Match>>() {} .getType();
            List<Match> matches = GSON.fromJson(jsonResponse, listType);

            matches.stream()
                    .filter(m -> m.userID() == 1)
                    .sorted(Comparator.comparing(Match::title))
                    .limit(5)
                    .forEach(m -> {
                        System.out.println("ID: " + m.id() + " | Title" + m.title());
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
