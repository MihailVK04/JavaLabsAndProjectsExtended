package bg.uni.fmi.theatre.repository;

import bg.uni.fmi.theatre.domain.Hall;
import bg.uni.fmi.theatre.domain.Performance;
import bg.uni.fmi.theatre.domain.Show;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Order(3)
public class PerformanceSeeder implements CommandLineRunner {

    private final PerformanceRepository performances;
    private final ShowRepository shows;
    private final HallRepository halls;

    public PerformanceSeeder(PerformanceRepository performances, ShowRepository shows, HallRepository halls) {
        this.performances = performances;
        this.shows = shows;
        this.halls = halls;
    }

    @Override
    public void run(String... args) {
        if (performances.count() > 0) return;

        Show hamlet = shows.findAll().stream()
            .filter(s -> s.getTitle().equals("Hamlet"))
            .findFirst().orElseThrow();
        Hall mainStage = halls.findAll().stream()
            .filter(h -> h.getName().equals("Main Stage"))
            .findFirst().orElseThrow();

        performances.save(new Performance(hamlet, mainStage,
            LocalDateTime.of(2026, 6, 15, 19, 0)));
        performances.save(new Performance(hamlet, mainStage,
            LocalDateTime.of(2026, 6, 22, 19, 0)));

        Show chicago = shows.findAll().stream()
            .filter(s -> s.getTitle().equals("Chicago"))
            .findFirst().orElseThrow();
        Hall studioTheatre = halls.findAll().stream()
            .filter(h -> h.getName().equals("Studio Theatre"))
            .findFirst().orElseThrow();

        performances.save(new Performance(chicago, studioTheatre,
            LocalDateTime.of(2026, 6, 18, 17, 0)));
        performances.save(new Performance(chicago, studioTheatre,
            LocalDateTime.of(2026, 6, 25, 17, 0)));

        Show swanLake = shows.findAll().stream()
            .filter(s -> s.getTitle().equals("Swan Lake"))
            .findFirst().orElseThrow();
        Hall amphitheatre = halls.findAll().stream()
            .filter(h -> h.getName().equals("Open Air Amphitheatre"))
            .findFirst().orElseThrow();

        performances.save(new Performance(swanLake, amphitheatre,
            LocalDateTime.of(2026, 7, 6, 20, 0)));
        performances.save(new Performance(swanLake, amphitheatre,
            LocalDateTime.of(2026, 7, 13, 20, 0)));

        // ... add more as needed
    }
}
