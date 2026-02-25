package bg.sofia.uni.fmi.mjt.burnout.semester;

import bg.sofia.uni.fmi.mjt.burnout.exception.CryToStudentsDepartmentException;
import bg.sofia.uni.fmi.mjt.burnout.exception.InvalidSubjectRequirementsException;
import bg.sofia.uni.fmi.mjt.burnout.plan.SemesterPlan;
import bg.sofia.uni.fmi.mjt.burnout.subject.SubjectRequirement;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;

public final class SoftwareEngineeringSemesterPlanner extends AbstractSemesterPlanner {

    private static final int ZERO = 0;
    private static final int ONE = 1;

    private void checkSubjectListConditions(SemesterPlan semesterPlan) {
        if (semesterPlan == null) {
            throw new IllegalArgumentException("Semester plan is missing or null");
        }

        if (!areUniqueSubjectRequirements(semesterPlan.subjectRequirements())) {
            throw new InvalidSubjectRequirementsException("There are duplicated categories");
        }

        if (checkForNotEnoughSubjects(semesterPlan)) {
            throw new CryToStudentsDepartmentException("Student cannot complete the requirements");
        }
    }

    private UniversitySubject[] getSubjectForCategory(UniversitySubject[] subjects, SubjectRequirement requirement) {

        UniversitySubject[] memory = new UniversitySubject[requirement.minAmountEnrolled()];
        UniversitySubject maxElement;
        int maxCredits = ZERO;

        for (UniversitySubject subject : subjects) {
            if (subject.category().equals(requirement.category())) {
                if (subject.credits() > maxCredits) {
                    maxCredits = subject.credits();
                    maxElement = subject;
                    for (int i = ZERO; i < requirement.minAmountEnrolled() - ONE; i++) {
                        memory[i] = memory[i + ONE];
                    }
                    memory[requirement.minAmountEnrolled() - ONE] = maxElement;
                }
            }
        }

        return memory;
    }

    public UniversitySubject[] calculateSubjectList(SemesterPlan semesterPlan) throws
        InvalidSubjectRequirementsException {

        checkSubjectListConditions(semesterPlan);

        UniversitySubject[] result = new UniversitySubject[semesterPlan.subjects().length];
        int size = ZERO;
        for (SubjectRequirement requirement : semesterPlan.subjectRequirements()) {
            size = concatUniversitySubjectsArrays(result, getSubjectForCategory(semesterPlan.subjects(), requirement),
                size);
        }

        int credits = ZERO;
        for ( int i = ZERO; i < size; i++) {
            credits += result[i].credits();
        }

        for ( int i = size; i <= (semesterPlan.subjects().length - ONE); i++) {
            if ( credits >= semesterPlan.minimalAmountOfCredits()) {
                result = resizeToOnlySubjects(result);
                return result;
            }
            credits = addSubjectsForCoveringCredits(result, semesterPlan.subjects(), i, credits);
        }
        if ( credits < semesterPlan.minimalAmountOfCredits()) {
            throw new CryToStudentsDepartmentException("Student cant reach credit limit with the subjects");
        }
        result = resizeToOnlySubjects(result);
        return result;
    }
}
