package bg.uni.fmi.theatre.domain;

import bg.uni.fmi.theatre.vo.PerformanceStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class Performance {

    private final Long id;
    private final Long showId;
    private final Long hallId;
    private LocalDateTime startTime;
    private PerformanceStatus status;

    public Performance(Long id, Long showId, Long hallId, LocalDateTime startTime) {
        if (showId == null) {
            throw new IllegalArgumentException("ShowId is required!");
        } else if (hallId == null) {
            throw new IllegalArgumentException("HallId is required!");
        } else if (startTime == null) {
            throw new IllegalArgumentException("StartTime is required!");
        }

        this.id = id;
        this.showId = showId;
        this.hallId = hallId;
        this.startTime = startTime;
        this.status = PerformanceStatus.SCHEDULED;
    }

    public Long getId() {
        return id;
    }

    public Long getShowId() {
        return showId;
    }

    public Long getHallId() {
        return hallId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public PerformanceStatus getStatus() {
        return status;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setStatus(PerformanceStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Performance that = (Performance) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Performance{" + "id=" + id + ", show=" + showId + ", start=" + startTime + ", status=" + status + '}';
    }
}
