package bg.sofia.uni.fmi.mjt.burnout.plan;

import bg.sofia.uni.fmi.mjt.burnout.subject.SubjectRequirement;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SemesterPlanTest {

    @Test
    void testConstructorThrowsException() {
        assertThrows(IllegalArgumentException.class,
            () -> new SemesterPlan(null, new SubjectRequirement[10], 10),
            "The constructor should throw the correct exception because of subjects(null)");
        assertThrows(IllegalArgumentException.class,
            () -> new SemesterPlan(new UniversitySubject[10], null, 10),
            "The constructor should throw the correct exception because of subject requirements(null)");
        assertThrows(IllegalArgumentException.class,
            () -> new SemesterPlan(new UniversitySubject[10], new SubjectRequirement[10], -1),
            "The constructor should throw the correct exception because of minimal amount of credits(negative)");
    }
}
