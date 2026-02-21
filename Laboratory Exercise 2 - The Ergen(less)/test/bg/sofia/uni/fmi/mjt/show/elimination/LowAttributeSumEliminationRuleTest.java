package bg.sofia.uni.fmi.mjt.show.elimination;

import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;
import bg.sofia.uni.fmi.mjt.show.ergenka.HumorousErgenka;
import bg.sofia.uni.fmi.mjt.show.ergenka.RomanticErgenka;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LowAttributeSumEliminationRuleTest {

    static LowAttributeSumEliminationRule rule;

    @BeforeAll
    static void setUpEnvironment() {
        rule = new LowAttributeSumEliminationRule(5);
    }

    @Test
    void testEliminateErgenkasCorrectElimination() {
        Ergenka ergenka1 = new RomanticErgenka("Gergana", (short) 20, 2, 8, 4,"none");
        Ergenka ergenka2 = new RomanticErgenka("Petya", (short) 20, 4, 0, 4,"none");
        Ergenka ergenka3 = new HumorousErgenka("Svetla", (short) 20, 0, 8, 4);
        Ergenka[] startArray = new Ergenka[] {ergenka1, ergenka2, ergenka3};
        Ergenka[] result = new Ergenka[3];
        result[0] = ergenka1;
        result[1] = ergenka3;
        assertArrayEquals(result, rule.eliminateErgenkas(startArray), "The two arrays should be equal");
    }
}
