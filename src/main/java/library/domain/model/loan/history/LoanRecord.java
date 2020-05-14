package library.domain.model.loan.history;

import library.domain.model.loan.loan.LoanDate;
import library.domain.model.member.MemberNumber;

/**
 * 貸出記録
 */
public class LoanRecord {
    MemberNumber memberNumber;
    LoanDate loanDate;

    public LoanRecord(MemberNumber memberNumber, LoanDate loanDate) {
        this.memberNumber = memberNumber;
        this.loanDate = loanDate;
    }
}
