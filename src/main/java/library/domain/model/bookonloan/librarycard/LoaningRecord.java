package library.domain.model.bookonloan.librarycard;

import library.domain.model.bookonloan.loan.LoanDate;
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
