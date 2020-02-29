package library.application.coordinator.returnbook;

import library.domain.model.bookonloan.loaning.RejectReason;
import library.domain.model.bookcollection.BookCollectionStatus;

/**
 * 返却結果
 */
public class ReturnBookResult {
    RejectReason rejectReason;

    public ReturnBookResult(RejectReason rejectReason) {
        this.rejectReason = rejectReason;
    }

    static public ReturnBookResult from(BookCollectionStatus bookCollectionStatus) {
        if (bookCollectionStatus == BookCollectionStatus.在庫中) {
//            return new ReturnBookResult(ExecutionResult.NG, new RejectReason("貸し出されていない蔵書です。"));
        }

//        return new ReturnBookResult(ExecutionResult.OK, new RejectReason("OK"));
        return null;
    }

    public String message() {
        return rejectReason.toString();
    }

    public boolean hasError() {
        // FIXME:
        return false;

    }

    public boolean ok() {
    // FIXME:
        return false;
    }
}
