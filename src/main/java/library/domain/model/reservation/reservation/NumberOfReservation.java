package library.domain.model.reservation.reservation;

/**
 * 予約の件数
 */
public class NumberOfReservation {
    int value;

    public NumberOfReservation(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
