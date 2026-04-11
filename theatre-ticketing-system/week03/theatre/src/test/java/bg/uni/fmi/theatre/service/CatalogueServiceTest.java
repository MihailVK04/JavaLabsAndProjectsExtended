package bg.uni.fmi.theatre.service;

import bg.uni.fmi.theatre.domain.Performance;
import bg.uni.fmi.theatre.domain.Show;
import bg.uni.fmi.theatre.repository.inmemory.InMemoryPerformanceRepository;
import bg.uni.fmi.theatre.repository.inmemory.InMemoryShowRepository;
import bg.uni.fmi.theatre.vo.AgeRating;
import bg.uni.fmi.theatre.vo.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CatalogueServiceTest {

    private CatalogueService service;
    private InMemoryShowRepository showRepository;
    private InMemoryPerformanceRepository performanceRepository;

    @BeforeEach
    void setUp() {
        showRepository = new InMemoryShowRepository();
        performanceRepository = new InMemoryPerformanceRepository();
        service = new CatalogueService(showRepository, performanceRepository, 5);
    }

    @Test
    void testAddShowValidSaving() {
        Show show = new Show(showRepository.nextId(), "Hamlet", "Classic drama", Genre.DRAMA,
            120, AgeRating.PG_16);
        service.addShow(show);
        Optional<Show> found = service.findShowById(show.getId());
        assertTrue(found.isPresent(), "The show should be wrapped in the optional object.");
        assertEquals("Hamlet", found.get().getTitle(), "The titles should match.");
    }

    @Test
    void testAddShowNullShowThrow() {
        assertThrows(IllegalArgumentException.class, () -> service.addShow(null),
            "Method should throw IllegalArgumentException.");
    }

    @Test
    void testSearchShowsTitleMatch() {
        service.addShow(new Show(showRepository.nextId(), "Hamlet", "Drama", Genre.DRAMA,
            120, AgeRating.PG_16));
        service.addShow(new Show(showRepository.nextId(), "Othello", "Drama", Genre.DRAMA,
            110, AgeRating.PG_16));
        service.addShow(new Show(showRepository.nextId(), "A Midsummer Night's Dream", "Comedy",
            Genre.COMEDY, 90, AgeRating.ALL));
        List<Show> results = service.searchShows("Ham", null, 0, 10);
        assertEquals(1, results.size(), "Size of result should be one.");
        assertEquals("Hamlet", results.getFirst().getTitle(), "The titles should match.");
    }

    @Test
    void testSearchShowsGenreMatch() {
        service.addShow(new Show(showRepository.nextId(), "Hamlet", "Drama", Genre.DRAMA,
            120, AgeRating.PG_16));
        service.addShow(new Show(showRepository.nextId(), "Chicago", "Musical", Genre.MUSICAL,
            130, AgeRating.PG_12));
        List<Show> results = service.searchShows(null, Genre.MUSICAL, 0, 10);
        assertEquals(1, results.size());
        assertEquals("Chicago", results.getFirst().getTitle());
    }

    @Test
    void testSearchShowsTitleCaseIntensiveMatch() {
        service.addShow(new Show(showRepository.nextId(), "Hamlet", "Drama", Genre.DRAMA,
            120, AgeRating.PG_16));
        List<Show> results = service.searchShows("HAMLET", null, 0, 10);
        assertEquals(1, results.size());
    }

    @Test
    void testSearchShowsEmptyQueryExpectedResult() {
        service.addShow(new Show(showRepository.nextId(), "Hamlet", "Drama", Genre.DRAMA,
            120, AgeRating.PG_16));
        service.addShow(new Show(showRepository.nextId(), "Chicago", "Musical", Genre.MUSICAL,
            130, AgeRating.PG_12));
        List<Show> results = service.searchShows("", null, 0, 10);
        assertEquals(2, results.size());
    }

    @Test
    void testSearchShowsPageOutOfBounds() {
        service.addShow(new Show(showRepository.nextId(), "Hamlet", "Drama", Genre.DRAMA,
            120, AgeRating.PG_16));
        List<Show> results = service.searchShows(null, null, 5, 10);
        assertTrue(results.isEmpty());
    }

    @Test
    void testSearchShowsNegativePageThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.searchShows(null, null, -1, 10));
    }

    @Test
    void testSearchShowsZeroSizeThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.searchShows(null, null, 0, 0));
    }

    @Test
    void testSearchShowsPaginationValid() {
        for (int i = 1; i <= 8; i++) {
            service.addShow(new Show((long) i, "Show " + i, "Desc", Genre.DRAMA, 90, AgeRating.ALL));
        }
        List<Show> page0 = service.searchShows(null, null, 0, 3);
        List<Show> page1 = service.searchShows(null, null, 1, 3);
        List<Show> page2 = service.searchShows(null, null, 2, 3);
        assertEquals(3, page0.size());
        assertEquals(3, page1.size());
        assertEquals(2, page2.size());
    }

    @Test
    void testAddPerformanceUnknownShowThrows() {
        Performance p = new Performance(1L, 999L, 1L, LocalDateTime.now().plusDays(1));
        assertThrows(IllegalArgumentException.class, () -> service.addPerformance(p));
    }

    @Test
    void testFindPerformancesByShowValidOutcome() {
        Show show = new Show(showRepository.nextId(), "Hamlet", "Drama", Genre.DRAMA, 120, AgeRating.PG_16);
        service.addShow(show);
        service.addPerformance(new Performance(performanceRepository.nextId(), show.getId(), 1L, LocalDateTime.now().plusDays(1)));
        service.addPerformance(new Performance(performanceRepository.nextId(), show.getId(), 1L, LocalDateTime.now().plusDays(2)));
        List performances = service.findPerformancesByShow(show.getId());
        assertEquals(2, performances.size());
    }
}
