package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PomodoroStatusTest {

    @Test
    public void testPomodoroStatus() {
        assertEquals("SHORT_BREAK", PomodoroStatus.SHORT_BREAK.toString());
        assertEquals("LONG_BREAK", PomodoroStatus.LONG_BREAK.toString());
        assertEquals("SHORT_STUDY", PomodoroStatus.SHORT_STUDY.toString());
        assertEquals("LONG_STUDY", PomodoroStatus.LONG_STUDY.toString());
    }
}