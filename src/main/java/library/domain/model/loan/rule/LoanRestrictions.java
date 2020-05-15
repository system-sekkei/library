package library.domain.model.loan.rule;

import library.domain.model.loan.loan.Loans;

/**
 * 貸出制限
 */
public enum LoanRestrictions {
    貸出５冊まで(5),
    貸出７冊まで(7),
    貸出４冊まで(4),
    貸出不可(0);

    int limit;

    LoanRestrictions(int limit) {
        this.limit = limit;
    }

    public CanLoan canLoan(Loans loans) {
        if (limit > loans.count()) {
            return CanLoan.貸出可能;
        }
        return CanLoan.貸出不可;
    }

}
