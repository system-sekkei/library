package library.domain.model.loan.loan;

import library.domain.model.item.ItemNumber;
import library.domain.model.member.MemberNumber;

import javax.validation.Valid;

/**
 * 貸出依頼
 */
public class LoanRequest {

    @Valid
    MemberNumber memberNumber;

    @Valid
    ItemNumber itemNumber;

    @Valid
    LoanDate loanDate = LoanDate.now();

    public LoanRequest(@Valid MemberNumber memberNumber, @Valid ItemNumber itemNumber, @Valid LoanDate loanDate) {
        this.memberNumber = memberNumber;
        this.itemNumber = itemNumber;
        this.loanDate = loanDate;
    }

    @Deprecated
    public LoanRequest() {
    }
    public MemberNumber memberNumber() {
        return memberNumber;
    }

    public ItemNumber itemNumber() {
        return itemNumber;
    }

    public LoanDate loanDate() {
        return loanDate;
    }

    @Override
    public String toString() {
        return "LoanRequestForm{" +
                "memberNumber=" + memberNumber +
                ", itemNumber=" + itemNumber +
                ", loanDate=" + loanDate +
                '}';
    }
}
