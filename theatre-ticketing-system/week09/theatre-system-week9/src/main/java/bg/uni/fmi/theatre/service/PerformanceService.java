package bg.uni.fmi.theatre.service;

import bg.uni.fmi.theatre.config.AppLogger;
import bg.uni.fmi.theatre.domain.Hall;
import bg.uni.fmi.theatre.domain.Performance;
import bg.uni.fmi.theatre.domain.Show;
import bg.uni.fmi.theatre.dto.PerformanceRequest;
import bg.uni.fmi.theatre.dto.PerformanceResponse;
import bg.uni.fmi.theatre.exception.NotFoundException;
import bg.uni.fmi.theatre.exception.ValidationException;
import bg.uni.fmi.theatre.repository.HallRepository;
import bg.uni.fmi.theatre.repository.PerformanceRepository;
import bg.uni.fmi.theatre.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceService {

    private final PerformanceRepository performanceRepository;
    private final ShowRepository showRepository;
    private final HallRepository hallRepository;
    private final AppLogger logger;

    public PerformanceService(PerformanceRepository performanceRepository, ShowService showService, AppLogger logger,
                              ShowRepository showRepository, HallRepository hallRepository) {
        this.performanceRepository = performanceRepository;
        this.showRepository = showRepository;
        this.hallRepository = hallRepository;
        this.logger = logger;
    }

    public PerformanceResponse addPerformance(PerformanceRequest req) {
        Show show = showRepository.findById(req.getShowId())
            .orElseThrow(() -> new NotFoundException("Show", req.getShowId()));
        Hall hall = hallRepository.findById(req.getHallId())
            .orElseThrow(() -> new NotFoundException("Hall", req.getHallId()));

        Performance performance = new Performance(show, hall, req.getStartTime());
        logger.debug("Adding performance for show: " + performance.getShow().getId());
        Performance saved = performanceRepository.save(performance);
        logger.info("Performance added: id=" + saved.getId());
        return PerformanceResponse.from(saved);
    }

    public List<PerformanceResponse> findPerformancesByShow(Long showId) {
        if (showId == null) {
            throw new ValidationException("showId must not be null");
        }

        showRepository.findById(showId)
            .orElseThrow(() -> new NotFoundException("show", showId));
        logger.debug("Fetching performances for show: " + showId);
        return performanceRepository.findByShowId(showId).stream()
            .map(PerformanceResponse::from).toList();
    }

    public List<PerformanceResponse> getAllPerformances() {
        return performanceRepository.findAll().stream()
            .map(PerformanceResponse::from).toList();
    }
}
