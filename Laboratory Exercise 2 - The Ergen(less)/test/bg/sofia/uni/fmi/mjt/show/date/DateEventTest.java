package bg.sofia.uni.fmi.mjt.show.date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateEventTest {

    @Test
    void testDateEventCorrectTensionLevel() {
        String location = "Location";
        int duration  = 25;
        DateEvent event1 = new DateEvent(location, -5, duration);
        DateEvent event2 = new DateEvent(location, 15, duration);
        DateEvent event3 = new DateEvent(location, 5, duration);

        assertTrue( () -> event1.tensionLevel() <= 10 && event1.tensionLevel() >= 0,
                "Tension level should be between 0 and 10");
        assertTrue( () -> event2.tensionLevel() <= 10 && event2.tensionLevel() >= 0,
                "Tension level should be between 0 and 10");
        assertTrue( () -> event3.tensionLevel() <= 10 && event3.tensionLevel() >= 0,
                "Tension level should be between 0 and 10");
    }
}
