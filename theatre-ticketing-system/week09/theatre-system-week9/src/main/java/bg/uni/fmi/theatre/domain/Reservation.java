package bg.uni.fmi.theatre.domain;

import bg.uni.fmi.theatre.vo.ReservationStatus;
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
@Table(name = "reservation")
public class Reservation {

    private static final int SEAT_LABEL_LENGTH = 20;
    private static final int CUSTOMER_NAME_LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id", nullable = false)
    private Performance performance;

    @Column(name = "seat_label", nullable = false, length = SEAT_LABEL_LENGTH)
    private String seatLabel;

    @Column(name = "customer_name", nullable = false, length = CUSTOMER_NAME_LENGTH)
    private String customerName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    @Column(name = "reserved_at", nullable = false)
    private LocalDateTime reservedAt;

    @Version
    private Long version;

    protected Reservation() {

    }

    public Reservation(Performance performance, String seatLabel, String customerName) {
        this.performance = performance;
        this.seatLabel = seatLabel;
        this.customerName = customerName;
        this.status = ReservationStatus.CONFIRMED;
        this.reservedAt = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public Performance getPerformance() {
        return performance;
    }

    public String getSeatLabel() {
        return seatLabel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Reservation{" +
            "id=" + id +
            ", seatLabel='" + seatLabel + '\'' +
            ", status=" + status +
            ", customerName='" + customerName + '\'' +
            '}';
    }
}
