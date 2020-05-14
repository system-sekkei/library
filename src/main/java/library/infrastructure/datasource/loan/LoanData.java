package library.infrastructure.datasource.loan;

import library.domain.model.loan.history.LoaningRecord;
import library.domain.model.loan.loan.LoanNumber;
import library.domain.model.loan.loan.LoanDate;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.member.MemberNumber;

public class LoanData {
    LoanNumber loanNumber;
    MemberNumber memberNumber;
    ItemNumber itemNumber;
    LoanDate loanDate;

    @Deprecated
    LoanData() {
    }

    public ItemNumber itemNumber() {
        return itemNumber;
    }

    public LoaningRecord toLoaningRecord() {
        return new LoaningRecord(memberNumber, loanDate);
    }
}
