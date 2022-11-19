package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents the list of Pomodoro intervals to run
public class PomodoroTimer implements Writable {
    private String name;
    private List<PomodoroInterval> pomoIntervals;
    private int indexCount;

    // EFFECTS: constructs an empty collection of intervals to run
    public PomodoroTimer() {
        pomoIntervals = new ArrayList<>();
        indexCount = 0;
    }

    // EFFECTS: constructs an empty collection of intervals with a name
    public PomodoroTimer(String name) {
        this.name = name;
        pomoIntervals = new ArrayList<>();
        indexCount = 0;
    }

    // EFFECTS: returns the name of the Pomodoro timer, getter
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds a new pomodoro interval to the collection of intervals to be run
    public void addInterval(PomodoroInterval interval) {
        pomoIntervals.add(interval);
    }

    // REQUIRES: the list of intervals to not be empty
    // MODIFIES: this
    // EFFECTS: returns the next time interval in the list to be run
    public PomodoroInterval getNextInterval() {
        PomodoroInterval nextInterval = null;
        if (indexCount == pomoIntervals.size()) {
            nextInterval = null;
        } else {
            nextInterval = pomoIntervals.get(indexCount);
            indexCount++;
        }
        return nextInterval;
    }

    // EFFECTS: checks if the collection of intervals to be run is empty
    public Boolean isEmpty() {
        if (pomoIntervals.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns the number of intervals in the collection to be run, getter
    public int length() {
        return pomoIntervals.size();
    }

    // Method was based on WorkRoom.getThingies() in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: returns an unmodified list of Pomodoro intervals in the Pomodoro timer
    public List<PomodoroInterval> getPomoIntervals() {
        return Collections.unmodifiableList(pomoIntervals);
    }


    // Method was based on WorkRoom.toJson() in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: changes interval data to JSon
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("pomoIntervals", pomoIntervalsToJson());
        return json;
    }

    // Method was based on WorkRoom.thingiesToJson() in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: returns the intervals in the timer as a JSON array
    private JSONArray pomoIntervalsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (PomodoroInterval pomoInterval : pomoIntervals) {
            jsonArray.put(pomoInterval.toJson());
        }
        return jsonArray;
    }
}

