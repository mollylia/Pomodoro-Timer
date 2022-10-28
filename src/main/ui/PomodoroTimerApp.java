package ui;

import model.PomodoroInterval;
import model.PomodoroStatus;
import model.PomodoroTimer;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

// Pomodoro timer application
public class PomodoroTimerApp {
    private static final String JSON_STORE = "./data/pomodorotimer.json";
    private Scanner input;
    private PomodoroTimer pomoTimer;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs Pomodoro timer and runs application
    public PomodoroTimerApp() {
        input = new Scanner(System.in);
        pomoTimer = new PomodoroTimer("My Pomodoro Timer");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runPomodoroApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runPomodoroApp() {
        Boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                toSave();
                String commandQuit = input.next();
                toSaveCommand(commandQuit);
                keepGoing = false;
            } else {
                processDisplayCommand(command);
            }
        }

//        System.out.println("\nGoodbye!");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\ta -> add interval");
        System.out.println("\tr -> run pomodoro timer");
        System.out.println("\ts -> save pomodoro timer");
        System.out.println("\tl -> load pomodoro timer");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processDisplayCommand(String command) {
        if (command.equals("a")) {
            addInterval();
        } else if (command.equals("r")) {
            runPomodoroTimer();
        } else if (command.equals("s")) {
            savePomodoroTimer();
        } else if (command.equals("l")) {
            loadPomodoroTimer();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompt user for status, name, and duration of the interval and adds it to PomodoroTimer
    private void addInterval() {
        Boolean setStatus;
        int setDuration;

        PomodoroStatus pomoStatus = readPomodoroStatus();
        System.out.println("Please enter a name for your interval");
        String name = input.next();

        String setName = name;

        if (pomoStatus == PomodoroStatus.SHORTBREAK) {
            setDuration = 300;
            setStatus = false;
        } else if (pomoStatus == PomodoroStatus.LONGBREAK) {
            setDuration = 600;
            setStatus = false;
        } else if (pomoStatus == PomodoroStatus.SHORTSTUDY) {
            setDuration = 1500;
            setStatus = true;
        } else {
            setDuration = 3000;
            setStatus = true;
        }

        PomodoroInterval newInterval = new PomodoroInterval(setStatus, setName, setDuration);
        pomoTimer.addInterval(newInterval);
    }

    // EFFECTS: prompts user to select category and returns it
    private PomodoroStatus readPomodoroStatus() {
        System.out.println("Please select a status for your interval");

        int menuLabel = 1;
        for (PomodoroStatus pomoStatus : PomodoroStatus.values()) {
            System.out.println(menuLabel + ": " + pomoStatus);
            menuLabel++;
        }

        int menuSelection = input.nextInt();
        return PomodoroStatus.values()[menuSelection - 1];
    }

    // EFFECTS: runs the list of intervals in the Pomodoro timer.
    //          if the list is empty, return an error message
    private void runPomodoroTimer() {
        Timer timer = new Timer();
        TimerTask timerInterval = new ui.PomodoroTimerDisplay(pomoTimer);
        timer.schedule(timerInterval, 0, 1000);
    }

    //
    private void savePomodoroTimer() {
        try {
            jsonWriter.open();
            jsonWriter.write(pomoTimer);
            jsonWriter.close();
            System.out.println("Saved " + pomoTimer.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // EFFECTS: gives the user an option to save the current state of the Pomodoro timer
    private void toSave() {
        System.out.println("Would you like to save?");
        System.out.println("\ty -> yes");
        System.out.println("\tn -> no");
    }

    private void toSaveCommand(String command) {
        if (command.equals("y")) {
            savePomodoroTimer();
        } else if (command.equals("n")) {
            System.out.println("\nGoodbye!");
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: pomoTimer
    // EFFECTS: parses Pomodoro intervals from JSON object and adds it to Pomodoro timer
    private void loadPomodoroTimer() {
        try {
            pomoTimer = jsonReader.read();
            System.out.println("Loaded last saved Pomodoro timer from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}



//    // EFFECTS: sets the status for the Pomodoro interval
//    //          cancel brings the user back to the display menu
//    private Boolean setIntervalStatus() {
//        String command = input.next();
//        input = new Scanner(System.in);
//
//        System.out.println("\nWill you be studying or taking a break?");
//        System.out.println("\ts -> studying");
//        System.out.println("\tb -> taking a break");
//        System.out.println("\tc -> cancel");
//
//        return processStatusCommand(command);
//    }
//
//    private Boolean processStatusCommand(String command) {
//        if (command.equals("s")) {
//            return true;
//        } else if (command.equals("b")) {
//            return false;
//        } else if (command.equals("c")) {
//            displayMenu();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//
//        return true;
//    }
//
//    private String setIntervalName() {
////        input = new Scanner(System.in);
//        String name = input.next();
//        System.out.println("What task will you be working on?");
//        return name;
//    }
//
//    private int setIntervalDuration() {
//        int duration = Integer.parseInt(input.next());
//        System.out.println("How long will you be working on the task?");
//        return duration;
//    }