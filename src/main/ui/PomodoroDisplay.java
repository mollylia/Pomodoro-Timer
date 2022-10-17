//package ui;
//
//import model.PomodoroInterval;
//import model.PomodoroTimer;
//
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.ArrayList;
//
//public class PomodoroDisplay extends TimerTask {
//
////    // CONSTANTS
//    public int DEFAULT_STUDY_TIME = 1500;         // time in seconds
//    public int DEFAULT_BREAK_TIME = 300;          // time in seconds
//    public int DEFAULT_INTERVAL_COUNTER = 3;      // number of times to repeat study + break interval
//
//
//    // FIELDS
//    private int runTime;
//    private int timeElapsed;
//    private int intervalElapsed;
//    private boolean isStudy;
//
//    private PomodoroTimer pomoTimer;
//
//    public void PomodoroDisplayStudyTime(long minute, long second) {
//
//    }
//
//
//
////    public static void displayStudyTime() {
////    }
////
////    public static void displayBreakTime() {
////        for (PomodoroInterval pomoInterval : pomoTimer) {
////            pomoInterval.getPomoStudyInterval().getStudyIntervalDuration();
////            pomoInterval.getPomoBreakInterval().getBreakIntervalDuration();
////        }
////    }
//
//
//    @Override
//    public void run() {
//        if ((timeElapsed < runTime) //&& (intervalElapsed < pomoTimer.size())
//        ) {
//
//            long durationInSeconds = runTime - timeElapsed;
//            long second = (durationInSeconds) % 60;
//            long minute = (durationInSeconds / (60)) % 60;
//            timeElapsed += 1;
//
//            System.out.println(minute + ":" + second);
//
//            if (!(isStudy)) {
//                breakTime();
//            } else {
//                studyTime();
//            }
//        } else {
//            System.out.println("Session complete!");
//            System.exit(0);
//        }
//    }
//
//    public void breakTime() {
//        if (timeElapsed == DEFAULT_BREAK_TIME) {
//            isStudy = true;
//            timeElapsed = 0;
//            intervalElapsed += 1;
//            runTime = DEFAULT_STUDY_TIME;
//
//            System.out.println("Break time is over!");
//        }
//    }
//
//    public void studyTime() {
//        if (timeElapsed == DEFAULT_STUDY_TIME) {
//            isStudy = false;
//            timeElapsed = 0;
//            runTime = DEFAULT_BREAK_TIME;
//
//            System.out.println("Time for a break!");
//        }
//    }
//
//
//}


//package model;
//
//import java.util.TimerTask;
//
//public class PomodoroDisplay extends TimerTask {
//
//    // CONSTANTS
//    private int DEFAULT_STUDY_TIME = 3;         // time in seconds
//    private int DEFAULT_BREAK_TIME = 2;          // time in seconds
//    private int DEFAULT_INTERVAL_COUNTER = 3;      // number of times to repeat study + break interval
//
//
//    // FIELDS
//    private int runTime;
//    private int timeElapsed;
//    private int intervalElapsed;
//    private Boolean isStudy;
//
//    public PomodoroDisplay() {
//        runTime = DEFAULT_STUDY_TIME;
//        timeElapsed = 0;
//        intervalElapsed = 0;
//        isStudy = true;
//    }
//
//
//    @Override
//    public void run() {
//        if ((timeElapsed < runTime) && (intervalElapsed < DEFAULT_INTERVAL_COUNTER)) {
//
//            long durationInSeconds = runTime - timeElapsed;
//            long second = (durationInSeconds) % 60;
//            long minute = (durationInSeconds / (60)) % 60;
//            timeElapsed += 1;
//
//            System.out.println(minute + ":" + second);
//
//            if (!(isStudy)) {
//                breakTime();
//            } else {
//                studyTime();
//            }
//        } else {
//            System.out.println("Session complete!");
//            System.exit(0);
//        }
//    }
//
//    public void breakTime() {
//        if (timeElapsed == DEFAULT_BREAK_TIME) {
//            isStudy = true;
//            timeElapsed = 0;
//            intervalElapsed += 1;
//            runTime = DEFAULT_STUDY_TIME;
//
//            System.out.println("Break time is over!");
//        }
//    }
//
//    public void studyTime() {
//        if (timeElapsed == DEFAULT_STUDY_TIME) {
//            isStudy = false;
//            timeElapsed = 0;
//            runTime = DEFAULT_BREAK_TIME;
//
//            System.out.println("Time for a break!");
//        }
//    }
//}

