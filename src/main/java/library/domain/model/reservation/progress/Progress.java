package library.domain.model.reservation.progress;

/**
 * 予約の進捗
 * TODO 進捗の状態遷移の表現の実験用
 */
public enum Progress {
    未準備("予約があるが、未取置"),
    準備完了("取置済、連絡済"),
    消込済("貸出または取置期限切れにより取置を解放");

    String description;

    Progress(String description) {
        this.description = description;
    }
}
