package library.domain.model.loan.history;

import library.domain.model.loan.loan.LoanDate;
import library.domain.model.member.MemberNumber;

/**
 * 貸出記録
 */
public class LoaningRecord {
    MemberNumber memberNumber;
    LoanDate loanDate;

    public LoaningRecord(MemberNumber memberNumber, LoanDate loanDate) {
        this.memberNumber = memberNumber;
        this.loanDate = loanDate;
    }
}
