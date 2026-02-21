package bg.sofia.uni.fmi.mjt.show.elimination;

import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;
import bg.sofia.uni.fmi.mjt.show.ergenka.HumorousErgenka;
import bg.sofia.uni.fmi.mjt.show.ergenka.RomanticErgenka;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class PublicVoteEliminationRuleTest {


    PublicVoteEliminationRule rule;

    @Test
    void testEliminateErgenkasOneElimination() {
        String[] votes = new String[] {"Petya", "Petya", "Petya", "Gergana", "Svetla"};
        rule = new PublicVoteEliminationRule(votes);
        Ergenka ergenka1 = new RomanticErgenka("Gergana", (short) 20, 2, 8, 8,"none");
        Ergenka ergenka2 = new RomanticErgenka("Petya", (short) 20, 4, 0, 7,"none");
        Ergenka ergenka3 = new HumorousErgenka("Svetla", (short) 20, 0, 8, 6);
        Ergenka[] startArray = new Ergenka[] {ergenka1, ergenka2, ergenka3};
        Ergenka[] result = new Ergenka[3];
        result[0] = ergenka1;
        result[1] = ergenka3;
        assertArrayEquals(result, rule.eliminateErgenkas(startArray), "The two arrays should be equal");
    }

    @Test
    void testEliminateErgenkasNoElimination() {
        String[] votes = new String[] {"Petya", "Petya", "Gergana", "Gergana", "Svetla", "Svetla"};
        rule = new PublicVoteEliminationRule(votes);
        Ergenka ergenka1 = new RomanticErgenka("Gergana", (short) 20, 2, 8, 8,"none");
        Ergenka ergenka2 = new RomanticErgenka("Petya", (short) 20, 4, 0, 7,"none");
        Ergenka ergenka3 = new HumorousErgenka("Svetla", (short) 20, 0, 8, 6);
        Ergenka[] startArray = new Ergenka[] {ergenka1, ergenka2, ergenka3};
        assertArrayEquals(startArray, rule.eliminateErgenkas(startArray), "The two arrays should be equal");
    }
}
