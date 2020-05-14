package library.domain.model.loan.loan;

/**
 * 返却しようとしている貸出図書
 */
public class ReturningBookOnLoan {
    Loan loan;
    ReturnDate returnDate;

    public ReturningBookOnLoan(Loan loan, ReturnDate returnDate) {
        this.loan = loan;
        this.returnDate = returnDate;
    }

    public Loan bookOnLoan() {
        return loan;
    }

    public ReturnDate returnDate() {
        return returnDate;
    }
}
