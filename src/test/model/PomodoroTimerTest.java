package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.*;

public class PomodoroTimerTest {
    private PomodoroTimer pomoTimer;

    private Event startEvent, stopEvent;
    private Timer timer;
    private TimerTask task;


    @BeforeEach
    public void setup() {
        pomoTimer = new PomodoroTimer();


        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {

            }
        };

        startEvent = new Event("Started Pomodoro Timer");
        stopEvent = new Event("Stopped Pomodoro Timer");
    }

    @Test
    public void testPomodoroTimerConstructor() {
        assertEquals(0, pomoTimer.length());
    }

    @Test
    public void testPomodoroTimerConstructorWithName() {
        PomodoroTimer pomoTimerWithName = new PomodoroTimer("My Pomodoro Timer");
        assertEquals("My Pomodoro Timer", pomoTimerWithName.getName());
    }

    @Test
    public void testAddOneInterval() {
        PomodoroInterval study1 = new PomodoroInterval(true, "study: CPSC 210", 3000);

        pomoTimer.addInterval(study1);
        assertEquals(1, pomoTimer.length());
    }

    @Test
    public void testAddMultipleIntervals() {
        PomodoroInterval study1 = new PomodoroInterval(true, "study: CPSC 210", 3000);
        PomodoroInterval break1 = new PomodoroInterval(false, "break", 600);
        PomodoroInterval study2 = new PomodoroInterval(true, "review: CPSC 210 lab", 3000);

        pomoTimer.addInterval(study1);
        pomoTimer.addInterval(break1);
        pomoTimer.addInterval(study2);
        assertEquals(3, pomoTimer.length());
    }

    @Test
    public void testLength() {
        PomodoroInterval study1 = new PomodoroInterval(true, "study: CPSC 210", 3000);
        PomodoroInterval break1 = new PomodoroInterval(false, "break", 600);
        PomodoroInterval study2 = new PomodoroInterval(true, "review: CPSC 210 lab", 3000);

        pomoTimer.addInterval(study1);
        pomoTimer.addInterval(break1);
        pomoTimer.addInterval(study2);
        assertEquals(3, pomoTimer.length());
    }

    @Test
    public void testIsNotEmpty() {
        PomodoroInterval study1 = new PomodoroInterval(true, "study: CPSC 210", 3000);
        pomoTimer.addInterval(study1);
        assertFalse(pomoTimer.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(pomoTimer.isEmpty());
    }

    @Test
    public void testGetNextInterval() {
        PomodoroInterval study1 = new PomodoroInterval(true, "study: CPSC 210", 3000);
        pomoTimer.addInterval(study1);
        assertEquals(study1, pomoTimer.getNextInterval());
    }

    @Test
    public void testGetNextIntervals() {
        PomodoroInterval study1 = new PomodoroInterval(true, "study: CPSC 210", 3000);
        PomodoroInterval break1 = new PomodoroInterval(false, "break", 600);
        PomodoroInterval study2 = new PomodoroInterval(true, "review: CPSC 210 lab", 3000);

        pomoTimer.addInterval(study1);
        pomoTimer.addInterval(break1);
        pomoTimer.addInterval(study2);

        assertEquals(study1, pomoTimer.getNextInterval());
        assertEquals(break1, pomoTimer.getNextInterval());
        assertEquals(study2, pomoTimer.getNextInterval());
    }

    @Test
    public void testGetNextIntervalsNoMore() {
        PomodoroInterval study1 = new PomodoroInterval(true, "study: CPSC 210", 3000);
        PomodoroInterval break1 = new PomodoroInterval(false, "break", 600);

        pomoTimer.addInterval(study1);
        pomoTimer.addInterval(break1);

        assertEquals(study1, pomoTimer.getNextInterval());
        assertEquals(break1, pomoTimer.getNextInterval());
        assertEquals(null, pomoTimer.getNextInterval());
    }


    @Test
    public void testStartTimer() {
        assertFalse(pomoTimer.hasStarted());
        pomoTimer.start(timer, task);
        assertTrue(pomoTimer.hasStarted());
        assertEquals("Started Pomodoro Timer", startEvent.getDescription());
    }

    @Test
    public void testStopTimer() {
        assertFalse(pomoTimer.hasStarted());
        pomoTimer.start(timer, task);
        assertTrue(pomoTimer.hasStarted());

        pomoTimer.stop();
        assertFalse(pomoTimer.hasStarted());
        assertEquals("Stopped Pomodoro Timer", stopEvent.getDescription());
    }
}

