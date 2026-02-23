package bg.sofia.uni.fmi.mjt.burnout.subject;

public enum Category {

    MATH,
    PROGRAMMING,
    THEORY,
    PRACTICAL;

    public double coefficient(){
        return switch (this){
            case MATH -> 0.2;
            case PROGRAMMING -> 0.1;
            case THEORY -> 0.15;
            case PRACTICAL -> 0.05;
        };
    }
}
