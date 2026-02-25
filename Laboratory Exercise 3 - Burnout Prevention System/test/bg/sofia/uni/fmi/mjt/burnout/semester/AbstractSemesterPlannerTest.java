package bg.sofia.uni.fmi.mjt.burnout.semester;

import bg.sofia.uni.fmi.mjt.burnout.exception.DisappointmentException;
import bg.sofia.uni.fmi.mjt.burnout.subject.Category;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractSemesterPlannerTest {

    //Could work with ComputerScience, I just chose the SoftwareEngineering.
    AbstractSemesterPlanner planner = new SoftwareEngineeringSemesterPlanner();

    @Test
    void testCalculateJarCountThrowsException() {
        assertThrows(IllegalArgumentException.class,
            () -> planner.calculateJarCount(null, 5, 40),
            "Should throw exception because of subjects(null)");
        assertThrows(IllegalArgumentException.class,
            () -> planner.calculateJarCount(
                new UniversitySubject[] {
                    new UniversitySubject("GIT", 5, 4, Category.PROGRAMMING, 10)},
                0, 40),
            "Should throw exception because of maximumSlackTime(not positive)");
        assertThrows(IllegalArgumentException.class,
            () -> planner.calculateJarCount(
                new UniversitySubject[] {
                    new UniversitySubject("GIT", 5, 4, Category.PROGRAMMING, 10)},
                5, 0),
            "Should throw exception because of semesterDuration(not positive)");
        UniversitySubject[] subjects = new UniversitySubject[] {
            new UniversitySubject("SARS", 5, 3, Category.THEORY, 15),
            new UniversitySubject( "ISE", 5, 2, Category.THEORY, 20)};
        assertThrows(DisappointmentException.class,
            () -> planner.calculateJarCount(
                subjects,
                10, 120),
            "Should throw exception because of slacking student(disappointed)");
    }

    @Test
    void testCalculateJarCountCorrect() {
        UniversitySubject[] subjects = new UniversitySubject[] {
            new UniversitySubject("SARS", 5, 3, Category.THEORY, 15),
            new UniversitySubject( "ISE", 5, 2, Category.THEORY, 20)};
        assertEquals(7, planner.calculateJarCount( subjects, 15, 50),
            "The count of the jars should Equal");
        assertEquals(14, planner.calculateJarCount( subjects, 15, 35),
            "The count of the jars should Equal");
    }
}
