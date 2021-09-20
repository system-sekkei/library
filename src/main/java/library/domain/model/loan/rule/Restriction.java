package library.domain.model.loan.rule;

import library.domain.model.delay.DelayStatus;
import library.domain.model.loan.Loans;
import library.domain.model.material.item.Item;
import library.domain.model.member.Member;

/**
 * *貸出制限
 */
public class Restriction {
    Member 会員;
    Loans 貸出リスト;
    Item 借りたい所蔵品;
    DelayStatus 遅延状態;

    public Restriction(Member 会員, Loans 貸出リスト, Item 借りたい所蔵品, DelayStatus 遅延状態) {
        this.会員 = 会員;
        this.貸出リスト = 貸出リスト;
        this.借りたい所蔵品 = 借りたい所蔵品;
        this.遅延状態 = 遅延状態;
    }

    static final RestrictionOfDelayMap restrictionOfDelayMap = new RestrictionOfDelayMap();

    static final RestrictionOfQuantityMap restrictionOfQuantityMap = new RestrictionOfQuantityMap();

    public Loanability 貸出可否判定() {
        RestrictionOfDelay 遅延状態による判定 = restrictionOfDelayMap.of(遅延状態);

        if (遅延状態による判定 == RestrictionOfDelay.新規貸出不可) return Loanability.新規貸出不可;
        if (遅延状態による判定 == RestrictionOfDelay.貸出停止) return Loanability.貸出一定期間停止;

        RestrictionOfQuantity 貸出点数条件 = restrictionOfQuantityMap.of(会員.type());
        return 貸出点数条件.貸出可能冊数による判定(貸出リスト, 借りたい所蔵品);
    }
}
