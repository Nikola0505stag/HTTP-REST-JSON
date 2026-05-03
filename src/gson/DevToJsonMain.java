package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DevToJsonMain {
    static void main() {
        Developer dev = new Developer("Nikola", 28, "DraftKings");
        DevManager devManager = new DevManager("Mike", "DraftKings &", 7);

        Gson gson = new Gson();
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String devJson = gson.toJson(dev);
        IO.println(devJson);

        // Gson version 2.10 and later also supports records
        Gson gsonNonEscaping = new GsonBuilder()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();
        String devManagerJson = gsonNonEscaping.toJson(devManager);
        IO.println(devManagerJson);
    }
}
