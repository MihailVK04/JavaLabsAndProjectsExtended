package bg.sofia.uni.fmi.mjt.show.ergenka;

import bg.sofia.uni.fmi.mjt.show.date.DateEvent;

public class HumorousErgenka implements Ergenka{
    private static final short MIN_DATING_AGE = 18;
    private static final int MIN_DATE_DURATION = 30;
    private static final int MAX_DATE_DURATION = 90;
    private final String name;
    private final short age;
    private final int romanceLevel;
    private final int humorLevel;
    private int rating;

    public HumorousErgenka(String name, short age, int romanceLevel, int humorLevel, int rating) {

        this.name = name;
        if (age < MIN_DATING_AGE) {
            this.age = MIN_DATING_AGE;
        }
        else{
            this.age = age;
        }
        this.romanceLevel = romanceLevel;
        this.humorLevel = humorLevel;
        this.rating = rating;
    }

    public String getName(){
        return name;
    }

    public short getAge() {
        return age;
    }

    public int getRomanceLevel() {
        return romanceLevel;
    }

    public int getHumorLevel() {
        return humorLevel;
    }

    public int getRating() {
        return rating;
    }

    public void reactToDate(DateEvent dateEvent) {
        int bonus;
        if ( dateEvent.duration() < MIN_DATE_DURATION ){
            bonus = -2;
        }
        else if ( dateEvent.duration() > MAX_DATE_DURATION ){
            bonus = -3;
        }
        else{
            bonus = 4;
        }

        int romanceBonus = Math.floorDiv(this.romanceLevel, 3);
        rating = ( ( humorLevel * 5 ) / dateEvent.tensionLevel() ) + romanceBonus + bonus;
    }
}
