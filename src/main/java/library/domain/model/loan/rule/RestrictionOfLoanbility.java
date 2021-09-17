package library.domain.model.loan.rule;

import library.domain.model.loan.Loans;
import library.domain.model.material.item.Item;
import library.domain.model.member.Member;

/**
 *
 */
public enum RestrictionOfLoanbility {
    貸出可,
    新規貸出不可,
    貸出一定期間停止;

    static final RestrictionOfQuantityMap map = new RestrictionOfQuantityMap();

    public Loanability 貸出可否判定(Member 会員, Loans 貸出リスト, Item 借りたい所蔵品) {
        RestrictionOfQuantity 貸出点数条件 = map.of(会員.type());
        return 貸出点数条件.貸出可能冊数による判定(貸出リスト, 借りたい所蔵品);
    }
}
