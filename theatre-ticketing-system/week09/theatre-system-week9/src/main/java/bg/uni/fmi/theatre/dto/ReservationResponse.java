package bg.uni.fmi.theatre.dto;

import bg.uni.fmi.theatre.domain.Reservation;
import bg.uni.fmi.theatre.vo.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationResponse(Long id, Long performanceId, String seatLabel, String customerName,
                                  ReservationStatus status, LocalDateTime reservedAt) {

    public static ReservationResponse from(Reservation r) {
        return new ReservationResponse(r.getId(), r.getPerformance().getId(), r.getSeatLabel(), r.getCustomerName(),
            r.getStatus(), r.getReservedAt());
    }
}
