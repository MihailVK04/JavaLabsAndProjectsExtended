package bg.uni.fmi.theatre.dto;

import bg.uni.fmi.theatre.vo.Genre;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PerformanceRequest {
    @NotNull
    private Long showId;

    @NotNull
    private Long hallId;

    @NotNull
    private LocalDateTime startTime;

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
