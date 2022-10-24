package ui;


import java.util.Timer;
import java.util.TimerTask;


public class Main {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask studySession = new PomodoroTimerDisplay();
        timer.schedule(studySession, 0, 1000);
    }
}

