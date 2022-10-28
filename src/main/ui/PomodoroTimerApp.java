package ui;

import model.PomodoroInterval;
import model.PomodoroTimer;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.Scanner;

// Pomodoro timer application
public class PomodoroTimerApp {
    private static final String JSON_STORE = "./data/pomodorointerval.json";
    private Scanner input;
    private PomodoroTimer pomoTimer;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs Pomodoro timer and runs application
    public PomodoroTimerApp() {
        input = new Scanner(System.in);
        pomoTimer = new PomodoroTimer();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runPomodoroApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runPomodoroApp() {
        Boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
                toSave();
            } else {
                processDisplayCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // TODO
    // EFFECTS: gives the user an option to save the current state of the Pomodoro timer
    private void toSave() {
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\ta -> add interval");
//        System.out.println("\te -> edit interval");
        System.out.println("\tr -> run pomodoro timer");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processDisplayCommand(String command) {
        if (command.equals("a")) {
            addInterval();
//        } else if (command.equals("e")) {
//            editInterval();
        } else if (command.equals("r")) {
            runPomodoroTimer();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompt user for status, name, and duration of the interval and adds it to PomodoroTimer
    private void addInterval() {
        Boolean setStatus = setIntervalStatus();
        String setName = setIntervalName();
        int setDuration = setIntervalDuration();

        PomodoroInterval newInterval = new PomodoroInterval(setStatus, setName, setDuration);
        pomoTimer.addInterval(newInterval);
    }

    // EFFECTS: sets the status for the Pomodoro interval
    //          cancel brings the user back to the display menu
    private Boolean setIntervalStatus() {
        String command = input.next();
        input = new Scanner(System.in);

        System.out.println("\nWill you be studying or taking a break?");
        System.out.println("\ts -> studying");
        System.out.println("\tb -> taking a break");
        System.out.println("\tc -> cancel");

        return processStatusCommand(command);
    }

    private Boolean processStatusCommand(String command) {
        if (command.equals("s")) {
            return true;
        } else if (command.equals("b")) {
            return false;
        } else if (command.equals("c")) {
            displayMenu();
        } else {
            System.out.println("Selection not valid...");
        }

        return true;
    }

    private String setIntervalName() {
//        input = new Scanner(System.in);
        String name = input.next();
        System.out.println("What task will you be working on?");
        return name;
    }

    private int setIntervalDuration() {
        int duration = Integer.parseInt(input.next());
        System.out.println("How long will you be working on the task?");
        return duration;
    }

    // EFFECTS: runs the list of intervals in the Pomodoro timer.
    //          if the list is empty, return an error message
    private void runPomodoroTimer() {
    }

    // MODIFIES: pomoTimer
    // EFFECTS: parses Pomodoro intervals from JSON object and adds it to Pomodoro timer
    // TODO
    private void loadPomodoroTimer() {
    }

}
