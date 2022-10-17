package ui;


import model.SimpleTimerDisplay;

import java.util.Timer;
import java.util.TimerTask;


public class Main {
    public static void main(String[] args) {
        Timer timer = new Timer();
//      TimerTask studyTask = new DisplayTimer();
//      timer.schedule(studyTask, 0, 1000);

        TimerTask studySession = new SimpleTimerDisplay();
        timer.schedule(studySession, 0, 1000);
    }
}

//import model.PomodoroTimer;
//
//import java.util.Timer;
//import java.util.TimerTask;


//public class Main {
//    private static PomodoroTimer pomoTimer = new PomodoroTimer();
//
//    public static void main(String[] args) {
////        new PomodoroDisplay().startTimer();
//
//        Timer timer = new Timer();
//        TimerTask studySession = new PomodoroDisplay();


//        for (PomodoroInterval pomoInterval : pomoTimer) {
//                //for(int i=0; i<pomoTimer.size(); i++ )
//                //this.runTime = pomoInterval.getPomoStudyInterval().getStudyIntervalDuration();
//                timer.schedule(studySession, 0, 1000);
//
//                //System.out.println(minute + ":" + second);


//    }
//}

////    public void addInterval() {
////        pomoTimer = new PomodoroTimer();
//
//        PomodoroInterval interval = new PomodoroInterval(new StudyInterval(), new BreakInterval());
//                //pomoTomer.addStudyInterval(interval);
//        //interval = new PomodoroInterval();
//        //interval.time = 2000;
//        //interval.name = "breaktime";
//        pomoTimer.add1(interval);
//    }
//    }

