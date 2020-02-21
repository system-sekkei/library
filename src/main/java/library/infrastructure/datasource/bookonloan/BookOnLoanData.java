package library.infrastructure.datasource.bookonloan;

import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.loan.BookOnLoanId;
import library.domain.model.bookonloan.loan.LoanDate;
import library.domain.model.member.MemberNumber;

public class BookOnLoanData {
    BookOnLoanId bookOnLoanId;
    MemberNumber memberNumber;
    BookCollectionCode bookCollectionCode;
    LoanDate loanDate;

    @Deprecated
    BookOnLoanData() {
    }
}
