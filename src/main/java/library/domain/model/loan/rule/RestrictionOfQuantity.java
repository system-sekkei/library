package library.domain.model.loan.rule;

import library.domain.model.loan.Loans;
import library.domain.model.material.entry.EntryType;
import library.domain.model.material.item.Item;

/**
 * *冊数制限(判定結果)
 */
enum RestrictionOfQuantity {
    貸出１５点まで(15, 15,  5),
    貸出２０点まで(20, 15, 5);

    int limit;
    int 図書の冊数制限;
    int 視聴覚資料の冊数制限;

    RestrictionOfQuantity(int limit, int 図書の冊数制限, int 視聴覚資料の冊数制限) {
        this.limit = limit;
        this.図書の冊数制限 = 図書の冊数制限;
        this.視聴覚資料の冊数制限 = 視聴覚資料の冊数制限;
    }

    public Loanability 貸出可能冊数による判定(Loans 貸出リスト, Item 借りたい所蔵品) {
        if (借りたい所蔵品.所蔵品目().所蔵品目種別() == EntryType.視聴覚資料) {
            if (貸出リスト.視聴覚資料の数() >= this.視聴覚資料の冊数制限) {
                return Loanability.視聴覚資料貸出不可;
            }
        }

        if (limit > 貸出リスト.count()) {
            return Loanability.貸出可能;
        }

        return Loanability.冊数制限により貸出不可;
    }

}
