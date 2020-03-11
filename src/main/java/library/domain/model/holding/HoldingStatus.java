package library.domain.model.holding;

/**
 * 蔵書の状態
 */
public enum HoldingStatus {
    在庫中,
    貸出中;

    public boolean outOnLoan() {
        return this == 貸出中;
    }

    public boolean retentionable() {
        return this == 在庫中;
    }
}
