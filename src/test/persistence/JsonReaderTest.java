package persistence;

import model.PomodoroInterval;
import model.PomodoroTimer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PomodoroTimer pomoTimer = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPomodoroTimer.json");
        try {
            PomodoroTimer pomoTimer = reader.read();
            assertEquals(0, pomoTimer.length());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPomodoroTimer.json");
        try {
            PomodoroTimer pomoTimer = reader.read();
            assertEquals("My Pomodoro Timer", pomoTimer.getName());
            List<PomodoroInterval> pomoIntervals = pomoTimer.getPomoIntervals();
            assertEquals(2, pomoIntervals.size());
            checkPomoInterval(pomoIntervals.get(0), true, "lab", 3000);
            checkPomoInterval(pomoIntervals.get(1), false, "nap", 600);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}