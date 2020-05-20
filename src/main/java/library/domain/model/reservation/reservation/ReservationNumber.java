package library.domain.model.reservation.reservation;

/**
 * 予約番号
 */
public class ReservationNumber {
    // TODO: UUIDに変更する
    int value;

    @Deprecated
    ReservationNumber() {
    }

    public ReservationNumber(int value) {
        this.value = value;
    }

    public static ReservationNumber generate() {
        // TODO: UUID生成処理
        return new ReservationNumber(0);
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return "ReservationNumber{" +
                "value=" + value +
                '}';
    }
}
