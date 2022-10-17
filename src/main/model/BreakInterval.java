package model;

public class BreakInterval {
    public static final String DEFAULT_BREAK_NAME = "short break";
    public static final int DEFAULT_BREAK_TIME = 300;

    private String intervalName;
    private int breakDuration;

    // EFFECTS: creates a default break interval with a name and duration (in seconds)
    public BreakInterval() {
        this.intervalName = DEFAULT_BREAK_NAME;
        this.breakDuration = DEFAULT_BREAK_TIME;
    }

    // MODIFIES: this
    // EFFECTS: lets the user set a specific (break) time interval with a given name
    public void setBreakInterval(String name, int duration) {
        this.intervalName = name;
        this.breakDuration = duration;
    }

    // EFFCTS: getter
    public String getBreakIntervalName() {
        return intervalName;
    }

    // EFFECTS: getter
    public int getBreakIntervalDuration() {
        return breakDuration;
    }
}
