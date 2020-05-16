package library.domain.model.item;

/**
 * 蔵書の状態
 */
public enum ItemStatus {
    在庫中,
    // TODO: 状態モデルに存在しないが追加。(取置棚にある状態はは在庫中でも貸出中でもない)
    取置中,
    貸出中;

    public boolean outOnLoan() {
        return this == 貸出中;
    }

    public boolean loanable() {
        return this == 在庫中;
    }
}
