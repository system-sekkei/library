package library.domain.model.bookcollection;

/**
 * 蔵書の状態
 */
public enum BookCollectionStatus {
    在庫中,
    貸出中;

    public boolean outOnLoan() {
        return this == 貸出中;
    }

    public boolean retentionable() {
        return this == 在庫中;
    }
}
