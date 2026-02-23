package bg.sofia.uni.fmi.mjt.burnout.semester;

import bg.sofia.uni.fmi.mjt.burnout.exception.CryToStudentsDepartmentException;
import bg.sofia.uni.fmi.mjt.burnout.exception.InvalidSubjectRequirementsException;
import bg.sofia.uni.fmi.mjt.burnout.plan.SemesterPlan;
import bg.sofia.uni.fmi.mjt.burnout.subject.SubjectRequirement;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;

public final class SoftwareEngineeringSemesterPlanner extends AbstractSemesterPlanner{

    private UniversitySubject[] getSubjectForCategory(UniversitySubject[] subjects, SubjectRequirement requirement) {

        UniversitySubject[] memory = new UniversitySubject[requirement.minAmountEnrolled()];
        UniversitySubject maxElement;
        int maxCredits = 0;

        for (UniversitySubject subject : subjects) {
            if (subject.category().equals(requirement.category())) {
                if(subject.credits() > maxCredits){
                    maxCredits = subject.credits();
                    maxElement = subject;
                    for(int i = 0; i < requirement.minAmountEnrolled()-1; i++){
                        memory[i] = memory[i + 1];
                    }
                    memory[requirement.minAmountEnrolled() - 1] = maxElement;
                }
            }
        }

        return memory;
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
        int size = 0;
        for (SubjectRequirement requirement : semesterPlan.subjectRequirements()){
            size = concatUniversitySubjectsArrays(result,getSubjectForCategory(semesterPlan.subjects(),requirement),size);
        }

        int credits = 0;
        for ( int i = 0; i <= size; i++){
            credits += result[i].credits();
        }


        for ( int i = size; i <= (semesterPlan.subjects().length - 1); i++){
            if ( credits >= semesterPlan.minimalAmountOfCredits()) {
                return result;
            }
            credits = addSubjectsForCoveringCredits(result, semesterPlan.subjects(), i, credits);
        }
        if ( credits < semesterPlan.minimalAmountOfCredits()) {
            throw new CryToStudentsDepartmentException("Student cant reach credit limit with the subjects");
        }
        return result;
    }
}
