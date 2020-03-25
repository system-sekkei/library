package library.infrastructure.datasource.bookonloan;

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
}
