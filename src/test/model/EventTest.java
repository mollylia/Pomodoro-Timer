package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    private Event event;
    private Date date;

    @BeforeEach
    public void setup() {
        event = new Event("Interval added: 210 lab (study, 3000 seconds)");
        date = Calendar.getInstance().getTime();
    }

    @Test
    public void testEvent() {
        assertEquals("Interval added: 210 lab (study, 3000 seconds)", event.getDescription());
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "Interval added: 210 lab (study, 3000 seconds)", event.toString());
    }


}
