package bg.sofia.uni.fmi.mjt.burnout.semester;
import bg.sofia.uni.fmi.mjt.burnout.exception.CryToStudentsDepartmentException;
import bg.sofia.uni.fmi.mjt.burnout.exception.InvalidSubjectRequirementsException;
import bg.sofia.uni.fmi.mjt.burnout.plan.SemesterPlan;
import bg.sofia.uni.fmi.mjt.burnout.subject.Category;
import bg.sofia.uni.fmi.mjt.burnout.subject.SubjectRequirement;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ComputerScienceSemesterPlannerTest {

    static ComputerScienceSemesterPlanner planner = new ComputerScienceSemesterPlanner();
    static SubjectRequirement[] invalidRequirements;
    static SubjectRequirement[] correctRequirements;
    static UniversitySubject[] invalidSubjects;
    static UniversitySubject[] correctSubjects;
    static SemesterPlan plan = mock();


    @BeforeAll
    static void setUpEnvironment() {
        invalidRequirements = new SubjectRequirement[] {new SubjectRequirement(Category.MATH, 5),
            new SubjectRequirement(Category.PRACTICAL, 9),
            new SubjectRequirement(Category.PRACTICAL, 3)};
        invalidSubjects = new UniversitySubject[] {
            new UniversitySubject("Discrete Math", 4, 3, Category.MATH, 25),
            new UniversitySubject("OOP Practicum", 5, 1, Category.PRACTICAL, 25)};
        correctRequirements = new SubjectRequirement[] {new SubjectRequirement(Category.MATH, 1),
            new SubjectRequirement(Category.PRACTICAL, 2)};
        correctSubjects = new UniversitySubject[] {
            new UniversitySubject("Discrete Math", 4, 3, Category.MATH, 45),
            new UniversitySubject("OOP Practicum", 5, 1, Category.PRACTICAL, 20),
            new UniversitySubject("GIT", 7, 5, Category.PROGRAMMING, 35),
            new UniversitySubject("Differential and Integral Calculation 3", 4, 5,
                Category.MATH, 30),
            new UniversitySubject("Data structures and algorithms Practicum", 7, 5,
                Category.PRACTICAL, 55)};
    }

    @Test
    void testCalculateSubjectListThrowsException() {
        when(plan.subjectRequirements()).thenReturn(invalidRequirements);
        assertThrows(InvalidSubjectRequirementsException.class, () -> planner.calculateSubjectList(plan),
            "The method should throw exception because of invalid requirements");
        assertThrows(IllegalArgumentException.class, () -> planner.calculateSubjectList(null),
            "The method should throw exception because of semester plan(null)");
        assertThrows(InvalidSubjectRequirementsException.class, () -> planner.calculateSubjectList(plan),
            "The method should throw exception because of invalid requirements");
        when(plan.subjectRequirements()).thenReturn(correctRequirements);
        when(plan.subjects()).thenReturn(invalidSubjects);
        assertThrows(CryToStudentsDepartmentException.class, () -> planner.calculateSubjectList(plan),
            "The method should throw exception because of impossible to meet requirements");
    }

    @Test
    void testCalculateSubjectListCorrectResult() {
        when(plan.subjects()).thenReturn(correctSubjects);
        when(plan.subjectRequirements()).thenReturn(correctRequirements);
        when(plan.minimalAmountOfCredits()).thenReturn(15);
        UniversitySubject[] list = new UniversitySubject[] {
            new UniversitySubject("GIT", 7, 5, Category.PROGRAMMING, 35),
            new UniversitySubject("Differential and Integral Calculation 3", 4, 5,
                Category.MATH, 30),
            new UniversitySubject("Data structures and algorithms Practicum", 7, 5,
                Category.PRACTICAL, 55),
            new UniversitySubject("Discrete Math", 4, 3, Category.MATH, 45),
            new UniversitySubject("OOP Practicum", 5, 1, Category.PRACTICAL, 20),};
        assertIterableEquals(Arrays.stream(list).toList(), List.of(planner.calculateSubjectList(plan)), "The array should be equal to the result");
    }
}
