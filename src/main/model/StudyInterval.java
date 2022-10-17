package model;

public class StudyInterval {
    public static final String DEFAULT_STUDY_NAME = "short study session";
    public static final int DEFAULT_STUDY_TIME = 1500;

    private String intervalName;
    private int studyDuration;

    // EFFECTS: creates a default study interval with a name and duration (in seconds)
    public StudyInterval() {
        this.intervalName = DEFAULT_STUDY_NAME;
        this.studyDuration = DEFAULT_STUDY_TIME;
    }

    // MODIFIES: this
    // EFFECTS: lets the user set a specific (study) time interval with a given name
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
