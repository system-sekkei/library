package library.infrastructure.datasource.loan;

import library.domain.model.loan.history.ReturnRecord;
import library.domain.model.loan.loan.LoanNumber;
import library.domain.model.loan.loan.ReturnDate;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.member.MemberNumber;

public class ReturnData {
    LoanNumber loanNumber;
    MemberNumber memberNumber;
    ItemNumber itemNumber;
    ReturnDate returnDate;

    @Deprecated
    ReturnData() {
    }

    public ItemNumber itemNumber() {
        return itemNumber;
    }

    // TODO ReturnRecordに直接マッピングする
    // TODO Returned と ReturnRecordを統合する？
    public ReturnRecord toReturningRecord() {
        return new ReturnRecord(memberNumber, returnDate);
    }
}
