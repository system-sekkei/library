package library.application.coordinator.returnbook;

import library.application.ExecutionResult;
import library.domain.model.bookonloan.loaning.RejectReason;
import library.domain.model.bookcollection.BookCollectionStatus;

/**
 * 返却結果
 */
public class ReturnBookResult {
    ExecutionResult result;
    RejectReason rejectReason;

    public ReturnBookResult(ExecutionResult result, RejectReason rejectReason) {
        this.result = result;
        this.rejectReason = rejectReason;
    }

    static public ReturnBookResult from(BookCollectionStatus bookCollectionStatus) {
        if (bookCollectionStatus == BookCollectionStatus.在庫中) {
            return new ReturnBookResult(ExecutionResult.NG, new RejectReason("貸し出されていない蔵書です。"));
        }

        return new ReturnBookResult(ExecutionResult.OK, new RejectReason("OK"));
    }

    public String message() {
        return rejectReason.toString();
    }

    public boolean hasError() {
        return result == ExecutionResult.NG;
    }

    public boolean ok() {
        return result == ExecutionResult.OK;
    }
}
