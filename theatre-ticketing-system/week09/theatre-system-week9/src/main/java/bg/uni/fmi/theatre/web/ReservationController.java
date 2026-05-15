package bg.uni.fmi.theatre.web;

import bg.uni.fmi.theatre.dto.ReservationRequest;
import bg.uni.fmi.theatre.dto.ReservationResponse;
import bg.uni.fmi.theatre.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@Tag(name = "Reservations", description = "Seat booking operations")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Book a seat", description = "Created a new reservation for a performance")
    @ApiResponse(responseCode = "201", description = "Reservation created successfully")
    @ApiResponse(responseCode = "400", description = "Seat already booked or invalid request")
    @ApiResponse(responseCode = "404", description = "Performance not found")
    public ReservationResponse bookSeat(@Valid @RequestBody ReservationRequest req) {
        return reservationService.bookSeat(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Cancel reservation", description = "Cancel an existing reservation")
    @ApiResponse(responseCode = "204", description = "Reservation cancelled successfully")
    @ApiResponse(responseCode = "404", description = "Reservation not found")
    public void cancelReservation(@PathVariable long id) {
        reservationService.cancelReservation(id);
    }

    @GetMapping
    @Operation(summary = "List reservations", description = "Get all reservations for a performance")
    @ApiResponse(responseCode = "200", description = "list of reservations")
    @ApiResponse(responseCode = "404", description = "Performance not found")
    public List<ReservationResponse> listByPerformance(@RequestParam Long performanceId) {
        return reservationService.findByPerformance(performanceId);
    }
}
