package gson;

import com.google.gson.Gson;

public class DevFromJsonMain {
    static void main() {
        String json = "{\"name\": \"Wesley\", \"age\": 20 }";

        Gson gson = new Gson();
        Developer dev = gson.fromJson(json, Developer.class);

        // if there is properties without initialization in json the value becomes the default value of the class
        System.out.printf("%s, %d years old, %s%n", dev.getName(), dev.getAge(), dev.getCompany());
    }
}
