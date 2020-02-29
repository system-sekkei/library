package library.domain.model.bookonloan.loan;

import library.application.ExecutionResult;
import library.application.Message;
import library.domain.model.bookcollection.BookCollectionStatus;
import library.domain.model.bookonloan.loaning.CanLoan;

/**
 * 貸出票
 */
public class LoaningCard {
    ExecutionResult result;
    Message message;

    public LoaningCard(ExecutionResult result, Message message) {
        this.result = result;
        this.message = message;
    }

    static public LoaningCard from(CanLoan canLoan) {
        if (canLoan == CanLoan.貸出不可) {
            return new LoaningCard(ExecutionResult.NG, new Message("これ以上本を貸し出すことができません。"));
        }

        return new LoaningCard(ExecutionResult.OK, new Message("OK"));
    }

    static public LoaningCard from(BookCollectionStatus bookCollectionStatus) {
        if (bookCollectionStatus == BookCollectionStatus.貸出中) {
            return new LoaningCard(ExecutionResult.NG, new Message("現在貸出中の蔵書です。"));
        }

        return new LoaningCard(ExecutionResult.OK, new Message("OK"));
    }

    public String message() {
        return message.toString();
    }

    public boolean hasError() {
        return result == ExecutionResult.NG;
    }

    public boolean ok() {
        return result == ExecutionResult.OK;
    }
}
