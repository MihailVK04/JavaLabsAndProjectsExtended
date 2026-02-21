package bg.sofia.uni.fmi.mjt.show.elimination;

import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

import java.lang.reflect.Array;

public class LowAttributeSumEliminationRule implements EliminationRule{
    private static final int ZERO = 0;
    private int threshold = ZERO;

    public LowAttributeSumEliminationRule(int threshold){
        if (threshold > ZERO) {
            this.threshold = threshold;
        }
    }

    public Ergenka[] eliminateErgenkas(Ergenka[] ergenkas) {
        int size = Array.getLength(ergenkas);
        Ergenka[] result = new Ergenka[size];
        int attributeSum, newIndex = 0;

        for ( int i = 0; i < size; i++ ) {
            attributeSum = sumOfAttributes(ergenkas[i]);
            if ( attributeSum >= threshold ) {
                result[newIndex] = ergenkas[i];
                newIndex++;
            }
        }

        return result;
    }

    private int sumOfAttributes(Ergenka ergenka) {
        return ergenka.getHumorLevel() + ergenka.getRomanceLevel();
    }
}
