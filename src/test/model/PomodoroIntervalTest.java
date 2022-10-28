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

    @Test
    public void testSetStatus() {
        assertTrue(pomoInterval.getStatus());
        pomoInterval.setStatus(false);
        assertFalse(pomoInterval.getStatus());
    }

    @Test
    public void testSetName() {
        assertEquals("study: CPSC 210", pomoInterval.getName());
        pomoInterval.setName("study: CPSC 210 lab");
        assertEquals("study: CPSC 210 lab", pomoInterval.getName());
    }

    @Test
    public void testSetDuration() {
        assertEquals(3000, pomoInterval.getDuration());
        pomoInterval.setDuration(3001);
        assertEquals(3001, pomoInterval.getDuration());
    }
}
