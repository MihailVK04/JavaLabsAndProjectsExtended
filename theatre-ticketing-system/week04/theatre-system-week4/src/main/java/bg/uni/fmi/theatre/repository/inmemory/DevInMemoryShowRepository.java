package bg.uni.fmi.theatre.repository.inmemory;

import bg.uni.fmi.theatre.domain.Show;
import bg.uni.fmi.theatre.repository.ShowRepository;
import bg.uni.fmi.theatre.vo.AgeRating;
import bg.uni.fmi.theatre.vo.Genre;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("dev")
public class DevInMemoryShowRepository implements ShowRepository {

    private static final int HAMLET_DURATION = 120;
    private static final int CHICAGO_DURATION = 135;
    private static final int MIDSUMMER_NIGHT_DREAM_DURATION = 95;
    private static final int PHANTOM_OF_OPERA_DURATION = 150;
    private static final int SWAN_LAKE_DURATION = 140;
    private final Map<Long, Show> store = new HashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    @PostConstruct
    public void seed() {
        save(new Show(nextId(), "Hamlet", "Shakespeare's tragedy", Genre.DRAMA, HAMLET_DURATION,
            AgeRating.PG_16));
        save(new Show(nextId(), "Chicago", "Broadway musical", Genre.MUSICAL, CHICAGO_DURATION,
            AgeRating.PG_12));
        save(new Show(nextId(), "A Midsummer Night's Dream", "Comedy classic", Genre.COMEDY,
            MIDSUMMER_NIGHT_DREAM_DURATION, AgeRating.ALL));
        save(new Show(nextId(), "The Phantom of the Opera", "Epic musical", Genre.MUSICAL,
            PHANTOM_OF_OPERA_DURATION, AgeRating.PG_12));
        save(new Show(nextId(), "Swan Lake", "Tchaikovsky's ballet", Genre.BALLET, SWAN_LAKE_DURATION,
            AgeRating.ALL));
    }

    @Override
    public Show save(Show show) {
        store.put(show.getId(), show);
        return show;
    }

    @Override
    public Optional<Show> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Show> findAll() {
        return List.copyOf(store.values());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return store.containsKey(id);
    }

    public long nextId() {
        return idSequence.getAndIncrement();
    }
}
