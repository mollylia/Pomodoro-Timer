package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.StudyInterval.DEFAULT_STUDY_NAME;
import static model.StudyInterval.DEFAULT_STUDY_TIME;
import static org.junit.jupiter.api.Assertions.*;

public class StudyIntervalTest {
    private StudyInterval sampleStudyInterval;

    @BeforeEach
    public void setup() {
        sampleStudyInterval = new StudyInterval();
    }

    @Test
    public void testStudyIntervalConstructor() {
        assertEquals(DEFAULT_STUDY_NAME, sampleStudyInterval.getStudyIntervalName());
        assertEquals(DEFAULT_STUDY_TIME, sampleStudyInterval.getStudyIntervalDuration());
    }

    @Test
    public void testSetStudyIntervalOnce() {
        sampleStudyInterval.setStudyInterval("assignment 1",600);
        assertEquals("assignment 1", sampleStudyInterval.getStudyIntervalName());
        assertEquals(600, sampleStudyInterval.getStudyIntervalDuration());
    }

    @Test
    public void testSetStudyIntervalMultipleTimes() {
        sampleStudyInterval.setStudyInterval("assignment 1",600);
        sampleStudyInterval.setStudyInterval("assignment 6",1200);
        sampleStudyInterval.setStudyInterval("assignment 2",100);
        assertEquals("assignment 2", sampleStudyInterval.getStudyIntervalName());
        assertEquals(100, sampleStudyInterval.getStudyIntervalDuration());
    }
}
