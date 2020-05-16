package library.domain.model.loan.rule;

import library.domain.model.loan.loan.Loans;

/**
 * 貸出制限の判定結果
 */
public enum RestrictionType {
    貸出５冊まで(5),
    貸出７冊まで(7),
    貸出４冊まで(4),
    貸出不可(0);

    int limit;

    RestrictionType(int limit) {
        this.limit = limit;
    }

    public Restriction shouldRestrict(Loans loans) {
        if (limit > loans.count()) {
            return Restriction.貸出可能;
        }
        return Restriction.貸出不可;
    }

}
