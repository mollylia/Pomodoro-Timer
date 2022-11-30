package model;

public class EventLogDisplay {

    public void printLog() {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.toString());
        }
    }
}
