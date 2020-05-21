package library.domain.model.item;

/**
 * 蔵書の状態
 */
public enum ItemStatus {
    未登録("登録されていない"),
    貸出可能("在庫あり(館内閲覧中の可能性あり)"),
    取置中("貸出予約により取り置いている"),
    貸出中("貸し出している"),
    貸出不可("図書館都合により貸出を停止中")
    ;

    String description;

    ItemStatus(String description) {
        this.description = description;
    }
}
