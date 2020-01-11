package library.domain.model;

/**
 * 蔵書の状態
 */
public enum CollectionStatus {
    在庫中,
    貸出中_期限内,
    貸出中_期限切れ
}
