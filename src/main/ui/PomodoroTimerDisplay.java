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
            runTime = nextInterval.getDuration() + 1;       // add 1 so timer can hit 0
            isStudy = nextInterval.getStatus();
        }
    }

    // EFFECTS: runs the timer with the set intervals
    @Override
    public void run() {
        if ((timeElapsed < runTime) && (intervalElapsed < totalIntervalCounter)) {
            long durationInSeconds = runTime - timeElapsed - 1;
            long second = (durationInSeconds) % 60;
            long minute = (durationInSeconds / 60) % 60;

            String formattedSec = String.format("%02d", second);
            String formattedMin = String.format("%02d", minute);

            timeElapsed += 1;
            timerDisplay.setText(formattedMin + ":" + formattedSec);

            if (!(isStudy)) {
                breakTime(runTime);
            } else {
                studyTime(runTime);
            }
        } else {
            JOptionPane.showMessageDialog(new JFrame("Notification"), "The session is complete!");
            cancel();
        }
    }

    // EFFECTS: checks to see if break interval has ended, if so notify the user
    public void breakTime(int breakTime) {
        if (timeElapsed == breakTime) {
            timeElapsed = 0;
            intervalElapsed += 1;
            getNextInterval();

            if (intervalElapsed < totalIntervalCounter) {
                JOptionPane.showMessageDialog(new JFrame("Notification"), "Break time is over!");
            }
        }
    }

    // EFFECTS: checks to see if study interval has ended, if so notify the user
    public void studyTime(int studyTime) {
        if (timeElapsed == studyTime) {
            timeElapsed = 0;
            intervalElapsed += 1;
            getNextInterval();

            if (intervalElapsed < totalIntervalCounter) {
                JOptionPane.showMessageDialog(new JFrame("Notification"), "Time for a break!");
            }
        }
    }
}