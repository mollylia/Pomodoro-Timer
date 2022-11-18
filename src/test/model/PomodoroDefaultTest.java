package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PomodoroDefaultTest {

    @Test
    public void testPomodoroDefault() {
        assertEquals("SHORT_BREAK", PomodoroDefault.SHORT_BREAK.toString());
        assertEquals("LONG_BREAK", PomodoroDefault.LONG_BREAK.toString());
        assertEquals("SHORT_STUDY", PomodoroDefault.SHORT_STUDY.toString());
        assertEquals("LONG_STUDY", PomodoroDefault.LONG_STUDY.toString());
    }

    @Test
    public void testGetPomodoroDefault() {
        assertEquals(300, PomodoroDefault.getPomodoroDefault(PomodoroDefault.SHORT_BREAK));
        assertEquals(600, PomodoroDefault.getPomodoroDefault(PomodoroDefault.LONG_BREAK));
        assertEquals(1500, PomodoroDefault.getPomodoroDefault(PomodoroDefault.SHORT_STUDY));
        assertEquals(3000, PomodoroDefault.getPomodoroDefault(PomodoroDefault.LONG_STUDY));
    }
}