package library.domain.model.book.collection;

/**
 * 蔵書の状態
 */
public enum ItemStatus {
    未登録("登録されていません"),
    貸出可能("在庫あり(館内閲覧中の可能性あり)"),
    取置中("貸出予約により取り置いています"),
    貸出中("貸し出しています"),
    貸出不可("図書館都合により貸出を停止中です")
    ;

    String description;

    ItemStatus(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
}
