package model;

public class PomodoroInterval {
    private StudyInterval studyInterval;
    private BreakInterval breakInterval;

    public PomodoroInterval(StudyInterval studyInterval, BreakInterval breakInterval) {
        this.studyInterval = studyInterval;
        this.breakInterval = breakInterval;
    }

//    public PomodoroInterval() {}

    // EFFECTS: getter
    public StudyInterval getPomoStudyInterval() {
        return studyInterval;
    }

    // EFFECTS: getter
    public BreakInterval getPomoBreakInterval() {
        return breakInterval;
    }

    // EFFECTS: add a given pomodoro interval onto the list of intervals to run
    public void add(PomodoroInterval interval) {
    }
}
