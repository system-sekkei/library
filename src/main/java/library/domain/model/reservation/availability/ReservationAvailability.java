package library.domain.model.reservation.availability;

/**
 * 予約可否
 */
public enum ReservationAvailability {
    冊数制限により予約不可("これ以上本を貸し出すことができません。"),
    視聴覚資料予約不可("これ以上視聴覚資料を予約することができません。"),
    予約一定期間停止("予約一定期間停止中です。"),
    予約可能("");

    ReservationAvailability(String message) {
        this.message = message;
    }

    String message;
}
