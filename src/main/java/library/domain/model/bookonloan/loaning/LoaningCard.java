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
            return new LoaningCard(null, new RejectReason("これ以上本を貸し出すことができません。"));
        }
        // FIXME:
        return new LoaningCard(null, new RejectReason("OK"));
    }

    static public LoaningCard from(BookCollectionStatus bookCollectionStatus) {
        if (bookCollectionStatus == BookCollectionStatus.貸出中) {
            return new LoaningCard(null, new RejectReason("現在貸出中の蔵書です。"));
        }
        // FIXME:
        return new LoaningCard(null, new RejectReason("OK"));
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
