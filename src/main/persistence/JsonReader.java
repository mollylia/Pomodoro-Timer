package persistence;

import model.Event;
import model.EventLog;
import model.PomodoroInterval;
import model.PomodoroTimer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Based on JsonReader in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
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
        EventLog.getInstance().logEvent(new Event("Loaded Pomodoro Timer"));

        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePomodoroTimer(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Pomodoro timer from JSON object and returns it
    private PomodoroTimer parsePomodoroTimer(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        PomodoroTimer pomoTimer = new PomodoroTimer("My Pomodoro Timer");
        addIntervals(pomoTimer, jsonObject);
        return pomoTimer;
    }

    // MODIFIES: pomoTimer
    // EFFECTS: parses intervals from JSON object and adds them to the timer
    private void addIntervals(PomodoroTimer pomoTimer, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("pomoIntervals");
        for (Object json : jsonArray) {
            JSONObject nextInterval = (JSONObject) json;
            addInterval(pomoTimer, nextInterval);
        }
    }

    // MODIFIES: pomoTimer
    // EFFECTS: parses intervals from JSON object and adds it to the timer
    private void addInterval(PomodoroTimer pomoTimer, JSONObject jsonObject) {
        Boolean status = jsonObject.getBoolean("status");
        String name = jsonObject.getString("name");
        int duration = jsonObject.getInt("duration");
        PomodoroInterval pomoInterval = new PomodoroInterval(status,name,duration);
        pomoTimer.addInterval(pomoInterval);
    }
}
