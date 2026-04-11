package bg.uni.fmi.theatre.service;

import bg.uni.fmi.theatre.config.AppLogger;
import bg.uni.fmi.theatre.config.TheatreProperties;
import bg.uni.fmi.theatre.domain.Show;
import bg.uni.fmi.theatre.dto.PageResponse;
import bg.uni.fmi.theatre.dto.ShowRequest;
import bg.uni.fmi.theatre.dto.ShowResponse;
import bg.uni.fmi.theatre.exception.NotFoundException;
import bg.uni.fmi.theatre.exception.ValidationException;
import bg.uni.fmi.theatre.repository.ShowRepository;
import bg.uni.fmi.theatre.vo.Genre;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ShowService {

    private static final int ZERO = 0;
    private final ShowRepository showRepository;
    private final AppLogger logger;
    private final int defaultPageSize;
    private final AtomicLong idSeq = new AtomicLong(100);

    public ShowService(ShowRepository showRepository, AppLogger logger, TheatreProperties properties) {
        this.showRepository = showRepository;
        this.logger = logger;
        this.defaultPageSize = properties.getDefaultPageSize();
    }

    public ShowResponse addShow(ShowRequest req) {
        if (req == null) {
            throw new ValidationException("Show must not be null");
        }

        Show show = new Show(idSeq.getAndIncrement(), req.getTitle(), req.getDescription(), req.getGenre(),
            req.getDurationMinutes(), req.getAgeRating());
        logger.debug("Adding show: " + show.getTitle());
        Show saved = showRepository.save(show);
        logger.info("Show added [" + saved.getId() + "] " + saved.getTitle());
        return ShowResponse.from(saved);
    }

    public ShowResponse getShowById(Long id) {
        if (id == null) {
            throw new ValidationException("Id must not be null");
        }

        logger.debug("Fetching show by id: " + id);
        return showRepository.findById(id).map(ShowResponse::from).orElseThrow(() -> {
            logger.error("Show not found: id=" + id);
            return new NotFoundException("Show", id);
        });
    }

    public Optional<ShowResponse> findShowById(Long id) {
        return showRepository.findById(id).map(ShowResponse::from);
    }

    public PageResponse<ShowResponse> searchShows(String titleQuery, Genre genre, int page, int size) {
        if (page < ZERO) {
            throw new ValidationException("page must not be negative");
        } if (size <= ZERO) {
            throw new ValidationException("size must be positive");
        }

        logger.debug("Searching shows - title='" + titleQuery + "', genre=" + genre + ", page=" + page);

        List<ShowResponse> allResults = showRepository.findAll().stream()
            .filter(s -> titleQuery == null || titleQuery.isBlank() ||
                s.getTitle().toLowerCase().contains(titleQuery.toLowerCase()))
            .filter(s -> genre == null || genre.equals(s.getGenre()))
            .sorted(Comparator.comparing(Show::getTitle))
            .map(ShowResponse::from)
            .collect(Collectors.toList());

        long total = allResults.size();
        logger.info("Search returned " + total + " total results");
        int fromIndex = page * size;
        List<ShowResponse> pageContent = fromIndex >= allResults.size() ? List.of() :
            allResults.subList(fromIndex, Math.min(fromIndex + size, allResults.size()));

        return new PageResponse<>(pageContent, page, size, total);
    }

    public PageResponse<ShowResponse> searchShows(String titleQuery, Genre genre) {
        return searchShows(titleQuery, genre, ZERO, defaultPageSize);
    }

    public List<ShowResponse> getAllShows() {
        logger.trace("getAllShows called");
        return showRepository.findAll().stream().map(ShowResponse::from).toList();
    }

    public ShowResponse updateShow(Long id, ShowRequest req) {
        Show existing = showRepository.findById(id).orElseThrow(() -> new NotFoundException("Show", id));

        existing.setTitle(req.getTitle());
        existing.setDescription(req.getDescription());
        existing.setGenre(req.getGenre());
        existing.setDurationMinutes(req.getDurationMinutes());
        existing.setAgeRating(req.getAgeRating());
        logger.info("Show updated: id=" + id);
        return ShowResponse.from(showRepository.save(existing));
    }

    public void deleteShow(Long id) {
        showRepository.findById(id).orElseThrow(() -> new NotFoundException("Show", id));
        showRepository.deleteById(id);
        logger.info("Show deleted: id=" + id);
    }
}
