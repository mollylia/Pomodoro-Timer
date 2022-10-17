package model;

public class PomodoroInterval {
    private StudyInterval studyInterval;
    private BreakInterval breakInterval;

    public PomodoroInterval(StudyInterval studyInterval, BreakInterval breakInterval) {
        this.studyInterval = studyInterval;
        this.breakInterval = breakInterval;
    }

//    // EFFECTS: add a given pomodoro interval onto the list of intervals to run
//    public void addPomodoroInterval(StudyInterval studyInterval, BreakInterval breakInterval) {
//    }

    // EFFECTS: getter
    public StudyInterval getPomoStudyInterval() {
        return studyInterval;
    }

    // EFFECTS: getter
    public BreakInterval getPomoBreakInterval() {
        return breakInterval;
    }


}
