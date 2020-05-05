package library.infrastructure.datasource.bookonloan;

import library.domain.model.loan.history.LoaningRecord;
import library.domain.model.loan.loan.BookOnLoanId;
import library.domain.model.loan.loan.LoanDate;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.member.MemberNumber;

public class BookOnLoanData {
    BookOnLoanId bookOnLoanId;
    MemberNumber memberNumber;
    ItemNumber itemNumber;
    LoanDate loanDate;

    @Deprecated
    BookOnLoanData() {
    }

    public ItemNumber itemNumber() {
        return itemNumber;
    }

    public LoaningRecord toLoaningRecord() {
        return new LoaningRecord(memberNumber, loanDate);
    }
}
