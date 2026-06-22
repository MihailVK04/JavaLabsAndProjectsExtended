package bg.uni.fmi.theatre.dto;

import bg.uni.fmi.theatre.domain.Performance;
import bg.uni.fmi.theatre.vo.PerformanceStatus;

import java.time.LocalDateTime;

public record PerformanceResponse(Long id, Long showId, Long hallId, LocalDateTime startTime,
                                  PerformanceStatus status) {

    public static PerformanceResponse from(Performance p) {
        return new PerformanceResponse(p.getId(), p.getShow().getId(), p.getHall().getId(), p.getStartTime(),
            p.getStatus());
    }
}
