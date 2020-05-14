package library.domain.model.loan.rule;

import library.domain.model.loan.loan.Loan;

/**
 * 貸出票
 */
public class LoaningCard {
    Loan loan;
    RejectReason rejectReason;

    private LoaningCard(Loan loan, RejectReason rejectReason) {
        this.loan = loan;
        this.rejectReason = rejectReason;
    }

    public LoaningCard(RejectReason rejectReason) {
        this(null, rejectReason);
    }

    public LoaningCard(Loan loan) {
        this(loan, null);
    }

    public String message() {
        return rejectReason.toString();
    }

    public boolean rejected() {
        return loan == null;
    }

    public boolean ok() {
        return loan != null;
    }
}
