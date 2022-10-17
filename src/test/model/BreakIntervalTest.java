package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.BreakInterval.DEFAULT_BREAK_NAME;
import static model.BreakInterval.DEFAULT_BREAK_TIME;
import static org.junit.jupiter.api.Assertions.*;

public class BreakIntervalTest {
    private BreakInterval sampleBreakInterval;

    @BeforeEach
    public void setup() {
        sampleBreakInterval = new BreakInterval();
    }

    @Test
    public void testBreakIntervalConstructor() {
        assertEquals(DEFAULT_BREAK_NAME, sampleBreakInterval.getBreakIntervalName());
        assertEquals(DEFAULT_BREAK_TIME, sampleBreakInterval.getBreakIntervalDuration());
    }

    @Test
    public void testSetStudyIntervalOnce() {
        sampleBreakInterval.setBreakInterval("long break",600);
        assertEquals("long break", sampleBreakInterval.getBreakIntervalName());
        assertEquals(600, sampleBreakInterval.getBreakIntervalDuration());
    }

    @Test
    public void testSetStudyIntervalMultipleTimes() {
        sampleBreakInterval.setBreakInterval("short break",300);
        sampleBreakInterval.setBreakInterval("long break",1200);
        sampleBreakInterval.setBreakInterval("short-ish break",480);
        assertEquals("short-ish break", sampleBreakInterval.getBreakIntervalName());
        assertEquals(480, sampleBreakInterval.getBreakIntervalDuration());
    }

}
