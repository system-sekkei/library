package library.domain.model.reservation.reservation;

/**
 * 予約の件数
 */
public class NumberOfReservation {
    int value;

    public NumberOfReservation(int value) {
        this.value = value;
    }

    @Deprecated
    public int value() {
        return value;
    }

    @Override
    public String toString() {
        if (value == 0) return "取置依頼はありません";
        return value + "件";
    }
}
