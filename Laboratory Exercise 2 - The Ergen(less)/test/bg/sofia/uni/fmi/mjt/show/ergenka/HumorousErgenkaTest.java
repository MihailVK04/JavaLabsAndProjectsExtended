package bg.sofia.uni.fmi.mjt.show.ergenka;

import bg.sofia.uni.fmi.mjt.show.date.DateEvent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HumorousErgenkaTest {

    static DateEvent event = mock();

    @BeforeAll
    static void setUpEvent() {
        when(event.tensionLevel()).thenReturn(5);
    }

    @Test
    void testReactToDateCorrectRating() {
        HumorousErgenka ergenka = new HumorousErgenka("Name", (short) 20, 4, 1, 0);
        when(event.duration()).thenReturn(25);
        ergenka.reactToDate(event);
        assertEquals(0, ergenka.getRating(),
                "ReactToDate does not correctly calculate rating, it should be 0");
        when(event.duration()).thenReturn(45);
        ergenka.reactToDate(event);
        assertEquals(6, ergenka.getRating(),
                "ReactToDate does not correctly calculate rating, it should be 6");
        when(event.duration()).thenReturn(95);
        ergenka.reactToDate(event);
        assertEquals(-1, ergenka.getRating(),
                "ReactToDate does not correctly calculate rating, it should be -1");
    }
}
