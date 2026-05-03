package gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class DevCollectionsMain {
    static void main() {
        List<Developer> devs = List.of(
                new Developer("Kelsey", 28, "DraftKings"),
                new Developer("Wesley", 20, "Google, Inc."));

        Gson gson = new Gson();
        String json = gson.toJson(devs);

        IO.println(json);

        Type type = new TypeToken<List<Developer>>() { }.getType();
        List<Developer> devsAgain = gson.fromJson(json, type);
        IO.println(devsAgain.size()); // 2

        // Map example
        json = "{ \"apple\":1, \"banana\":2, \"cherry\":3}";
        Map<String, Integer> map = gson.fromJson(json, new TypeToken<Map<String, Integer>>() { } .getType());
        IO.println(map);

    }
}
