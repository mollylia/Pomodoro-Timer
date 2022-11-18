package ui;

import model.PomodoroInterval;
import model.PomodoroDefault;
import model.PomodoroTimer;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

// Based on WorkRoomApp in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents the Pomodoro timer application
public class PomodoroTimerApp {
    private static final String JSON_STORE = "./data/pomodorotimer.json";
    private Scanner input;
    private PomodoroTimer pomoTimer;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs Pomodoro timer and runs application
    public PomodoroTimerApp() {
//        input = new Scanner(System.in);
        pomoTimer = new PomodoroTimer("My Pomodoro Timer");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
//        runPomodoroApp();
    }

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
    // EFFECTS: prompt user for a category interval with a name and adds it to PomodoroTimer
    public void addInterval() {
        Boolean setStatus;
        int setDuration;

        PomodoroDefault pomoStatus = readPomodoroStatus();
        System.out.println("Please enter a name for your interval");
        String name = input.next();

        String setName = name;

        if (pomoStatus == PomodoroDefault.SHORT_BREAK) {
            setDuration = PomodoroDefault.getPomodoroDefault(PomodoroDefault.SHORT_BREAK);
            setStatus = false;
        } else if (pomoStatus == PomodoroDefault.LONG_BREAK) {
            setDuration = PomodoroDefault.getPomodoroDefault(PomodoroDefault.LONG_BREAK);
            setStatus = false;
        } else if (pomoStatus == PomodoroDefault.SHORT_STUDY) {
            setDuration = PomodoroDefault.getPomodoroDefault(PomodoroDefault.SHORT_STUDY);
            setStatus = true;
        } else {
            setDuration = PomodoroDefault.getPomodoroDefault(PomodoroDefault.LONG_STUDY);
            setStatus = true;
        }

        PomodoroInterval newInterval = new PomodoroInterval(setStatus, setName, setDuration);
        pomoTimer.addInterval(newInterval);
    }

    // EFFECTS: prompts user to select a type of interval and returns it
    public PomodoroDefault readPomodoroStatus() {
        System.out.println("Please select a status for your interval");

        int menuLabel = 1;
        for (PomodoroDefault pomoStatus : PomodoroDefault.values()) {
            System.out.println(menuLabel + ": " + pomoStatus);
            menuLabel++;
        }

        int menuSelection = input.nextInt();
        return PomodoroDefault.values()[menuSelection - 1];
    }

    // EFFECTS: runs the list of intervals in the Pomodoro timer.
    //          if the list is empty, return an error message
    public void runPomodoroTimer() {
        if (pomoTimer.length() == 0) {
            System.out.println("There are no intervals to run...");
        } else {
            Timer timer = new Timer();
            TimerTask timerInterval = new ui.PomodoroTimerDisplay(pomoTimer);
            timer.schedule(timerInterval, 0, 1000);
        }
    }

    // EFFECTS: saves Pomodoro timer to file
    public void savePomodoroTimer() {
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

    // MODIFIES: this
    // EFFECTS: processes user command
    private void toSaveCommand(String command) {
        if (command.equals("y")) {
            savePomodoroTimer();
        } else if (command.equals("n")) {
            System.out.println("\nGoodbye!");
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Pomodoro timer from file
    public PomodoroTimer loadPomodoroTimer() {
        try {
            pomoTimer = jsonReader.read();
            System.out.println("Loaded " + pomoTimer.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

        return pomoTimer;
    }
}