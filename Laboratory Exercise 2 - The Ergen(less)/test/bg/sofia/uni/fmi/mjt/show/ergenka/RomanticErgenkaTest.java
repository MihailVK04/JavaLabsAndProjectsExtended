package bg.sofia.uni.fmi.mjt.show.ergenka;

import bg.sofia.uni.fmi.mjt.show.date.DateEvent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RomanticErgenkaTest {

    static DateEvent event = mock();

    @BeforeAll
    static void setUpEvent() {
        when(event.tensionLevel()).thenReturn(7);
        when(event.location()).thenReturn("Brazil");
    }

    @Test
    void testReactToDateWithoutFavouriteLocation() {
        RomanticErgenka ergenka = new RomanticErgenka("Elena", (short) 20, 1, 4, 0,
                "none");
        when(event.duration()).thenReturn(25);
        ergenka.reactToDate(event);
        assertEquals(-1, ergenka.getRating(), "Rating is not correct. It should equal -1");
        when(event.duration()).thenReturn(45);
        ergenka.reactToDate(event);
        assertEquals(2, ergenka.getRating(), "Rating is not correct. It should equal 2");
        when(event.duration()).thenReturn(150);
        ergenka.reactToDate(event);
        assertEquals(0, ergenka.getRating(), "Rating is not correct. It should equal 0");
    }

    @Test
    void testReactToDateWithFavouriteLocation() {
        RomanticErgenka ergenka = new RomanticErgenka("Elena", (short) 20, 1, 4, 0,
                "Brazil");
        when(event.duration()).thenReturn(25);
        ergenka.reactToDate(event);
        assertEquals(4, ergenka.getRating(), "Rating is not correct. It should equal 4");
        when(event.duration()).thenReturn(45);
        ergenka.reactToDate(event);
        assertEquals(7, ergenka.getRating(), "Rating is not correct. It should equal 7");
        when(event.duration()).thenReturn(150);
        ergenka.reactToDate(event);
        assertEquals(5, ergenka.getRating(), "Rating is not correct. It should equal 5");
    }
}
