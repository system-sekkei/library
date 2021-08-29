package library.domain.model.material.item;

/**
 * 所蔵品の状態
 */
public enum ItemStatus {
    未登録("登録されていません"),
    在庫中("在庫あり(館内閲覧中の可能性あり)"),
    予約中("次の予約があります"),
    取置中("貸出予約により取り置いています"),
    貸出中("貸し出しています"),
    その他("図書館都合により貸出を停止中です")
    ;

    String description;

    ItemStatus(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
}
