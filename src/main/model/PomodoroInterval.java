package model;

import org.json.JSONObject;
import persistence.Writable;


// Represents an interval with a status, name, and duration
public class PomodoroInterval implements Writable {
    private Boolean status;          // the type of the interval: study (true) or break (false)
    private String name;             // the name of the interval reminding the user of the task to work on
    private int duration;            // the duration of the time interval in seconds


    // EFFECTS: constructs a pomodoro interval with the given name, time interval, and status.
    //          the status is true if the duration is for studying and false if it is for taking a break
    public PomodoroInterval(Boolean status, String name, int duration) {
        this.status = status;
        this.name = name;
        this.duration = duration;
    }

    // EFFECTS: sets the status of the interval, setter
    public void setStatus(Boolean newStatus) {
        this.status = newStatus;
    }

    // EFFECTS: sets the name of the interval, setter
    public void setName(String newName) {
        this.name = newName;
    }

    // EFFECTS: sets the duration of the interval, setter
    public void setDuration(int newDuration) {
        this.duration = newDuration;
    }

    // EFFECTS: returns the status of the interval, getter
    public Boolean getStatus() {
        return status;
    }

    // EFFECTS: returns the name of the interval, getter
    public String getName() {
        return name;
    }

    // EFFECTS: returns the duration of the interval, getter
    public int getDuration() {
        return duration;
    }

    // EFFECTS: represents string representation of this interval
    public String toString() {
        return getIntervalType() + ": " + name + " (" + duration + " seconds)";
//        return name + " (" + getIntervalType() + "): " + duration + " seconds";
    }

    // EFFECTS: returns the type of interval as string, either study or break
    public String getIntervalType() {
        if (status) {
            return "study";
        } else {
            return "break";
        }
    }

    // Method was based on Thingy.toJson() in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: changes interval data to JSon
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("status", status);
        json.put("name", name);
        json.put("duration", duration);
        return json;
    }
}