package library.infrastructure.datasource.bookonloan;

import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.LoanDate;

public class BookOnLoanData {
    BookCollectionCode bookCollectionCode;
    LoanDate loanDate;

    @Deprecated
    BookOnLoanData() {
    }
}
