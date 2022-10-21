package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PomodoroIntervalTest {
    private PomodoroInterval pomoInterval;


    @BeforeEach
    public void setup() {
        pomoInterval = new PomodoroInterval(true, "study: CPSC 210", 3000);
    }

    @Test
    public void testPomodoroIntervalConstructor() {
        assertTrue(pomoInterval.getStatus());
        assertEquals("study: CPSC 210", pomoInterval.getName());
        assertEquals(3000, pomoInterval.getDuration());
    }
}
