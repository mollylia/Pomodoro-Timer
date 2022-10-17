package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static model.BreakInterval.DEFAULT_BREAK_TIME;
import static model.StudyInterval.DEFAULT_STUDY_TIME;
import static org.junit.jupiter.api.Assertions.*;

public class PomodoroTimerTest {
    private ArrayList<PomodoroInterval> samplePomoTimer;
    private PomodoroInterval samplePomoInterval;
    private StudyInterval sampleStudyInterval;
    private BreakInterval sampleBreakInterval;
    private StudyInterval si1;
    private BreakInterval bi1;
    private PomodoroInterval pi1;
    private StudyInterval si2;
    private BreakInterval bi2;
    private PomodoroInterval pi2;

    @BeforeEach
    public void setup() {
        samplePomoTimer = new ArrayList<>();
        sampleStudyInterval = new StudyInterval();
        sampleBreakInterval = new BreakInterval();
        samplePomoInterval = new PomodoroInterval(sampleStudyInterval, sampleBreakInterval);

        si1 = new StudyInterval();
        si1.setStudyInterval("assignment 6",1200);
        bi1 = new BreakInterval();
        bi1.setBreakInterval("long break",1200);
        pi1 = new PomodoroInterval(si1, bi1);

        si2 = new StudyInterval();
        si2.setStudyInterval("assignment 2",100);
        bi2 = new BreakInterval();
        bi2.setBreakInterval("short-ish break",480);
        pi2 = new PomodoroInterval(si2, bi2);
    }

    @Test
    public void testPomodoroTimerConstructor() {
        assertEquals(0, samplePomoTimer.size());
    }

//    @Test
//    void testAddPomodoroInterval() {
//        samplePomoTimer.add(samplePomoInterval);
//        assertEquals(1, samplePomoTimer.size());
//        assertEquals(samplePomoInterval, samplePomoTimer.get(0));
//    }

    @Test
    void testAddPomodoroInterval() {
        PomodoroTimer testPomoTimer = new PomodoroTimer();
        testPomoTimer.addInterval(sampleStudyInterval, sampleBreakInterval);
        assertEquals(1, testPomoTimer.size());
        assertEquals(samplePomoInterval, testPomoTimer.get(0));
    }



    @Test
    void testAddMultiplePomodoroInterval() {
        samplePomoTimer.add(samplePomoInterval);
        samplePomoTimer.add(pi1);
        samplePomoTimer.add(pi2);

        assertEquals(3, samplePomoTimer.size());
        assertEquals(samplePomoInterval, samplePomoTimer.get(0));
        assertEquals(pi1, samplePomoTimer.get(1));
        assertEquals(pi2, samplePomoTimer.get(2));
    }


    @Test
    void testRemovePomodoroInterval() {
        samplePomoTimer.add(samplePomoInterval);
        samplePomoTimer.remove(samplePomoInterval);
        assertEquals(0, samplePomoTimer.size());
    }

    @Test
    void testRemoveMultiplePomodoroInterval() {
        samplePomoTimer.add(samplePomoInterval);
        samplePomoTimer.add(pi1);
        samplePomoTimer.add(pi2);
        samplePomoTimer.remove(pi1);
        samplePomoTimer.remove(pi2);

        assertEquals(1, samplePomoTimer.size());
        assertEquals(samplePomoInterval, samplePomoTimer.get(0));
    }

    @Test
    void testTimeElapsedSingleInterval() {
        samplePomoTimer.add(samplePomoInterval);
        assertEquals(DEFAULT_STUDY_TIME + DEFAULT_BREAK_TIME,
                samplePomoTimer.get(0).getPomoStudyInterval().getStudyIntervalDuration() +
                samplePomoTimer.get(0).getPomoBreakInterval().getBreakIntervalDuration());
    }
}
