package bg.sofia.uni.fmi.mjt.burnout.semester;

import bg.sofia.uni.fmi.mjt.burnout.exception.DisappointmentException;
import bg.sofia.uni.fmi.mjt.burnout.plan.SemesterPlan;
import bg.sofia.uni.fmi.mjt.burnout.subject.Category;
import bg.sofia.uni.fmi.mjt.burnout.subject.SubjectRequirement;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;

public abstract sealed class AbstractSemesterPlanner implements SemesterPlannerAPI permits
    ComputerScienceSemesterPlanner, SoftwareEngineeringSemesterPlanner {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int DAYS_FOR_ONE_JAR = 5;
    private static final int DOUBLE = 2;

    protected UniversitySubject[] resizeToOnlySubjects(UniversitySubject[] result) {
        int size = ZERO;
        for (UniversitySubject subject : result) {
            if (subject != null) {
                size++;
            } else {
                break;
            }
        }
        if (size == ZERO) {
            return null;
        }
        UniversitySubject[] correctResult = new UniversitySubject[size];
        System.arraycopy(result, ZERO, correctResult, ZERO, size);
        return correctResult;
    }

    private void checkConditionsForJarCount(UniversitySubject[] subjects, int maximumSlackTime, int semesterDuration) {
        if ( subjects == null ) {
            throw new IllegalArgumentException("Subjects are null");
        } else if ( maximumSlackTime <= ZERO ) {
            throw new IllegalArgumentException("MaximumSlackTime is not positive");
        } else if ( semesterDuration <= ZERO ) {
            throw new IllegalArgumentException("SemesterDuration is not positive");
        }
    }

    protected int addSubjectsForCoveringCredits(UniversitySubject[] destination,
                                                UniversitySubject[] arrayWithAllSubjects, int size, int credits) {
        int maxCredits = ZERO;
        UniversitySubject maxElement = null;

        for ( UniversitySubject subject : arrayWithAllSubjects) {
            if (subject.credits() > maxCredits) {
                for (int i = ZERO; i <= size; i++) {
                    if (!(subject.equals(destination[i]))) {
                        maxCredits = subject.credits();
                        maxElement = subject;
                        break;
                    }
                }
            }
        }

        destination[size + ONE] = maxElement;
        return maxCredits + credits;
    }

    protected int concatUniversitySubjectsArrays(UniversitySubject[] destination, UniversitySubject[] arrayForConcat,
                                                 int size) {
        System.arraycopy(arrayForConcat, ZERO, destination, size, arrayForConcat.length);
        return size + arrayForConcat.length;
    }

    protected boolean checkForNotEnoughSubjects(SemesterPlan semesterPlan) {

        for (SubjectRequirement requirement : semesterPlan.subjectRequirements()) {
            int count = ZERO;

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

    protected boolean areUniqueSubjectRequirements(SubjectRequirement[] subjectRequirements) {
        Category[] categoryValues = Category.values();
        int[] occurrences = new int[categoryValues.length];

        for (SubjectRequirement requirement : subjectRequirements) {
            Category category = requirement.category();
            int index = ZERO;

            for (Category match : categoryValues) {
                if (match.equals(category)) {
                    occurrences[index] += ONE;
                }
                if (occurrences[index] > ONE) {
                    return false;
                }
                index++;
            }
        }

        return true;
    }

    public int calculateJarCount(UniversitySubject[] subjects, int maximumSlackTime, int semesterDuration) {

        checkConditionsForJarCount(subjects, maximumSlackTime, semesterDuration);

        int sumOfBreak = ZERO;
        int sumOfLearning = ZERO;
        for ( UniversitySubject subject : subjects ) {
            sumOfBreak += (int) Math.ceil((subject.neededStudyTime() * subject.category().getCoefficient()));
            sumOfLearning += subject.neededStudyTime();
        }

        int freeTimeThisSemester = semesterDuration - (sumOfBreak + sumOfLearning);
        if (freeTimeThisSemester > maximumSlackTime) {
            throw new DisappointmentException("Grandkid has too much free time");
        }

        int jarsPerDays = Math.ceilDiv(sumOfLearning, DAYS_FOR_ONE_JAR);
        if (freeTimeThisSemester < ZERO) {
            return jarsPerDays * DOUBLE;
        } else {
            return jarsPerDays;
        }
    }
}
