package ui;

import model.PomodoroDefault;
import model.PomodoroInterval;
import model.PomodoroTimer;

import java.util.TimerTask;

// Displays the running time of the current Pomodoro interval
public class PomodoroTimerDisplay extends TimerTask {
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

        this.pomoTimer = pomoTimer;
        totalIntervalCounter = pomoTimer.length();
        getNextInterval();
    }

    // EFFECTS: gets the time and status of the next interval
    public void getNextInterval() {
        PomodoroInterval nextInterval = pomoTimer.getNextInterval();

        if (nextInterval != null) {
            runTime = nextInterval.getDuration();
            isStudy = nextInterval.getStatus();
        } else {
            initializeInterval();
        }
    }

    // EFFECTS: initializes the time
    public void initializeInterval() {
        PomodoroInterval study1 = new PomodoroInterval(true, "study: CPSC 210", 5);
        PomodoroInterval break1 = new PomodoroInterval(false, "break", 3);
        PomodoroInterval study2 = new PomodoroInterval(true, "study: CPSC 210 lab", 5);

        pomoTimer = new PomodoroTimer();
        pomoTimer.addInterval(study1);
        pomoTimer.addInterval(break1);
        pomoTimer.addInterval(study2);

        totalIntervalCounter = pomoTimer.length();
    }

    // EFFECTS: runs the timer with the set intervals
    @Override
    public void run() {
        if ((timeElapsed < runTime) && (intervalElapsed < totalIntervalCounter)) {
            long durationInSeconds = runTime - timeElapsed;
            long second = (durationInSeconds) % 60;
            long minute = (durationInSeconds / (60)) % 60;
            timeElapsed += 1;
            System.out.println(minute + ":" + second);

            if (!(isStudy)) {
                breakTime(runTime);
            } else {
                studyTime(runTime);
            }

        } else {
            System.out.println("Session complete!");
            System.exit(0);
        }
    }

    // EFFECTS: checks to see if break interval has ended, if so notify the user
    public void breakTime(int breakTime) {
        if (timeElapsed == breakTime) {
            timeElapsed = 0;
            intervalElapsed += 1;
            getNextInterval();
            System.out.println("Break time is over!");
        }
    }

    // EFFECTS: checks to see if study interval has ended, if so notify the user
    public void studyTime(int studyTime) {
        if (timeElapsed == studyTime) {
            timeElapsed = 0;
            intervalElapsed += 1;
            getNextInterval();
            System.out.println("Time for a break!");
        }
    }
}


// EFFECTS: runs the timer and notifies the user of the end of a break
//    public void breakTime(int time) {
//        if (timeElapsed == time) {
//            timeElapsed = 0;
//            intervalElapsed += 1;
//            getNextInterval();
//            System.out.println("Break time is over!");
//        }
//    }


// EFFECTS: runs the timer and notifies the user of the start of a break
//        public void studyTime (int time){
//            if (timeElapsed == time) {
//                timeElapsed = 0;
//                intervalElapsed += 1;
//                getNextInterval();
//                System.out.println("Time for a break!");
//            }
//        }


//    // EFFECTS: initializes the time
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


//if (interval.getDuration() == PomodoroDefault.getPomodoroDefault(PomodoroDefault.SHORT_STUDY)) {
//        studyTime(PomodoroDefault.SHORT_STUDY);
//        } else if (interval.getDuration() == PomodoroDefault.getPomodoroDefault(PomodoroDefault.LONG_STUDY)) {
//        studyTime(PomodoroDefault.LONG_STUDY);
//        } else if (interval.getDuration() == PomodoroDefault.getPomodoroDefault(PomodoroDefault.SHORT_BREAK)) {
//        breakTime(PomodoroDefault.SHORT_BREAK);
//        } else if (interval.getDuration() == PomodoroDefault.getPomodoroDefault(PomodoroDefault.LONG_BREAK)) {
//        breakTime(PomodoroDefault.LONG_BREAK);
//        }