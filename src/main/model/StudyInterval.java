package model;

public class StudyInterval {
    public static final String DEFAULT_STUDY_NAME = "short study session";
    public static final int DEFAULT_STUDY_TIME = 1500;

    private String intervalName;
    private int studyDuration;

    // EFFECTS: constructs a default study interval with a name and duration (in seconds)
    public StudyInterval() {
        this.intervalName = DEFAULT_STUDY_NAME;
        this.studyDuration = DEFAULT_STUDY_TIME;
    }

    // REQUIRES: duration > 0
    // MODIFIES: this
    // EFFECTS: constructs set a specific (study) time interval with a given name and duration (in seconds)
    public void setStudyInterval(String name, int duration) {
        this.intervalName = name;
        this.studyDuration = duration;
    }

    // EFFECTS: getter
    public String getStudyIntervalName() {
        return intervalName;
    }

    // EFFECTS: getter
    public int getStudyIntervalDuration() {
        return studyDuration;
    }
}
