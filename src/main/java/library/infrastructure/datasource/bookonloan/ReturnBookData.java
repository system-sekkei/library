package library.infrastructure.datasource.bookonloan;

import library.domain.model.bookonloan.librarycard.ReturningRecord;
import library.domain.model.bookonloan.loan.BookOnLoanId;
import library.domain.model.bookonloan.returning.ReturnDate;
import library.domain.model.holding.HoldingCode;
import library.domain.model.member.MemberNumber;

public class ReturnBookData {
    BookOnLoanId bookOnLoanId;
    MemberNumber memberNumber;
    HoldingCode holdingCode;
    ReturnDate returnDate;

    @Deprecated
    ReturnBookData() {
    }

    public HoldingCode holdingCode() {
        return holdingCode;
    }

    public ReturningRecord toReturningRecord() {
        return new ReturningRecord(memberNumber, returnDate);
    }
}
