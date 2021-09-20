package library.domain.model.loan.rule;

import library.domain.model.loan.Loans;
import library.domain.model.loan.NumberOfLoans;
import library.domain.model.material.entry.EntryType;
import library.domain.model.material.item.Item;

/**
 * *冊数制限(判定結果)
 */
enum RestrictionOfQuantity {
    貸出１５点_視聴覚資料5点まで(NumberOfLoans.of(15), NumberOfLoans.of(5)),
    貸出２０点_視聴覚資料5点まで(NumberOfLoans.of(20), NumberOfLoans.of(5));

    NumberOfLoans 冊数上限;
    NumberOfLoans 視聴覚資料の冊数上限;

    RestrictionOfQuantity(NumberOfLoans 冊数上限, NumberOfLoans 視聴覚資料の冊数上限) {
        this.冊数上限 = 冊数上限;
        this.視聴覚資料の冊数上限 = 視聴覚資料の冊数上限;
    }

    public Loanability 貸出可能冊数による判定(Loans 貸出リスト, Item 借りたい所蔵品) {
        if (借りたい所蔵品.所蔵品目().所蔵品目種別() == EntryType.視聴覚資料) {
            if (this.視聴覚資料の冊数上限.より多い(貸出リスト.視聴覚資料の数().足す(1))) {
                return Loanability.視聴覚資料貸出不可;
            }
        }

        if (冊数上限.より少ない(貸出リスト.冊数())) {
            return Loanability.貸出可能;
        }

        return Loanability.冊数制限により貸出不可;
    }

}
