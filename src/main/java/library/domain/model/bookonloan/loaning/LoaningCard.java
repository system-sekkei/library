package library.domain.model.bookonloan.loaning;

import library.domain.model.bookonloan.loan.BookOnLoan;

/**
 * 貸出票
 */
public class LoaningCard {
    BookOnLoan bookOnLoan;
    RejectReason rejectReason;

    private LoaningCard(BookOnLoan bookOnLoan, RejectReason rejectReason) {
        this.bookOnLoan = bookOnLoan;
        this.rejectReason = rejectReason;
    }

    public LoaningCard(RejectReason rejectReason) {
        this(null, rejectReason);
    }

    public LoaningCard(BookOnLoan bookOnLoan) {
        this(bookOnLoan, null);
    }

    public String message() {
        return rejectReason.toString();
    }

    public boolean rejected() {
        return bookOnLoan == null;
    }

    public boolean ok() {
        return bookOnLoan != null;
    }
}
