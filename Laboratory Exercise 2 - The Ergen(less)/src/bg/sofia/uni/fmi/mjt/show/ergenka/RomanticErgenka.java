package bg.sofia.uni.fmi.mjt.show.ergenka;

import bg.sofia.uni.fmi.mjt.show.date.DateEvent;

public class RomanticErgenka implements Ergenka {
    private static final short MIN_DATING_AGE = 18;
    private static final int MIN_DATE_DURATION = 30;
    private static final int MAX_DATE_DURATION = 120;
    private final String name;
    private final short age;
    private final int romanceLevel;
    private final int humorLevel;
    private int rating;
    private final String favoriteDateLocation;

    public RomanticErgenka(String name, short age, int romanceLevel, int humorLevel, int rating, String favoriteDateLocation) {
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
        this.favoriteDateLocation = favoriteDateLocation;
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
        int bonus = 0;
        if ( dateEvent.location().equals(favoriteDateLocation) ) {
            bonus += 5;
        }
        if ( dateEvent.duration() < MIN_DATE_DURATION) {
            bonus -= 3;
        }
        else if ( dateEvent.duration() > MAX_DATE_DURATION) {
            bonus -= 2;
        }

        int humorRating = Math.floorDiv(this.humorLevel, 3);

        rating = ( (romanceLevel * 7) / dateEvent.tensionLevel() ) + humorRating + bonus;
    }
}
