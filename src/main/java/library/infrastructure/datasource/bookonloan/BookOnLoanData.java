package library.infrastructure.datasource.bookonloan;

import library.domain.model.bookonloan.loan.BookOnLoanId;
import library.domain.model.bookonloan.loan.LoanDate;
import library.domain.model.holding.HoldingCode;
import library.domain.model.member.MemberNumber;

public class BookOnLoanData {
    BookOnLoanId bookOnLoanId;
    MemberNumber memberNumber;
    HoldingCode holdingCode;
    LoanDate loanDate;

    @Deprecated
    BookOnLoanData() {
    }
}
