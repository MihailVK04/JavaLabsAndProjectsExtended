package bg.uni.fmi.theatre.entity;

import bg.uni.fmi.theatre.domain.Performance;
import bg.uni.fmi.theatre.vo.PerformanceStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "performance")
public class PerformanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "show_id", nullable = false)
    private Long showId;

    @Column(name = "hall_id", nullable = false)
    private Long hallId;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PerformanceStatus status;

    protected  PerformanceEntity() {}

    public static PerformanceEntity from(Performance performance) {
        PerformanceEntity e = new PerformanceEntity();
        e.id = performance.getId();
        e.showId = performance.getShowId();
        e.hallId = performance.getHallId();
        e.startTime = performance.getStartTime();
        e.status = performance.getStatus();
        return e;
    }

    public Performance toPerformance() {
        Performance p = new Performance(this.id, this.showId, this.hallId, this.startTime);
        p.setStatus(this.status);
        return p;
    }
}
