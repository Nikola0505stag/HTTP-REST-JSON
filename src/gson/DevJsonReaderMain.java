package gson;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DevJsonReaderMain {
    static void main() throws IOException {

        Gson gson = new Gson();

        try (Reader reader = Files.newBufferedReader(
                Path.of("src","gson", "devs.json"))) {
            List<Developer> devs = gson.fromJson(reader, List.class);
            IO.println(devs);
        }

    }
}
