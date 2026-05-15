package bg.uni.fmi.theatre.domain;

import bg.uni.fmi.theatre.vo.PerformanceStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "performance")
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerformanceStatus status;

    @Version
    private Long version;

    protected Performance() {

    }

    public Performance(Show show, Hall hall, LocalDateTime startTime) {
        if (show == null) {
            throw new IllegalArgumentException("Show is required!");
        } else if (hall == null) {
            throw new IllegalArgumentException("Hall is required!");
        } else if (startTime == null) {
            throw new IllegalArgumentException("StartTime is required!");
        }

        this.show = show;
        this.hall = hall;
        this.startTime = startTime;
        this.status = PerformanceStatus.SCHEDULED;
    }

    public Long getId() {
        return id;
    }

    public Show getShow() {
        return show;
    }

    public Hall getHall() {
        return hall;
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
        return "Performance{id=" + id + ", show=" + show + ", start=" + startTime + ", status=" + status + '}';
    }
}
