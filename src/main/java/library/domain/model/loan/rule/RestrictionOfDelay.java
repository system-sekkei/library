package library.domain.model.loan.rule;

/**
 * *遅延状態による制限(判定結果)
 */
enum RestrictionOfDelay {
    貸出可能,
    新規貸出不可,
    貸出停止;
}
