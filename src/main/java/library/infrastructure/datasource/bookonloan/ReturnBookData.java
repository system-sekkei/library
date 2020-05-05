package library.infrastructure.datasource.bookonloan;

import library.domain.model.loan.history.ReturningRecord;
import library.domain.model.loan.loan.BookOnLoanId;
import library.domain.model.loan.loan.ReturnDate;
import library.domain.model.book.item.ItemNumber;
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
