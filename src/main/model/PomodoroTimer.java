package model;

import java.util.*;

public class PomodoroTimer extends ArrayList {
    private List<PomodoroInterval> pomoIntervals;

    // EFFECTS: constructs an empty collection of intervals to run
    public PomodoroTimer() {
        this.pomoIntervals = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new pomodoro interval to the collection of intervals to be run
    public void addInterval(StudyInterval studyInterval, BreakInterval breakInterval) {
        this.pomoIntervals.add(new PomodoroInterval(studyInterval, breakInterval));
    }
}

