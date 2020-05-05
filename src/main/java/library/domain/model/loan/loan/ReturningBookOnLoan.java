package library.domain.model.loan.loan;

/**
 * 返却しようとしている貸出図書
 */
public class ReturningBookOnLoan {
    BookOnLoan bookOnLoan;
    ReturnDate returnDate;

    public ReturningBookOnLoan(BookOnLoan bookOnLoan, ReturnDate returnDate) {
        this.bookOnLoan = bookOnLoan;
        this.returnDate = returnDate;
    }

    public BookOnLoan bookOnLoan() {
        return bookOnLoan;
    }

    public ReturnDate returnDate() {
        return returnDate;
    }
}
