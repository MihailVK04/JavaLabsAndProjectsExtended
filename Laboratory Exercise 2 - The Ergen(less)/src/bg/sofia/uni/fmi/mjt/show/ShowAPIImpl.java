package bg.sofia.uni.fmi.mjt.show;

import bg.sofia.uni.fmi.mjt.show.date.DateEvent;
import bg.sofia.uni.fmi.mjt.show.elimination.EliminationRule;
import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

public class ShowAPIImpl implements ShowAPI{
    private Ergenka[] ergenkas;
    private final EliminationRule[] defaultEliminationRules;

    private void playEliminations(EliminationRule[] eliminations){
        for ( EliminationRule rule : eliminations ) {
            ergenkas = rule.eliminateErgenkas(ergenkas);
        }
    }

    public ShowAPIImpl(Ergenka[] ergenkas, EliminationRule[] defaultEliminationRules) {
        this.ergenkas = ergenkas;
        this.defaultEliminationRules = defaultEliminationRules;
    }

    public Ergenka[] getErgenkas() {
        return ergenkas;
    }

    public void playRound(DateEvent dateEvent) {
        for ( Ergenka ergenka : ergenkas){
            organizeDate(ergenka,dateEvent);
        }
    }

    public void eliminateErgenkas(EliminationRule[] eliminationRules) {
        if ( eliminationRules.length == 0 ) {
            playEliminations(defaultEliminationRules);
        }
        else{
            playEliminations(eliminationRules);
        }
    }

    public void organizeDate(Ergenka ergenka, DateEvent dateEvent) {
        ergenka.reactToDate(dateEvent);
    }

    public void setErgenkas(Ergenka[] ergenkas) {
        this.ergenkas = ergenkas;
    }
}
