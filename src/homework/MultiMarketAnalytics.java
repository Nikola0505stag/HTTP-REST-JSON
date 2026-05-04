package homework;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

record Post(int userId, int id, String title) {}
record Comment(int postId, int id, String body) {}

public class MultiMarketAnalytics {
    private final static String URL1 = "https://jsonplaceholder.typicode.com/posts";
    private final static String URL2 = "https://jsonplaceholder.typicode.com/comments";
    private final static Gson GSON = new Gson();

    static void main() throws Exception{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requestPosts = HttpRequest.newBuilder()
                .uri(URI.create(URL1))
                .GET()
                .build();

        HttpRequest requestComments = HttpRequest.newBuilder()
                .uri(URI.create(URL2))
                .GET()
                .build();


        String jsonPosts = client.send(requestPosts, HttpResponse.BodyHandlers.ofString()).body();
        String jsonComments = client.send(requestComments, HttpResponse.BodyHandlers.ofString()).body();

        Type typePosts = new TypeToken<List<Post>>() { } .getType();
        Type typeComments = new TypeToken<List<Comment>>() { } .getType();

        List<Post> posts = GSON.fromJson(jsonPosts, typePosts);
        List<Comment> comments = GSON.fromJson(jsonComments, typeComments);

        List<Post> postsFilteredID = posts.stream()
                .filter(m -> m.id() >= 1 && m.id() <= 3)
                .toList();

        Map<Integer, Integer> countComments = new HashMap<>(); // postId, count

        for (Comment comment : comments) {
            int postId = comment.postId();

            if (countComments.containsKey(postId)) {
                int count = countComments.get(postId);
                count += 1;
                countComments.put(postId, count);
            } else {
                countComments.put(postId, 1);
            }
        }

        Map<Post, Integer> postsComments = new LinkedHashMap<>();

        for (Post post : posts) {
            int id = post.id();
            int cntComments = countComments.get(id);
            postsComments.put(post, cntComments);
        }

        Post maxPost = null;
        int maxComments = Integer.MIN_VALUE;

        for (Map.Entry<Post, Integer> entry : postsComments.entrySet()) {
            if (entry.getValue() > maxComments) {
                maxComments = entry.getValue();
                maxPost = entry.getKey();
            }
        }

        if (maxPost != null) {
            System.out.println("Most Popular Post ID: " + maxPost.id() + " with " + maxComments + " comments.");
        }

    }

}
