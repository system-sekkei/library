package library.infrastructure.datasource.bookonloan;

import library.domain.model.bookonloan.librarycard.ReturningRecord;
import library.domain.model.bookonloan.loan.BookOnLoanId;
import library.domain.model.bookonloan.returning.ReturnDate;
import library.domain.model.item.ItemNumber;
import library.domain.model.member.MemberNumber;

public class ReturnBookData {
    BookOnLoanId bookOnLoanId;
    MemberNumber memberNumber;
    ItemNumber itemNumber;
    ReturnDate returnDate;

    @Deprecated
    ReturnBookData() {
    }

    public ItemNumber itemNumber() {
        return itemNumber;
    }

    public ReturningRecord toReturningRecord() {
        return new ReturningRecord(memberNumber, returnDate);
    }
}
