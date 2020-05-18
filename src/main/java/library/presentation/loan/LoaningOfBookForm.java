package library.presentation.loan;

import library.domain.model.loan.loan.LoanDate;
import library.domain.model.item.ItemNumber;
import library.domain.model.member.MemberNumber;

import javax.validation.Valid;

public class LoaningOfBookForm {

    @Valid
    MemberNumber memberNumber = null;

    @Valid
    ItemNumber itemNumber = null;

    @Valid
    LoanDate loanDate = LoanDate.now();

    public String memberNumber() {
        return memberNumber.toString();
    }
    @Override
    public String toString() {
        return "LoaningOfBookForm{" +
                "memberNumber=" + memberNumber +
                ", itemNumber=" + itemNumber +
                ", loanDate=" + loanDate +
                '}';
    }
}
