package library.domain.model.bookonloan.loan;

import library.domain.model.bookcollection.BookCollectionStatus;
import library.domain.model.bookonloan.loaning.CanLoan;

/**
 * 貸出票
 */
public class LoaningCard {
    BookOnLoan bookOnLoan;
    Message message;

    public LoaningCard(BookOnLoan bookOnLoan, Message message) {
        this.bookOnLoan = bookOnLoan;
        this.message = message;
    }

    static public LoaningCard from(CanLoan canLoan) {
        if (canLoan == CanLoan.貸出不可) {
            return new LoaningCard(null, new Message("これ以上本を貸し出すことができません。"));
        }
        // FIXME:
        return new LoaningCard(null, new Message("OK"));
    }

    static public LoaningCard from(BookCollectionStatus bookCollectionStatus) {
        if (bookCollectionStatus == BookCollectionStatus.貸出中) {
            return new LoaningCard(null, new Message("現在貸出中の蔵書です。"));
        }
        // FIXME:
        return new LoaningCard(null, new Message("OK"));
    }

    public String message() {
        return message.toString();
    }

    public boolean hasError() {
        return bookOnLoan == null;
    }

    public boolean ok() {
        return bookOnLoan != null;
    }
}
