package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PomodoroIntervalTest {
    private PomodoroInterval samplePomoInterval;
    private StudyInterval pomoStudy;
    private BreakInterval pomoBreak;

    @BeforeEach
    public void setup() {
        pomoStudy = new StudyInterval();
        pomoStudy.setStudyInterval("lab 4", 120);

        pomoBreak = new BreakInterval();
        pomoBreak.setBreakInterval("long break", 600);

        samplePomoInterval = new PomodoroInterval(pomoStudy, pomoBreak);
    }

    @Test
    public void testPomodoroIntervalConstructor() {
        assertEquals(pomoStudy, samplePomoInterval.getPomoStudyInterval());
        assertEquals(pomoBreak, samplePomoInterval.getPomoBreakInterval());
    }
}
