package ui;

import model.DisplayTimer;

import java.util.Timer;
import java.util.TimerTask;


public class Main {
    public static void main(String[] args) {
        Timer timer = new Timer();
//      TimerTask studyTask = new DisplayTimer();
//      timer.schedule(studyTask, 0, 1000);

        TimerTask studySession = new PomodoroTimer();
        timer.schedule(studySession, 0, 1000);
    }
}