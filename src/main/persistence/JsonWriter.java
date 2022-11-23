package persistence;

import model.PomodoroTimer;
import org.json.JSONObject;


import java.io.*;

// Based on JsonWriter in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Pomodoro Timer to file
    public void write(PomodoroTimer pomoTimer) {
        JSONObject json = pomoTimer.toJson();
        saveToFile(json.toString(TAB));
    }

    // EFFECTS: writes JSON file as string
    public String writeToString(PomodoroTimer pomoTimer) {
        String output = "";
        JSONObject json = pomoTimer.toJson();
        output = json.toString(TAB);
        return output;
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}

