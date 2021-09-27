package library.domain.model.reservation;

/**
 * 予約の状態
 */
public enum ReservationStatus {
    未準備("予約があるが、未取置"),
    準備完了("取置済、連絡済"),
    消込済("貸出または取置期限切れにより取置を解放");

    String description;

    ReservationStatus(String description) {
        this.description = description;
    }
}
