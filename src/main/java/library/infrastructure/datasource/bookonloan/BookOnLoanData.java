package library.infrastructure.datasource.bookonloan;

import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.LoanDate;
import library.domain.model.member.MemberNumber;

public class BookOnLoanData {
    MemberNumber memberNumber;
    BookCollectionCode bookCollectionCode;
    LoanDate loanDate;

    @Deprecated
    BookOnLoanData() {
    }
}
