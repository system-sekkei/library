package library.application.coordinator.returnbook;

import library.application.ExecutionResult;
import library.domain.model.bookonloan.loan.Message;
import library.domain.model.bookcollection.BookCollectionStatus;

/**
 * 返却結果
 */
public class ReturnBookResult {
    ExecutionResult result;
    Message message;

    public ReturnBookResult(ExecutionResult result, Message message) {
        this.result = result;
        this.message = message;
    }

    static public ReturnBookResult from(BookCollectionStatus bookCollectionStatus) {
        if (bookCollectionStatus == BookCollectionStatus.在庫中) {
            return new ReturnBookResult(ExecutionResult.NG, new Message("貸し出されていない蔵書です。"));
        }

        return new ReturnBookResult(ExecutionResult.OK, new Message("OK"));
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
