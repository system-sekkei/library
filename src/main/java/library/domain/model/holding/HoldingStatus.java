package library.domain.model.holding;

/**
 * 蔵書の状態
 */
public enum HoldingStatus {
    在庫中,
    取置中,
    貸出中;

    public boolean outOnLoan() {
        return this == 貸出中;
    }

    public boolean loanable() {
        return this == 在庫中;
    }
}
