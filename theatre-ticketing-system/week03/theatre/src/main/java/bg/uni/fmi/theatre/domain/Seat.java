package bg.uni.fmi.theatre.domain;

import java.util.Objects;

public class Seat {

    private static final int ZERO = 0;
    private final Long id;
    private final Long hallId;
    private String rowLabel;
    private int seatNumber;
    private String zoneCode;

    public Seat(Long id, Long hallId, String rowLabel, int seatNumber, String zoneCode) {
        if (hallId == null) {
            throw new IllegalArgumentException("HallId is required!");
        } else if (rowLabel == null || rowLabel.isBlank()) {
            throw new IllegalArgumentException("RowLabel is required!");
        } else if (seatNumber <= ZERO) {
            throw new IllegalArgumentException("SeatNumber should be a positive number");
        }

        this.id = id;
        this.hallId = hallId;
        this.rowLabel = rowLabel;
        this.seatNumber = seatNumber;
        this.zoneCode = zoneCode;
    }

    public Long getId() {
        return id;
    }

    public Long getHallId() {
        return hallId;
    }

    public String getRowLabel() {
        return rowLabel;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setRowLabel(String rowLabel) {
        this.rowLabel = rowLabel;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(id, seat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Seat{" + "id=" + id + ", hall=" + hallId + ", row=" + rowLabel + seatNumber + ", zone=" + zoneCode +
            '}';
    }
}
