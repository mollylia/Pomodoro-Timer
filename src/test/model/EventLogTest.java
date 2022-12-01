package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventLogTest {
    private Event event1, event2, event3;

    @BeforeEach
    public void setup() {
        event1 = new Event("Loaded Pomodoro Timer");
        event2 = new Event("Interval added: 210 lab (study, 3000 seconds)");
        event3 = new Event("Saved Pomodoro Timer");

        EventLog eventLog = EventLog.getInstance();
        eventLog.logEvent(event1);
        eventLog.logEvent(event2);
        eventLog.logEvent(event3);
    }

    @Test
    public void testLogEvent() {
        List<Event> events = new ArrayList<Event>();

        EventLog eventLog = EventLog.getInstance();
        for (Event event : eventLog) {
            events.add(event);
        }

        assertTrue(events.contains(event1));
        assertTrue(events.contains(event2));
        assertTrue(events.contains(event3));
    }

    @Test
    public void testClear() {
        EventLog eventLog = EventLog.getInstance();
        eventLog.clear();
        Iterator<Event> iterate = eventLog.iterator();
        assertTrue(iterate.hasNext());
        assertEquals("Event log cleared.", iterate.next().getDescription());
        assertFalse(iterate.hasNext());
    }
}
