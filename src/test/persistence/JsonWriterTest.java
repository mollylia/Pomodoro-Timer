package persistence;


import model.PomodoroInterval;
import model.PomodoroTimer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            PomodoroTimer pomoTimer = new PomodoroTimer("My Pomodoro Timer");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            PomodoroTimer pomoTimer = new PomodoroTimer("My Pomodoro Timer");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPomodoroTimer.json");
            writer.open();
            writer.write(pomoTimer);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPomodoroTimer.json");
            pomoTimer = reader.read();
            assertEquals("My Pomodoro Timer", pomoTimer.getName());
            assertEquals(0, pomoTimer.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            PomodoroTimer pomoTimer = new PomodoroTimer("My work room");
            pomoTimer.addInterval(new PomodoroInterval(false, "b", 300));
            pomoTimer.addInterval(new PomodoroInterval(true, "s", 1500));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPomodoroTimer.json");
            writer.open();
            writer.write(pomoTimer);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPomodoroTimer.json");
            pomoTimer = reader.read();
            assertEquals("My Pomodoro Timer", pomoTimer.getName());
            List<PomodoroInterval> thingies = pomoTimer.getPomoIntervals();
            assertEquals(2, thingies.size());
            checkPomoInterval(thingies.get(0), false, "b", 300);
            checkPomoInterval(thingies.get(1), true,"s", 1500);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}