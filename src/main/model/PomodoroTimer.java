package model;

import java.util.*;

public class PomodoroTimer {
    private ArrayList<PomodoroInterval> pomoIntervals;
    private int indexCount;

    // EFFECTS: constructs an empty collection of intervals to run
    public PomodoroTimer() {
        pomoIntervals = new ArrayList<>();
        indexCount = 0;
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
        PomodoroInterval nextInterval = pomoIntervals.get(indexCount);
        indexCount++;
        return nextInterval;
    }

    public int length() {
        return pomoIntervals.size();
    }

    public Boolean isEmpty() {
        if (pomoIntervals.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
