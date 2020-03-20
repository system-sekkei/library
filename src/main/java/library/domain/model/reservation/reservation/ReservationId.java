package library.domain.model.reservation.reservation;

/**
 * 貸出予約ID
 */
public class ReservationId {
    int value;

    @Deprecated
    ReservationId() {
    }

    public ReservationId(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
