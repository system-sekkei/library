package library.domain.model.loan.rule;

import library.domain.model.loan.loan.Loans;

/**
 * 冊数制限
 */
enum RestrictionOfQuantity {
    貸出５冊まで(5),
    貸出７冊まで(7),
    貸出４冊まで(4),
    貸出不可(0);

    int limit;

    RestrictionOfQuantity(int limit) {
        this.limit = limit;
    }

    public RestrictionResult shouldRestrict(Loans loans) {
        if (limit > loans.count()) {
            return RestrictionResult.貸出可能;
        }
        return RestrictionResult.貸出不可;
    }

}
