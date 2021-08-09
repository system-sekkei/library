package library.domain.model.loan;

import library.domain.model.material.item.ItemNumber;
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
    LoanRequest() {
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

    public static LoanRequest empty() {
        return new LoanRequest();
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
