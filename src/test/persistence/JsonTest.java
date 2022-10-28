package persistence;

import model.PomodoroInterval;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPomoInterval(PomodoroInterval pomoInterval, Boolean status, String name, int duration) {

        assertEquals(name, pomoInterval.getName());
        assertEquals(status, pomoInterval.getStatus());
        assertEquals(name, pomoInterval.getName());
        assertEquals(duration, pomoInterval.getDuration());
    }
}
