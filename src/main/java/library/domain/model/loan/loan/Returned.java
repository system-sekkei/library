package library.domain.model.loan.loan;

/**
 * 貸出図書の返却
 * （イベント）
 */
public class Returned {
    Loan loan;
    ReturnDate returnDate;

    public Returned(Loan loan, ReturnDate returnDate) {
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
