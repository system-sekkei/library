package library.infrastructure.datasource.loan;

import library.domain.model.loan.history.ReturnRecord;
import library.domain.model.loan.loan.LoanNumber;
import library.domain.model.loan.loan.ReturnDate;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.member.MemberNumber;

public class ReturnBookData {
    LoanNumber loanNumber;
    MemberNumber memberNumber;
    ItemNumber itemNumber;
    ReturnDate returnDate;

    @Deprecated
    ReturnBookData() {
    }

    public ItemNumber itemNumber() {
        return itemNumber;
    }

    public ReturnRecord toReturningRecord() {
        return new ReturnRecord(memberNumber, returnDate);
    }
}
