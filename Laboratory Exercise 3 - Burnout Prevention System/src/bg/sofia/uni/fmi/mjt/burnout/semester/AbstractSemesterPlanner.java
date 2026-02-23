package bg.sofia.uni.fmi.mjt.burnout.semester;

import bg.sofia.uni.fmi.mjt.burnout.exception.DisappointmentException;
import bg.sofia.uni.fmi.mjt.burnout.plan.SemesterPlan;
import bg.sofia.uni.fmi.mjt.burnout.subject.Category;
import bg.sofia.uni.fmi.mjt.burnout.subject.SubjectRequirement;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;

public abstract sealed class AbstractSemesterPlanner implements SemesterPlannerAPI permits ComputerScienceSemesterPlanner, SoftwareEngineeringSemesterPlanner{

    private static final int DAYS_FOR_ONE_JAR = 5;
    private static final int DOUBLE = 2;

    protected int addSubjectsForCoveringCredits(UniversitySubject[] destination, UniversitySubject[] arrayWithAllSubjects, int size, int credits) {
        int maxCredits = 0;
        UniversitySubject maxElement = null;

        for ( UniversitySubject subject : arrayWithAllSubjects){
            if(subject.credits() > maxCredits) {
                for (int i = 0; i <= size; i++) {
                    if (!(subject.equals(destination[i]))) {
                        maxCredits = subject.credits();
                        maxElement = subject;
                    }
                }
            }
        }

        destination[size+1] = maxElement;
        return maxCredits + credits;
    }

    protected int concatUniversitySubjectsArrays(UniversitySubject[] destination, UniversitySubject[] arrayForConcat, int size) {
        for ( int i = 0; i < arrayForConcat.length; i++){
            destination[size + i] = arrayForConcat[i];
        }
        return size + arrayForConcat.length - 1;
    }

    protected boolean checkForNotEnoughSubjects(SemesterPlan semesterPlan){

        for (SubjectRequirement requirement : semesterPlan.subjectRequirements()){
            int count = 0;

            for (UniversitySubject subject : semesterPlan.subjects()) {
                if (subject.category().equals(requirement.category())) {
                    count++;
                }
            }
            if (count < requirement.minAmountEnrolled()) {
                return true;
            }
        }

        return false;
    }

    protected boolean checkUniqueSubjectRequirements(SubjectRequirement[] subjectRequirements){
        Category[] categoryValues = Category.values();
        int[] occurrences = new int[categoryValues.length];

        for (SubjectRequirement requirement : subjectRequirements) {
            Category category = requirement.category();
            int index = 0;

            for (Category match : categoryValues) {
                if (match.equals(category)){
                    occurrences[index] += 1;
                }
                if (occurrences[index] > 1){
                    return false;
                }
                index++;
            }
        }

        return true;
    }

    public int calculateJarCount(UniversitySubject[] subjects, int maximumSlackTime, int semesterDuration) throws IllegalArgumentException, DisappointmentException {

        if ( subjects == null ){
            throw new IllegalArgumentException("Subjects are null");
        }
        else if ( maximumSlackTime <= 0 ) {
            throw new IllegalArgumentException("MaximumSlackTime is not positive");
        }
        else if ( semesterDuration <= 0 ) {
            throw new IllegalArgumentException("SemesterDuration is not positive");
        }

        int sumOfBreak = 0, sumOfLearning = 0;
        for ( UniversitySubject subject : subjects ) {
            sumOfBreak += Math.ceil((subject.neededStudyTime() * subject.category().coefficient()));
            sumOfLearning += subject.neededStudyTime();
        }

        int freeTimeThisSemester = semesterDuration - (sumOfBreak + sumOfLearning);
        if (freeTimeThisSemester > maximumSlackTime) {
            throw new DisappointmentException("Grandkid has too much free time");
        }

        int jarsPerDays = Math.ceilDiv(sumOfLearning,DAYS_FOR_ONE_JAR);
        if(freeTimeThisSemester < 0){
            return jarsPerDays * DOUBLE;
        }
        else {
            return jarsPerDays;
        }
    }
}
