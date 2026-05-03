package homework;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class BasicFetcher {

    public void fetchData(String url) {

        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofMinutes(1))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
        } catch (Exception e) {
            System.out.println("Error occurred " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void main() {
        BasicFetcher fetcher = new BasicFetcher();
        fetcher.fetchData("https://jsonplaceholder.typicode.com/posts/1");
    }
}
