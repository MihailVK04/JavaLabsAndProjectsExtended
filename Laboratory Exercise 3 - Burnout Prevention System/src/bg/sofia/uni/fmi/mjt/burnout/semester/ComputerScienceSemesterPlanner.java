package bg.sofia.uni.fmi.mjt.burnout.semester;

import bg.sofia.uni.fmi.mjt.burnout.exception.CryToStudentsDepartmentException;
import bg.sofia.uni.fmi.mjt.burnout.exception.InvalidSubjectRequirementsException;
import bg.sofia.uni.fmi.mjt.burnout.plan.SemesterPlan;
import bg.sofia.uni.fmi.mjt.burnout.subject.Category;
import bg.sofia.uni.fmi.mjt.burnout.subject.SubjectRequirement;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;

public final class ComputerScienceSemesterPlanner extends AbstractSemesterPlanner {

    private static final int ZERO = 0;

    private int[] getRequirementsMap(SemesterPlan semesterPlan) {
        int[] requirementsAmountMap = new int[semesterPlan.subjectRequirements().length];

        for ( int i = ZERO; i < semesterPlan.subjectRequirements().length; i++) {
            requirementsAmountMap[i] = semesterPlan.subjectRequirements()[i].minAmountEnrolled();
        }

        return requirementsAmountMap;
    }

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

    private boolean allRequirementsAreMet(int[] requirementsCount) {
        for (int i : requirementsCount) {
            if ( i != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean subjectAlreadyInUse(UniversitySubject current, UniversitySubject[] resultArray) {
        for (UniversitySubject subject : resultArray) {
            if (current.equals(subject)) {
                return true;
            }
        }
        return false;
    }

    private UniversitySubject getBestRatedSubject(SemesterPlan semesterPlan, UniversitySubject[] resultArray) {
        int bestRating = ZERO;
        UniversitySubject bestRatedSubject = null;

        for (UniversitySubject itr : semesterPlan.subjects()) {
            if (itr.rating() > bestRating && !subjectAlreadyInUse(itr, resultArray)) {
                bestRating = itr.rating();
                bestRatedSubject = itr;
            }
        }

        return bestRatedSubject;
    }

    public UniversitySubject[] calculateSubjectList(SemesterPlan semesterPlan) throws
        InvalidSubjectRequirementsException {
        checkSubjectListConditions(semesterPlan);

        UniversitySubject[] result = new UniversitySubject[semesterPlan.subjects().length];
        int credits = ZERO;
        int[] requirementsAmountMap = getRequirementsMap(semesterPlan);

        for ( int i = ZERO; i < semesterPlan.subjects().length; i++) {
            result[i] = getBestRatedSubject(semesterPlan, result);
            credits += result[i].credits();
            Category category = result[i].category();
            int index = ZERO;
            for (SubjectRequirement subjectRequirement : semesterPlan.subjectRequirements()) {
                if (subjectRequirement.category().equals(category)) {
                    requirementsAmountMap[index]--;
                }
                index++;
            }
            if (credits > semesterPlan.minimalAmountOfCredits() && allRequirementsAreMet(requirementsAmountMap)) {
                result = resizeToOnlySubjects(result);
                return result;
            }
        }
        if (!allRequirementsAreMet(requirementsAmountMap) && credits < semesterPlan.minimalAmountOfCredits()) {
            throw new CryToStudentsDepartmentException("Student cannot meet all his requirements");
        } else {
            result = resizeToOnlySubjects(result);
            return result;
        }
    }
}
