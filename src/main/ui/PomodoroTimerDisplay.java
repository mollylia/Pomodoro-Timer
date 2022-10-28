package ui;

import model.PomodoroInterval;
import model.PomodoroTimer;

import java.util.TimerTask;

// Displays the running time of the current Pomodoro interval
public class PomodoroTimerDisplay extends TimerTask {
    private static final int DEFAULT_STUDY_TIME = 5;         // time in seconds
    private static final int DEFAULT_BREAK_TIME = 3;         // time in seconds

    private int runTime;
    private int timeElapsed;
    private int intervalElapsed;
    private int totalIntervalCounter;
    private boolean isStudy;
    private PomodoroTimer pomoTimer;

    // EFFECTS: initializes the time and intervals passed to 0
    public PomodoroTimerDisplay(PomodoroTimer pomoTimer) {
        timeElapsed = 0;
        intervalElapsed = 0;

        //initializeInterval();
        this.pomoTimer = pomoTimer;
        totalIntervalCounter = pomoTimer.length();
        getNextInterval();
    }

    // EFFECTS: initializes the time
//    public void initializeInterval() {
//        PomodoroInterval study1 = new PomodoroInterval(true, "study: CPSC 210", 5);
//        PomodoroInterval break1 = new PomodoroInterval(false, "break", 3);
//        PomodoroInterval study2 = new PomodoroInterval(true, "study: CPSC 210 lab", 5);
//
//        pomoTimer = new PomodoroTimer();
//        pomoTimer.addInterval(study1);
//        pomoTimer.addInterval(break1);
//        pomoTimer.addInterval(study2);
//
//        totalIntervalCounter = pomoTimer.length();
//    }

    // EFFECTS: gets the time and status of the next interval
    public void getNextInterval() {
        PomodoroInterval nextInterval = pomoTimer.getNextInterval();

        if (nextInterval != null) {
            runTime = nextInterval.getDuration();
            isStudy = nextInterval.getStatus();
        }
    }


    @Override
    public void run() {
        if ((timeElapsed < runTime) && (intervalElapsed < totalIntervalCounter)) {
            long durationInSeconds = runTime - timeElapsed;
            long second = (durationInSeconds) % 60;
            long minute = (durationInSeconds / (60)) % 60;
            timeElapsed += 1;

            System.out.println(minute + ":" + second);

            if (!(isStudy)) {
                breakTime();
            } else {
                studyTime();
            }
        } else {
            System.out.println("Session complete!");
            System.exit(0);
        }
    }

    public void breakTime() {
        if (timeElapsed == DEFAULT_BREAK_TIME) {
            timeElapsed = 0;
            intervalElapsed += 1;
            getNextInterval();
            System.out.println("Break time is over!");
        }
    }

    public void studyTime() {
        if (timeElapsed == DEFAULT_STUDY_TIME) {
            timeElapsed = 0;
            intervalElapsed += 1;
            getNextInterval();
            System.out.println("Time for a break!");
        }
    }
}