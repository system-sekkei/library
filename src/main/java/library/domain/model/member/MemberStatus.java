package library.domain.model.member;

/**
 * 会員登録の状態
 */
public enum MemberStatus {
    未登録,
    有効,
    // TODO: 現在未使用なので、どういうケースで利用する想定か確認する。
    無効
}
