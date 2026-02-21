package bg.sofia.uni.fmi.mjt.show;

import bg.sofia.uni.fmi.mjt.show.date.DateEvent;
import bg.sofia.uni.fmi.mjt.show.elimination.EliminationRule;
import bg.sofia.uni.fmi.mjt.show.elimination.LowestRatingEliminationRule;
import bg.sofia.uni.fmi.mjt.show.elimination.PublicVoteEliminationRule;
import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;
import bg.sofia.uni.fmi.mjt.show.ergenka.HumorousErgenka;
import bg.sofia.uni.fmi.mjt.show.ergenka.RomanticErgenka;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowAPIImplTest {

    static ShowAPI showAPI;

    @BeforeAll
    static void setShowAPI() {
        Ergenka ergenka1 = new RomanticErgenka("Gergana", (short) 20, 2, 8, 8,"none");
        Ergenka ergenka2 = new RomanticErgenka("Petya", (short) 20, 4, 0, 7,"none");
        Ergenka ergenka3 = new HumorousErgenka("Svetla", (short) 20, 4, 1, 0);
        Ergenka[] ergenkas = new Ergenka[] {ergenka1, ergenka2, ergenka3};
        EliminationRule[] rules = new EliminationRule[] {new LowestRatingEliminationRule()};
        showAPI = new ShowAPIImpl(ergenkas, rules);
    }

    @Test
    void testOrganizeDateCorrect() {
        DateEvent event = new DateEvent("Bulgaria",5,25);
        Ergenka copy = new HumorousErgenka(showAPI.getErgenkas()[2].getName(), showAPI.getErgenkas()[2].getAge(),
                showAPI.getErgenkas()[2].getRomanceLevel(), showAPI.getErgenkas()[2].getHumorLevel(),
                showAPI.getErgenkas()[2].getRating());
        showAPI.organizeDate(showAPI.getErgenkas()[2], event);
        assertEquals(0, showAPI.getErgenkas()[2].getRating(),
                "OrganizeDate is not implemented correctly");
        showAPI.getErgenkas()[2] = copy;
    }
    
    @Test
    void testEliminateErgenkasNotEmptyArray() {
        String[] votes = new String[] {"Petya", "Petya", "Petya", "Gergana", "Svetla"};
        EliminationRule[] rules = new EliminationRule[] {new PublicVoteEliminationRule(votes)};
        Ergenka[] copy = new Ergenka[] {showAPI.getErgenkas()[0], showAPI.getErgenkas()[1], showAPI.getErgenkas()[2]};
        showAPI.eliminateErgenkas(rules);
        Ergenka[] result = new Ergenka[3];
        result[0] = copy[0];
        result[1] = copy[2];
        assertArrayEquals(result, showAPI.getErgenkas(), "After elimination there should be change in the array");
        showAPI.setErgenkas(copy);
    }
    
    @Test
    void testEliminateErgenkasEmptyArray() {
        EliminationRule[] rules = new EliminationRule[0];
        Ergenka[] copy = new Ergenka[] {showAPI.getErgenkas()[0], showAPI.getErgenkas()[1], showAPI.getErgenkas()[2]};
        showAPI.eliminateErgenkas(rules);
        Ergenka[] result = new Ergenka[3];
        result[0] = copy[0];
        result[1] = copy[1];
        assertArrayEquals(result, showAPI.getErgenkas(), "There should be an elimination by the default rules");
        showAPI.setErgenkas(copy);
    }
    
    @Test
    void testPlayRoundCorrectChange() {
        DateEvent event = new DateEvent("Bulgaria",5,25);
        Ergenka copy1 = new HumorousErgenka(showAPI.getErgenkas()[0].getName(), showAPI.getErgenkas()[0].getAge(),
                showAPI.getErgenkas()[0].getRomanceLevel(), showAPI.getErgenkas()[0].getHumorLevel(),
                showAPI.getErgenkas()[0].getRating());
        Ergenka copy2 = new HumorousErgenka(showAPI.getErgenkas()[1].getName(), showAPI.getErgenkas()[1].getAge(),
                showAPI.getErgenkas()[1].getRomanceLevel(), showAPI.getErgenkas()[1].getHumorLevel(),
                showAPI.getErgenkas()[1].getRating());
        Ergenka copy3 = new HumorousErgenka(showAPI.getErgenkas()[2].getName(), showAPI.getErgenkas()[2].getAge(),
                showAPI.getErgenkas()[2].getRomanceLevel(), showAPI.getErgenkas()[2].getHumorLevel(),
                showAPI.getErgenkas()[2].getRating());
        Ergenka[] copyArray = new Ergenka[] {copy1, copy2, copy3};
        showAPI.playRound(event);
        assertEquals(1, showAPI.getErgenkas()[0].getRating(),
                "First is not correct");
        assertEquals(2, showAPI.getErgenkas()[1].getRating(),
                "Second is not correct");
        assertEquals(0, showAPI.getErgenkas()[2].getRating(),
                "Third is not correct");
        showAPI.setErgenkas(copyArray);
    }
}
