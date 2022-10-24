package model;

public class PomodoroInterval {
    private Boolean status;
    private String name;
    private int duration;

    // REQUIRES: duration >= 0
    // EFFECTS: constructs a pomodoro interval with the given name, time interval, and status.
    //          the status is true if the duration is for studying and false if it is for taking a break
    public PomodoroInterval(Boolean status, String name, int duration) {
        this.status = status;
        this.name = name;
        this.duration = duration;
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
}