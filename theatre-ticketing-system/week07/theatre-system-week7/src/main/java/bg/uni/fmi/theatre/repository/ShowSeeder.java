package bg.uni.fmi.theatre.repository;

import bg.uni.fmi.theatre.domain.Show;
import bg.uni.fmi.theatre.vo.AgeRating;
import bg.uni.fmi.theatre.vo.Genre;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ShowSeeder implements CommandLineRunner {

    private static final int HAMLET_DURATION = 120;
    private static final int CHICAGO_DURATION = 135;
    private static final int MIDSUMMER_NIGHT_DREAM_DURATION = 95;
    private static final int PHANTOM_OF_OPERA_DURATION = 150;
    private static final int SWAN_LAKE_DURATION = 140;
    private static final int ZERO = 0;
    private final ShowRepository shows;

    public ShowSeeder(ShowRepository shows) {
        this.shows = shows;
    }

    @Override
    public void run(String... args) {
        if (shows.count() > ZERO) {
            return;
        }

        shows.save(new Show(null, "Hamlet", "Shakespeare's tragedy", Genre.DRAMA, HAMLET_DURATION,
            AgeRating.PG_16));

        shows.save(new Show(null, "Chicago", "Broadway musical", Genre.MUSICAL, CHICAGO_DURATION,
            AgeRating.PG_12));

        shows.save(new Show(null, "A Midsummer Night's Dream", "Comedy classic", Genre.COMEDY,
            MIDSUMMER_NIGHT_DREAM_DURATION, AgeRating.ALL));

        shows.save(new Show(null, "The Phantom of the Opera", "Epic musical", Genre.MUSICAL,
            PHANTOM_OF_OPERA_DURATION, AgeRating.PG_12));

        shows.save(new Show(null, "Swan Lake", "Tchaikovsky's ballet", Genre.BALLET, SWAN_LAKE_DURATION,
            AgeRating.ALL));
    }
}
