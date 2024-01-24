package ui;

public interface PomodoroObservable {
    // EFFECTS: adds an observer to the list of observers
    void addObserver(PomodoroObserver observer);

    // EFFECTS: removes an observer from the list of observers
    void removeObserver(PomodoroObserver observer);

    // EFFECTS: notifies all observers of a change
    void notifyObservers();
}
