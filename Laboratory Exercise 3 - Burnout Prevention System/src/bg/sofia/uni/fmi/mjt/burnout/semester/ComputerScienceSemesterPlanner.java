package bg.sofia.uni.fmi.mjt.burnout.semester;

import bg.sofia.uni.fmi.mjt.burnout.exception.CryToStudentsDepartmentException;
import bg.sofia.uni.fmi.mjt.burnout.exception.InvalidSubjectRequirementsException;
import bg.sofia.uni.fmi.mjt.burnout.plan.SemesterPlan;
import bg.sofia.uni.fmi.mjt.burnout.subject.Category;
import bg.sofia.uni.fmi.mjt.burnout.subject.SubjectRequirement;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;

public final class ComputerScienceSemesterPlanner extends AbstractSemesterPlanner{

    private boolean allRequirementsAreMet(int[] requirementsCount) {
        for (int i : requirementsCount){
            if ( i != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean subjectAlreadyInUse(UniversitySubject current, UniversitySubject[] resultArray){
        for (UniversitySubject subject : resultArray) {
            if (current.equals(subject)) {
                return true;
            }
        }
        return false;
    }

    private UniversitySubject getBestRatedSubject(SemesterPlan semesterPlan, UniversitySubject[] resultArray) {
        int bestRating = 0;
        UniversitySubject bestRatedSubject = null;

        for (UniversitySubject itr : semesterPlan.subjects()) {
            if (itr.rating() > bestRating && !subjectAlreadyInUse(itr,resultArray)) {
                bestRating = itr.rating();
                bestRatedSubject = itr;
            }
        }

        return bestRatedSubject;
    }

    public UniversitySubject[] calculateSubjectList(SemesterPlan semesterPlan) throws InvalidSubjectRequirementsException, CryToStudentsDepartmentException, IllegalArgumentException {

        if (semesterPlan.subjects() == null) {
            throw new IllegalArgumentException("Semester plan is missing or null");
        }

        if (!checkUniqueSubjectRequirements(semesterPlan.subjectRequirements())) {
            throw new InvalidSubjectRequirementsException("There are duplicated categories");
        }

        if (checkForNotEnoughSubjects(semesterPlan)) {
            throw new CryToStudentsDepartmentException("Student cannot complete the requirements");
        }

        UniversitySubject[] result = new UniversitySubject[semesterPlan.subjects().length];
        int credits = 0;
        int[] requirementsAmountMap = new int[semesterPlan.subjectRequirements().length];

        for ( int i = 0; i < semesterPlan.subjectRequirements().length; i++) {
            requirementsAmountMap[i] = semesterPlan.subjectRequirements()[i].minAmountEnrolled();
        }

        for ( int i = 0; i < semesterPlan.subjects().length; i++) {
            result[i] = getBestRatedSubject(semesterPlan, result);
            credits += result[i].credits();
            Category category = result[i].category();
            int index = 0;

            for (SubjectRequirement subjectRequirement : semesterPlan.subjectRequirements()) {
                if (subjectRequirement.category().equals(category)) {
                    requirementsAmountMap[index]--;
                }
                index++;
            }

            if (credits > semesterPlan.minimalAmountOfCredits() && allRequirementsAreMet(requirementsAmountMap)) {
                return result;
            }
        }

        if(!allRequirementsAreMet(requirementsAmountMap) && credits < semesterPlan.minimalAmountOfCredits()) {
            throw new CryToStudentsDepartmentException("Student cannot meet all his requirements");
        }
        else{
            return result;
        }
    }
}
