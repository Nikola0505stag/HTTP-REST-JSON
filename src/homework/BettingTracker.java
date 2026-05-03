package homework;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

class EmptyDataException extends RuntimeException {
    public EmptyDataException(String message) {
        super(message);
    }
    public EmptyDataException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

record Bet(int userId, int id, String title, boolean completed){}
// userId = PlayerId; id = BetID; title = Event Name; completed = Winning Bet?


public class BettingTracker {
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    private static final String URL = "https://jsonplaceholder.typicode.com/todos";

    static void main() throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .build();

        String jsonResponse = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();

        Type type = new TypeToken<ArrayList<Bet>> () {}.getType();
        List<Bet> bets = GSON.fromJson(jsonResponse, type);

        if (bets.isEmpty()) {
            throw new EmptyDataException("Empty data!");
        }

        List<Bet> completedBets = bets.stream()
                .filter(m -> m.completed() == true)
                .filter(m -> m.userId() == 2)
                .toList();
        System.out.println(completedBets.size());

        StringBuilder stringBuilder = new StringBuilder();

        for (var bet : completedBets) {
            String title = bet.title();
            stringBuilder.append(title + ",");
        }
        System.out.println(stringBuilder);

    }
}
