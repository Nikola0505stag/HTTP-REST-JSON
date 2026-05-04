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

record Odds(int postId, int id, String email) {}

public class OddsChecker {
    private static final String URL = "https://jsonplaceholder.typicode.com/comments";
    private static final Gson GSON = new Gson();

    static void main() throws Exception{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .build();

        String json = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        // System.out.println(json);
        Type type = new TypeToken<ArrayList<Odds>> () {} .getType();
        List<Odds> odds = GSON.fromJson(json, type);

        List<String> emails = odds.stream()
                .filter(m -> m.id() > 100)
                .sorted(Comparator.comparing(Odds::id))
                .limit(3)
                .map(m -> m.email())
                .toList();

        for (String email : emails) {
            System.out.println(email);
        }
    }
}
