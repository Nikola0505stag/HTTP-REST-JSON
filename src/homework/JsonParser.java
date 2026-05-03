package homework;

import com.google.gson.Gson;

record Candidate(String name, int score_test){}

public class JsonParser {
    private static final String JSON = "{\"name\": \"Java Expert\", \"score\": 95}";
    private static Gson GSON = new Gson();

    static void main() {
        Candidate candidate = GSON.fromJson(JSON, Candidate.class);
        System.out.println("Candidate " + candidate.name() + " has a score of " + candidate.score_test());
    }

}
