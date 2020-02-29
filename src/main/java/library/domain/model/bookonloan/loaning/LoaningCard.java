package library.domain.model.bookonloan.loaning;

import library.domain.model.bookcollection.BookCollectionStatus;
import library.domain.model.bookonloan.loan.BookOnLoan;

/**
 * 貸出票
 */
public class LoaningCard {
    BookOnLoan bookOnLoan;
    RejectReason rejectReason;

    public LoaningCard(BookOnLoan bookOnLoan, RejectReason rejectReason) {
        this.bookOnLoan = bookOnLoan;
        this.rejectReason = rejectReason;
    }

    static public LoaningCard from(CanLoan canLoan) {
        if (canLoan == CanLoan.貸出不可) {
            return new LoaningCard(null, RejectReason.貸出冊数超過);
        }
        // FIXME:
        return new LoaningCard(null, null);
    }

    static public LoaningCard from(BookCollectionStatus bookCollectionStatus) {
        if (bookCollectionStatus == BookCollectionStatus.貸出中) {
            return new LoaningCard(null, RejectReason.蔵書が貸出中);
        }
        // FIXME:
        return new LoaningCard(null, null);
    }

    public String message() {
        return rejectReason.toString();
    }

    public boolean hasError() {
        return bookOnLoan == null;
    }

    public boolean ok() {
        return bookOnLoan != null;
    }
}
