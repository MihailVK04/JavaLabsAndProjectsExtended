package bg.sofia.uni.fmi.mjt.show.elimination;

import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

import java.util.Arrays;

public class PublicVoteEliminationRule implements EliminationRule{
    private static final int ONE_VOTE = 1;
    private static final int ZERO = 0;
    private static final int HALF = 2;
    private static final String NO_NAME = "none";
    private final String[] votes;

    private String findEliminated(){
        int threshold = Math.floorDiv(votes.length, HALF) + ONE_VOTE;
        int size = votes.length;
        Arrays.sort(votes);
        String name = NO_NAME;
        int count = ZERO;

        for ( int i = 0; i < size; i++ ) {
            if ( votes[i].equals(name) ) {
                count++;
                if ( count >= threshold ) {
                    return name;
                }
            }
            else {
                name = votes[i];
                count = ONE_VOTE;
            }
        }
        return NO_NAME;
    }

    public PublicVoteEliminationRule(String[] votes){
        this.votes = votes;
    }

    public Ergenka[] eliminateErgenkas(Ergenka[] ergenkas) {
        String nameOfEliminated = findEliminated();
        if ( nameOfEliminated.equals(NO_NAME) ){
            return ergenkas;
        }
        else{
            int newIndex = ZERO;
            Ergenka[] result = new Ergenka[ergenkas.length];
            for (Ergenka ergenka : ergenkas) {
                if (!nameOfEliminated.equals(ergenka.getName())) {
                    result[newIndex] = ergenka;
                    newIndex++;
                }
            }
            return result;
        }
    }
}
