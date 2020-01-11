package library.domain.model.bookcollection;

/**
 * 蔵書の状態
 */
public enum BookCollectionStatus {
    在庫中,
    貸出中_期限内,
    貸出中_期限切れ
}
