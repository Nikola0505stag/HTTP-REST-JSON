package homework;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

record GameResult(int userId, int id, String title, boolean completed){}

public class TheVIPТournamentLeaderboard {
    private static final String URL = "https://jsonplaceholder.typicode.com/todos";
    private static final Gson GSON = new Gson();

    static void main() throws Exception{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .build();
        String jsonResponse = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        Type type = new TypeToken<ArrayList<GameResult>>() {} .getType();
        List<GameResult> result = GSON.fromJson(jsonResponse, type);

        List<GameResult> completedGames = result.stream()
                .filter(m -> m.completed() == true)
                .filter(m -> m.userId() >= 1 && m.userId() <= 5)
                .toList();

        Map<Integer, Integer> numberOfGames = new HashMap<>();

        for (GameResult game : completedGames) {
            int userId = game.userId();

            if (numberOfGames.containsKey(userId)) {
                int count = numberOfGames.get(userId);
                count += 1;
                numberOfGames.put(userId, count);
            } else {
                numberOfGames.put(userId, 1);
            }
        }
        numberOfGames.forEach((key, value) -> {
            System.out.println("Key: " + key + ", Value: " + value);
        });

    }

}
