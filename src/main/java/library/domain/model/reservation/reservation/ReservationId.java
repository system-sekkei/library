package library.domain.model.reservation.reservation;

/**
 * 貸出予約ID
 */
public class ReservationId {
    // TODO: UUIDに変更する
    int value;

    @Deprecated
    ReservationId() {
    }

    public ReservationId(int value) {
        this.value = value;
    }

    public static ReservationId generate() {
        // TODO: UUID生成処理
        return new ReservationId(0);
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return "ReservationId{" +
                "value=" + value +
                '}';
    }
}
