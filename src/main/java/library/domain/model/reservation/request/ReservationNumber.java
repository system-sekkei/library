package library.domain.model.reservation.request;

/**
 * 予約番号
 */
public class ReservationNumber {
    int value;

    @Deprecated
    ReservationNumber() {
    }

    public ReservationNumber(String textValue) {
        this.value = Integer.parseInt(textValue);
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    public boolean sameValue(ReservationNumber other) {
        return this.value == other.value;
    }
}
