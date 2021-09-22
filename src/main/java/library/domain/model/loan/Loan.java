package library.domain.model.loan;

import library.domain.model.material.item.Item;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;

/**
 * *貸出
 */
public class Loan {
    LoanNumber loanNumber;
    Member member;
    Item item;
    LoanDate loanDate;

    @Deprecated
    Loan() {
    }

    public Loan(LoanNumber loanNumber, Member member, Item item, LoanDate loanDate) {
        this.loanNumber = loanNumber;
        this.member = member;
        this.item = item;
        this.loanDate = loanDate;
    }

    public Member member() {
        return member;
    }

    public LoanDate date() {
        return loanDate;
    }

    public LoanNumber loanNumber() {
        return loanNumber;
    }

    public Item item() {
        return item;
    }
    public MemberNumber memberNumber() {
        return member.number();
    }

    public LoanDate loanDate() {
        return loanDate;
    }


}
