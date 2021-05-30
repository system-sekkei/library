package library.domain.model.reservation.request;

/**
 * 予約の件数
 */
public class NumberOfReservation {
    int value;

    public NumberOfReservation(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == 0) return "取置が必要な予約はありません";
        return value + "件";
    }
}
