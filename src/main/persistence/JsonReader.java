package persistence;

import model.PomodoroInterval;
import model.PomodoroTimer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PomodoroTimer read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePomodoroTimer(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private PomodoroTimer parsePomodoroTimer(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        PomodoroTimer pomoTimer = new PomodoroTimer();
        addThingies(pomoTimer, jsonObject);
        return pomoTimer;
    }

    // MODIFIES: pomoTimer
    // EFFECTS: parses intervals from JSON object and adds them to the timer
    private void addThingies(PomodoroTimer pomoTimer, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("intervals");
        for (Object json : jsonArray) {
            JSONObject nextInterval = (JSONObject) json;
            addThingy(pomoTimer, nextInterval);
        }
    }

    // MODIFIES: pomoTimer
    // EFFECTS: parses intervals from JSON object and adds it to the timer
    private void addThingy(PomodoroTimer pomoTimer, JSONObject jsonObject) {
        Boolean status = Boolean.valueOf(jsonObject.getString("status"));
        String name = jsonObject.getString("name");
        int duration = jsonObject.getInt("duration");
        PomodoroInterval pomoInterval = new PomodoroInterval(status,name,duration);
        pomoTimer.addInterval(pomoInterval);
    }
}
