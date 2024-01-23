package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.*;

// Represents the list of Pomodoro intervals to run
public class PomodoroTimer implements Writable {
    private String name;
    private boolean started;
    private List<PomodoroInterval> pomoIntervals;
    private int indexCount;

    private Timer timer;

    // EFFECTS: constructs an empty collection of intervals to run
    public PomodoroTimer() {
        pomoIntervals = new ArrayList<>();
        started = false;
        indexCount = 0;
    }

    // EFFECTS: constructs an empty collection of intervals with a name
    public PomodoroTimer(String name) {
        this.name = name;
        pomoIntervals = new ArrayList<>();
        indexCount = 0;
    }

    // MODIFIES: this
    // EFFECTS: adds a new pomodoro interval to the collection of intervals to be run
    public void addInterval(PomodoroInterval interval) {
        pomoIntervals.add(interval);
        EventLog.getInstance().logEvent(new Event("Added interval: " + interval.getName()
                + " (" + interval.getIntervalType() + ", " + interval.getDuration() + " seconds)"));
    }

    // REQUIRES: the list of intervals to not be empty
    // MODIFIES: this
    // EFFECTS: returns the next time interval in the list to be run
    public PomodoroInterval getNextInterval() {
        PomodoroInterval nextInterval;
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
        return pomoIntervals.isEmpty();
    }

    // EFFECTS: starts the pomodoro timer and logs the event
    public void start(Timer timer, TimerTask timerInterval) {
        started = true;
        this.timer = timer;
        this.timer = new Timer();
        this.timer.schedule(timerInterval, 0, 1000);
        EventLog.getInstance().logEvent(new Event("Started Pomodoro Timer"));
    }

    // MODIFIES: timer
    // EFFECTS: stops the pomodoro timer and logs the event
    public void stop() {
        started = false;
        timer.cancel();
        EventLog.getInstance().logEvent(new Event("Stopped Pomodoro Timer"));
    }

    // EFFECTS: returns the name of the Pomodoro timer, getter
    public String getName() {
        return name;
    }

    // EFFECTS: checks if the timer has been started
    public Boolean hasStarted() {
        return started;
    }

    // EFFECTS: returns the number of intervals in the collection to be run, getter
    public int length() {
        return pomoIntervals.size();
    }

    // EFFECTS: returns an unmodified list of Pomodoro intervals in the Pomodoro timer
    public List<PomodoroInterval> getPomoIntervals() {
        return Collections.unmodifiableList(pomoIntervals);
    }

    // EFFECTS: changes interval data to JSon
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("pomoIntervals", pomoIntervalsToJson());
        return json;
    }

    // EFFECTS: returns the intervals in the timer as a JSON array
    private JSONArray pomoIntervalsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (PomodoroInterval pomoInterval : pomoIntervals) {
            jsonArray.put(pomoInterval.toJson());
        }
        return jsonArray;
    }
}

