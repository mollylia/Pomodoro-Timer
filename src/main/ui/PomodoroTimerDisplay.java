package ui;

import model.PomodoroInterval;
import model.PomodoroTimer;

import javax.swing.*;
import java.util.TimerTask;

// Displays the running time of the current Pomodoro interval
public class PomodoroTimerDisplay extends TimerTask {
    private int runTime;
    private int timeElapsed;
    private int intervalElapsed;
    private int totalIntervalCounter;
    private boolean isStudy;
    private PomodoroTimer pomoTimer;
    private JLabel timerDisplay;

    // EFFECTS: initializes the time and intervals passed to 0
    public PomodoroTimerDisplay(PomodoroTimer pomoTimer, JLabel runTime) {
        timeElapsed = 0;
        intervalElapsed = 0;
        timerDisplay = runTime;

        if (timerDisplay == null) {
            timerDisplay = new JLabel("No Pomodoro Timer");
        }

        this.pomoTimer = pomoTimer;
        if (pomoTimer != null) {
            totalIntervalCounter = pomoTimer.length();
            getNextInterval();
        } else {
            totalIntervalCounter = 0;
        }
    }

    // EFFECTS: gets the time and status of the next interval
    public void getNextInterval() {
        PomodoroInterval nextInterval = pomoTimer.getNextInterval();

        if (nextInterval != null) {
            runTime = nextInterval.getDuration();
            isStudy = nextInterval.getStatus();
        }
    }

    // EFFECTS: runs the timer with the set intervals
    @Override
    public void run() {
        if ((timeElapsed < runTime) && (intervalElapsed < totalIntervalCounter)) {
            long durationInSeconds = runTime - timeElapsed;
            long second = (durationInSeconds) % 60;
            long minute = (durationInSeconds / 60) % 60;

            String formatedSec = String.format("%02d", second);
            String formatedMin = String.format("%02d", minute);

            timeElapsed += 1;
            //System.out.println(minute + ":" + second);
            // timerDisplay.setText(minute + ":" + second);
            timerDisplay.setText(formatedMin + ":" + formatedSec);

            if (!(isStudy)) {
                breakTime(runTime);
            } else {
                studyTime(runTime);
            }

        } else {
//            System.out.println("Session complete!");
            timerDisplay.setText("Session complete!");
            //System.exit(0);
        }
    }

    // EFFECTS: checks to see if break interval has ended, if so notify the user
    public void breakTime(int breakTime) {
        if (timeElapsed == breakTime) {
            timeElapsed = 0;
            intervalElapsed += 1;
            getNextInterval();
//            System.out.println("Break time is over!");
            timerDisplay.setText("Break time is over!");
        }
    }

    // EFFECTS: checks to see if study interval has ended, if so notify the user
    public void studyTime(int studyTime) {
        if (timeElapsed == studyTime) {
            timeElapsed = 0;
            intervalElapsed += 1;
            getNextInterval();
//            System.out.println("Time for a break!");
            timerDisplay.setText("Time for a break!");
        }
    }
}