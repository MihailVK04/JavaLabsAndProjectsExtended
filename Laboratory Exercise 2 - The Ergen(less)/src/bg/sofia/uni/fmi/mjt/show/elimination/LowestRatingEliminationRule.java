package bg.sofia.uni.fmi.mjt.show.elimination;

import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

import java.lang.reflect.Array;

public class LowestRatingEliminationRule implements EliminationRule{
    private static final int MAX_RATING = 10;

    public Ergenka[] eliminateErgenkas(Ergenka[] ergenkas) {
        int size = Array.getLength(ergenkas);
        int minRating = getMinRating(size, ergenkas);

        Ergenka[] result = new Ergenka[size];
        int newIndex = 0;

        for ( int i = 0; i < size; i++ ) {
            if ( ergenkas[i].getRating() > minRating){
                result[newIndex] = ergenkas[i];
                newIndex++;
            }
        }

        return result;
    }

    private int getMinRating(int size, Ergenka[] ergenkas) {
        int minRating = MAX_RATING;

        for ( int i = 0; i < size; i++ ) {
            int rating = ergenkas[i].getRating();
            if ( rating < minRating ) {
                minRating = rating;
            }
        }

        return minRating;
    }
}
