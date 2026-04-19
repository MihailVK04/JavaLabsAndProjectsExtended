package bg.uni.fmi.theatre.service;

import bg.uni.fmi.theatre.config.AppLogger;
import bg.uni.fmi.theatre.domain.Performance;
import bg.uni.fmi.theatre.dto.PerformanceResponse;
import bg.uni.fmi.theatre.exception.ValidationException;
import bg.uni.fmi.theatre.repository.PerformanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceService {

    private final PerformanceRepository performanceRepository;
    private final ShowService showService;
    private final AppLogger logger;

    public PerformanceService(PerformanceRepository performanceRepository, ShowService showService, AppLogger logger) {
        this.performanceRepository = performanceRepository;
        this.showService = showService;
        this.logger = logger;
    }

    public PerformanceResponse addPerformance(Performance performance) {
        if (performance == null) {
            throw new ValidationException("Performance must not be null");
        }

        showService.getShowById(performance.getShowId());
        logger.debug("Adding performance for show: " + performance.getShowId());
        Performance saved = performanceRepository.save(performance);
        logger.info("Performance added: id=" + saved.getId());
        return PerformanceResponse.from(saved);
    }

    public List<PerformanceResponse> findPerformancesByShow(Long showId) {
        if (showId == null) {
            throw new ValidationException("showId must not be null");
        }

        showService.getShowById(showId);
        logger.debug("Fetching performances for show: " + showId);
        return performanceRepository.findByShowId(showId).stream()
            .map(PerformanceResponse::from).toList();
    }

    public List<PerformanceResponse> getAllPerformances() {
        return performanceRepository.findAll().stream()
            .map(PerformanceResponse::from).toList();
    }
}
