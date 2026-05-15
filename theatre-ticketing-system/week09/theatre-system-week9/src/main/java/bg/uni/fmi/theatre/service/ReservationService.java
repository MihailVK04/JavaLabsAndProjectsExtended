package bg.uni.fmi.theatre.service;

import bg.uni.fmi.theatre.domain.Performance;
import bg.uni.fmi.theatre.domain.Reservation;
import bg.uni.fmi.theatre.dto.ReservationRequest;
import bg.uni.fmi.theatre.dto.ReservationResponse;
import bg.uni.fmi.theatre.exception.NotFoundException;
import bg.uni.fmi.theatre.exception.ValidationException;
import bg.uni.fmi.theatre.repository.PerformanceRepository;
import bg.uni.fmi.theatre.repository.ReservationRepository;
import bg.uni.fmi.theatre.vo.PerformanceStatus;
import bg.uni.fmi.theatre.vo.ReservationStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final PerformanceRepository performanceRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              PerformanceRepository performanceRepository) {
        this.reservationRepository = reservationRepository;
        this.performanceRepository = performanceRepository;
    }

    @Transactional
    public ReservationResponse bookSeat(ReservationRequest req) {
        Performance performance = performanceRepository.findById(req.getPerformanceId())
            .orElseThrow(() -> new NotFoundException("Performance", req.getPerformanceId()));

        if (performance.getStatus() != PerformanceStatus.SCHEDULED) {
            throw new ValidationException("Cannot book seat for a " +
                performance.getStatus().name().toLowerCase() +  " performance");
        }

        if (reservationRepository.existsByPerformanceIdAndSeatLabel(req.getPerformanceId(), req.getSeatLabel())) {
            throw new ValidationException("Seat " +  req.getSeatLabel() + " is  already booked");
        }

        Reservation reservation = new Reservation(performance, req.getSeatLabel(), req.getCustomerName());
        Reservation saved =  reservationRepository.save(reservation);

        return ReservationResponse.from((saved));
    }

    @Transactional
    public void cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Reservation", id));
        reservation.setStatus(ReservationStatus.CANCELLED);
    }

    public List<ReservationResponse> findByPerformance(Long performanceId) {
        performanceRepository.findById(performanceId)
            .orElseThrow(() -> new NotFoundException("Performance", performanceId));

        return reservationRepository.findByPerformanceId(performanceId).stream()
            .map(ReservationResponse::from)
            .toList();
    }
}
