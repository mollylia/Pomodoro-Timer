package ui;

import model.PomodoroTimer;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PomodoroTimerData {
    private static final String JSON_STORE = "./data/pomodorotimer.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);


    // EFFECTS: saves Pomodoro timer to file
    public void savePomodoroTimer(PomodoroTimer pomoTimer) {
        try {
            jsonWriter.open();
            jsonWriter.write(pomoTimer);
            jsonWriter.close();
            System.out.println("Saved " + pomoTimer.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Pomodoro timer from file
    public PomodoroTimer loadPomodoroTimer(PomodoroTimer pomoTimer) {
        try {
            pomoTimer = jsonReader.read();
            System.out.println("Loaded " + pomoTimer.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

        return pomoTimer;
    }
}
