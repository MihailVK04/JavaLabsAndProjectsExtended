package bg.uni.fmi.theatre.service;

import bg.uni.fmi.theatre.domain.Performance;
import bg.uni.fmi.theatre.domain.Show;
import bg.uni.fmi.theatre.repository.PerformanceRepository;
import bg.uni.fmi.theatre.repository.ShowRepository;
import bg.uni.fmi.theatre.vo.Genre;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CatalogueService {

    private static final int PAGE_SIZE_BY_DEFAULT = 10;
    private static final int ZERO = 0;
    private final ShowRepository showRepository;
    private final PerformanceRepository performanceRepository;
    private final int pageSize;

    public CatalogueService(ShowRepository showRepository, PerformanceRepository performanceRepository) {
        this(showRepository, performanceRepository, PAGE_SIZE_BY_DEFAULT);
    }

    public CatalogueService(ShowRepository showRepository, PerformanceRepository performanceRepository, int pageSize) {
        if (showRepository == null) {
            throw new IllegalArgumentException("ShowRepository is required!");
        } else if (performanceRepository == null) {
            throw new IllegalArgumentException("PerformanceRepository is required!");
        } else if (pageSize <= ZERO) {
            throw new IllegalArgumentException("PageSize should be positive number");
        }

        this.showRepository = showRepository;
        this.performanceRepository = performanceRepository;
        this.pageSize = pageSize;
    }

    public Show addShow(Show show) {
        if (show == null) {
            throw new IllegalArgumentException("Show must not be null.");
        }
        return showRepository.save(show);
    }

    public Optional<Show> findShowById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
        return showRepository.findById(id);
    }

    public List<Show> searchShows(String titleQuery, Genre genre, int page, int size) {
        if (page < ZERO) {
            throw new IllegalArgumentException("Page must not be negative.");
        } else if (size <= ZERO) {
            throw new IllegalArgumentException("Size must be positive.");
        }

        List<Show> result = showRepository.findAll()
            .stream()
            .filter(s -> titleQuery == null || titleQuery.isBlank() || s.getTitle().toLowerCase()
                .contains(titleQuery.toLowerCase()))
            .filter(s -> genre == null || s.getGenre().equals(genre))
            .sorted(Comparator.comparing(Show::getTitle))
            .collect(Collectors.toList());

        int fromIndex = page * size;
        if (fromIndex >= result.size()) {
            return List.of();
        }
        int toIndex = Math.min(fromIndex + size, result.size());
        return result.subList(fromIndex, toIndex);
    }

    public List<Show> searchShows(String titleQuery, Genre genre) {
        return searchShows(titleQuery, genre, ZERO, pageSize);
    }

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public Performance addPerformance(Performance performance) {
        if (performance == null) {
            throw new IllegalArgumentException("Performance is required!");
        } else if (!showRepository.existsById(performance.getShowId())) {
            throw new IllegalArgumentException("Performance not found: " + performance.getShowId());
        }
        return performanceRepository.save(performance);
    }

    public List<Performance> findPerformancesByShow(Long showId) {
        if (showId == null) {
            throw new IllegalArgumentException("ShowId is required!");
        } else if (!showRepository.existsById(showId)) {
            throw new IllegalArgumentException("Show not found: " + showId);
        }
        return performanceRepository.findByShowId(showId);
    }
}
