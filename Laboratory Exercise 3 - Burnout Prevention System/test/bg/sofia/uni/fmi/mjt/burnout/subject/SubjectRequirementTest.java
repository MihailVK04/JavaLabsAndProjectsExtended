package bg.sofia.uni.fmi.mjt.burnout.subject;

import bg.sofia.uni.fmi.mjt.burnout.subject.Category;
import bg.sofia.uni.fmi.mjt.burnout.subject.SubjectRequirement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubjectRequirementTest {

    @Test
    void testConstructorThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new SubjectRequirement(null, 5),
            "The constructor should throw the correct exception because of Category(null)");
        assertThrows(IllegalArgumentException.class, () -> new SubjectRequirement(Category.MATH, -5),
            "The constructor should throw the correct exception because of amount enrolled(negative)");
    }
}
